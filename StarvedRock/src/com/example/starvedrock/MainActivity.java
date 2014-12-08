package com.example.starvedrock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;
/**
 * This is the first activity when opening the application.
 * It loads in the FragmentAdapter.
 * 
 * What still needs done?
 * Getting JSON requests off the GUI and into a background task. 
 * For an example, look at async task at http://developer.android.com/reference/android/os/AsyncTask.html.
 * 
 * @author josh
 *
 */
public class MainActivity extends FragmentActivity  {
	//TAG used for error handling              
	protected static final String TAG = "Getting array";
	//This is where the server url goes.
	String url = "http://10.133.95.136:8000";
	//Used for testing purposes to make sure you are recieving information from server.
	private ProgressDialog pDialog;
	
	// Tags used to get JSON from server
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
	    // temporary string to show the parsed response. Used for testing purposes
	    private String jsonResponse;
	    
	    StarvedRockDataSource db;
	
	private ViewPager mViewPager;
	private FragmentAdapter mAdapter;
	/*
	 * (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new StarvedRockDataSource(this);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        mAdapter=new FragmentAdapter(getSupportFragmentManager());
        final ActionBar actionBar=getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);    
          
        // Specify that tabs should be displayed in the action bar.
        makeJsonObjectRequest();
        mViewPager = (ViewPager) findViewById(R.id.pager);
       mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                        getActionBar().setSelectedNavigationItem(position);
                    }
                });
     // mViewPager.setAdapter(mAdapter);
        
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
			//	mViewPager.setCurrentItem(tab.getPosition());
			
				
				mViewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}
            
        };

        // Add 3 tabs, specifying the tab's text and TabListener
        
            actionBar.addTab(
                    actionBar.newTab()
                            .setText("Map")
                            .setTabListener(tabListener));
            actionBar.addTab(
                    actionBar.newTab()
                            .setText("POI")
                            .setTabListener(tabListener));
            actionBar.addTab(
                    actionBar.newTab()
                            .setText("Notification")
                            .setTabListener(tabListener));
        
        
    }
    
    /*
     * Gets the Json request from the server and adds it to the databates
     */
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
	                    	JSONArray poiArr=response.getJSONArray("data").getJSONObject(1).getJSONArray(TAG_LOC);
	         	            for(int i=0; i< poiArr.length(); i++){
	                    		JSONObject jobj=(JSONObject) poiArr.get(i);
	                    	    String name = jobj.getString(TAG_POI_NAME);
		                        String lat =jobj.getString(TAG_POI_LAT);
		                        String lon =jobj.getString(TAG_POI_LON);
		                        String poi =jobj.getString(TAG_POI_TYPE);
		                        String descr=jobj.getString(TAG_POI_NOTE);
		                        Log.d(TAG, poi);
		                        db.createPOIEntry(new POI(name, POI.getType(poi), Double.parseDouble(lat), Double.parseDouble(lon), descr, 0));
		                           
		                        jsonString += "name: " + name + " "+lat+ " "+lon+ " "+poi+ " "+descr+"\n\n";
	         	            }
	                    	
	                    } catch (JSONException e) {
	                        e.printStackTrace();
	                        Toast.makeText(getApplicationContext(),
	                                "Error: " + e.getMessage(),
	                                Toast.LENGTH_LONG).show();
	                    }
	                    hidepDialog();
	                }
	            }, new Response.ErrorListener() {
	 
	                @Override
	                public void onErrorResponse(VolleyError error) {
	                    VolleyLog.d(TAG, "Error: " + error.getMessage());
	                    Toast.makeText(getApplicationContext(),
	                            error.getMessage(), Toast.LENGTH_SHORT).show();
	                    // hide the progress dialog
	                    hidepDialog();
	                }
	            });
	 
	    // Adding request to request queue
	    AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
    
    //Private method to show the dialog.
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
 
    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
	
}
