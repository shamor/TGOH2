package edu.ycp.cs.cs496.TGOH;

import edu.ycp.cs.cs496.cs496_assign02.MobileInventoryClient;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
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
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void setDefaultView(){
		setContentView(R.layout.activity_main);
		
		Button Signin = (Button) findViewById(R.id.signin);
		Button Signup = (Button) findViewById(R.id.signup);
		
		final EditText Username = (EditText) findViewById(R.id.Username);
		final EditText Password = (EditText) findViewById(R.id.Password);
		
		
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
		setContentView(R.layout.request_page);
		
		Spinner spin = (Spinner) findViewById(R.id.spinner1);
		
		Button submit = (Button) findViewById(R.id.submit);
		
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