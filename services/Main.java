package com.IMDB.services;

import java.io.IOException;
import java.util.List;

import com.IMDB.beans.Award;
import com.IMDB.beans.IDGenerator;
import com.IMDB.beans.Movie;
import com.IMDB.beans.Person;
import com.IMDB.dao.IDGeneratorDB;
import com.IMDB.dao.MovieDB;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;



public class Main {

	public static void main(String[] args) 
	{
		MovieDB moviedb = new MovieDB();
		IDGeneratorDB idDB = new IDGeneratorDB();

		
		String DB4OFILENAME = "/home/ramesh/maneesha/movie_test3.db4o";
		EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();

		configuration.common().objectClass(Movie.class).objectField("reviews").cascadeOnDelete(true);
		configuration.common().objectClass(Movie.class).objectField("crew").cascadeOnDelete(true);
		configuration.common().objectClass(Person.class).objectField("role").cascadeOnDelete(true);
		ObjectContainer db = Db4oEmbedded.openFile(configuration, DB4OFILENAME);
		
		List <Award> allawards = db.query(Award.class); 
		 System.out.println("ALL AWARDS " +allawards.size());
		 for (int i = 0; i < allawards.size(); i++) 
		 {
			 System.out.println(allawards.get(i).getMovie().getMovieID());
		 }
		 
		 Movie movie49 = moviedb.getMovie(db, null, "Mr Perfect");
		 System.out.println(movie49.getTitle() + " " + movie49.getCrew().size());
		 
		
		
		 //String newMovieID = idDB.getNextMovieID(db);
		// System.out.println("MOVIE ID " + newMovieID);
		// idDB.updateNextMovieID(db, "Movie0");
		//Movie m = moviedb.getMovie(db);
		//System.out.println("RESULT " +m.getLanguage()+ " " + m.getTitle() + m.getPoster().getLink());
			
		
	}

}
