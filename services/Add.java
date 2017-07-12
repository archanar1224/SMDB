package com.IMDB.services;
import com.IMDB.beans.*;
import com.IMDB.dao.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;


public class Add 
{
	/*public static void main(String args[])
	{
		String DB4OFILENAME = "/home/maneesha/dm/oodb/movie_test2.db4o";
		EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
		ObjectContainer db = Db4oEmbedded.openFile(configuration, DB4OFILENAME);
		addPerson(db);
		System.out.println("in main after add");
	}*/
	public static void addPerson(ObjectContainer db)
	{
		
		try 
		{
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			Date dob = formatter.parse("20/09/1923");
			String personID = "P21";
			//String movieID = "M4";
			Person person = new Person(personID,"Nag Rao", Gender.MALE, dob, 160, "India", null, null, null, null);
			PersonDB persondb = new PersonDB();
			persondb.addPerson(db, person);
			//MovieDB moviedb = new MovieDB();
					
			
			//Movie m = moviedb.getMovie(db, movieID);
			//PersonMovie pm1 = new PersonMovie(person, m, "Actress");
			
			//PersonMovieDB pmdb = new PersonMovieDB();
			//pmdb.addPersonMovie(db, pm1);
			
			// Update movie to add this actor
		//	moviedb.addCrew(db, pm1,movieID);
			
			// Update actor to add this movie
			//persondb.addRole(db, pm1, personID);
			
			
			//Photo 
			String path = "nagrao.png";
			String photoID = "PH21";
			float size = 10;
			Image photo = new Image(path, photoID, MultimediaType.IMAGE, ImageType.PNG);
			//update person to add photo
			persondb.addPhoto(db, photo, personID);
			
			// -----------------------------------------------------------------------------------------------------------------------------------

			Date dob2 = formatter.parse("29/08/1959");
			String personID2 = "P22";
			Person person2 = new Person(personID2,"Nagarjuna", Gender.MALE, dob2, 170, "India", null, null, null, null);
			persondb.addPerson(db, person2);

			String path2 = "nagarjuna.png";
			String photoID2 = "PH22";
			float size2 = 10;
			Image photo2 = new Image(path2, photoID2, MultimediaType.IMAGE, ImageType.PNG);
			//update person to add photo
			persondb.addPhoto(db, photo2, personID2);


			// -----------------------------------------------------------------------------------------------------------------------------------

			Date dob3 = formatter.parse("23/11/1986");
			String personID3 = "P23";
			Person person3 = new Person(personID3,"Chaitanya", Gender.MALE, dob3, 165, "India", null, null, null, null);
			persondb.addPerson(db, person3);

			String path3 = "chaitanya.png";
			String photoID3 = "PH23";
			float size3 = 10;
			Image photo3 = new Image(path3, photoID3, MultimediaType.IMAGE, ImageType.PNG);
			//update person to add photo
			persondb.addPhoto(db, photo3, personID3);


				// -----------------------------------------------------------------------------------------------------------------------------------

			Date dob4 = formatter.parse("08/04/1994");
			String personID4 = "P24";
			Person person4 = new Person(personID4,"Akhil", Gender.MALE, dob4, 168, "India", null, null, null, null);
			persondb.addPerson(db, person4);

			String path4 = "akhil.png";
			String photoID4 = "PH24";
			float size4 = 10;
			Image photo4 = new Image(path4, photoID4, MultimediaType.IMAGE, ImageType.PNG);
			//update person to add photo
			persondb.addPhoto(db, photo4, personID4);
			
			
		} 
		
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void addMovie(ObjectContainer db)
	{
	
		
		ArrayList<String> genre = new ArrayList<String>();
		String movieID = "M9";
		genre.add("fantasy");
		genre.add("drama");
		Movie movie = new Movie(movieID,"Manam", "Telugu", 2014, 163, 8.3, "family story", genre, null, null, null, null, null, null);
		//movie.addReview("good","Arch",new Date(2014));
		//movie.addReview("nice graphics","Hari",new Date(1999));
		MovieDB moviedb = new MovieDB();
		moviedb.addMovie(db, movie);
		
		// Adding audio and video and poster
		
		//poster
		String path = "manam.png";
		String posterID = "PH20";
		float size = 20;
		Image photo = new Image(path, posterID, MultimediaType.IMAGE, ImageType.PNG);
		moviedb.addPoster(db, photo, movieID);

		//video 
		
		
		
	
			
		System.out.println("Stored " + movie);

					
	}
	public static void addAward(ObjectContainer db)
	{
		MovieDB movieDB = new MovieDB();
		PersonDB personDB = new PersonDB();
		AwardDB awardDB = new AwardDB();
		Movie m = movieDB.getMovie(db, "M4");
		//Award award2 = new Award("A8", "Kerala state film award", new Date(2015), "Best Movie", m);
		//awardDB.addAward(db, award2);
		
		//update movie to add award
		//movieDB.addAward(db, award2, "M4");
		
		
		
		/*//4th award -> personaward, actually needs to come from UI
		Person person = personDB.getPerson(db, "P1");
		Award award4 = new PersonAward("A4", "Oscar",  new Date(1998), "Best director", m, person);
		//Update person to add this award
		awardDB.addAward(db, award4);
		personDB.addAward(db, award4, "P1");
		movieDB.addAward(db, award4, "M1");*/
		
		/*
		// Add award to song1
		MultimediaDB multimediaDB = new MultimediaDB();
		String songID = "S1";
		Audio song = multimediaDB.getAudio(db, songID);
		Award award5 = new MusicAward("A5", "filmfare", new Date(2002), "best song", m, song);
		// add this award to movie
		awardDB.addAward(db, award5);
		movieDB.addAward(db, award5, "M1");
		// TODO -- should we add to audio class??*/
		
		
	}
	
	
	
}
