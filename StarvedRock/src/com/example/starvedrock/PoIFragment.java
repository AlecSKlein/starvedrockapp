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

/**
 * Takes the POIAdapter and sets it into a fragment for the FragmentActivity.
 * @author josh
 *
 */
public class PoIFragment extends Fragment {
	
    Button poiButton;
    Switch sButton;
    POI poiThis;
    POIAdapter plistadp;
    private ListView lstPOI;
    
    /*
     * (non-Javadoc)
     * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
     */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
			View view=inflater.inflate(R.layout.poi_list, container, false);
			 StarvedRockDataSource db=new StarvedRockDataSource(getActivity());
	       
	        lstPOI=(ListView) view.findViewById(R.id.poilistview);
	        plistadp=new POIAdapter(getActivity(), R.layout.poi_view, db.getAllPOI());
	        lstPOI.setAdapter(plistadp);
	        return view;
	}
  
	
	
}
