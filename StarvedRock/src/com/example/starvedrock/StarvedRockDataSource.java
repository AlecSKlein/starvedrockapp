package com.example.starvedrock;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class StarvedRockDataSource {
	
	private SQLiteDatabase db;
	private StarvedRockDBHelper dbHelper;

	private String[] poiColumns = {
		StarvedRockDBHelper.NAME, StarvedRockDBHelper.TYPE, StarvedRockDBHelper.PICTURES, StarvedRockDBHelper.LATITUDE, StarvedRockDBHelper.LONGITUDE, StarvedRockDBHelper.NOTES 	
	};
	
	private String[] notificationColumns = {
			StarvedRockDBHelper.TYPE, StarvedRockDBHelper.TEXT, StarvedRockDBHelper.TIME, StarvedRockDBHelper.EXPIRES};
	
	public StarvedRockDataSource(Context context){
		dbHelper=new StarvedRockDBHelper(context);
	}
	
	public void openForWriting() throws SQLException
	{
		db = dbHelper.getWritableDatabase();
	}
	
	public void openForReading() throws SQLException
	{
		db = dbHelper.getReadableDatabase();	
	}
	
	public boolean tableExists()
	{
		db = dbHelper.getReadableDatabase();
		boolean test = dbHelper.regionTableExists(db);
		
		dbHelper.close();
		return test;
	}
	
	public void close()
	{
		dbHelper.close();
	}
	
	public void createNotificationEntry(Notification info)
	{
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(StarvedRockDBHelper.TYPE, info.getType().name());
		values.put(StarvedRockDBHelper.TEXT, info.getText());
		values.put(StarvedRockDBHelper.TIME, info.getDate());
		values.put(StarvedRockDBHelper.EXPIRES, info.getExpires());
		db.insert(StarvedRockDBHelper.NOTIFICATION_TABLE, null, values);
		db.close();
	}
	
	public void createPOIEntry(POI info)
	{
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(StarvedRockDBHelper.NAME, info.getName());
		values.put(StarvedRockDBHelper.TYPE, info.getType().name());
		
		values.put(StarvedRockDBHelper.LATITUDE, info.getLatitude());
		values.put(StarvedRockDBHelper.LONGITUDE, info.getLongitude());
		values.put(StarvedRockDBHelper.NOTES, info.getNote());
		db.insert(StarvedRockDBHelper.POI_TABLE, null, values);
		db.close();
	}
	
	public void deleteNotification(String time)
	{
		db.delete(StarvedRockDBHelper.NOTIFICATION_TABLE, StarvedRockDBHelper.EXPIRES + " = "+ time, null);
	}
	
	public void deletePOI(double latitude, double longitude)
	{
		db.delete(StarvedRockDBHelper.NOTIFICATION_TABLE, StarvedRockDBHelper.LATITUDE + " = "+ latitude+" AND "+StarvedRockDBHelper.LONGITUDE+ " = "+ longitude, null);
	}
	public List<Notification> getAllNotifications()
	{
		db=dbHelper.getReadableDatabase();
		ArrayList<Notification> noteList=new ArrayList<Notification>();
		String selectQuery = "Select * FROM "+ StarvedRockDBHelper.NOTIFICATION_TABLE;
		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			noteList.add(cursorToNotification(cursor));
			cursor.moveToNext();
		}
		cursor.close();
		db.close();
		return noteList;
	}
	
	public List<POI> getAllPOI()
	{
		db=dbHelper.getReadableDatabase();
		ArrayList<POI> poiList=new ArrayList<POI>();
		String selectQuery = "Select "+poiColumns[0]+", "+poiColumns[1]+", "+poiColumns[3]+", "+poiColumns[4]+", "+poiColumns[5] +" FROM "+ StarvedRockDBHelper.POI_TABLE;
		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			poiList.add(cursorToPoI(cursor));
			cursor.moveToNext();
		}
		cursor.close();
		db.close();
		return poiList;
	}

	private Notification cursorToNotification(Cursor cursor) {
		return new Notification(Notification.Type.valueOf(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));

	}

	private POI cursorToPoI(Cursor cursor) {
		return new POI(cursor.getString(0),POI.Type.valueOf( cursor.getString(1)),cursor.getDouble(2), cursor.getDouble(3), cursor.getString(4));

	}
}
