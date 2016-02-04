package com.example.stayzilla;



import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends ListActivity {
	private Context context;
	private static String url="http://10.0.2.2/stayzilla/";
	
	private static final String VTYPE = "hotels";
	private static final String VCOLOR = "displayName";
	private static final String VFUEL = "address";
	private static final String VTREAD = "city";
	
	ArrayList<HashMap<String, String>> jsonlist = new ArrayList<HashMap<String, String>>();
	
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView (R.layout.list);
		new ProgressTask(MainActivity.this).execute();
		lv=getListView();
	}
	
	private class ProgressTask extends AsyncTask<String, Void, Boolean> {

		private ProgressDialog dialog;
		private ListActivity activity;
		
		public ProgressTask(ListActivity activity) {
			
			this.activity=activity;
			context=activity;
			dialog=new ProgressDialog(context);
			
					
			
		}
		
		private Context context;

		
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			this.dialog.setMessage("Progress Start");
			this.dialog.show();
		}



		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			
			if (dialog.isShowing())
			{
				dialog.dismiss();
			}
			
			ListAdapter adapter = new SimpleAdapter(context, jsonlist,R.layout.list_item, new String[] { VTYPE, VCOLOR,VFUEL, VTREAD }, new int[] {R.id.vehicleType, R.id.vehicleColor, R.id.fuel,R.id.treadType });
			
			setListAdapter(adapter);
			
			
			
		}



		@Override
		protected Boolean doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			JSONParser jParser = new JSONParser();
			JSONArray json = jParser.getJSONFromUrl(url);
			
			for (int i=0;i<json.length();i++) {
				
				try {
					
					JSONObject c = json.getJSONObject(i);
					String vtype = c.getString(VTYPE);
					String vcolor = c.getString(VCOLOR);
					String vfuel = c.getString(VFUEL);
					String vtread = c.getString(VTREAD);
					HashMap<String, String> map = new HashMap<String, String>();
					map.put(VTYPE, vtype);
					map.put(VCOLOR, vcolor);
					map.put(VFUEL, vfuel);
					map.put(VTREAD, vtread);
					jsonlist.add(map);
					
							
				}
				
				catch (JSONException e)
				{
					e.printStackTrace();
				}
			}
			
			
			return null;
		}

		
		
	}
	
}
