package com.example.androidhive;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ListMobileActivity extends ListActivity {
	 
		Intent i;
		static final String[] MOBILE_OS = 
	               new String[] { "Get in", "Tourist Attraction", "Food & Drinks", "Conveince","Words of Wisdom"};
	 
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
	 
			setListAdapter(new MobileArrayAdapter(this, MOBILE_OS));
	 
		}
	 
		@Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
	 
			//get selected items
			if (position==0)
			{
				i=new Intent(v.getContext(),GetIn.class);
				startActivityForResult(i,0);
			}
			else if (position==1)
			{
				i=new Intent(v.getContext(),Tourist.class);
				startActivityForResult(i,0);
				
			}
			else if (position==2)
			{
				i=new Intent(v.getContext(),Eat.class);
				startActivityForResult(i,0);
				
			}
			else if (position==3)
			{
				i=new Intent(v.getContext(),Conv.class);
				startActivityForResult(i,0);
				
			}
			else if (position==4)
			{
				i=new Intent(v.getContext(),Words.class);
				startActivityForResult(i,0);
				
			}
			
			String selectedValue = (String) getListAdapter().getItem(position);
			Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
	 
		}
	 
	}