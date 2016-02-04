package learn2crack.jsonparsing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import learn2crack.jsonparsing.library.JSONParser;



public class MainActivity extends Activity {
	
	//URL to get JSON Array
	private static String url = "http://127.0.0.1/my_projects/json/";
	
	//JSON Node Names 
	private static final String TAG_USER = "user";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_EMAIL = "email";
	
	JSONArray user = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        setContentView(R.layout.activity_main);
        
		// Creating new JSON Parser
		JSONParser jParser = new JSONParser();

		// Getting JSON from URL
		JSONObject json = jParser.getJSONFromUrl(url);
		
		try {
			// Getting JSON Array
			user = json.getJSONArray(TAG_USER);
			
			JSONObject c = user.getJSONObject(0);
			
			// Storing  JSON item in a Variable
			String id = c.getString(TAG_ID);
			String name = c.getString(TAG_NAME);
			String email = c.getString(TAG_EMAIL);
			
			//Importing TextView
			final TextView uid = (TextView)findViewById(R.id.uid);
			final TextView name1 = (TextView)findViewById(R.id.name);
			final TextView email1 = (TextView)findViewById(R.id.email);
			
			//Set JSON Data in TextView
			uid.setText(id);
			name1.setText(name);
			email1.setText(email);

		
	} catch (JSONException e) {
		e.printStackTrace();
	}

			

    }


 
    
}
