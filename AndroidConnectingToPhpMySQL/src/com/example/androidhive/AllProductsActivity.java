package com.example.androidhive;

import java.text.NumberFormat;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
public class AllProductsActivity extends ListActivity {

	private ProgressDialog pDialog;
	private RatingBar ratingBar;
	
	JSONParser jParser = new JSONParser();
	ArrayList<HashMap<String, String>> hotel_list;
	private static String url_all_products = "http://192.168.168.56/stayzilla/";

	// JSON Node names
	private static final String TAG_HOTELS = "hotels";
	private static final String TAG_NAME = "displayName";
	private static final String TAG_ADDRESS = "address";
	private static final String TAG_STARS = "starRating";
	private static final String TAG_ID = "id";
	String[] new_id = new String[2];
	private Bundle basket;
	private Intent person;
	private ListView lv;
	

	// products JSONArray
	JSONArray hotels = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_hotels);
		ratingBar=(RatingBar)findViewById(R.id.ratingBar1);
		hotel_list = new ArrayList<HashMap<String, String>>();
		new Grab_Data().execute();
		lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					basket=new Bundle();
					basket.putString("ID",new_id[position]);
					person=new Intent(AllProductsActivity.this,receive.class);
					person.putExtras(basket);
					startActivity(person);
					overridePendingTransition(R.anim.slide_in,0);
						
					
					
				
				
			}
		});

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
			pDialog = new ProgressDialog(AllProductsActivity.this);
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
						String name = c.getString(TAG_NAME);
						String add = c.getString(TAG_ADDRESS);
						String stars = c.getString(TAG_STARS);
						//float f1 = (float) 2.0;
						//ratingBar.setRating(2.0f);
						new_id[i] = c.getString(TAG_ID);
						
						
						
						
						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						//map.put(TAG_PID, id);
						map.put(TAG_NAME, name);
						map.put(TAG_ADDRESS, add);
						map.put(TAG_STARS, stars);
						
						// adding HashList to ArrayList
						hotel_list.add(map);
					
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
					ListAdapter adapter = new SimpleAdapter(
							AllProductsActivity.this, hotel_list,
							R.layout.list_item, new String[] { 
									TAG_NAME,TAG_ADDRESS,TAG_STARS},
							new int[] {  R.id.name,R.id.name2,R.id.name3 });
					// updating listview
					setListAdapter(adapter);
				}
			});

		}

	}
}