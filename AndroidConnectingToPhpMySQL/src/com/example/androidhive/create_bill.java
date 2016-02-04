package com.example.androidhive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class create_bill extends Activity{
	
	TextView bill_text;
	
	JSONParser jsonParser = new JSONParser();
	private Bundle basket;
	private Intent person;
	String rtype=null,rprice=null,rocc=null,rtax=null;
	private ProgressDialog pDialog;
	private String url = "http://192.168.168.56/stayzilla/create_product.php";
	
	
	
	
	
		  @Override
	  public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bill);
		bill_text=(TextView)findViewById(R.id.bill);
		bill_text.setMovementMethod(LinkMovementMethod.getInstance());
		
		Bundle gotbasket=getIntent().getExtras() ;
		
		rtype=gotbasket.getString("rtype");
		rprice=gotbasket.getString("rprice");
		rocc=gotbasket.getString("rocc");
		rtax=gotbasket.getString("rtax");
		JSONParser jsonParser = new JSONParser();
		new Add_Data().execute();
		
		}
	 
		  class Add_Data extends AsyncTask<String, String, String> {

				/**
				 * Before starting background thread Show Progress Dialog
				 * */
				@Override
				protected void onPreExecute() {
					super.onPreExecute();
					pDialog = new ProgressDialog(create_bill.this);
					pDialog.setMessage(" uploading your bill .....");
					pDialog.setIndeterminate(false);
					pDialog.setCancelable(true);
					pDialog.show();	
					
					
				}

				/**
				 * Creating product
				 * */
				protected String doInBackground(String... args) {
					
					// Building Parameters
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("room_type", rtype));
					params.add(new BasicNameValuePair("room_price", rprice));
					params.add(new BasicNameValuePair("tax", rtax));
					params.add(new BasicNameValuePair("room_occupied", rocc));
					
					
					// getting JSON Object
					// Note that create product url accepts POST method
					JSONObject json = jsonParser.makeHttpRequest(url,
							"POST", params);
					
					// check log cat fro response
					Log.d("Create Response", json.toString());
					 
					
					try {
						int success = json.getInt("database_connect");

						if (success == 1) {
							// successfully created product
							bill_text.append("successful");
							
							// closing this screen
						} else {
							// failed to create product
							bill_text.setText("not successful");
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
					// dismiss the dialog once done
					pDialog.dismiss();
					bill_text.setText("http://192.168.168.56/stayzilla/bill1.dat");
					
					
				}
				

		    }
 

}
