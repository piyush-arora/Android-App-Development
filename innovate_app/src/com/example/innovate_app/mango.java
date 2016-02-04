package com.example.innovate_app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class mango extends Activity
{
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mango);
		final TextView display = (TextView)findViewById(R.id.mango_text);		
		Button on=(Button)findViewById(R.id.turn_on);
		Button of=(Button)findViewById(R.id.turn_off);
		on.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				display.setText("Your device is in self charging mode");
			}
		});
		of.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				display.setText("Your device is not in self charging mode");
			}
		});


	}
}
