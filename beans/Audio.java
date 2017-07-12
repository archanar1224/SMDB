package com.IMDB.beans;



public class Audio extends Multimedia
{
	private AudioType fileType;
	public Audio()
	{
		super();
	}
	
	public Audio(String id)
	{
		super(id);
		
	}
	public Audio(String link, String id, MultimediaType type, AudioType fileType)
	{
		super(link, id, type);
		this.fileType = fileType;
	}
	
	public AudioType getFileType() 
	{
		return fileType;
	}
	public void setFileType(AudioType fileType) 
	{
		this.fileType = fileType;
	}
}
