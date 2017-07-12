package com.IMDB.beans;

import java.util.Date;

public class Review
{
	private String description;
	private String contributor;
	private Date date;
	public Review()
	{

	}
	public Review(String description, String contributor, Date date)
	{
		this.description = description;
		this.contributor = contributor;
		this.date = date;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String descprition) {
		this.description = descprition;
	}
	
	public String getContributor() {
		return contributor;
	}
	public void setContributor(String contributor) {
		this.contributor = contributor;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
