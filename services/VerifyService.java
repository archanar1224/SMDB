package com.IMDB.services;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.IMDB.beans.Movie;
import com.IMDB.beans.Person;
import com.IMDB.dao.MovieDB;
import com.IMDB.dao.PersonDB;

@Path("/verify")
public class VerifyService 
{
	
	@Path("/person")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String verifyPerson(@QueryParam("person_name") String personName) throws ClassNotFoundException
	{
		System.out.println("personname is " + personName);
	
		JSONObject job = new JSONObject();
		try
		{
			PersonDB persondb = new PersonDB();
			List<Person> personList = persondb.getPeople(Loader.db);
			for(int i=0;i<personList.size();i++)
			{
				if(personList.get(i).getName().equalsIgnoreCase(personName))
				{
					System.out.println("matched movie " + personName);
					return job.put("person_name", personName).toString();
				}
			}
				
			personName=null;
			return job.put("person_name",personName).toString();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return("exception");
			
		}


		
		
	}

	
	
	
	@Path("/movie")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String verifyMovie(@QueryParam("movie_name") String movieName) throws ClassNotFoundException
	{
		
		JSONObject job = new JSONObject();
		try
		{
			MovieDB moviedb = new MovieDB();
			List<Movie> movieList = moviedb.getAllMovies(Loader.db);
			for(int i=0;i<movieList.size();i++)
			{
				if(movieList.get(i).getTitle().equalsIgnoreCase(movieName))
				{
					System.out.println("matched movie"+movieName);
					return job.put("movie_name", movieName).toString();
				}
			}
			movieName = null;
			return job.put("movie_name",movieName).toString();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return("exception");
			
		}
		
		
		
	}
}
