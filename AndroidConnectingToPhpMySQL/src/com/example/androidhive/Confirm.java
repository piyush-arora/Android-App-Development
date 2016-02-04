package com.example.androidhive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Confirm extends Activity{
	
	Button tobill,tips;
	private Bundle basket;
	private Intent person;
	String rtype=null,rprice=null,rocc=null,rtax=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm);
		Bundle gotbasket=getIntent().getExtras() ;
		
		rtype=gotbasket.getString("rtype");
		rprice=gotbasket.getString("rprice");
		rocc=gotbasket.getString("rocc");
		rtax=gotbasket.getString("rtax");
		tips=(Button) findViewById(R.id.tips);
		tips.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent (Confirm.this,ListMobileActivity.class);
				startActivity(i);
				overridePendingTransition(R.anim.slide_in,0);
			}
		});
		
		
		tobill=(Button)findViewById(R.id.tobill);
		tobill.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				basket=new Bundle();
				basket.putString("rtype",rtype);
				basket.putString("rtax",rtax);
				basket.putString("rocc",rocc);
				basket.putString("rprice",rprice);
				
				person=new Intent(Confirm.this,create_bill.class);
				person.putExtras(basket);
				startActivity(person);
				overridePendingTransition(R.anim.slide_in,0);
					
				
				
			
				
				//Intent i = new Intent(getApplicationContext(), Rooms.class);
				//startActivity(i);
				
			}
		});
	
		
		
			
	}
	
	
}
