package com.example.androidhive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class Rooms extends ListActivity {

	String rtype=null,rprice=null,rocc=null,rtax=null;
	private ProgressDialog pDialog;
	String got_id;
	JSONParser jParser = new JSONParser();
	ArrayList<HashMap<String, String>> room_list;
	private static String url_all_products = "http://192.168.168.56/stayzilla/";
	
	private static final String TAG_HOTELS="hotels";
	private static final String TAG_ROOMS="rooms";
	private static final String TAG_RTYPE="rtype";
	private static final String TAG_RPRICE="rdiscountPriceWithTax";
	private static final String TAG_ROCCUPANTS="roccupants";
	private static final String TAG_TAX="withTax";
	
	private Bundle basket;
	private Intent person;
	
	
	private static final String TAG_ID="id";
	
	private ListView lv;
	
	JSONArray hotels=null;
	JSONArray rooms=null;
	
	
	
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rooms);
		Bundle gotbasket=getIntent().getExtras() ;
		
		got_id=gotbasket.getString("ID");
		room_list = new ArrayList<HashMap<String, String>>();
		new Grab_Data().execute();
		lv = getListView();
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					
				basket=new Bundle();
				basket.putString("rtype",rtype);
				basket.putString("rtax",rtax);
				basket.putString("rocc",rocc);
				basket.putString("rprice",rprice);
				
				person=new Intent(Rooms.this,Confirm.class);
				person.putExtras(basket);
				startActivity(person);
				overridePendingTransition(R.anim.slide_in,0);
					
				
				
			
				
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
			pDialog = new ProgressDialog(Rooms.this);
			pDialog.setMessage("Getting Hotel List");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
			
		}
		
		
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
						//JSONObject c = hotels.getJSONObject(i);
						
						//rooms=json.getJSONArray(TAG_ROOMS);
						
						
						JSONObject d=hotels.getJSONObject(i);
						
						if (got_id.equals(d.getString(TAG_ID)))
						{
						rooms=d.getJSONArray(TAG_ROOMS);
						
						//rooms=hotels.getJSONArray(i);
						
						for (int j=0;j<rooms.length();j++) {
							
							JSONObject e=rooms.getJSONObject(j);
							rtype=e.getString(TAG_RTYPE);
							rprice=e.getString(TAG_RPRICE);
							rocc=e.getString(TAG_ROCCUPANTS);
							rtax=e.getString(TAG_TAX);
							
						

						// Storing each json item in variable
						//String id = c.getString(TAG_PID);
						
						
						
						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						//map.put(TAG_PID, id);
						map.put(TAG_RTYPE, rtype);
						map.put(TAG_RPRICE, rprice);
						map.put(TAG_ROCCUPANTS, rocc);
						map.put(TAG_TAX, rtax);
						
						// adding HashList to ArrayList
						room_list.add(map);
						
						}
						
						}
						
					
				} 
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */
					ListAdapter adapter = new SimpleAdapter(
							Rooms.this, room_list,
							R.layout.room_list, new String[] { 
									TAG_RTYPE,TAG_RPRICE,TAG_ROCCUPANTS},
							new int[] {  R.id.rtype,R.id.rprice,R.id.roccupants });
					// updating listview
					setListAdapter(adapter);
				}
			});
			
		}
		
	}
	
}
