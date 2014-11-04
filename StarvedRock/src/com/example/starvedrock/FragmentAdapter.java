package com.example.starvedrock;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;




public class FragmentAdapter extends FragmentStatePagerAdapter {

//	private Context context;

	public FragmentAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int index) {
		// Depending on which tab is picked, we will make an if/else to set fragment
		Fragment fragment;
		if(index ==0){
			fragment = new OurMapFragment();
			return fragment;
		}else
		{
			fragment = new PoIFragment();
			return fragment;
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

}
