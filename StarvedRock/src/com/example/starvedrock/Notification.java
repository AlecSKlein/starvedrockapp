package com.example.starvedrock;


public class Notification {
	public static enum Type{WARNING, UPDATE, EVENT}
	private String text;
	private String date;
	private String expires;
	private  Type type;
	
	 public static void main(String[] args)
	  {
		//Notification.Type t= Notification.Type.WARNING;
		 Notification test=new Notification(Notification.Type.WARNING, "", "yyyy-MM-dd HH:mm:ss", "");
	    System.out.println(test.getDate());
	  }
	
	public Notification(Notification.Type aType, String info, String aDate, String aExpire )
	{
		type=aType;
		setText(info);
		date=aDate;
		setExpires(aExpire);
	}
	
	public Type getType()
	{
		return type;
		
	}
	
	public String getDate()
	{
		return date;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the expires
	 */
	public String getExpires() {
		return expires;
	}

	/**
	 * @param expires the expires to set
	 */
	public void setExpires(String expires) {
		this.expires = expires;
	}
	
	
}
