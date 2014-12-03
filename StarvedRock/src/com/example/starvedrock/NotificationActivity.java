package com.example.starvedrock;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;
/**
 * Gets the Notification when pressed on the notification adaper.
 * 
 * The GUI needs to be changed to be visually appeasing. 
 * @author josh
 *
 */
public class NotificationActivity extends Activity {

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		
		//Gets the date and expiration from fragmentadapter to fill in information
		Intent intent=getIntent();
		String date = intent.getStringExtra(StarvedRockDBHelper.TIME);
		String expires = intent.getStringExtra(StarvedRockDBHelper.EXPIRES);
		StarvedRockDataSource db=new StarvedRockDataSource(this);
		Notification note=db.getNotification(date, expires);
		
		//Sets up the view
		TextView nameView = (TextView)findViewById(R.id.noteTextView);
		TextView catView = (TextView) findViewById(R.id.dateView);
		TextView noteView = (TextView)findViewById(R.id.expireView);
		//if note is not null, make the views show the information, otherwise just show values recieved from database. 
		if(note!= null)
		{
			nameView.setText(note.getText());
		catView.setText(note.getDate());
			noteView.setText(note.getExpires());
			
		}else
		{
			nameView.setText("Unable to retrieve from database");
			catView.setText(date);
			noteView.setText(String.valueOf(expires));//setText(point.getNote());
		
		}
	}

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notification, menu);
		return true;
	}

}
