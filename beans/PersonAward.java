package com.IMDB.beans;
import java.util.Date;


public class PersonAward extends Award
{
	Person person;
	public PersonAward(String awardID, String awardName, Date receivedOn, String title, Movie movie, AwardType awardType, Person person) 
	{
		super(awardID, awardName, receivedOn, title, movie, awardType);
		this.person = person;
	}
	public Person getPerson()
	{
		return person;
	}
	
	public void setPerson(Person person) 
	{
		this.person = person;
	}

}
