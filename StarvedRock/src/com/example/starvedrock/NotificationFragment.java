package com.example.starvedrock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Fragment used in FragmentAdapter for the notification.
 * Takes a NotificationAdapter and fills in the information.
 * @author josh
 *
 */
public class NotificationFragment extends Fragment {
	
	StarvedRockDataSource db; 
    // Progress dialog
	
	Button poiButton;
   
   Notification poiThis;
    NotificationAdapter plistadp;
    private ListView lstPOI;
  
    
    
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
			View view=inflater.inflate(R.layout.notification_fragment, container, false);
			 StarvedRockDataSource db=new StarvedRockDataSource(getActivity());
		       
		        lstPOI=(ListView) view.findViewById(R.id.notilistview);
		        plistadp=new NotificationAdapter(getActivity(), R.layout.noti_view, db.getAllNotifications());
		        lstPOI.setAdapter(plistadp);
		        return view;
		
	   	 
	      
	}

}
