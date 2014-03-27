package edu.ycp.cs.cs496.TGOH;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void setDefaultView(){
		setContentView(R.layout.activity_main);
		
		Button Signin = (Button) findViewById(R.id.button1);
		Button Signup = (Button) findViewById(R.id.button2);
		
		Signup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setContentView(R.layout.signuppage);
				// setting a new account to the Database.
				
			}
		});
	}

}
