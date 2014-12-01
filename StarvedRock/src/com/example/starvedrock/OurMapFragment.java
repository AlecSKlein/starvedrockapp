package com.example.starvedrock;

import java.util.ArrayList;
import java.util.List;


import com.google.android.gms.maps.GoogleMap;



import android.graphics.Typeface;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

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
	    } catch (InflateException e) {
	        /* map is already there, just return view as it is */
	    }
	    return view;
		
	}
}
