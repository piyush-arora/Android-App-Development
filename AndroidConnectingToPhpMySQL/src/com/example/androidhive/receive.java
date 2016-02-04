package com.example.androidhive;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class receive extends Activity{
	
	
	private ProgressDialog pDialog;
	
	String res,bar,swim;
	
	JSONParser jParser = new JSONParser();
	ArrayList<HashMap<String, String>> amenity_list;
	private static String url_all_products = "http://192.168.168.56/stayzilla/";
	
	private static final String TAG_AMENTIES = "amenities";
	private static final String TAG_RESTAURANT="restaurant";
	private static final String TAG_BAR="bar";
	private static final String TAG_SWIM="swim";
	private static final String TAG_ID="id";
	private static final String TAG_HOTELS="hotels";
	
	JSONArray hotels = null;
	JSONArray amenities = null;
	
	TextView rest_text1,bar_text1,swim_text1;
	String got_id;
	Button room;
	private Bundle basket;
	private Intent person;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_item2);
		//tv=(TextView)findViewById(R.id.receive);
		Bundle gotbasket=getIntent().getExtras() ;
		got_id=gotbasket.getString("ID");
		//tv.setText(x);		
		
		rest_text1=(TextView) findViewById(R.id.rest_text);
		bar_text1=(TextView) findViewById(R.id.bar_text);
		swim_text1=(TextView) findViewById(R.id.swim_text);
		room=(Button) findViewById (R.id.room_button);
		new Grab_Data().execute();
		room.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				basket=new Bundle();
				basket.putString("ID",got_id);
				person=new Intent(receive.this,Rooms.class);
				person.putExtras(basket);
				 startActivity(person);
				 overridePendingTransition(R.anim.slide_in,0);
				
				//Intent i = new Intent(getApplicationContext(), Rooms.class);
				//startActivity(i);
				
			}
		});
		
	}
		
		
		
		class Grab_Data extends AsyncTask<String, String, String> {

			/**
			 * Before starting background thread Show Progress Dialog
			 * */
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				pDialog = new ProgressDialog(receive.this);
				pDialog.setMessage("Getting Amentiries");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(false);
				pDialog.show();
				
			}

			/**
			 * getting All products from url
			 * */
			protected String doInBackground(String... args) {
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				// getting JSON string from URL
				JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);
				
				// Check your log cat for JSON reponse
				Log.d("All Products: ", json.toString());

				try {
					// Checking for SUCCESS TAG
						// products found
						// Getting Array of Products
						hotels = json.getJSONArray(TAG_HOTELS);
						
						// looping through All Products
						for (int i = 0; i < hotels.length(); i++) {
							JSONObject c = hotels.getJSONObject(i);
							if (got_id.equals(c.getString(TAG_ID)))
							{
								JSONObject d=c.getJSONObject(TAG_AMENTIES);
								res=d.getString(TAG_RESTAURANT);
								bar=d.getString(TAG_BAR);
								swim=d.getString(TAG_SWIM);
								
								
							}
							
							
						} 
						
						
						
						
						
						
				} catch (JSONException e) {
					e.printStackTrace();
				}

				return null;
			}

			/**
			 * After completing background task Dismiss the progress dialog
			 * **/
			protected void onPostExecute(String file_url) {
				// dismiss the dialog after getting all products
				

				pDialog.dismiss();
				
				rest_text1.setText("Restaurant : "+res);
				bar_text1.setText("Bar : "+bar);
				swim_text1.setText("Swim : " +swim);
				
				// updating UI from Background Thread
				
			}
		}
	}


		
	