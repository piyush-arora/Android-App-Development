package com.example.androidhive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MobileArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;
 
	public MobileArrayAdapter(Context context, String[] values) {
		super(context, R.layout.tips, values);
		this.context = context;
		this.values = values;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.tips, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		textView.setText(values[position]);
 
		// Change icon based on name
		String s = values[position];
 
		System.out.println(s);
 
		if (s.equals("Get in")) {
			imageView.setImageResource(R.drawable.assassin);
		} else if (s.equals("Tourist Attraction")) {
			imageView.setImageResource(R.drawable.hotel);
		} else if (s.equals("Food & Beverages")) {
			imageView.setImageResource(R.drawable.hotel2);
		} else if (s.equals("Conveince")) {
			imageView.setImageResource(R.drawable.room2);
		} else {
			imageView.setImageResource(R.drawable.room);
		}
 
		return rowView;
	}
}