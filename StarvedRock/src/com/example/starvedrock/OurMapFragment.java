package com.example.starvedrock;

import java.util.ArrayList;
import java.util.List;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



import android.graphics.Typeface;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

/**
 * Map Fragment
 * What still needs to be done:
 * 	Get location
 *  Make offline
 *  add trails
 *  add markers
 * @author josh
 *
 */
public class OurMapFragment extends Fragment {
	private GoogleMap mMap;
	private static View view;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	        // Inflate the layout for this fragment
		//setUpMapIfNeeded();
		if (view != null) {
	        ViewGroup parent = (ViewGroup) view.getParent();
	        if (parent != null)
	            parent.removeView(view);
	    }
	    try {
	    	
	        view = inflater.inflate(R.layout.map_view, container, false);
	        StarvedRockDataSource db= new StarvedRockDataSource(getActivity());
	        
	        //The code in comments does not work at this time.  
	    	/*ArrayList<POI> markers=(ArrayList<POI>) db.getAllPOI();
	    	for(int i=0; i<markers.size(); i++){
	    		POI point=markers.get(i);
		    	mMap= ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
		    	mMap.addMarker(new MarkerOptions()
		        .position(new LatLng(point.getLatitude(), point.getLongitude()))
		        .title(point.getName()));	
	    	}*/
	    } catch (InflateException e) {
	        /* map is already there, just return view as it is */
	    }
	    return view;
		
	}
}
