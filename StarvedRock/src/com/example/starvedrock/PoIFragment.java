package com.example.starvedrock;


import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PoIFragment extends Fragment {
	
    TextView tview;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
			View view=inflater.inflate(R.layout.poi_view, container, false);
			tview=(TextView)view.findViewById(R.id.poiText);
			tview.setTextColor(Color.parseColor("#00FF00"));
	      //  Notification note=new Notification(Notification.Type.UPDATE, "Hello there sexy, I am from the db, and I know you love me","2014-11-16 12:35:15", "eventually");
	        POI note=new POI("ISU", POI.Type.GEOLOGY, 0000,1.111, "Do a little dance now");
	        StarvedRockDataSource db=new StarvedRockDataSource(getActivity());
	        db.createPOIEntry(note);
	       ArrayList<POI> noteLs=(ArrayList<POI>) db.getAllPOI();
	        tview.setText(noteLs.get(0).getNote());
	        tview.setTextSize(20);
	        return view;
	}
	
  
	public void showText(String text)
	{
		tview.setText(text);
	}
}
