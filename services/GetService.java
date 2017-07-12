package com.IMDB.services;

import java.io.IOException;
import java.sql.SQLException;
import com.*;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.IMDB.beans.*;
import com.IMDB.dao.MovieDB;
import com.IMDB.dao.PersonDB;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

@Path("/get")
public class GetService 
{
	
	@Path("/movie")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getMovie(@FormParam("movie_id") String movieID) throws ClassNotFoundException
	{
		System.out.println("movieid is" + movieID);
		
		
			MovieDB moviedb = new MovieDB();
			Movie movie = moviedb.getMovie(Loader.db,movieID);
			System.out.println("movie id"+movieID);
			JSONObject data=null;
			
			
			try 
			{
				data= new JSONObject();
				data.put("movie_id", movieID);
				data.put("title", movie.getTitle());
				
				data.put("genre",movie.getGenre());
				data.put("plot_summary",movie.getPlotSummary());
				data.put("language",movie.getLanguage());
				//System.out.println("yoyoy" + movies.get(i).getLanguage());
				data.put("yearOfRelease",movie.getYearOfRelease());
				data.put("rating",movie.getRating());
				data.put("duration", movie.getDuration());
				data.put("rating",movie.getRating());
				data.put("poster", movie.getPoster().getLink());
				if(movie.getSongs()!=null)
				{
					String[] songs = new String[movie.getSongs().size()];
					for(int i=0;i<songs.length;i++)
						songs[i]=movie.getSongs().get(i).getLink();
					//System.out.println("song" + songs[0]);
					data.put("songs",songs);
				}
				else
				{
					data.put("songs", "");
				}
				
				
				data.put("trailer", movie.getTrailer().getLink());
				
				int crew_size=0;
				if(movie.getCrew()!=null)
				{
					crew_size = movie.getCrew().size();
					System.out.println("CREW SIZE : "+ crew_size);
				}
				String name[] = new String[crew_size];
				String photo[] = new String[crew_size];
				String id[] = new String[crew_size];
				String role[] = new String[crew_size];
				System.out.println("crew_size in  movies"+crew_size);
				
				for(int i=0;i<crew_size;i++)
				{
					System.out.println("crew details"+movie.getCrew().get(i).getPerson().getName());
					name[i] = movie.getCrew().get(i).getPerson().getName();
					photo[i] = movie.getCrew().get(i).getPerson().getPhoto().getLink();
					id[i] = movie.getCrew().get(i).getPerson().getPersonID();
					role[i]=movie.getCrew().get(i).getRole();
					
				}
				data.put("name",name);
				data.put("photo", photo);
				data.put("id",id);
				data.put("role",role);
				String json= data.toString();
				return json;
			} 
			catch (JSONException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}         
	
	
	@Path("/review")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getReview(@FormParam("movie_id") String movieID) 
	{
		
		try
		{
			MovieDB moviedb = new MovieDB();
			Movie movie = moviedb.getMovie(Loader.db, movieID);
			System.out.println("movieid in get review" + movieID);
			ArrayList<Review> reviews = movie.getReviews();
			
			JSONArray array = new JSONArray();
			JSONObject data=null;
			String poster = movie.getPoster().getLink();
			System.out.println("poster"+poster);
			for(int i=0;i<reviews.size();i++)
			{
				
				data= new JSONObject();
				
				data.put("author",reviews.get(i).getContributor());
				data.put("date", reviews.get(i).getDate());
				data.put("review", reviews.get(i).getDescription());
				   
				array.put(data);
				
			}
			JSONObject result = new JSONObject();
		    result.put("reviewData",array);
		    result.put("poster", poster);
		    String json= result.toString();
		    return json;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	@Path("/latestmovies")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getLatestMovies() throws ClassNotFoundException
	{
			
		MovieDB moviedb = new MovieDB();
		List<Movie> movies = moviedb.getLatestMovies(Loader.db);
		System.out.println("size is"+movies.size());
		JSONArray array = new JSONArray();
		JSONObject data=null;
		try 
		{
			for(int i=0;i<movies.size();i++)
			{
				System.out.println(" hello " + movies.get(i).getTitle());
				data= new JSONObject();
				System.out.println("two");
				
				data.put("picture",movies.get(i).getTitle());
				
				data.put("id", movies.get(i).getMovieID());
				data.put("language",movies.get(i).getLanguage());
				System.out.println("yoyoy" + movies.get(i).getLanguage());
				data.put("yearOfRelease",movies.get(i).getYearOfRelease());
				data.put("rating",movies.get(i).getRating());
				data.put("poster", movies.get(i).getPoster().getLink());
				   
				array.put(data);
				
			}
			JSONObject result = new JSONObject();
		    result.put("Data", array);
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
				
	
	@Path("/featuredpeople")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getFeaturedPeople() throws ClassNotFoundException
	{
		try
		{
			PersonDB peopledb = new PersonDB();
			List<Person> people = peopledb.getFeaturedPeople(Loader.db);
			System.out.println("size is " + people.size());
			JSONArray array = new JSONArray();
			JSONObject data=null;
			for(int i=0;i<people.size();i++)
			{
				data= new JSONObject();
				data.put("name",people.get(i).getName());
				data.put("id", people.get(i).getPersonID());
				data.put("dp", people.get(i).getPhoto().getLink());
				   
				array.put(data);
				
			}
			JSONObject result = new JSONObject();
		    result.put("Data", array);
		    String json= result.toString();
		
	        return json;

		}
		catch (JSONException e) 
		{
			e.printStackTrace();
			return null;
			
		}
	}

	
	@Path("/persondetails")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getPersonDetails(@FormParam("person_id") String personID) throws ClassNotFoundException
	{
		try
		{	
			System.out.println("FIRSTTTTTTTTTTTTTTTTTTTTT");
			PersonDB persondb = new PersonDB();
			Person person = persondb.getPerson(Loader.db, personID);
	
			System.out.println("ONEEEEEEEEEEEEEEEE");
			JSONObject data=null;
		
			data= new JSONObject();
			
			ArrayList<PersonAward> pAwards = person.getAwards();
			/*if(pAwards!=null)
			{
				System.out.println(" not null " + pAwards.size());
				System.out.println(pAwards.get(0).getMovie().getTitle());
			}
			else
			{
				System.out.println("NULL");
			}*/
			
			data.put("name",person.getName());
			data.put("id", person.getPersonID());
			data.put("gender",person.getGender() );
			Date d = person.getDob();
			Format formatter = new SimpleDateFormat("dd-MM-yyyy");
			String dob = formatter.format(d);
			data.put("dob", dob);
			System.out.println("TW");
			System.out.println("TWOOOOOOOOOOOOOOOOOOOOOOOO");
			data.put("pob", person.getPlaceOfBirth());
			if(pAwards!=null)
			{
				data.put("awards", pAwards.toString());
			}
			
			data.put("image", person.getPhoto());
			data.put("height",person.getHeight());
			data.put("dp",person.getPhoto().getLink());
			System.out.println(" photo : "+ person.getPhoto().getFileType().toString());
			System.out.println("THREEE");
			ArrayList<PersonMovie> movieList = person.getRole();
			int movieSize = 0;
			if(movieList!=null)
				movieSize=movieList.size();
			String id[] = new String[movieSize];
		    String title[] = new String[movieSize];
		    String poster[] = new String[movieSize];
		    String role[] = new String[movieSize];
			for(int i=0;i<movieSize;i++)
			{
				PersonMovie m = movieList.get(i);
				id[i] = m.getMovie().getMovieID();
				title[i] = m.getMovie().getTitle();
				poster[i] = m.getMovie().getPoster().getLink();
				role[i] = m.getRole();
			}
	  
			data.put("movie_id", id);
			data.put("poster", poster);
			data.put("title", title);
			data.put("role", role);
	
			
	        String json= data.toString();
	        System.out.println("LAST");
	        return json;	
		}
		catch(JSONException e) 
		{
			//e.printStackTrace();
			System.out.println(e.getCause() + " " + e.getMessage());
			return null;
			
		}
		
			
	}
	
	@Path("/awardsofmovie")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getAwardsOfMovie(@FormParam("movie_id") String movieID)
	{
		MovieDB moviedb = new MovieDB();
		ArrayList<Award> awards = moviedb.getAwards(Loader.db, movieID);
		System.out.println("in side award service " + movieID);
		try
		{
			JSONArray array = new JSONArray();
			JSONObject data=null;
			
			
			for(int i=0;i<awards.size();i++)
			{
				
				data= new JSONObject();
				
				data.put("awardName",awards.get(i).getAwardName());
				data.put("receivedOn", awards.get(i).getReceivedOn().toString());
				data.put("title", awards.get(i).getTitle());
				data.put("movie", awards.get(i).getMovie().getTitle());
				data.put("awardType", awards.get(i).getAwardType().toString());
				//person?
				if(awards.get(i).getAwardType().equals(AwardType.PERSON))
				{
					PersonAward pAward = (PersonAward) awards.get(i);
					data.put("person", pAward.getPerson().getName());
				}
				
				array.put(data);
				
			}
			JSONObject result = new JSONObject();
		    result.put("awardData",array);
		    
		    String json= result.toString();
		    return json;
		}
		catch(JSONException e) 
		{
			e.printStackTrace();
			return null;
			
		}
	
	}
	
	@Path("/awardsofperson")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getAwardsOfPerson(@FormParam("person_id") String personID)
	{
		PersonDB persondb = new PersonDB();
		ArrayList<PersonAward> awards = persondb.getAwards(Loader.db, personID);
		
		try
		{
			JSONArray array = new JSONArray();
			JSONObject data=null;
			
			
			for(int i=0;i<awards.size();i++)
			{
				
				data= new JSONObject();
				
				data.put("awardName",awards.get(i).getAwardName());
				data.put("receivedOn", awards.get(i).getReceivedOn().toString());
				data.put("title", awards.get(i).getTitle());
				data.put("movieName", awards.get(i).getMovie().getTitle());
				data.put("movieID", awards.get(i).getMovie().getMovieID());
				data.put("awardType", awards.get(i).getAwardType().toString());
				
				
				data.put("person", awards.get(i).getPerson().getName());
				
				
				array.put(data);
				
			}
			JSONObject result = new JSONObject();
		    result.put("awardData",array);
		    
		    String json= result.toString();
		    return json;
		}
		catch(JSONException e) 
		{
			e.printStackTrace();
			return null;
			
		}
	}
			 
	@Path("/allAwards")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllAwards()
	{
		return "";
	}	
	
	@Path("/allPersonAwards")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllPersonAwards()
	{
		return "";
		
	}
	
}
