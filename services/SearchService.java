package com.IMDB.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.IMDB.beans.Movie;
import com.IMDB.beans.Person;
import com.IMDB.dao.MovieDB;
import com.IMDB.dao.PersonDB;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

@Path("/search")
public class SearchService 
{

	@Path("/moviesearchresult")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String search() throws ClassNotFoundException
	{
		try 
		{			
			MovieDB moviedb = new MovieDB();
			PersonDB persondb=new PersonDB();
			List<Movie> movies = moviedb.getAllMovies(Loader.db);
			List<Person> person = persondb.getPeople(Loader.db);
			System.out.println("size is " + movies.size());
			JSONArray array = new JSONArray();
			JSONObject data=null;
				
			for(int i=0;i<movies.size();i++)
			{
				data= new JSONObject();
				data.put("picture",movies.get(i).getTitle());
				data.put("id", movies.get(i).getMovieID());
				data.put("poster", movies.get(i).getPoster().getLink());
				   
				array.put(data);
				
			}
			JSONArray array1 = new JSONArray();
			for(int i=0;i<person.size();i++)
			{
				data= new JSONObject();
				
				data.put("name",person.get(i).getName());
				
				data.put("personid", person.get(i).getPersonID());
				data.put("person_poster", person.get(i).getPhoto().getLink());
				   
				array1.put(data);
				
			}
			JSONObject result = new JSONObject();
		    result.put("Data", array);
		    result.put("Data1", array1);
		    String json= result.toString();
			return json;
		
		}
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
							
	}	

	
}