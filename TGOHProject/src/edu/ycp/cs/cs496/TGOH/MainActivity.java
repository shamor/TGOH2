package edu.ycp.cs.cs496.TGOH;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import edu.ycp.cs.cs496.TGOH.controller.AddAnnouncement;
import edu.ycp.cs.cs496.TGOH.controller.AddCourse;
import edu.ycp.cs.cs496.TGOH.controller.DeleteCourse;
import edu.ycp.cs.cs496.TGOH.controller.DeleteUser;
import edu.ycp.cs.cs496.TGOH.controller.GetAnnouncements;
import edu.ycp.cs.cs496.TGOH.controller.GetCourseByName;
import edu.ycp.cs.cs496.TGOH.controller.GetCoursesfromUser;
import edu.ycp.cs.cs496.TGOH.controller.GetUser;
import edu.ycp.cs.cs496.TGOH.controller.PutPassword;
import edu.ycp.cs.cs496.TGOH.controller.RegisterForCourse;
import edu.ycp.cs.cs496.TGOH.controller.RemovingAnAnnouncement;
import edu.ycp.cs.cs496.TGOH.controller.adduser;
import edu.ycp.cs.cs496.TGOH.temp.Courses;
import edu.ycp.cs.cs496.TGOH.temp.Notification;
import edu.ycp.cs.cs496.TGOH.temp.Registration;
import edu.ycp.cs.cs496.TGOH.temp.RegistrationStatus;
import edu.ycp.cs.cs496.TGOH.temp.User;

public class MainActivity extends Activity {
	public String username = "";
	public User Currentuser = null;
	
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
		username = "";
		Currentuser = null;
		
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
        			//get a user object from the database
					try {	
						Currentuser = controller.getUser(userName);

						if(Currentuser.getPassword().equals(passWord)){
								username = userName;
								if(username.equals("master")){
										setMaster_Notification_Page();
								}else{
									if(Currentuser.getType()){
										//user is student, go to student page
										setClass_Selection_Page();
									}else{
										//user is teacher, go to teacher page
										setTeacher_Selection_Page();
									}
								}
						}else{
							//check to make sure the userName and passWord for the user are both correct
							Toast.makeText(MainActivity.this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();
						}
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(MainActivity.this, "User does not exsist" , Toast.LENGTH_SHORT).show();
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
				if(Password.getText().toString().equals(Passwordcheck.getText().toString())){   //check to see if passwords entered are equal
					adduser controller = new adduser();
					GetUser con = new GetUser();
					boolean type = isStudent.isChecked();
					try {
						if(con.getUser(Username.getText().toString()).equals(null)){
							if(controller.postUser(Username.getText().toString(), Password.getText().toString(),FirstName.getText().toString(), LastName.getText().toString(), type)){
								// toast box: right
								setDefaultView();
								if(type == true){
									Toast.makeText(MainActivity.this, "Welcome to TGOH. Please log in.", Toast.LENGTH_SHORT).show();
								}else{
									Toast.makeText(MainActivity.this, "You have requested to be a teacher. Your request is pending...", Toast.LENGTH_SHORT).show();
								}
							}else{
								// toast box: error
								Toast.makeText(MainActivity.this, "Error: try again", Toast.LENGTH_SHORT).show();
							}
						}else{
							Toast.makeText(MainActivity.this, "Please select another username.", Toast.LENGTH_SHORT).show();
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
	
	/**DONE(FOR NOW)
	 * This is for students to select a class and view their schedule
	 */
	public void setClass_Selection_Page(){
	
		if(username.equals(""))
		{
			Toast.makeText(MainActivity.this, "No one is logged in!" , Toast.LENGTH_SHORT).show();
			setDefaultView();
		}
		else
		{
			setContentView(R.layout.class_selection_page);
				
			Button viewSchedule = (Button) findViewById(R.id.btnback);
			Button Req = (Button) findViewById(R.id.btnRequestClass);
			ListView lview = (ListView) findViewById(R.id.listView1);
			Button LogOut = (Button) findViewById(R.id.button1);
			Button Settings = (Button) findViewById(R.id.button2);
			
			//pull the list of user courses from the database
			GetCoursesfromUser con = new GetCoursesfromUser(); 
			List<String> classes = new ArrayList<String>();
			Courses[] courses = null;


			try {
				 courses = con.getCourses(Currentuser.getId());
				for(int i = 0; i < courses.length; i++){
					classes.add(courses[i].getCourse());
				}
			} catch (Exception e) {
				e.printStackTrace();
				//Toast.makeText(MainActivity.this, "User does not have any courses." , Toast.LENGTH_SHORT).show();
			}
			
			final Courses[] courses2 = courses;
			ArrayAdapter<String> la = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classes);
			lview.setAdapter(la);      
			
			lview.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
					//pull item from listview - go to homepage associated with the class

					Courses course = new Courses();
					course = courses2[arg2]; 
	
					setStudent_Home_Page(course);
				}
			});

			viewSchedule.setOnClickListener(new View.OnClickListener() {
	
				@Override
				public void onClick(View v) {
					setSchedule_Page(courses2);
				}
			});
			
			Req.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					setRequest_Page();
				}}
			);
			
			LogOut.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// Logs the user out and brings back to sign-in page.
					setDefaultView();
				}
			});
			
			Settings.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// Logs the user out and brings back to sign-in page.
					setSettings_Page();
				}
			});
		}
	}

	/**DONE(FOR NOW)
	 * Displays a student's course homepage 
	 * @param course name of the course that homepage is displayed for
	 */
	public void setStudent_Home_Page(Courses course)
	{
		if(username.equals(""))
		{
			Toast.makeText(MainActivity.this, "No one is logged in!" , Toast.LENGTH_SHORT).show();
			setDefaultView();
		}
		else
		{
			setContentView(R.layout.studenthomepage);
			
			TextView classlbl = (TextView) findViewById(R.id.classlbl);
			classlbl.setText("for: " + course.getCourse()); 
			
			Button LogOut = (Button) findViewById(R.id.button1);
			Button back = (Button) findViewById(R.id.button2);
			
			LogOut.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// Logs the user out and brings back to sign-in page.
					setDefaultView();
				}
			});
			
			back.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					setClass_Selection_Page();
				}
			});
				//view holding the announcements
				ListView lview = (ListView) findViewById(R.id.listView1);
				
				//list of announcements
				List<String> announcements = new ArrayList<String>();
			
				GetAnnouncements con = new GetAnnouncements();
				
				Notification[]  announce = null;
				
				try {
					announce = con.getAnnouncements(course.getId());
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
				//adding the announcements to the list
				for(int i = 0; i< announce.length; i++){
					announcements.add(announce[i].getText());
				}
				
				ArrayAdapter<String> la = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, announcements);
				lview.setAdapter(la);
		}
	}

	/**(Needs to implement database)registration
	 * Page students use to request a new class
	 */
	public void setRequest_Page() {
		if(username.equals(""))
		{
			Toast.makeText(MainActivity.this, "No one is logged in!" , Toast.LENGTH_SHORT).show();
			setDefaultView();
		}
		else
		{
			setContentView(R.layout.request_page); 
			
			Button LogOut = (Button) findViewById(R.id.button1);
			Button Back = (Button) findViewById(R.id.button2);
			Button submit = (Button) findViewById(R.id.submitbtn);
			final EditText TeacherName = (EditText) findViewById(R.id.txtteachername);	
			

			
			LogOut.setOnClickListener(new View.OnClickListener() 
			{
				@Override
				public void onClick(View v) {
					// Logs the user out and brings back to sign-in page.
					setDefaultView();
				}
			});

			Back.setOnClickListener(new View.OnClickListener() 
			{
				@Override
				public void onClick(View v) {
					setClass_Selection_Page();
				}
			});
			
			submit.setOnClickListener(new View.OnClickListener() 
			{
				Courses[] courses = null;
				@Override
				public void onClick(View v) {
					ListView lview = (ListView) findViewById(R.id.listView1);
					String Teachername = TeacherName.getText().toString();
					
					//pull the list of user courses from the database
					GetCoursesfromUser con = new GetCoursesfromUser(); 
					List<String> classes = new ArrayList<String>();
					GetUser getuse = new GetUser();
					
					User Teacher = new User();
					try {
						if(getuse.getUser(Teachername).getType() == false){
							Teacher = getuse.getUser(Teachername);
						}else{
							Toast.makeText(MainActivity.this, "User is not an intructor." , Toast.LENGTH_SHORT).show();
						}
						
					} catch (Exception e1) {
						Toast.makeText(MainActivity.this, "User does not have any courses." , Toast.LENGTH_SHORT).show();
						e1.printStackTrace();
					} 
					
					if(Teacher!=null){
						try {
							courses = con.getCourses(Teacher.getId());
							
							for(int i = 0; i< courses.length; i++){
								classes.add(courses[i].getCourse());
							}
						} catch (Exception e) {
							e.printStackTrace();
						} 
					}else{
						
					}
					
					ArrayAdapter<String> la = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, classes);
					lview.setAdapter(la);  
					final Courses[] courses2 = courses;
					lview.setOnItemClickListener(new OnItemClickListener() {
								@Override
								public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
									
									Registration reg = new Registration();
									RegisterForCourse con = new RegisterForCourse();
									reg.setUserId(Currentuser.getId());
									reg.setStatus(RegistrationStatus.PENDING); 
					
									Courses course = new Courses();
									course = courses2[arg2]; 
									reg.setCourseId(course.getId());
									
									try {
										con.postRegisterRequest(reg);
									} catch (Exception e) {
										e.printStackTrace();
									}
									Toast.makeText(MainActivity.this, "Request sent to teacher", Toast.LENGTH_SHORT).show();
								}
							});
					

					
				}
			});		
					
		}
	}

	/**DONE(FOR NOW)
	 * Displays all of the student's announcements
	 * @param courses 
	 */
	public void setSchedule_Page(Courses[] courses){
		if(username.equals(""))
		{
			Toast.makeText(MainActivity.this, "No one is logged in!" , Toast.LENGTH_SHORT).show();
			setDefaultView();
		}
		else
		{
			setContentView(R.layout.schedule_page);
			
			Button Back = (Button) findViewById(R.id.btnBack);
			Button LogOut = (Button) findViewById(R.id.button1);
			
			Back.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					setClass_Selection_Page();
				}
			});
			
			LogOut.setOnClickListener(new View.OnClickListener()
			{
				@Override
				// Logs the user out and brings back to sign-in page.
				public void onClick(View v)
				{
					setDefaultView();
				}
			});
	
			ListView lview = (ListView) findViewById(R.id.listView1);
			
			//list of announcements
			List<String> announcements = new ArrayList<String>();
		
			GetAnnouncements con = new GetAnnouncements();
			
			Notification[]  announce = null;
			for(int i = 0; i <courses.length; i++){
				try {
					announce = con.getAnnouncements(courses[i].getId());
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
				//adding the announcements to the list
				for(int j = 0; j < announce.length; j++){
					announcements.add(announce[j].getText());
				}
			}
			
			ArrayAdapter<String> la = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, announcements);
			lview.setAdapter(la);
		}
	}
	
	/**DONE(FOR NOW)
	 * Teacher's homepage
	 * @param course
	 */
	public void setTeacher_Main_Page(final Courses course)
	{
		if(username.equals(""))
		{
			Toast.makeText(MainActivity.this, "No one is logged in!" , Toast.LENGTH_SHORT).show();
			setDefaultView();
		}
		else
		{

			setContentView(R.layout.teacher_main_page);
			
			Button notify = (Button) findViewById(R.id.btnNotify);
			Button LogOut = (Button) findViewById(R.id.logoutbtn);
			Button back = (Button) findViewById(R.id.backbtn);
			Button delete = (Button) findViewById(R.id.button2);
			Button add = (Button) findViewById(R.id.button1);
			final EditText announcmentText = (EditText) findViewById(R.id.editText1);
			
			// Add onClickListener
			notify.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					setTeacher_Notification_Page(course);
				}
			});
			
			back.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					setTeacher_Selection_Page();
				}
			});
			
			delete.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					//delete course
					DeleteCourse con = new DeleteCourse(); 
					try {
						con.deleteCourse(course.getId());
						Toast.makeText(MainActivity.this, "Course Deleted!" , Toast.LENGTH_SHORT).show();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			LogOut.setOnClickListener(new View.OnClickListener()
			{
				@Override
				// Logs the user out and brings back to sign-in page.
				public void onClick(View v) 
				{
					setDefaultView();
				}
			});

			// Add onClickListener
			add.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					//pull message from text box
					Notification newNot = new Notification();
					newNot.setText(announcmentText.getText().toString());
					newNot.setCourseId(course.getId());
					
					//add to database through Add Announcement controller
					AddAnnouncement controller = new AddAnnouncement();
					try {
						if(controller.postAnnouncement(newNot)){
							Toast.makeText(MainActivity.this, "Announcement Added" , Toast.LENGTH_SHORT).show();
							setTeacher_Main_Page(course);
						}else{
							Toast.makeText(MainActivity.this, "Internal Error. Try again later" , Toast.LENGTH_SHORT).show();
						}
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(MainActivity.this, "Internal Error." , Toast.LENGTH_SHORT).show();
					} 
					
				}
			});
			
			final ListView lview = (ListView) findViewById(R.id.listView1);
			
			//list of announcements
			List<String> announcements = new ArrayList<String>();
			
			//controller for getting the Announcements
			GetAnnouncements con = new GetAnnouncements();
			
			Notification[]  announce = null;
				try {
					announce = con.getAnnouncements(course.getId());
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
				//adding the announcements to the list
				for(int j = 0; j < announce.length; j++){
					announcements.add(announce[j].getText());
				}
				
				ArrayAdapter<String> la = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, announcements);
				lview.setAdapter(la);
				
				//When  an announcement is clicked, delete it
				final Notification[] announce2 = announce;
				lview.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
						//pull item from listview - delete announcement	
						
						RemovingAnAnnouncement removeCon = new RemovingAnAnnouncement();
						Notification note = new Notification();
						note = announce2[arg2]; 
						
						try {
							if(removeCon.deleteAnnouncment(note.getId())){
								setTeacher_Main_Page(course);
							}else{
								Toast.makeText(MainActivity.this, "Internal Error, Try Again" , Toast.LENGTH_SHORT).show();
							}
						} catch (Exception e) {
							e.printStackTrace();
							Toast.makeText(MainActivity.this, "Internal Error." , Toast.LENGTH_SHORT).show();
						}
						
						}
				});
		}	
	}
	

	/**(needs to implement database)registration
	 * Displays the students who are pending for
	 * a given course the teacher teaches
	 */
	public void setTeacher_Notification_Page(final Courses course)
	{
		if(username.equals(""))
		{
			Toast.makeText(MainActivity.this, "No one is logged in!" , Toast.LENGTH_SHORT).show();
			setDefaultView();
		}
		else
		{
			setContentView(R.layout.teacher_notification_page);
			
			Button acceptButton = (Button) findViewById(R.id.btnaccept);
			Button denyButton = (Button) findViewById(R.id.btndeny);
			Button logOutButton = (Button) findViewById(R.id.btnlogout);
			Button backButton = (Button) findViewById(R.id.btnback);
			
			//TODO: pull notifications from database
			List<String> list = new ArrayList<String>();
		//	List<String> courseName = new ArrayList<String>();
			
			list.add("d");
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
			
			int counter = 0;
			ArrayList<CheckBox> checks = new ArrayList<CheckBox>();
			
			// Access Linear layout for ScrollView
			LinearLayout layout4Checks = (LinearLayout) findViewById(R.id.linearLayout1);
			
			//Add Check Box to go next to requests' names
			for (String students : list)
			{
				CheckBox check = new CheckBox(this);
				check.setLayoutParams(new LayoutParams(
						LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				check.setText(students);
				checks.add(check);
				
				// Add check to layout
				layout4Checks.addView(checks.get(counter));
				counter++;
			}
			
			final ArrayList<CheckBox> checkL2 = checks;
			
			//Accept Button
			// Add accept button onClickListener
			acceptButton.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					//TODO: Adds the course to the user's list of courses.  Removes user from list.
					for (CheckBox students : checkL2)
					{
						if(students.isChecked())
						{
							GetUser UConn = new GetUser();
							Registration reg = new Registration(); 
							try {
								User user = UConn.getUser(students.getText().toString());	
								reg.setUserId(user.getId());
								reg.setCourseId(course.getId()); 
								reg.setStatus(RegistrationStatus.APPROVED);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							

							
							
							RegisterForCourse con2  = new RegisterForCourse();
							try {
								con2.postRegisterRequest(reg);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			});
			
			//Add Deny Button
			// Add Deny button onClickListener
			denyButton.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					//TODO: Removes user from list.  Sends sad message to user.
				}
			});
			
			//Log Off Button
			// Add log-off button onClickListener
			logOutButton.setOnClickListener(new View.OnClickListener() 
			{
				@Override
				public void onClick(View v)
				{
					// Logs the user out and brings back to sign-in page.
					setDefaultView();
				}
			});		
		
			//Add Back Button
			// Add back button onClickListener
			backButton.setOnClickListener(new View.OnClickListener() 
			{
				@Override
				public void onClick(View v)
				{
					setTeacher_Main_Page(course);
				}
			});
		}
	}
	
	/**DONE(FOR NOW)
	 * Page which displays classes the teacher is teaching
	 */
	public void setTeacher_Selection_Page()
	{
		if(username.equals(""))
		{
			Toast.makeText(MainActivity.this, "No one is logged in!" , Toast.LENGTH_SHORT).show();
			setDefaultView();
		}
		else
		{
			setContentView(R.layout.teacher_selection_page);
			
			Button LogOut = (Button) findViewById(R.id.button1);
			Button Create = (Button) findViewById(R.id.button2);
			Button Settings = (Button) findViewById(R.id.button3);
			ListView lview = (ListView) findViewById(R.id.listView1);
			
			//pull the list of user courses from the database
			GetCoursesfromUser con = new GetCoursesfromUser(); 
			List<String> classes = new ArrayList<String>();
			Courses[] courses = null;

			try {
				courses = con.getCourses(Currentuser.getId());
				
				for(int i = 0; i< courses.length; i++){
					classes.add(courses[i].getCourse());
				}
			} catch (Exception e) {
				e.printStackTrace();
				//Toast.makeText(MainActivity.this, "User does not have any courses." , Toast.LENGTH_SHORT).show();
			} 
			
			final Courses[] courses2 = courses;
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classes);
			lview.setAdapter(adapter);
			
			lview.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
					Courses course = new Courses();
					course = courses2[arg2]; 
	
					setTeacher_Main_Page(course);

				}
			});
		
			
			LogOut.setOnClickListener(new View.OnClickListener()
			{
				@Override
				// Logs the user out and brings back to sign-in page.
				public void onClick(View v) 
				{
					setDefaultView();
				}
			});
			
			Create.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v) 
				{
					setCreate_Course();
				}
			});
			
			Settings.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// Logs the user out and brings back to sign-in page.
					setSettings_Page();
				}
			});
		}
	}
	
	/**DONE(FOR NOW)
	 * method to create a new course
	 */
	public void setCreate_Course()
	{
		if(username.equals(""))
		{
			Toast.makeText(MainActivity.this, "No one is logged in!" , Toast.LENGTH_SHORT).show();
			setDefaultView();
		}
		else
		{
			setContentView(R.layout.create_course);
			
			Button LogOut = (Button) findViewById(R.id.button1);
			Button back = (Button) findViewById(R.id.button2);
			Button submit = (Button) findViewById(R.id.button3);
			final EditText newCourse = (EditText) findViewById(R.id.txtCourse);
			
			
			LogOut.setOnClickListener(new View.OnClickListener()
			{
				@Override
				// Logs the user out and brings back to sign-in page.
				public void onClick(View v) 
				{
					setDefaultView();
				}
			});
			
			back.setOnClickListener(new View.OnClickListener() 
			{
				@Override
				public void onClick(View v)
				{
					setTeacher_Selection_Page();
				}
			});
			
			submit.setOnClickListener(new View.OnClickListener() 
			{
				@Override
				public void onClick(View v)
				{
					//add the course to the courses database
					AddCourse con = new AddCourse(); 
					Courses course = new Courses();
					course.setCourse(newCourse.getText().toString());
					course.setTeacher(username);
					
					try {
						con.postCourse(course);
						Toast.makeText(MainActivity.this, "Added: " + newCourse.getText().toString(), Toast.LENGTH_SHORT).show();
					} catch (Exception e) {
						e.printStackTrace();
					} 

					//Add the teacher to the course
					Registration reg = new Registration(); 
					reg.setUserId(Currentuser.getId());
					RegistrationStatus regStat = null; 
					reg.setStatus(regStat.APPROVED); 
					
					GetCourseByName con1 = new GetCourseByName();
					
					Courses course2 = new Courses(); 
					try {
						course2 = con1.getCourse(newCourse.getText().toString());
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
					
					reg.setCourseId(course2.getId());
					RegisterForCourse con2  = new RegisterForCourse();
					
					try {
						con2.postRegisterRequest(reg);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		}
	}

	/**(implement database)registration
	 * the master's notification/homepage
	 */
	public void setMaster_Notification_Page()
	{
		if(username.equals(""))
		{
			Toast.makeText(MainActivity.this, "No one is logged in!" , Toast.LENGTH_SHORT).show();
			setDefaultView();
		}
		else
		{
			setContentView(R.layout.master_notifications_page);
			
			Button LogOut = (Button) findViewById(R.id.button1);
			
			//TODO: pull notifications from the database
			List<String> list = new ArrayList<String>();
			List<String> notifications = new ArrayList<String>();
			
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
			
			// Create Linear layout for ScrollView
			// Access Linear layout for ScrollView
			LinearLayout layout4Checks = (LinearLayout) findViewById(R.id.linearLayout1);
					
			//Add Check Box to go next to requests' names
				for (String students : list)
				{
					CheckBox check = new CheckBox(this);
					check.setLayoutParams(new LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));
					check.setText(students);
					
					// Add heck to layout
					layout4Checks.addView(check);
				//checks.add(check);
				//counter++;
			}
			
			LogOut.setOnClickListener(new View.OnClickListener()
			{
				@Override
				// Logs the user out and brings back to sign-in page.
				public void onClick(View v) 
				{
					setDefaultView();
				}
			});
		}
	}
	/**Finished
	 * Allows User to change some of their options
	 */
	public void setSettings_Page()
	{
		if(username.equals(""))
		{
			Toast.makeText(MainActivity.this, "No one is logged in!" , Toast.LENGTH_SHORT).show();
			setDefaultView();
		}
		else
		{
			setContentView(R.layout.settings);
			
			Button Submit = (Button) findViewById(R.id.button1);	
			Button LogOut = (Button) findViewById(R.id.button2);
			Button Delete = (Button) findViewById(R.id.button3);			
			Button Back = (Button) findViewById(R.id.button4);
			
	        final EditText Password = (EditText) findViewById(R.id.editText1);
	        final EditText Passwordcheck = (EditText) findViewById(R.id.editText2);
	        
			Submit.setOnClickListener(new View.OnClickListener()
			{
				@Override
				// Changes the user's password
				public void onClick(View v) 
				{
					//TODO: Change the user's password
					if(Password.getText().toString().equals(Passwordcheck.getText().toString())) 				//check to see if passwords entered are equal
					{
						PutPassword newPass = new PutPassword();
						try {
							newPass.putPassword(Currentuser, Password.getText().toString());
							Toast.makeText(MainActivity.this, "You have just changed your password", Toast.LENGTH_SHORT).show();
						} catch (Exception e) {
							e.printStackTrace();
						} 
					}
					else																						//Inform users that their passwords do not match each other
					{
						Toast.makeText(MainActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
					}
				}
			});
	        
			LogOut.setOnClickListener(new View.OnClickListener()
			{
				@Override
				// Logs the user out and brings back to sign-in page.
				public void onClick(View v) 
				{
					setDefaultView();
				}
			});
			
			Delete.setOnClickListener(new View.OnClickListener()
			{
				@Override
				// Deletes the user's account FOREVER!!!
				public void onClick(View v) 
				{
					// Delete the user's account FOREVER!!!
					DeleteUser delU = new DeleteUser();
					try {
						delU.deleteUser(username);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// goes back to homepage and resets the username
					setDefaultView();
				}
			});
			
			Back.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if(Currentuser.getType())
					{
						//user is student, go to student page
						setClass_Selection_Page();
					}
					else
					{
						//user is teacher, go to teacher page
						setTeacher_Selection_Page();
					}
				}
			});
		}
	}
}
