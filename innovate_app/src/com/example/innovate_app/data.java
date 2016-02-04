package com.example.innovate_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class data extends Activity  
{
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data);
		final TextView data_display = (TextView)findViewById(R.id.data_display);		
		TextView email = (TextView)findViewById(R.id.email_text);		
		TextView password = (TextView)findViewById(R.id.password_text);		
		TextView name = (TextView)findViewById(R.id.name_text);		
		final EditText display_email= (EditText)findViewById(R.id.email_enter);
		final EditText display_password= (EditText)findViewById(R.id.password_enter);
		final EditText display_name= (EditText)findViewById(R.id.editname);
		Button submit=(Button)findViewById(R.id.submit);
		display_name.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				display_name.getText().toString();
			}
		});
		display_email.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				display_email.getText().toString();
			}
		});
		display_password.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				display_password.getText().toString();
			}
		});
		submit.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View v)
			{
				data_display.setText("you are successfully login");
			}
		});
		
		
		
	}

	
}
