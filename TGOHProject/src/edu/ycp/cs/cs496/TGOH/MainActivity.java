package edu.ycp.cs.cs496.TGOH;

import java.io.IOException;
import java.net.URISyntaxException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.ycp.cs.cs496.TGOH.User.User;
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
		setContentView(R.layout.activity_main);
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
		
		final EditText Username = (EditText) findViewById(R.id.UserName);
		final EditText Password = (EditText) findViewById(R.id.txtPassword);	
		
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
        		String userName = Username.getText().toString();
        		String passWord = Password.getText().toString();
        		
        		if(userName.equals(null))
        		{
        			Toast.makeText(MainActivity.this, "Please enter a username.", Toast.LENGTH_SHORT).show();
        		}
        		else
        		{
        			//check to make sure the userName and passWord for the user are both correct
        			
        		}
				
				// Jason will make a new method for the schedule page.
				setClass_Selection_Page();

			}
		});
	}
	
	
	/**
	 *Display the Sign up page 
	 **/
	public void setSignupPage(){
		setContentView(R.layout.signuppage);
		
		Button Signin = (Button) findViewById(R.id.button1);
		Button Back = (Button) findViewById(R.id.button2);
		// TODO: pull information from text boxes and add the new user to the database
		//also error checking
		
		
		Signin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				adduser controller = new adduser();
				try {
					if(controller.postItem(FirstName.getText().toString(), LastName.getText().toString(), Username.getText().toString(), Password.getText().toString())){
						// toast box: right
						setDefaultView();
					}else{
						// toast box: error
					}
				} catch (JsonGenerationException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}	
				setDefaultView();
				// Jason will implement controllers
				// TODO Auto-generated method stub
				setClass_Selection_Page();
				// jason will implement controllers
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
		Button Req = (Button) findViewById(R.id.button1);
		
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
		setContentView(R.layout.activity_main);
		
		Spinner spin = (Spinner) findViewById(R.id.spinner1);
		
		Button submit = (Button) findViewById(R.id.button1);
		
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
		
		Button Back = (Button) findViewById(R.id.button1);
		Back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setClass_Selection_Page();
			}
		});
		
	}
}
