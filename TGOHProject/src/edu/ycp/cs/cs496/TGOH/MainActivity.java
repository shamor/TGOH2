package edu.ycp.cs.cs496.TGOH;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.ycp.cs.cs496.TGOH.controller.GetUser;
import edu.ycp.cs.cs496.TGOH.controller.adduser;
import edu.ycp.cs.cs496.TGOH.temp.Courses;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setDefaultView(); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
    /**DONE(FOR NOW)
     * This will take us to the sign in page
     */
	public void setDefaultView(){
		setContentView(R.layout.activity_main);
		
		Button Signin = (Button) findViewById(R.id.btnSignIn);
		Button Signup = (Button) findViewById(R.id.btnSignUp);	
		
		Signup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// setting a new account to the Database.
				setSignupPage();
			}
		});
		
		Signin.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View v) {
				EditText Username = (EditText) findViewById(R.id.txtUserName);
				EditText Password = (EditText) findViewById(R.id.txtPassword);
				
				String userName = Username.getText().toString();
				String passWord = Password.getText().toString();
        		GetUser controller = new GetUser();
        		
					try {
						if(controller.getUser(userName).getPassword().equals(passWord)){
							if(controller.getUser(userName).getType()){
								setClass_Selection_Page();
							}else{
								//go to teacher page
								setTeacher_Notification_Page();
							}
						}
						else
						{
							//check to make sure the userName and passWord for the user are both correct
							Toast.makeText(MainActivity.this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();
						}
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(MainActivity.this, "Invalid User" , Toast.LENGTH_SHORT).show();
					} 
			}
		});
	}
	

	/**DONE(FOR NOW)
	 *Display the Sign up page 
	 *User enters firstname/lastname/username/passowrd/usertype
	 **/
	public void setSignupPage(){
		setContentView(R.layout.signuppage);
		
		Button Signin = (Button) findViewById(R.id.btnSignUp);
		Button Back = (Button) findViewById(R.id.back);
		//pull information from text boxes and add the new user to the database
		//also error checking
        final EditText Username = (EditText) findViewById(R.id.UserNameSignup);
        final EditText Password = (EditText) findViewById(R.id.PassSignUp);
        final EditText FirstName = (EditText) findViewById(R.id.FirstNameSignup);
        final EditText LastName = (EditText) findViewById(R.id.LastNameSignup);
        final EditText Passwordcheck = (EditText) findViewById(R.id.editText1);
        final RadioButton isStudent = (RadioButton) findViewById(R.id.studentradio);
		
        Signin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(Password.getText().toString().equals(Passwordcheck.getText().toString())){//check to see if passwords entered are equal
					adduser controller = new adduser();
					boolean type = isStudent.isChecked();
					try {
						if(controller.postItem(Username.getText().toString(), Password.getText().toString(),FirstName.getText().toString(), LastName.getText().toString(), type)){
							// toast box: right
							setDefaultView();
							if(type == true){
								Toast.makeText(MainActivity.this, "Welcome to TGOH, please log in", Toast.LENGTH_SHORT).show();
							}else{
								Toast.makeText(MainActivity.this, "You have requested to be a teacher. Your request is pending...", Toast.LENGTH_SHORT).show();
							}
						}else{
							// toast box: error
							Toast.makeText(MainActivity.this, "Error: try again", Toast.LENGTH_SHORT).show();
						}
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(MainActivity.this, "Invalid Request." , Toast.LENGTH_SHORT).show();
					}	
				}else{//Inform users that their passwords do not match each other
					Toast.makeText(MainActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		Back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//take user back to original sign in page
				setDefaultView();
			}
		});
		
		
	}
	
	/**
	 * This is for students to select a class and view their schedule
	 * 
	 */
	public void setClass_Selection_Page(){
		setContentView(R.layout.class_selection_page);
			
		
		//pull student's classes front the database and display them 
		/*for(Courses course :courselist){
		  		TextView ClassName = new TextView(this);
		  		ClassName.setText(course.getName());
		  		
		  		//add the class name to the layout
		  		R.layout.addView(ClassName);
		  }
		*/
		Button viewSchedule = (Button) findViewById(R.id.btnback);
		Button Req = (Button) findViewById(R.id.btnRequestClass);
		
		viewSchedule.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setSchedule_Page();
			}
		});
		
		Req.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setRequest_Page();
			}
		});
	}


	protected void setRequest_Page() {
		setContentView(R.layout.request_page);
		
		
	}

	public void setSchedule_Page(){
		setContentView(R.layout.schedule_page);
		
		Button Back = (Button) findViewById(R.id.btnBack);
		Back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setClass_Selection_Page();
			}
		});
		
	}
	
	public void setTeacher_Main_Page(){
		setContentView(R.layout.teacher_main_page);
		
		Button notify = (Button) findViewById(R.id.button1);
		// Add onClickListener
		notify.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				setTeacher_Notification_Page();
			}
		});
		
		//TODO: Add onClick events for the remaining buttons
	}
	
	// FIXME: get rid of this
	static class User {
		
	}
	
	public void setTeacher_Notification_Page()
	{
		setContentView(R.layout.teacher_notification_page);/*
// Create Linear layout
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		RelativeLayout.LayoutParams llp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.FILL_PARENT);
		
//Add Log Off Button
		Button logOutButton = new Button(this);
		logOutButton.setText(R.string.btnLogOut);
		logOutButton.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		
		// Add back button onClickListener
		logOutButton.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v)
			{
				//TODO: Log Out
			}
		});		
		
		// Add button to layout
		layout.addView(logOutButton);
		
//Add Accept Button
		Button acceptButton = new Button(this);
		acceptButton.setText("Accept");
		acceptButton.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		// Add back button onClickListener
		acceptButton.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				//TODO: Adds the course to the user's list of courses.  Removes user from list.
			}
		});
		
		// Add button to layout
		layout.addView(acceptButton);
		
//Add Deny Button
		Button denyButton = new Button(this);
		denyButton.setText("Deny");
		denyButton.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		// Add back button onClickListener
		denyButton.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v)
			{
				//TODO: Removes user from list.  Sends sad message to user.
			}
		});
		
		// Add button to layout
		layout.addView(denyButton);
		
//Add Back Button
		Button backButton = new Button(this);
		backButton.setText("Back");
		backButton.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		// Add back button onClickListener
		backButton.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				setTeacher_Main_Page();
			}
		});
		
		// Add button to layout
		layout.addView(backButton);
		
		ScrollView scrollView = new ScrollView(this);
		ScrollView.LayoutParams slp = new ScrollView.LayoutParams(
				ScrollView.LayoutParams.FILL_PARENT,
				ScrollView.LayoutParams.FILL_PARENT);
		scrollView.setLayoutParams(slp);
		layout.addView(scrollView);
		//get students controller, populate an array/list with students
		
		//TEST
		List<Courses> list = new ArrayList<Courses>();
		List<String> courseName = new ArrayList<String>();
		GetUser con = new GetUser(); 
		try {
			list = con.getUser("d").getCourse();
			for(Courses c : list){
				courseName.add(c.getCourse(0));
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//list.add("foo");
		//list.add("bar");
		//list.add("baz");
		//list.add("boz");
		//list.add("gaz");
		//list.add("goz");
		//list.add("roz");
		//list.add("Carl");
		//list.add("Cody");
		//list.add("codyhh09");
		//list.add("Bobo");
		
		//int counter = 0;
		//ArrayList<View> checks = new ArrayList<View>();
		
		
		// Create Linear layout for ScrollView
		LinearLayout layout4Checks = new LinearLayout(this);
		layout4Checks.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams llp2 = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT);
		
		
		//Add Check Box to go next to requests' names
		for (String students : courseName)

		{
			CheckBox check = new CheckBox(this);
			check.setLayoutParams(new LayoutParams(
					LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			check.setText(students);
	
			// Add check to layout
			layout4Checks.addView(check);
			//checks.add(check);
			//counter++;
		}
		scrollView.addView(layout4Checks);
		// Make inventory view visible
		setContentView(layout,llp);*/
	}
}
