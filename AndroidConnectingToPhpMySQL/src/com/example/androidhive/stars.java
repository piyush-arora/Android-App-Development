package com.example.androidhive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class stars extends Activity {
	
	private RatingBar ratingBar;
	private TextView txtRatingValue;
	private Button btnSubmit;
	 
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stars);
		ratingBar=(RatingBar)findViewById(R.id.ratingBar1);
		txtRatingValue=(TextView)findViewById(R.id.rate_text);
		btnSubmit=(Button)findViewById(R.id.star_button);
		btnSubmit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				String rating=String.valueOf(ratingBar.getRating());
				txtRatingValue.setText(rating);
			}
		});  
	 	
	  }
	 
	  
	}