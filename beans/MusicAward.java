package com.IMDB.beans;
import java.util.Date;

public class MusicAward extends Award
{
	Audio audio;
	public MusicAward(String awardID, String awardName, Date receivedOn, String title, Movie movie, AwardType awardType, Audio audio) 
	{
		super(awardID, awardName, receivedOn, title, movie, awardType);
		this.audio = audio;
	}

	public Audio getAudio()
	{
		return audio;
	}
	
	public void setAudio(Audio audio) 
	{
		this.audio = audio;
	}

}

