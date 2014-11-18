package com.example.starvedrock;





import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class MainActivity extends FragmentActivity  {
	;
	private ViewPager mViewPager;
	private FragmentAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter=new FragmentAdapter(getSupportFragmentManager());
        final ActionBar actionBar=getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);    
          
        // Specify that tabs should be displayed in the action bar.
    
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
}
