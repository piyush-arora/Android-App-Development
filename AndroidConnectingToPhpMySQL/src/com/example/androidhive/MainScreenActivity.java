package com.example.androidhive;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainScreenActivity extends Activity implements OnClickListener{
	
	Spinner location_spin;
	private Button btnSubmit;
	String location=null;
	TextView location_text; 
	Bundle basket;
	Intent person;
	private EditText fromDateEtxt;
    private EditText toDateEtxt;
    
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    Button btnShowLocation;
    GPSTracker gps;
    
    private SimpleDateFormat dateFormatter;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		// Buttons
		location_text=(TextView)findViewById(R.id.text_location);
		 btnShowLocation = (Button) findViewById(R.id.gps_button);
	       
		location_spin=(Spinner)findViewById(R.id.location_spinner);
		location_spin.setOnItemSelectedListener(new CustomOnItemSelectedListener());
		
		dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
		findViewsById();
        
        setDateTimeField();
		
		// view products click event
		
		
		
		

		btnSubmit = (Button) findViewById(R.id.Submit_location);
		// view products click event
		btnSubmit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				location=String.valueOf(location_spin.getSelectedItem());
				basket=new Bundle();
				basket.putString("piyush", location);
				
				person=new Intent(MainScreenActivity.this,AllProductsActivity.class);
				person.putExtras(basket);
				
				location_text.setText(location);
				startActivity(person);
				
			}
		});
		
		 btnShowLocation.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {		
					// create class object
			        gps = new GPSTracker(MainScreenActivity.this);

					// check if GPS enabled		
			        if(gps.canGetLocation()){
			        	
			        	double latitude = gps.getLatitude();
			        	double longitude = gps.getLongitude();
			        	
			        	// \n is for new line
			        	Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();	
			        }else{
			        	Toast.makeText(getApplicationContext(), "SORRY GPS NOT WORKING", Toast.LENGTH_LONG).show();	
				        
			        	// can't get location
			        	// GPS or Network is not enabled
			        	// Ask user to enable GPS/network in settings
			        	gps.showSettingsAlert();
			        }
			        new Handler().postDelayed(new Runnable()
			        {
			           @Override
			           public void run()
			           {
			             // your code here
			        	   Intent i=new Intent(MainScreenActivity.this,outof.class);
			        	   startActivity(i);
			           }
			        }, 1000/* 1sec delay */);
					
				}
			});

		
				
	}

	
	
	
	private void setDateTimeField() {
		// TODO Auto-generated method stub
		 fromDateEtxt.setOnClickListener((OnClickListener) this);
	        toDateEtxt.setOnClickListener((OnClickListener) this);
	        
	        Calendar newCalendar = Calendar.getInstance();
	        fromDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {
	        	 
	            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
	                Calendar newDate = Calendar.getInstance();
	                newDate.set(year, monthOfYear, dayOfMonth);
	                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
	            }
	 
	        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
	        
	        toDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {
	        	 
	            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
	                Calendar newDate = Calendar.getInstance();
	                newDate.set(year, monthOfYear, dayOfMonth);
	                toDateEtxt.setText(dateFormatter.format(newDate.getTime()));
	            }
	 
	        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
	        
	        
	        	    }


	private void findViewsById() {
		// TODO Auto-generated method stub
		
		fromDateEtxt = (EditText) findViewById(R.id.etxt_fromdate);    
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();
        
        toDateEtxt = (EditText) findViewById(R.id.etxt_todate);
        toDateEtxt.setInputType(InputType.TYPE_NULL);
		
	}
	
	public void onClick(View view) {
        if(view == fromDateEtxt) {
            fromDatePickerDialog.show();
        } else if(view == toDateEtxt) {
            toDatePickerDialog.show();
        }        
    }
	
	
	
	

	
}
