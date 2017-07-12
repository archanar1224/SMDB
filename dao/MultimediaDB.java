package com.IMDB.dao;

import com.IMDB.beans.*;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class MultimediaDB 
{
	public Audio getAudio(ObjectContainer db,String audioID)
	{
		ObjectSet result = db.queryByExample(new Audio(audioID));
		Audio audio = (Audio) result.next();
		return audio;
	}
}
