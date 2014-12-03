package com.example.starvedrock;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
/**
 * ArrayAdapter for POIs used in instantiate the PoIFragment.
 * @author josh
 *
 */
public class POIAdapter extends ArrayAdapter<POI> {
	int resource;
	StarvedRockDataSource db;
	private Context mContext;
	POI pItem;
	
	/**
	 * Constructor that takes in the context, resource, and array.
	 * @param context The activity
	 * @param resource The GUI id
	 * @param objects array of items
	 */
	public POIAdapter(Context context, int resource, 			List<POI> objects) {
		super(context, resource,  objects);
		this.resource=resource;
		this.mContext=context;
		db=new StarvedRockDataSource(context);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		LinearLayout poiVIew;
		pItem= getItem(position);
		if(convertView==null){
			poiVIew = new LinearLayout(getContext());
			String inflater= Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater vi;
			vi=(LayoutInflater)getContext().getSystemService(inflater);
			vi.inflate(resource, poiVIew, true);
		}else
		{
			poiVIew=(LinearLayout) convertView;
		}
		Button poiButton=(Button)poiVIew.findViewById(R.id.poiText);
		 poiButton.setText(pItem.getName());//pItem.getName());
	        //poiButton.setTextSize(20);
		Switch sButton=(Switch)poiVIew.findViewById(R.id.switchbutton);
		
			sButton.setChecked(pItem.isFlag());
			//Sets up the listener to open the POIActivity.
			poiButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(mContext, POIActivity.class);
				//	intent.setClass(, POIActivity.class);
					intent.putExtra(StarvedRockDBHelper.LATITUDE, pItem.getLatitude());
	            	 intent.putExtra(StarvedRockDBHelper.LONGITUDE, pItem.getLongitude());
	            	 mContext.startActivity(intent);
				}
				
			});
			//When the button is pressed, it sets the flag in the db on or off
			sButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
	            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	                if (isChecked) {
	                	
	                	db.updatePOIFlag(pItem.getLatitude(), pItem.getLongitude(), 1);
	                } else {

	                	
	                	db.updatePOIFlag(pItem.getLatitude(), pItem.getLongitude(), 0);
	                }
	            }
	        });
		
		return poiVIew;
		
	}
}
