package com.example.androidhive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.androidhive.AllProductsActivity.Grab_Data;

public class EditProductActivity extends Activity {

	TextView tv;
	String x;
	private ProgressDialog pDialog;
	JSONParser jParser = new JSONParser();
	//ArrayList<HashMap<String, String>> hotel_list;
	private static String url_all_products = "http://192.168.137.64/stayzilla";

	// JSON Node names
	private static final String TAG_HOTELS = "amenities";
	private static final String TAG_RESTAURANT="restaurant";
	private static final String TAG_BAR="bar";
	private static final String TAG_SWIM="swim";
	//private ListView lv;

	// products JSONArray
	JSONArray hotels = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.receive);
		tv=(TextView)findViewById(R.id.receive);
		Bundle gotbasket=getIntent().getExtras() ;
		x=gotbasket.getString("ID");
		tv.setText(x);		
		
		//hotel_list = new ArrayList<HashMap<String, String>>();
		//new Grab_Data().execute();
		//lv = getListView();
		
	}
	
	
	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	class Grab_Data extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(EditProductActivity.this);
			pDialog.setMessage("Getting Hotel List");
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

						// Storing each json item in variable
						//String id = c.getString(TAG_PID);
						String restaurant = c.getString(TAG_RESTAURANT);
						String bar = c.getString(TAG_BAR);
						String swim = c.getString(TAG_SWIM);
						
						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						//map.put(TAG_PID, id);
						map.put(TAG_RESTAURANT, restaurant);
						map.put(TAG_BAR, bar);
						map.put(TAG_SWIM, swim);

						// adding HashList to ArrayList
						//hotel_list.add(map);
					
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
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */
					//ListAdapter adapter = new SimpleAdapter(
						//	EditProductActivity.this, hotel_list,
							//R.layout.list_item2, new String[] { 
							//		TAG_RESTAURANT,TAG_BAR,TAG_SWIM},
							//new int[] {  R.id.name2,R.id.name22,R.id.name32 });
					// updating listview
				//	setListAdapter(adapter);
				}
			});

		}

	}
	
}
