package com.IMDB.dao;

import com.IMDB.beans.Award;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
public class AwardDB 
{
	public void addAward(ObjectContainer db, Award award)
	{
		db.store(award);		
	}
}
