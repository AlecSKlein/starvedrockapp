package com.example.starvedrock;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;
import android.text.TextUtils;
/**
 * This program deals with recieving information from the server.
 * @author josh
 *
 */
public class AppController extends Application {
	  public static final String TAG = AppController.class.getSimpleName();
	  
	    private RequestQueue mRequestQueue;
	  
	    private static AppController mInstance;
	  
	    
	    /*
	     * (non-Javadoc)
	     * @see android.app.Application#onCreate()
	     */
	    @Override
	    public void onCreate() {
	        super.onCreate();
	        mInstance = this;
	    }
	  
	    /**
	     * Returns instance of this class
	     * @return
	     */
	    public static synchronized AppController getInstance() {
	        return mInstance;
	    }
	  
	  /**
	   * Gets the RequestQueue from server
	   * @return
	   */
	    public RequestQueue getRequestQueue() {
	        if (mRequestQueue == null) {
	            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
	        }
	  
	        return mRequestQueue;
	    }
	  
	    /**
	     * Adds to the request Queue
	     * @param req Items to add to Queue
	     * @param tag message to add to each request
	     */
	    public <T> void addToRequestQueue(Request<T> req, String tag) {
	        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
	        getRequestQueue().add(req);
	    }
	  
	    /**
	     * Adds an item to requese queue with a default name for the tag.
	     * @param req
	     */
	    public <T> void addToRequestQueue(Request<T> req) {
	        req.setTag(TAG);
	        getRequestQueue().add(req);
	    }
	  
	    /**
	     * Cancels pending request with the object
	     * @param tag
	     */
	    public void cancelPendingRequests(Object tag) {
	        if (mRequestQueue != null) {
	            mRequestQueue.cancelAll(tag);
	        }
	    }
}
