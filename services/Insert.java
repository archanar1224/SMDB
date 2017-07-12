package com.IMDB.services;

import com.IMDB.beans.*;
import com.IMDB.dao.*;
import java.util.ArrayList;
import java.util.Date;

import com.db4o.ObjectContainer;



public class Insert
{
	public static void addMovie(ObjectContainer db, String movieID, String title, String language, int yearOfRelease, int duration, double rating, String plotSummary, ArrayList<String> genre, ArrayList<PersonMovie> crew, ArrayList<Review> reviews,
			ArrayList<Audio> songs, Image poster, Video trailer, ArrayList<Award> awards)
	{
		Movie movie = new Movie(movieID,title, language, yearOfRelease, duration, rating, plotSummary, genre, crew, reviews, songs, poster, trailer, awards);
		
		MovieDB moviedb = new MovieDB();
		moviedb.addMovie(db, movie);
	}
	
	
	public static void addPerson(ObjectContainer db, String personID, String name, Gender gender, Date dob, int height, String placeOfBirth,
			ArrayList<PersonMovie> role, ArrayList<PersonAudio> audio, ArrayList<Award> awards, Image photo)
	{
	//	Person person = new Person(personID, name, gender, dob, height, placeOfBirth, role, audio, awards, photo);
		
//		PersonDB persondb = new PersonDB();
	//	persondb.addPerson(db, person);
	}
	
}
