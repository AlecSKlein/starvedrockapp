package com.example.starvedrock;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
/**
 * Adapter used to show all notifications
 * @author josh
 *
 */
public class NotificationAdapter extends ArrayAdapter<Notification> {
	int resource;
	StarvedRockDataSource db;
	private Context mContext;
	Notification pItem;
	/**
	 * Constructor that takes in the context, resource, and array.
	 * @param context The activity
	 * @param resource The GUI id
	 * @param objects array of items
	 */
	public NotificationAdapter(Context context, int resource, 			List<Notification> objects) {
		super(context, resource,  objects);
		this.resource=resource;
		this.mContext=context;
		db=new StarvedRockDataSource(context);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		LinearLayout poiVIew;
		pItem= getItem(position);
		if(convertView==null){
			poiVIew = new LinearLayout(getContext());
			String inflater= Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater vi;
			vi=(LayoutInflater)getContext().getSystemService(inflater);
			vi.inflate(resource, poiVIew, true);
		}else
		{
			poiVIew=(LinearLayout) convertView;
		}
		Button poiButton=(Button)poiVIew.findViewById(R.id.notiText);
		 poiButton.setText(pItem.getText());
	   
		 	/*
		 	 *	Button listener for opening NotificationActivity. 
		 	 */
			poiButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(mContext, NotificationActivity.class);
				//	intent.setClass(, POIActivity.class);
					intent.putExtra(StarvedRockDBHelper.TIME, pItem.getDate());
	            	 intent.putExtra(StarvedRockDBHelper.EXPIRES, pItem.getExpires());
	            	 mContext.startActivity(intent);
				}
				
			});
			
			
		
		//sButton.
		//idText.setText(String.valueOf(pItem.getID()));
		//poiButton.setText(pItem.getName());
		
		return poiVIew;
		
	}
}
