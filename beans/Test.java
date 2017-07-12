package com.IMDB.beans;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Test {

	public static void main(String[] args) 
	{
		
		// TODO Auto-generated method stub
		
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded
				.newConfiguration(), "/home/ramesh/db4o_test3");
				
		try 
		{
			// do something with db4o
			// storeFirstPilot
			Pilot pilot1 = new Pilot("Michael Schumacher", 100);
			db.store(pilot1);
			System.out.println("Stored " + pilot1);
			
			// storeSecondPilot
			Pilot pilot2 = new Pilot("Rubens Barrichello", 99);
			db.store(pilot2);
			System.out.println("Stored " + pilot2);
			
			/*
			// retrieveAllPilotQBE
			Pilot proto = new Pilot(null, 0);
			ObjectSet result = db.queryByExample(proto);
			listResult(result);
			
			
			// Or retrieveAllPilots
			ObjectSet result2 = db.queryByExample(Pilot.class);
			listResult(result2);
			
			// OR
			 List <Pilot> pilots = db.query(Pilot.class); 
			 System.out.println(pilots.get(0).getName());
			 */
			 //OR
			// retrievePilotByName
			 Pilot proto3 = new Pilot("Michael Schumacher", 0);
			 ObjectSet result3 = db.queryByExample(proto3);
			 System.out.println("here");
			 listResult(result3);
			 System.out.println("YY "+proto3);
			 
			// retrievePilotByExactPoints
			/* Pilot proto4 = new Pilot(null, 100);
			 ObjectSet result4 = db.queryByExample(proto4);
			 listResult(result4);*/
		} 
		finally 
		{
			db.close();
		}
		

	}

	public static void listResult(ObjectSet result)
	{
		System.out.println(result.size());
		while(result.hasNext()) {
		System.out.println(result.next());
		
	}
		}
	
}
