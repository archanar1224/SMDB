package com.IMDB.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import com.IMDB.beans.Movie;
import com.IMDB.beans.Person;
import com.IMDB.beans.PersonMovie;
import com.IMDB.dao.MovieDB;
import com.IMDB.dao.PersonDB;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;


@Path("/delete")
public class DeleteService 
{
	
	
	@Path("/movie")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteMovie(@FormParam("movie_id") String movieID) throws ClassNotFoundException
	{
		try
		{
			System.out.println("movieid is"+movieID);
		
			MovieDB moviedb = new MovieDB();
			PersonDB persondb = new PersonDB();
			ArrayList<Person> people = moviedb.deleteMovie(Loader.db, movieID);
			if(people!=null)
			{
				for(int i=0;i<people.size();i++)
				{
					//call an update function on each person
					System.out.println("person name : " + people.get(i).getName());
					persondb.updateRole(Loader.db, people.get(i).getPersonID(), movieID);
				}
			}

			JSONObject id = new JSONObject();
			
			
			id.put("movie_id",moviedb);
			
			
			return id.toString();
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
		
	}
	
	@Path("/person")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String deletePerson(@FormParam("person_id") String personID) throws ClassNotFoundException
	{
		
		try
		{
			PersonDB persondb = new PersonDB();
			MovieDB moviedb = new MovieDB();
			//call del function and complete the service
			ArrayList<Movie> movies = persondb.deletePerson(Loader.db, personID);
			
			//ArrayList<Person> people = moviedb.deleteMovie(Loader.db, movieID);
			if(movies!=null)
			{
				for(int i=0;i<movies.size();i++)
				{
					//call an update function on each movie he/she was a part of
					System.out.println("movie name : " + movies.get(i).getTitle());
					moviedb.updateCrew(Loader.db, movies.get(i).getMovieID(), personID);
					
					ArrayList<PersonMovie> newCrew = moviedb.getCrew(Loader.db, movies.get(i).getMovieID());
					if(newCrew != null)
					{
						System.out.println("in del , new crew size " + newCrew.size());
					}
					
				}
			}
			
			System.out.println("after del person");
			JSONObject id = new JSONObject();
			id.put("person_id",personID);
			return id.toString();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 	
	}

}
