package com.example.starvedrock;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
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
	protected static final String TAG = null;
	String url = "http://10.135.0.224:8000";
	private Button btnMakeObjectRequest, btnMakeArrayRequest;
	 
    // Progress dialog
    private ProgressDialog pDialog;
 
    private TextView txtResponse;
 
    // temporary string to show the parsed response
    private String jsonResponse;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
			View view=inflater.inflate(R.layout.notification_fragment, container, false);
			btnMakeObjectRequest = (Button) view.findViewById(R.id.btnObjRequest);
	        btnMakeArrayRequest = (Button) view.findViewById(R.id.btnArrayRequest);
	        txtResponse = (TextView) view.findViewById(R.id.txtResponse);
	 
	        pDialog = new ProgressDialog(getActivity());
	        pDialog.setMessage("Please wait...");
	        pDialog.setCancelable(false);
	 
	        btnMakeObjectRequest.setOnClickListener(new View.OnClickListener() {
	 
	            @Override
	            public void onClick(View v) {
	                // making json object request
	                makeJsonObjectRequest();
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
	 
	                    try {
	                        // Parsing json object response
	                        // response will be a json object
	                        String name = response.getString("notification");
/*	                        String email = response.getString("email");
	                        JSONObject phone = response.getJSONObject("phone");
	                        String home = phone.getString("home");
	                        String mobile = phone.getString("mobile");
	*/ 
	                        jsonResponse = "";
	                        jsonResponse += ": " + name + "\n\n";
	  //                      jsonResponse += "Email: " + email + "\n\n";
	    //                    jsonResponse += "Home: " + home + "\n\n";
	      //                  jsonResponse += "Mobile: " + mobile + "\n\n";
	 
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
	                    // hide the progress dialog
	                    hidepDialog();
	                }
	            });
	 
	    // Adding request to request queue
	    AppController.getInstance().addToRequestQueue(jsonObjReq);
		
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
