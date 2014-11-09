package com.example.starvedrock;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotificationDBHelper extends SQLiteOpenHelper {

	//Values for Notification Table
	public static final String TYPE="type";
	public static final String TEXT="note";
	public static final String TIME="time";
	public static final String EXPIRES="expires";
	public static final String NOTIFICATION_TABLE="notifications";
	
	//Values for POI Table
	public static final String POI_TABLE="poi";
	
	
	
	
	private static final String DATABASE_NAME = "StarvedRockStor.db";
	private static final int DATABASE_VERSION =1;
	
	private static final String CREATE_TABLE_NOTIFICATION = "CREATE TABLE "
			+ NOTIFICATION_TABLE + "(" +TYPE + " TEXT, "
			+ TEXT + " TEXT, " + TIME + " Primary datetime, "
			+EXPIRES +" date);";
	
	NotificationDBHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
