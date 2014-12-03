package com.example.starvedrock;

import android.content.Context;
import android.database.Cursor;
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
	public static final String FLAG="marker";
	
	
	
	private static final String DATABASE_NAME = "StarvedRockStor.db";
	private static final int DATABASE_VERSION =1;
	
	//Strings to create tables
	private static final String CREATE_TABLE_NOTIFICATION = "CREATE TABLE "
			+ NOTIFICATION_TABLE + "(" +TYPE + " TEXT, "
			+ TEXT + " TEXT, " + TIME + "  TEXT, "
			+EXPIRES +" TEXT);";

	private static final String CREATE_TABLE_POI = "CREATE TABLE "
			+ POI_TABLE + "(" +NAME + " TEXT, "+TYPE+ " TEXT, "+PICTURES+" blob, "+ LATITUDE + " REAL, " + LONGITUDE + " REAL, "
			+ NOTES + " TEXT, " + FLAG + " INTEGER, " + "PRIMARY KEY ("+LATITUDE +", "+LONGITUDE+"));";

	/**
	 * Constructor
	 * @param context
	 */
	StarvedRockDBHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	/*
	 * (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase database) {
		// TODO Auto-generated method stub
		Log.d(null,"IN ONCREATE");
		database.execSQL(CREATE_TABLE_NOTIFICATION);
		database.execSQL(CREATE_TABLE_POI);
	}

	/*
	 * (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(StarvedRockDBHelper.class.getName(), 
				"upgrading database from version " + oldVersion 
				+ " to " + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + NOTIFICATION_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + POI_TABLE);
		onCreate(db);

	}

	/**
	 * Checks if the notificationt table exists.
	 * @param database
	 * @return
	 */
	public boolean regionTableExists(SQLiteDatabase database)
	{
		if(!database.isOpen())
			return false;
		
		Cursor cursor = database.rawQuery("SELECT COUNT(*) FROM " + NOTIFICATION_TABLE + ";", null);
		
		int count = cursor.getCount();
		Log.i("NotificationDB", "Has " + count + " Rows");
		if(count < 4)
		{
			cursor.close();
			return false;
		}
		cursor.close();
		return true;
	}
}
