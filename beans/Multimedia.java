package com.IMDB.beans;



public class Multimedia 
{
	private String link;
	private String id;
	private MultimediaType type;
	
	public Multimedia()
	{
		
	}
	public Multimedia(String id)
	{
		this.id = id;
	}
	public Multimedia(String link, String id, MultimediaType type) 
	{
		this.link = link;
		this.id = id;
		this.type = type;
	}
	
	public MultimediaType getType() {
		return type;
	}
	public void setType(MultimediaType type) {
		this.type = type;
	}
	public String getLink() 
	{
		return link;
	}
	public void setLink(String link) 
	{
		this.link = link;
	}
	public String getId() 
	{
		return id;
	}
	public void setId(String id) 
	{
		this.id = id;
	}
	
	
}
