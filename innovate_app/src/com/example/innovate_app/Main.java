package com.example.innovate_app;





import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity 
{

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        Button database = (Button)findViewById(R.id.dbase);
        Button EXIT = (Button)findViewById(R.id.exit);
        Button power = (Button)findViewById(R.id.button1);
        Button login = (Button)findViewById(R.id.log);
        
        TextView display = (TextView)findViewById(R.id.display);
        
        // energy mode
        database.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Intent x=new Intent("com.example.innovate_app.MANGO");
				startActivity(x);
			}
		});
        // power details
        power.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Intent x=new Intent("com.example.innovate_app.APPLE");
				startActivity(x);
			}
		});

        login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Intent x=new Intent("com.example.innovate_app.DATA");
				startActivity(x);
			}
		});

        
        EXIT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

        
    }


    


	@Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }





    
}
