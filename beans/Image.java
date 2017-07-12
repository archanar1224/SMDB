package com.IMDB.beans;


public class Image extends Multimedia 
{
	private ImageType fileType;
	public Image()
	{
		super();		
	}
	public Image(String link, String id, MultimediaType type, ImageType fileType) 
	{
		super(link, id, type);
		this.fileType = fileType;
	}
	public ImageType getFileType() 
	{
		return fileType;
	}

	public void setFileType(ImageType fileType) 
	{
		this.fileType = fileType;
	}
}
