package com.cardmanager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final ArrayList<String> values;
	
	public CustomAdapter(Context context, List<String> objects) {
		super(context, R.layout.row, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.values = (ArrayList<String>) objects;
		
	}
	@Override
	public View getView(int position, View convert, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    View rowView = inflater.inflate(R.layout.row, parent, false);
		    TextView textView = (TextView) rowView.findViewById(R.id.card1);
		    
		    textView.setText(values.get(position));
		    return rowView;
	}

}
