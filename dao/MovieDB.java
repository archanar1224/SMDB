package com.IMDB.dao;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.IMDB.beans.*;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;



public class MovieDB 
{
	
	public void addMovie(ObjectContainer db, Movie movie)
	{		
		db.store(movie);
		System.out.println("Stored " + movie);
	}

	public List<Movie> getLatestMovies(ObjectContainer db)
	{
		List<Movie> movies = new ArrayList<Movie>();
		int numberOfMovies = 4;
		
		ObjectSet result = db.queryByExample(Movie.class);
		if(result.size() < numberOfMovies)
		{
			numberOfMovies = result.size(); 
		}
		Calendar now = Calendar.getInstance();   // Gets the current date and time
		int currentYear = now.get(Calendar.YEAR); 
		int count=0;
		while(count<numberOfMovies)
		{
			result = db.queryByExample(new Movie(null, null, null, currentYear, 0, 0, null,
					null, null, null, null, null, null, null));
			System.out.println(result.size());
			while(result.hasNext()) 
			{
				Movie m = (Movie) result.next();
				movies.add(m);
				System.out.println(m.getTitle());
				count++;
				if(count==numberOfMovies)
				{
					break;
				}
				
			}
			if(count<numberOfMovies)
			{
				currentYear--;
			}
		}
		return movies;
		
	}
	
	public List<Movie> getAllMovies(ObjectContainer db)
	{
		List<Movie> movies = new ArrayList<Movie>();
		ObjectSet result = db.queryByExample(Movie.class);
		System.out.println(result.size());
		while(result.hasNext()) 
		{
			Movie m = (Movie) result.next();
			movies.add(m);
			System.out.println(m.getTitle());
		}
		
		return movies;
		
	}
	
	public Movie getMovie(ObjectContainer db, String movieID)
	{
		ObjectSet result = db.queryByExample(new Movie(movieID));
		
		Movie movie = (Movie) result.next();
		return movie;
	
	}
	public Movie getMovie(ObjectContainer db, String movieID, String movieName)
	{
		ObjectSet result = db.queryByExample(new Movie(movieID, movieName));
		Movie movie = (Movie) result.next();
		return movie;
	
	}

	
	public void displayMovie(ObjectContainer db, String movieID)
	{
		ObjectSet result = db.queryByExample(new Movie(movieID));
		
		System.out.println(result.size());
		
		Movie movie = (Movie) result.next();
		int awardSize = movie.getAwards().size();
		System.out.println(awardSize);
		System.out.println(movie.getAwards().get(awardSize-1).getTitle());
		System.out.println(movie.getReviews().size());
		System.out.println("After display");
		System.out.println(movie.getPoster().getLink());
		System.out.println(movie.getSongs().get(1).getLink());
		//System.out.println(result2);	
	}
	public void addPoster(ObjectContainer db, Image poster, String movieID)
	{
		ObjectSet result = db.queryByExample(new Movie(movieID));
		//ObjectSet result = db.queryByExample(new Movie(movieID,null,null,0,0,0.0,null,null,null,null,null,null,null,null));
		Movie movie = (Movie) result.next();
		movie.setPoster(poster);
		db.store(movie);	
		System.out.println("Added poster  for " + movie);
	}
	
	public Image getPoster(ObjectContainer db, String movieID)
	{
		ObjectSet result = db.queryByExample(new Movie(movieID));
		Movie movie = (Movie) result.next();
		
		return movie.getPoster();
	}
	
	public ArrayList<Award> getAwards(ObjectContainer db, String movieID)
	{
		ObjectSet result = db.queryByExample(new Movie(movieID));
		Movie movie = (Movie) result.next();
		return movie.getAwards();
	
	}
	
	public ArrayList<Audio> getSongs(ObjectContainer db, String movieID)
	{
		ObjectSet result = db.queryByExample(new Movie(movieID));
		Movie movie = (Movie) result.next();
		
		int size = movie.getSongs().size();
		System.out.println("size "+ size );
		System.out.println(movie.getSongs().get(size-1).getId());
		return movie.getSongs();
		
	}

	public void addTrailer(ObjectContainer db, Video trailer, String movieID)
	{
		ObjectSet result = db.queryByExample(new Movie(movieID));
		//ObjectSet result = db.queryByExample(new Movie(movieID,null,null,0,0,0.0,null,null,null,null,null,null,null,null));
		Movie movie = (Movie) result.next();
		movie.setTrailer(trailer);
		
		db.store(movie);
		System.out.println("Added trailer  for " + movie);
	}
	
	
	public void setCrew(ObjectContainer db, ArrayList<PersonMovie> crew,  String movieID)
	{
		ObjectSet result = db.queryByExample(new Movie(movieID));
		//ObjectSet result = db.queryByExample(new Movie(movieID,null,null,0,0,0.0,null,null,null,null,null,null,null,null));
		Movie movie = (Movie) result.next();
		movie.setCrew(crew);
		
		db.store(movie);
		System.out.println("stored crew for movie "+ movie);
	}
	
	public void addReviews(ObjectContainer db, ArrayList<Review> reviews, String movieID)
	{
		ObjectSet result = db.queryByExample(new Movie(movieID));
		//ObjectSet result = db.queryByExample(new Movie(movieID,null,null,0,0,0.0,null,null,null,null,null,null,null,null));
		Movie movie = (Movie) result.next();
		
		movie.setReviews(reviews);
		System.out.println("before in db "+ movie.getReviews().size());
		db.store(movie);		

		System.out.println("Added reviews  for " + movie);
	}
	
	public void addReview(ObjectContainer db, Review review, String movieID)
	{
		ObjectSet result = db.queryByExample(new Movie(movieID));
		//ObjectSet result = db.queryByExample(new Movie(movieID,null,null,0,0,0.0,null,null,null,null,null,null,null,null));
		Movie movie = (Movie) result.next();
		
		ArrayList<Review> reviews = movie.getReviews();
		
		if(reviews == null)
		{
			reviews = new ArrayList<Review>();
		}
		System.out.println("before review size " + reviews.size());
		reviews.add(review);
		movie.setReviews(reviews);
			
		db.store(movie);
		System.out.println("Added reviews  for " + movie);
	}
	
	/*public void deleteMovie(ObjectContainer db, String movieID)
	{
		ObjectSet result = db.queryByExample(new Movie(movieID));
		Movie movie = (Movie) result.next();
		db.delete(movie);
		
	}*/
	
	public ArrayList<PersonMovie> getCrew(ObjectContainer db, String movieID)
	{
		ObjectSet result = db.queryByExample(new Movie(movieID));
		Movie movie = (Movie) result.next();
		return movie.getCrew();
	}
	
	public void updateCrew(ObjectContainer db, String movieID, String personID)
	{
		ObjectSet result = db.queryByExample(new Movie(movieID));
		Movie movie = (Movie) result.next();
		
		System.out.println("Before updating crew : " + movie.getCrew().size());
		
		//result = db.queryByExample(PersonMovie.class);
		
		
		for(int i=0; i<movie.getCrew().size(); i++)
		{
			if(movie.getCrew().get(i).getPerson().getPersonID().equals(personID))
			{
				System.out.println("del matched");
				movie.getCrew().remove(i);
			}
		}
		
		System.out.println("after updating crew : " + movie.getCrew().size());

		movie.setCrew(movie.getCrew());
		db.store(movie);
	}
	
	public ArrayList<Person> deleteMovie(ObjectContainer db, String movieID)
	{
		ObjectSet result = db.queryByExample(new Movie(movieID));
		Movie movie = (Movie) result.next();
		
		ArrayList<PersonMovie> crew = movie.getCrew();
		ArrayList<Person> people = new ArrayList<Person>();
		if(crew!=null)
		{		
			for(int i=0; i<crew.size(); i++)
			{
				people.add(crew.get(i).getPerson());
			}
		}
		
		db.delete(movie);
		db.commit();
		System.out.println("after delete movie");
		
		// result = db.queryByExample(new Movie(movieID));
		 //movie = (Movie) result.next();
		//System.out.println("still there?? " + movie.getTitle());
		
		
		return people;
	}
	
	
	public void addSongs(ObjectContainer db, ArrayList<Audio> songs, String movieID)
	{
		ObjectSet result = db.queryByExample(new Movie(movieID));
		//ObjectSet result = db.queryByExample(new Movie(movieID,null,null,0,0,0.0,null,null,null,null,null,null,null,null));
		Movie movie = (Movie) result.next();
		
		movie.setSongs(songs);
		System.out.println("before in db "+ movie.getSongs().size());
		db.store(movie);
		
		System.out.println("Added song  for " + movie);
	}
	
	
	public void addCrew(ObjectContainer db, PersonMovie pm, String movieID)
	{
		ObjectSet result = db.queryByExample(new Movie(movieID));
		Movie movie = (Movie) result.next();
		ArrayList<PersonMovie> crew = movie.getCrew();
		boolean alreadyThere = false;
		if(crew == null)
		{
			crew = new ArrayList<PersonMovie>();
		}
		System.out.println("before crew size " + crew.size());
		
		for(int i=0; i<crew.size(); i++)
		{
			if(crew.get(i).getPerson().equals(pm.getPerson()) && crew.get(i).getRole().equals(pm.getRole()))
			{
				System.out.println("already there ");
				alreadyThere = true;
				break;
			}
		}
		if(!alreadyThere)
		{
			crew.add(pm);
			movie.setCrew(crew);
			System.out.println("not there");
			db.store(movie);
		}
		
		
		System.out.println("Added person  for " + movie);
	}
	
	public void addAward(ObjectContainer db, Award award, String movieID)
	{
		ObjectSet result = db.queryByExample(new Movie(movieID));
		//ObjectSet result = db.queryByExample(new Movie(movieID,null,null,0,0,0.0,null,null,null,null,null,null,null,null));
		Movie movie = (Movie) result.next();
		ArrayList<Award> awards = movie.getAwards();
		if(awards == null)
		{
			awards = new ArrayList<Award>();
		}
		System.out.println("before award size " + awards.size());
		awards.add(award);
		movie.setAwards(awards);
			
		db.store(movie);
		System.out.println("Added award  for " + movie);
		
	}
	
	public void addAward(ObjectContainer db, Award award, Movie movie)
	{
		
		ArrayList<Award> awards = movie.getAwards();
		if(awards == null)
		{
			awards = new ArrayList<Award>();
		}
		System.out.println("before award size " + awards.size());
		awards.add(award);
		movie.setAwards(awards);
			
		db.store(movie);
		System.out.println("Added award  for " + movie);
		
	}
	
	public static void listResult(ObjectSet result)
	{
		System.out.println(result.size());
		while(result.hasNext()) 
		{
			Movie m = (Movie) result.next();
			System.out.println(m);
			System.out.println(m.getTitle());
		}
	}
		
		
}
