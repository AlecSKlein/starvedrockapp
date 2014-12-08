package com.example.starvedrock;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
/**
 * Takes the STarvedRockDBHelper and adds, removes, updates, and retrieves items from the database.
 * @author josh
 *
 */
public class StarvedRockDataSource {
	
	private SQLiteDatabase db;
	private StarvedRockDBHelper dbHelper;
	
	private String[] poiColumns = {
		StarvedRockDBHelper.NAME, StarvedRockDBHelper.TYPE, StarvedRockDBHelper.PICTURES, StarvedRockDBHelper.LATITUDE, StarvedRockDBHelper.LONGITUDE, StarvedRockDBHelper.NOTES, StarvedRockDBHelper.FLAG 	
	};
	
	private String[] notificationColumns = {
			StarvedRockDBHelper.TYPE, StarvedRockDBHelper.TEXT, StarvedRockDBHelper.TIME, StarvedRockDBHelper.EXPIRES};
	
	/**
	 * Constructor that takes in the current activity
	 * @param context
	 */
	public StarvedRockDataSource(Context context){
		dbHelper=new StarvedRockDBHelper(context);
	}
	
	/**
	 * Opens the database for writing.
	 * @throws SQLException
	 */
	public void openForWriting() throws SQLException
	{
		db = dbHelper.getWritableDatabase();
	}
	
	/**
	 * Opens the database for reading.
	 * @throws SQLException
	 */
	public void openForReading() throws SQLException
	{
		db = dbHelper.getReadableDatabase();	
	}
	
	/**
	 * Checks if the table exists.
	 * @return
	 */
	public boolean tableExists()
	{
		db = dbHelper.getReadableDatabase();
		boolean test = dbHelper.regionTableExists(db);
		
		dbHelper.close();
		return test;
	}
	
	/**
	 * Closes the database
	 */
	public void close()
	{
		dbHelper.close();
	}
	
	/**
	 * Creates a notification in the notification table
	 * @param info notification to add
	 */
	public void createNotificationEntry(Notification info)
	{
		Notification point=null;
		point=getNotification(info.getDate(), info.getExpires());
		if(point==null)
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
	}
	
	/**
	 * Adds a POI into the POI table in the database.
	 * @param info  POI to add
	 */
	public void createPOIEntry(POI info)
	{
		POI point=null;
		point=getPOI(info.getLatitude(), info.getLongitude());
		if(point==null && info!= null)
		{
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(StarvedRockDBHelper.NAME, info.getName());
		values.put(StarvedRockDBHelper.TYPE, info.getTypeToString());
		
		values.put(StarvedRockDBHelper.LATITUDE, info.getLatitude());
		values.put(StarvedRockDBHelper.LONGITUDE, info.getLongitude());
		values.put(StarvedRockDBHelper.NOTES, info.getNote());
		values.put(StarvedRockDBHelper.FLAG, 0);
		db.insert(StarvedRockDBHelper.POI_TABLE, null, values);
		db.close();
		}
	}
	
	/**
	 * Deletes a notification using the expiration.
	 * @param time expiration to delete.
	 */
	public void deleteNotification(String time)
	{
		db.delete(StarvedRockDBHelper.NOTIFICATION_TABLE, StarvedRockDBHelper.EXPIRES + " = "+ time, null);
	}
	
	/**
	 * Deletes a POI based off of latitude and longitude.
	 * @param latitude
	 * @param longitude
	 */
	public void deletePOI(double latitude, double longitude)
	{
		db.delete(StarvedRockDBHelper.NOTIFICATION_TABLE, StarvedRockDBHelper.LATITUDE + " = "+ latitude+" AND "+StarvedRockDBHelper.LONGITUDE+ " = "+ longitude, null);
	}
	
	/**
	 * Gets all the notifications and returns it in a list.
	 * @return
	 */
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
	
	/**
	 * Gets all the POI and returns them as a list.
	 * @return
	 */
	public List<POI> getAllPOI()
	{
		db=dbHelper.getReadableDatabase();
		ArrayList<POI> poiList=new ArrayList<POI>();
		String selectQuery = "Select "+poiColumns[0]+", "+poiColumns[1]+", "+poiColumns[3]+", "+poiColumns[4]+", "+poiColumns[5]+", "+poiColumns[6] +" FROM "+ StarvedRockDBHelper.POI_TABLE;
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
	
	/**
	 * Takes in the date and expiration and returns a specific Notification.
	 * @param date
	 * @param expires
	 * @return
	 */
	public Notification getNotification(String date, String expires)
	{
		db=dbHelper.getReadableDatabase();
		Notification point= null;
		String[] selectQuery ={ notificationColumns[0],notificationColumns[1],notificationColumns[2], notificationColumns[3]};
		Cursor cursor= db.query(StarvedRockDBHelper.NOTIFICATION_TABLE, selectQuery,  notificationColumns[2]+ "=?"+" and "+notificationColumns[3]+ "=?", new String[] {date, expires}, null, null, null);

		cursor.moveToFirst();
		if(!cursor.isAfterLast())
		{
			point=cursorToNotification(cursor);
			//cursor.moveToNext();
		}
		cursor.close();
		db.close();
		return point;
	}
	
	/**
	 * Takes in the latitude and longitude and returns a specific POI.
	 * @param lat latitude
	 * @param lon longitude
	 * @return
	 */
	public POI getPOI(double lat, double lon)
	{
		db=dbHelper.getReadableDatabase();
		POI point= null;
		String[] selectQuery ={ poiColumns[0],poiColumns[1], poiColumns[3],poiColumns[4], poiColumns[5],poiColumns[6]};// +" FROM "+ StarvedRockDBHelper.POI_TABLE+" WHERE "+ StarvedRockDBHelper.LATITUDE+" = "+lat+" AND "+StarvedRockDBHelper.LONGITUDE+" = "+lon;
		//Cursor cursor = db.rawQuery(selectQuery, null);
		Cursor cursor= db.query(StarvedRockDBHelper.POI_TABLE, selectQuery,  poiColumns[3]+ "=?"+" and "+poiColumns[4]+ "=?", new String[] {String.valueOf(lat), String.valueOf(lon)}, null, null, null);

		cursor.moveToFirst();
		if(!cursor.isAfterLast())
		{
			point=cursorToPoI(cursor);
			//cursor.moveToNext();
		}
		cursor.close();
		db.close();
		return point;
	}

	/**
	 * Gets all the POIflags that the user wants to show.
	 * @return
	 */
	public List<POI> getPOIFlags()
	{
		db=dbHelper.getReadableDatabase();
		String[] selectQuery ={ poiColumns[0],poiColumns[1], poiColumns[3],poiColumns[4], poiColumns[5],poiColumns[6]};// +" FROM "+ StarvedRockDBHelper.POI_TABLE+" WHERE "+ StarvedRockDBHelper.LATITUDE+" = "+lat+" AND "+StarvedRockDBHelper.LONGITUDE+" = "+lon;
		//Cursor cursor = db.rawQuery(selectQuery, null);
		Cursor cursor= db.query(StarvedRockDBHelper.POI_TABLE, selectQuery,  poiColumns[6]+ "=?", new String[] {"1"}, null, null, null);
		db=dbHelper.getReadableDatabase();
		ArrayList<POI> poiList=new ArrayList<POI>();
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
	/**
	 * Takes in the latitude and longitude and updates rather the POI should be shown or not.
	 * @param lat	latitude
	 * @param lon	longitude
	 * @param flag	1 for true, any other integer for false(prefer 0) 
	 */
	public void updatePOIFlag(double lat, double lon, int flag){
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		ContentValues values =new ContentValues();
		
		values.put(poiColumns[6], flag);
		db.update(StarvedRockDBHelper.POI_TABLE, values,  poiColumns[3]+ "=?"+" and "+poiColumns[4]+ "=?", new String[] {String.valueOf(lat), String.valueOf(lon)});
		db.close();
	}
	
	/**
	 * Private method to return a notification
	 * @param cursor
	 * @return
	 */
	private Notification cursorToNotification(Cursor cursor) {
		return new Notification(Notification.Type.valueOf(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));

	}

	/**
	 * Private method to return a POI.
	 * @param cursor
	 * @return
	 */
	private POI cursorToPoI(Cursor cursor) {
		return new POI(cursor.getString(0),POI.Type.valueOf( cursor.getString(1)),cursor.getDouble(2), cursor.getDouble(3), cursor.getString(4), cursor.getInt(5));

	}
}
