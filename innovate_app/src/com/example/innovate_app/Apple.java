package com.example.innovate_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Apple extends Activity 
{
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.apple);
		Button charge=(Button)findViewById(R.id.charge_button);
		Button time=(Button)findViewById(R.id.time_duraation_button);
		 charge.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v)
				{
					Intent x=new Intent("com.example.innovate_app.MILK");
					startActivity(x);
				}
			});
		 time.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v)
				{
					Intent x=new Intent("com.example.innovate_app.WATER");
					startActivity(x);
				}
			});


		
		

		
	}

	
}
