package edu.ycp.cs.cs496.TGOH;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.ycp.cs.cs496.TGOH.controller.GetUser;
import edu.ycp.cs.cs496.TGOH.controller.adduser;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
        		//check to see if a user name is entered
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

			}
		});
	}
	
	
	/**
	 *Display the Sign up page 
	 **/
	public void setSignupPage(){
		setContentView(R.layout.signuppage);
		
		Button Signin = (Button) findViewById(R.id.btnSignUp);
		Button Back = (Button) findViewById(R.id.button2);
		// TODO: pull information from text boxes and add the new user to the database
		//also error checking
        final EditText Username = (EditText) findViewById(R.id.UserName);
        final EditText Password = (EditText) findViewById(R.id.PassSignUp);
        final EditText FirstName = (EditText) findViewById(R.id.UserName);
        final EditText LastName = (EditText) findViewById(R.id.PassSignUp);
        
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
		
		Button viewSchedule = (Button) findViewById(R.id.button2);
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
		
		Button Back = (Button) findViewById(R.id.btnBack);
	}
}
