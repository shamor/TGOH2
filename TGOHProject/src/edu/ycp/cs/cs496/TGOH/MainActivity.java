package edu.ycp.cs.cs496.TGOH;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;

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
				setSigninPage();
			}
		});
		
		Signin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Jason will make a new method for the schedule page.
				setSchedule_page();

				
			}
		});
	}
	
	
	/**
	 *Display the Sign up page 
	 **/
	public void setSigninPage(){
		setContentView(R.layout.signuppage);
		
		Button Signin = (Button) findViewById(R.id.button1);
		Button Back = (Button) findViewById(R.id.button2);
		// TODO: pull information from text boxes and add the new user to the database
		//also error checking
		Signin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setDefaultView();
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
	
	public void setSchedule_page(){
		setContentView(R.layout.class_selection_page);
		
		Button VS = (Button) findViewById(R.id.button1);
		Button Req = (Button) findViewById(R.id.button2);
		
		VS.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setContentView(R.layout.schedule_page);
				
			}
		});
	}
}
