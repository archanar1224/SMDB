package com.IMDB.beans;


public class Video extends Multimedia
{
	private VideoType fileType;
	
	public Video()
	{
		super();
	}
	
	public Video(String link, String id, MultimediaType type, VideoType fileType)
	{
		super(link, id, type);
		this.fileType = fileType;
	}
	
	public VideoType getFileType() 
	{
		return fileType;
	}
	public void setFileType(VideoType fileType) 
	{
		this.fileType = fileType;
	}
	
}
