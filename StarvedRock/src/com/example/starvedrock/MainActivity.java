package com.example.starvedrock;



import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class MainActivity extends Activity {
	private GoogleMap mMap;
	private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ActionBar actionBar=getActionBar();
     
        //final ViewPager mViewPager= (ViewPager) findViewById(R.id.pager); 
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
       	mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(41.3189, -88.9901), 17));
        // Specify that tabs should be displayed in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                        getActionBar().setSelectedNavigationItem(position);
                    }
                });
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
        for (int i = 0; i < 3; i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText("")
                            .setTabListener(tabListener));
        }
   
    }
}
