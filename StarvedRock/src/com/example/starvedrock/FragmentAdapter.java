package com.example.starvedrock;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.TextView;

/**
 * This class extends a fragmentStatePagerAdapter to allow scrolling.
 * This adapter allows a user to switch from the map fragment to the notification fragment to the poifragment.
 * @author josh
 *
 */


public class FragmentAdapter extends FragmentStatePagerAdapter {

	/**
	 * Constructor 
	 * @param fm
	 */
	public FragmentAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	/*
	 * This item returns the fragment at each index
	 * (non-Javadoc)
	 * @see android.support.v4.app.FragmentStatePagerAdapter#getItem(int)
	 */
	@Override
	public Fragment getItem(int index) {
		// Depending on which tab is picked, we will make an if/else to set fragment
		Fragment fragment;
		if(index==1)
		{
			fragment = new PoIFragment();
			//Notification note=new Notification(Notification.Type.UPDATE, "Hello there sexy", "now", "eventually");
			//TextView tview=(TextView)fragment.getView().findViewById(R.id.poiText);   
			 //tview.setText(note.getText());
		       // ((PoIFragment) fragment).showText(note.getText());
			return fragment;
		}else if(index==2){
			fragment=new NotificationFragment();
			return fragment;
		}
		else{
			fragment = new OurMapFragment();
			return fragment;
		}
	}

	/*
	 * Returns the number of fragments
	 * (non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

}
