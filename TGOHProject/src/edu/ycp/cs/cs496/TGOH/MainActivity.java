package edu.ycp.cs.cs496.TGOH;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.ycp.cs.cs496.TGOH.controller.GetUser;
import edu.ycp.cs.cs496.TGOH.controller.adduser;

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
	
    // Method for displaying data entry view
	public void setDefaultView(){
		setContentView(R.layout.activity_main);
		
		Button Signin = (Button) findViewById(R.id.btnSignIn);
		Button Signup = (Button) findViewById(R.id.btnSignUp);	
		
		Signup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// setting a new account to the Database.
				setSignupPage();
				//setTeacher_Main_Page();
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
							Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
							setClass_Selection_Page();
						}
						else
						{
							//check to make sure the userName and passWord for the user are both correct
							Toast.makeText(MainActivity.this, "wrong", Toast.LENGTH_SHORT).show();
						}
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(MainActivity.this, "Invalid User" , Toast.LENGTH_SHORT).show();
					} 
			}
		});
	}
	

	/**
	 *Display the Sign up page 
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
        
		Signin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				adduser controller = new adduser();
				try {
					if(controller.postItem(Username.getText().toString(), Password.getText().toString(),FirstName.getText().toString(), LastName.getText().toString(), false)){
						// toast box: right
						setDefaultView();
						Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();
					}else{
						// toast box: error
						Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
					}
				} catch (JsonGenerationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
		Button viewSchedule = (Button) findViewById(R.id.button2);
		Button Req = (Button) findViewById(R.id.btnRequestClass);
		Button submit = (Button) findViewById(R.id.button3);
		
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
		
		Spinner spin = (Spinner) findViewById(R.id.spinner1);
		
		Button submit = (Button) findViewById(R.id.btnSubmitString);
		
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				boolean success = false; 
				//send request for class to database
				if(success){
					
				}else{
					Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
				}
			}
		});
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
		
		Button notify = (Button) findViewById(R.id.button2);
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
		// Create Linear layout
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT);
		
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
		
		ScrollView scrollLayout = new ScrollView(this);
		ScrollView.LayoutParams slp = new ScrollView.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT);
		scrollLayout.setLayoutParams(slp);

		//get students controller, populate an array/list with students
		
		//TEST
		List<String> list = new ArrayList<String>();
		list.add("foo");
		list.add("bar");
		list.add("baz");
		list.add("boz");
		list.add("gaz");
		list.add("goz");
		list.add("roz");
		list.add("Carl");
		list.add("Cody");
		list.add("codyhh09");
		list.add("Bobo");
		
		//int counter = 0;
		//ArrayList<View> checks = new ArrayList<View>();
		// Create Linear layout
		LinearLayout layout2 = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams llp2 = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT);
		
		
		//Add Check Box to go next to requests' names
		for (String students : list)
		{
			CheckBox check = new CheckBox(this);
			check.setLayoutParams(new LayoutParams(
					LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			check.setText(students);
	
			// Add check to layout
			layout.addView(check);
			//checks.add(check);
			//counter++;
		}
		scrollLayout.addView(layout2);
		// Make inventory view visible
		setContentView(layout,llp);
		
	}
}
