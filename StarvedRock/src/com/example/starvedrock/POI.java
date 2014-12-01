package com.example.starvedrock;

public class POI 
{
	public	static enum Type{PARKING, HISTORICAL, GEOLOGY, VEGETATION, RESTROOMS};

	
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
	
public boolean isFlag() {
	return flag;
}

public void setFlag(boolean flag) {
	this.flag = flag;
}

/**
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

public static POI.Type getType(String string)
{
	Type aType = null;
	if(string.equalsIgnoreCase("PARKING"))
			aType=Type.PARKING;
	else if(string.equalsIgnoreCase("HISTORICAL"))
			aType=Type.HISTORICAL;
	else if(string.equalsIgnoreCase("GEOLOGOY"))
			aType=Type.GEOLOGY;
	else if(string.equalsIgnoreCase("VEGETATION"))
			aType=Type.VEGETATION;
	else if(string.equalsIgnoreCase("RESTROOMS"))
			aType=Type.RESTROOMS;
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
