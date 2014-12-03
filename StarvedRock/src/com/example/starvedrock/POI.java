package com.example.starvedrock;
/**
 * POI class used for point of interests. 
 * @author josh
 *
 */
public class POI 
{
	/*
	 * enum Type used for the type of point of interest. 
	 *
	 */
	public	static enum Type{PARKING, HISTORICAL, GEOLOGY, VEGETATION, RESTROOMS, TRAILS, VISITATION_CENTER, CAMPGROUND, DINING, LODGING, RANGER_STATION};

	
	/*	This is just set up for pictures. Picture is not implemented at this point.
	Example code: http://stackoverflow.com/questions/9357668/how-to-store-image-in-sqlite-database
	*/
private	byte[] picture;
private	String name;
private	double latitude;
private	double longitude;
private	String note;
private Type type;
private boolean flag;

/**
 * Takes in the name, the type, the latitude, the longitude, information about the poi, and rather 1 or 0 to show on map.
 * @param aName	Name of POI
 * @param aType Type of POI using the enum POI.Type
 * @param aLat Latitude of POI
 * @param aLong Longitude of POI
 * @param aNote Information about the poi
 * @param i Used for displaying markers, 1 means show, 0 means don't. This is a number because of how the database stores it.
 */
public POI(String aName, POI.Type aType,double aLat, double aLong, String aNote, int i){
	setPicture(null);
	setName(aName);
	type=aType;
	setLatitude(aLat);
	setLongitude(aLong);
	setNote(aNote);
	if(i==1){
		flag=true;
	}else
	{
		flag=false;
	}
	
	
	
}

/**
 * Returns a boolean on rather the POI should be shown or not.
 * @return
 */
public boolean isFlag() {
	return flag;
}

public void setFlag(boolean flag) {
	this.flag = flag;
}

/**
 *
 * @return the name
 */
public String getName() {
	return name;
}

/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}
/**
 * Takes in the string of POI.Type and returns the POI.Type
 * @param string
 * @return
 */
public static POI.Type getType(String string)
{
	Type aType = null;
	if(string.equalsIgnoreCase("PARKING"))
			aType=Type.PARKING;
	else if(string.equalsIgnoreCase("HISTORICAL"))
			aType=Type.HISTORICAL;
	else if(string.equalsIgnoreCase("GEOLOGY"))
			aType=Type.GEOLOGY;
	else if(string.equalsIgnoreCase("VEGETATION"))
			aType=Type.VEGETATION;
	else if(string.equalsIgnoreCase("RESTROOMS"))
			aType=Type.RESTROOMS;
	else if(string.equalsIgnoreCase("TRAILS"))
		aType=Type.TRAILS; 
	else if(string.equalsIgnoreCase("VISITATION CENTER"))
			aType=Type.VISITATION_CENTER; 
	else if(string.equalsIgnoreCase("CAMPGROUND"))
			aType=Type.CAMPGROUND;
	else if(string.equalsIgnoreCase("DINING"))
		aType=Type.DINING; 
	else if(string.equalsIgnoreCase("LODGING"))
		aType=Type.LODGING;
	else if(string.equalsIgnoreCase("RANGER STATION"))
		aType=Type.RANGER_STATION;
	
	return aType;
}

/**
 * Returns the POI.Type as a string. This is used because some of the types have spaces which aren't allowed in enums.
 * @return
 */
public String getTypeToString()
{
	Type string=type;
	String aType = null;
	if(string.equals(Type.PARKING))
			aType="Parking";
	else if(string.equals(Type.HISTORICAL))
			aType="HISTORICAL";
	else if(string.equals(Type.GEOLOGY))
			aType="GEOLOGY";
	else if(string.equals(Type.VEGETATION))
			aType="VEGETATION";
	else if(string.equals(Type.RESTROOMS))
			aType="RESTROOMS";
	else if(string.equals(Type.TRAILS))
		aType="TRAILS"; 
	else if(string.equals(Type.VISITATION_CENTER))
			aType="VISITATION CENTER"; 
	else if(string.equals(Type.CAMPGROUND))
			aType="CAMPGROUND";
	else if(string.equals(Type.DINING))
		aType="DINING"; 
	else if(string.equals(Type.LODGING))
		aType="LODGING";
	else if(string.equals(Type.RANGER_STATION))
		aType="Ranger Station";
	
	return aType;
}


/**
 * @return the latitude
 */
public double getLatitude() {
	return latitude;
}

/**
 * @param latitude the latitude to set
 */
public void setLatitude(double latitude) {
	this.latitude = latitude;
}

/**
 * @return the longitude
 */
public double getLongitude() {
	return longitude;
}

/**
 * @param longitude the longitude to set
 */
public void setLongitude(double longitude) {
	this.longitude = longitude;
}

/**
 * @return the note
 */
public String getNote() {
	return note;
}

/**
 * @param note the note to set
 */
public void setNote(String note) {
	this.note = note;
}

/**
 * @return the type
 */
public Type getType() {
	return type;
}

/**
 * @param type the type to set
 */
public void setType(Type type) {
	this.type = type;
}

/**
 * @return the picture
 */
public byte[] getPicture() {
	return picture;
}

/**
 * @param picture the picture to set
 */
public void setPicture(byte[] picture) {
	this.picture = picture;
}
}
