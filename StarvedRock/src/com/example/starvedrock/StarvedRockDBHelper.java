package com.example.starvedrock;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StarvedRockDBHelper extends SQLiteOpenHelper {

	//Values for Notification Table
	public static final String TYPE="type";
	public static final String TEXT="note";
	public static final String TIME="time";
	public static final String EXPIRES="expires";
	public static final String NOTIFICATION_TABLE="notifications";
	
	//Values for POI Table
	public static final String POI_TABLE="poi";
	//Placeholder for pictures
	public static final String NAME="name";
	public static final String PICTURES="pictures";
	public static final String LATITUDE="Latitude";
	public static final String LONGITUDE="Longitude";
	public static final String NOTES="notes";
	
	
	
	
	private static final String DATABASE_NAME = "StarvedRockStor.db";
	private static final int DATABASE_VERSION =1;
	
	//Strings to create tables
	private static final String CREATE_TABLE_NOTIFICATION = "CREATE TABLE "
			+ NOTIFICATION_TABLE + "(" +NAME + " TEXT, "
			+ TEXT + " TEXT, " + TIME + " Primary datetime, "
			+EXPIRES +" date);";

	private static final String CREATE_TABLE_POI = "CREATE TABLE "
			+ POI_TABLE + "(" +NAME + " TEXT, "+PICTURES+" blob, "+ LATITUDE + " REAL, " + LONGITUDE + " REAL, "
			+ NOTES + " TEXT, " + "PRIMARY KEY ("+LATITUDE +", "+LONGITUDE+");";

	StarvedRockDBHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase database) {
		// TODO Auto-generated method stub
		Log.d(null,"IN ONCREATE");
		database.execSQL(CREATE_TABLE_NOTIFICATION);
		database.execSQL(CREATE_TABLE_POI);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(StarvedRockDBHelper.class.getName(), 
				"upgrading database from version " + oldVersion 
				+ " to " + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + NOTIFICATION_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + POI_TABLE);
		onCreate(db);

	}

}
