package com.IMDB.beans;
import java.util.Date;


public class Award
{
	String awardID;
	String awardName;
	Date receivedOn;
	String title;
	Movie movie;
	AwardType awardType;
	
	public Award()
	{
		
	}
	public Award(String awardID)
	{
		this.awardID = awardID;
	}
	public Award(String awardID, String awardName, Date receivedOn, String title, Movie movie, AwardType awardType)
	{
		super();
		this.awardID = awardID;
		this.awardName = awardName;
		this.receivedOn = receivedOn;
		this.title = title;
		this.movie = movie;
		this.awardType = awardType;
	}
	
	public AwardType getAwardType() {
		return awardType;
	}
	public void setAwardType(AwardType awardType) {
		this.awardType = awardType;
	}
	public String getAwardID() {
		return awardID;
	}



	public void setAwardID(String awardID) {
		this.awardID = awardID;
	}

	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public String getAwardName() {
		return awardName;
	}
	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}
	public Date getReceivedOn() {
		return receivedOn;
	}
	public void setReceivedOn(Date receivedOn) {
		this.receivedOn = receivedOn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	

}
