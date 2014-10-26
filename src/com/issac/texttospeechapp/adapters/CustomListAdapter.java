package com.issac.texttospeechapp.adapters;

import com.example.texttospeechapp.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

public class CustomListAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final String[] values;
	public CustomListAdapter(Context context, String[] objects) {
		super(context,R.layout.text_list,objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		values = objects;
	}
	
	@Override
	public View getView(int position,View contentView,ViewGroup parent){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.text_list,parent,false);
		Button read = (Button) rowView.findViewById(R.id.readButton);
		Button stop = (Button) rowView.findViewById(R.id.stopButton);
		EditText text = (EditText) rowView.findViewById(R.id.inputTextField);
		text.setText("Insert At position : "  + position);
		
		return rowView;
	}

}
