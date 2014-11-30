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

public class POIAdapter extends ArrayAdapter<POI> {
	int resource;
	StarvedRockDataSource db;
	private Context mContext;
	POI pItem;
	public POIAdapter(Context context, int resource, 			List<POI> objects) {
		super(context, resource,  objects);
		this.resource=resource;
		this.mContext=context;
		db=new StarvedRockDataSource(context);
		// TODO Auto-generated constructor stub
	}
	
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
			
			sButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
	            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	                if (isChecked) {
	                	
	                	db.updatePOIFlag(pItem.getLatitude(), pItem.getLongitude(), 1);
	                } else {

	                	
	                	db.updatePOIFlag(pItem.getLatitude(), pItem.getLongitude(), 0);
	                }
	            }
	        });
		
		//sButton.
		//idText.setText(String.valueOf(pItem.getID()));
		//poiButton.setText(pItem.getName());
		
		return poiVIew;
		
	}
}
