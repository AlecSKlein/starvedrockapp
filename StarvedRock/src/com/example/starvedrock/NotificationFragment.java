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
import android.widget.TextView;
import android.widget.Toast;

public class NotificationFragment extends Fragment {
	protected static final String TAG = "Getting array";
	String url = "http://10.133.94.86:8000";
	private Button btnMakeObjectRequest, btnMakeArrayRequest;
	StarvedRockDataSource db; 
    // Progress dialog
    private ProgressDialog pDialog;
 
    private TextView txtResponse;
    private static final String TAG_JSON= "data";
    private static final String TAG_NOTI= "notifications";
    private static final String TAG_NOTI_TEXT="notification";
    private static final String TAG_NOTI_DATE="date";
    
    private static final String TAG_LOC="locations";
    private static final String TAG_POI="marker";
    private static final String TAG_POI_LAT="lat";
    private static final String TAG_POI_LON="lng";
    private static final String TAG_POI_TYPE="poi";
    private static final String TAG_POI_NOTE="description";
    private static final String TAG_POI_NAME="id";
    // temporary string to show the parsed response
    private String jsonResponse;
    
    private JSONArray notiArray=null;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
			View view=inflater.inflate(R.layout.notification_fragment, container, false);
			btnMakeObjectRequest = (Button) view.findViewById(R.id.btnObjRequest);
	        btnMakeArrayRequest = (Button) view.findViewById(R.id.btnArrayRequest);
	        txtResponse = (TextView) view.findViewById(R.id.txtResponse);
	        db = new StarvedRockDataSource(getActivity());
	   	 
	        pDialog = new ProgressDialog(getActivity());
	        pDialog.setMessage("Please wait...");
	        pDialog.setCancelable(false);
	 
	        btnMakeObjectRequest.setOnClickListener(new View.OnClickListener() {
	 
	            @Override
	            public void onClick(View v) {
	                // making json object request
	              makeJsonObjectRequest();
	          //  makeJsonArrayRequest();
	            }
	        });
	        btnMakeArrayRequest.setOnClickListener(new View.OnClickListener() {
	        	 
	            @Override
	            public void onClick(View v) {
	                // making json array request
	                makeJsonArrayRequest();
	            }
	        });
			return view;
	}
	private void makeJsonObjectRequest() {
		showpDialog();
		
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
	            url, null, new Response.Listener<JSONObject>() {
	 
	                @Override
	                public void onResponse(JSONObject response) {
	                    Log.d(TAG, response.toString());
	                   // txtResponse.setText(response.toString());
	                    try {
	                        // Parsing json object response
	                        // response will be a json object
	                        //String name = response.getString("notification");
	                    	JSONArray notiArr =response.getJSONArray("data").getJSONObject(0).getJSONArray("notifications");
	                    	String jsonString = ""+notiArr.get(0).toString();
	                    	/*for(int i = 0; i<notiArr.length(); i++)
	                    	{
	                    		jsonString+= ""+notiArr.get(i).toString()+"\n\n";
		                    	
	                    	}*/
	                    	for(int i=0; i< notiArr.length(); i++){
	                    		JSONObject jobj=(JSONObject) notiArr.get(i);
	                    		
		                        String date = jobj.getString(TAG_NOTI_DATE);
		                        String name= jobj.getString("notification");
		                        jsonString +=  "name: " + name + "\n\n";
			                    Notification notifi=new Notification(Notification.Type.UPDATE, name, date, "12/30/2015");
			                    db.createNotificationEntry(notifi);
		                        jsonString += "Date: " + date + "\n\n";
	                    	}
	                    	/*JSONArray poiArr=response.getJSONArray("data").getJSONObject(1).getJSONArray(TAG_LOC);
	         	           	jsonString=poiArr.getString(1).toString();
	         	           for(int i=0; i< poiArr.length(); i++){
	                    		JSONObject jobj=(JSONObject) poiArr.get(1);
	                    		//String name= jobj.getString(TAG_NOTI);
	                    		//if(jobj.getBoolean(TAG_POI_NAME)){
		                        String name = jobj.getString(TAG_POI_NAME);
		                        String lat =jobj.getString(TAG_POI_LAT);
		                        String lon =jobj.getString(TAG_POI_LON);
		                        String poi =jobj.getString(TAG_POI_TYPE);
		                        String descr=jobj.getString(TAG_POI_NOTE);
		                        
		                        db.createPOIEntry(new POI(name, POI.getType(poi), Double.parseDouble(lat), Double.parseDouble(lon), descr, 0));
		                        //jsonString +=  "name: " + name + "\n\n";
			                       
		                        jsonString += "name: " + name + " "+lat+ " "+lon+ " "+poi+ " "+descr+"\n\n";
		                        //jsonString += "Lat: " + lat + "\n\n";
	                    		//}
	         	           }*/
	                    	//JSONObject jobj=(JSONObject) jsonResponse.get(0);
	                    //	String name= jobj.toString();
	                      //  String email = response.getString("date");
/*	                        JSONObject phone = response.getJSONObject("phone");
	                        String home = phone.getString("home");
	                        String mobile = phone.getString("mobile");
	*/ 
	                       // jsonResponse = "";
	             //           jsonResponse += ": " + name + "\n\n";
	                        //jsonResponse += "Email: " + email + "\n\n";
	    //                    jsonResponse += "Home: " + home + "\n\n";
	      //                  jsonResponse += "Mobile: " + mobile + "\n\n";
	 
	                        txtResponse.setText(jsonString);
	 
	                    } catch (JSONException e) {
	                        e.printStackTrace();
	                        Toast.makeText(getActivity().getApplicationContext(),
	                                "Error: " + e.getMessage(),
	                                Toast.LENGTH_LONG).show();
	                    }
	                    hidepDialog();
	                }
	            }, new Response.ErrorListener() {
	 
	                @Override
	                public void onErrorResponse(VolleyError error) {
	                    VolleyLog.d(TAG, "Error: " + error.getMessage());
	                    Toast.makeText(getActivity().getApplicationContext(),
	                            error.getMessage(), Toast.LENGTH_SHORT).show();
	                    // hide the progress dialog
	                    hidepDialog();
	                }
	            });
	 
	    // Adding request to request queue
	    AppController.getInstance().addToRequestQueue(jsonObjReq);
		
	}
	
	private void makeJsonArrayRequest() {
		 
	    showpDialog();
	 
	    JsonArrayRequest req = new JsonArrayRequest(url,
	            new Response.Listener<JSONArray>() {
	                @Override
	                public void onResponse(JSONArray response) {
	                    Log.d(TAG, response.toString());
	 
	                    try {
	                        // Parsing json array response
	                        // loop through each json object
	                        jsonResponse = "";
	                       JSONObject jsonObject =(JSONObject) response.getJSONObject(0);//response.getJSONArray(TAG_NOTI);
	                       JSONArray jArray=jsonObject.getJSONArray("data");
	                        
	                        for (int i = 0; i < jArray.length(); i++) {
	 
	                            JSONObject person = (JSONObject) jArray.get(i);
	                            
	         //                           .getString(TAG_NOTI_DATE);
	 
	                            String name = person.getString(TAG_NOTI_DATE);
	                       	 
	                            /*String email = person.getString("email");
	                            JSONObject phone = person
	                                    .getJSONObject("phone");
	                            String home = phone.getString("home");
	                            String mobile = phone.getString("mobile");*/
	 
	                            jsonResponse += "Name: " + name + "\n\n";
//	                            jsonResponse += "Email: " + email + "\n\n";
	//                            jsonResponse += "Home: " + home + "\n\n";
	 //                           jsonResponse += "Mobile: " + mobile + "\n\n\n";
	 
	                        }
	 
	                        txtResponse.setText(jsonResponse);
	 
	                    } catch (JSONException e) {
	                        e.printStackTrace();
	                        Toast.makeText(getActivity().getApplicationContext(),
	                                "Error: " + e.getMessage(),
	                                Toast.LENGTH_LONG).show();
	                    }
	 
	                    hidepDialog();
	                }
	            }, new Response.ErrorListener() {
	                @Override
	                public void onErrorResponse(VolleyError error) {
	                    VolleyLog.d(TAG, "Error: " + error.getMessage());
	                    Toast.makeText(getActivity().getApplicationContext(),
	                            error.getMessage(), Toast.LENGTH_SHORT).show();
	                    hidepDialog();
	                }
	            });
	 
	    // Adding request to request queue
	    AppController.getInstance().addToRequestQueue(req);
	}
	
	 private void showpDialog() {
	        if (!pDialog.isShowing())
	            pDialog.show();
	    }
	 
	    private void hidepDialog() {
	        if (pDialog.isShowing())
	            pDialog.dismiss();
	    }
}
