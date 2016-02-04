package com.example.androidhive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.splash);
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(2000);
				} catch(InterruptedException e){
					e.printStackTrace();
				}finally {
					Intent i=new Intent(Splash.this,MainScreenActivity.class);
					startActivity(i);
					overridePendingTransition(R.anim.slide_in,0);
				}
			}
		};
		timer.start();
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	

}
