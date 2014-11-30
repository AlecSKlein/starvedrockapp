package com.example.starvedrock;


import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

public class PoIFragment extends Fragment {
	
    Button poiButton;
    Switch sButton;
    POI poiThis;
    POIAdapter plistadp;
    private ListView lstPOI;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
			View view=inflater.inflate(R.layout.poi_list, container, false);
			/*poiButton=(Button)view.findViewById(R.id.poiText);
			poiButton.setTextColor(Color.parseColor("#00FF00"));
			sButton=(Switch)view.findViewById(R.id.switchbutton);
	      //  Notification note=new Notification(Notification.Type.UPDATE, "Hello there sexy, I am from the db, and I know you love me","2014-11-16 12:35:15", "eventually");
	     //   POI note=new POI("ISU", POI.Type.GEOLOGY, 0000,1.111, "Do a little dance now", 0);*/
	        StarvedRockDataSource db=new StarvedRockDataSource(getActivity());
	       // db.createPOIEntry(note);
	      //  db.createPOIEntry(note);
	        /*
	       ArrayList<POI> noteLs=(ArrayList<POI>) db.getAllPOI();
	       poiThis=noteLs.get(0); 
	       poiButton.setText(poiThis.getName());
	        poiButton.setTextSize(20);
	        //sButton = (Switch) findViewById(R.id.togglebutton);
	        sButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
	            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	                if (isChecked) {
	                    // The toggle is enabled
	                } else {
	                    // The toggle is disabled
	                }
	            }
	        });
	        poiButton.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
	            	 Intent intent = new Intent(getActivity(), POIActivity.class);
	            	 intent.putExtra(StarvedRockDBHelper.LATITUDE, poiThis.getLatitude());
	            	 intent.putExtra(StarvedRockDBHelper.LONGITUDE, poiThis.getLongitude());
	            	 startActivity(intent);
	             }
	         });*/
	        lstPOI=(ListView) view.findViewById(R.id.poilistview);
	        plistadp=new POIAdapter(getActivity(), R.layout.poi_view, db.getAllPOI());
	        lstPOI.setAdapter(plistadp);
	  //      OnItemClickListener show= showPOI();
//	        lstPOI.setOnItemClickListener(show);
	        return view;
	}
	/*
	private OnItemClickListener showPOI() 
	{
		OnItemClickListener show=new OnItemClickListener()
		{
			@Override
			 public void onItemClick(AdapterView parent, View v, int position, long id) 
			 {
				 POI point= plistadp.getItem(position);
            	 Intent intent = new Intent(parent.getContext(), POIActivity.class);
            	 intent.putExtra(StarvedRockDBHelper.LATITUDE, point.getLatitude());
            	 intent.putExtra(StarvedRockDBHelper.LONGITUDE, point.getLongitude());
            	 startActivity(intent);
			    }

			
			 };
		return show;
	}*/

  
	public void showText(String text)
	{
		poiButton.setText(text);
	}
	
}
