package com.IMDB.dao;

import com.IMDB.beans.IDGenerator;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;


public class IDGeneratorDB 
{
	public void storeIDGenerator(ObjectContainer db, IDGenerator idGen)
	{
		db.store(idGen);
	}
	public String getNextMovieID(ObjectContainer db)
	{
		int nextMovieID;
		ObjectSet result = db.queryByExample(new IDGenerator());
		IDGenerator idGen = (IDGenerator) result.next();
		int currentMovieID = idGen.getCurrentMovieID();
		if(currentMovieID == 0)
		{
			nextMovieID = 1;
		}
		else
		{
			 nextMovieID = currentMovieID + 1;
		}
		return "Movie" + nextMovieID;
	}
	
	
	public String getNextPersonID(ObjectContainer db)
	{
		int nextPersonID;
		ObjectSet result = db.queryByExample(new IDGenerator());
		IDGenerator idGen = (IDGenerator) result.next();
		int currentPersonID = idGen.getCurrentPersonID();
		if(currentPersonID == 0)
		{
			nextPersonID = 1;
		}
		else
		{
			nextPersonID = currentPersonID + 1;
		}
		return "Person" + nextPersonID;
	}
	
	
	public String getNextMediaID(ObjectContainer db)
	{
		int nextMediaID;
		ObjectSet result = db.queryByExample(new IDGenerator());
		IDGenerator idGen = (IDGenerator) result.next();
		int currentMediaID = idGen.getCurrentMediaID();
		if(currentMediaID == 0)
		{
			nextMediaID = 1;
		}
		else
		{
			nextMediaID = currentMediaID + 1;
		}
		return "Media" + nextMediaID;
	}
	
	public String getNextAwardID(ObjectContainer db)
	{
		int nextAwardID;
		ObjectSet result = db.queryByExample(new IDGenerator());
		IDGenerator idGen = (IDGenerator) result.next();
		int currentAwardID = idGen.getCurrentAwardID();
		if(currentAwardID == 0)
		{
			nextAwardID = 1;
		}
		else
		{
			nextAwardID = currentAwardID + 1;
		}
		return "Award" + nextAwardID;
	}
	
	public void updateNextMovieID(ObjectContainer db, String movieIDString)
	{
		ObjectSet result = db.queryByExample(new IDGenerator());
		IDGenerator idGen = (IDGenerator) result.next();
		//Get only number
		int movieID = Integer.parseInt(movieIDString.substring(5));
		idGen.setCurrentMovieID(movieID);
		db.store(idGen);
		
	}
	
	public void updateNextPersonID(ObjectContainer db, String personIDString)
	{
		ObjectSet result = db.queryByExample(new IDGenerator());
		IDGenerator idGen = (IDGenerator) result.next();
		//Get only number
		int personID = Integer.parseInt(personIDString.substring(6));
		idGen.setCurrentPersonID(personID);
		db.store(idGen);
		
	}
	
	public void updateNextMediaID(ObjectContainer db, String mediaIDString)
	{
		ObjectSet result = db.queryByExample(new IDGenerator());
		IDGenerator idGen = (IDGenerator) result.next();
		//Get only number
		int mediaID = Integer.parseInt(mediaIDString.substring(5));
		idGen.setCurrentMediaID(mediaID);
		db.store(idGen);
		
	}
	
	public void updateNextAwardID(ObjectContainer db, String awardIDString)
	{
		ObjectSet result = db.queryByExample(new IDGenerator());
		IDGenerator idGen = (IDGenerator) result.next();
		//Get only number
		int awardID = Integer.parseInt(awardIDString.substring(5));
		idGen.setCurrentAwardID(awardID);
		db.store(idGen);
		
	}
}
