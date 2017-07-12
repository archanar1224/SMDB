package com.IMDB.services;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
import com.IMDB.dao.IDGeneratorDB;
import com.IMDB.dao.MovieDB;
import com.IMDB.dao.PersonDB;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import sun.java2d.pipe.SpanShapeRenderer.Simple;

@Path("/add")
public class AddService 
{

	@Path("/movie")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String addMovie(@QueryParam("jsonData") String jsonData) throws ClassNotFoundException, JSONException
	{
		IDGeneratorDB idDB = new IDGeneratorDB();
		System.out.println("inside service add movie");
		
		System.out.println("jsondata"+jsonData);		 
		JSONObject jObject  = new JSONObject(jsonData); // json
		String title = jObject.getString("title"); 
		String language = jObject.getString("language");
		System.out.println("lang "+ language);
		int releaseyear = Integer.parseInt(jObject.getString("releasedate"));
		int duration = Integer.parseInt(jObject.getString("duration"));
		double rating = Double.parseDouble(jObject.getString("rating"));
		String plot = jObject.getString("plot");
		JSONArray genre = jObject.getJSONArray("genre");
		ArrayList<String> genreList = new ArrayList<String>();
		for(int i=0;i<genre.length();i++)
		{
			genreList.add(genre.get(i).toString());
			System.out.println(genre.get(i).toString());
		}
		String posterlink = jObject.getString("poster");
		
		if(posterlink.contains("fakepath"))
		{
			System.out.println("YES FAKEPATH");
			String[] splitResult = posterlink.split("\\\\");
			posterlink = splitResult[splitResult.length-1];
		}
		
		
		JSONArray songs = jObject.getJSONArray("songs");
		//System.out.println(songs.get(0));
		System.out.println(songs);
		ArrayList<Audio> songsList = new ArrayList<Audio>();
		for(int i=0;i<songs.length();i++)
		{
			String audioID = idDB.getNextMediaID(Loader.db);
			idDB.updateNextMediaID(Loader.db, audioID);
			String audioName = songs.get(i).toString();
			
			
			if(audioName.contains("fakepath"))
			{
				System.out.println("YES FAKEPATH");
				String[] splitResult = audioName.split("\\\\");
				audioName = splitResult[splitResult.length-1];
			}
			
			System.out.println("SONG NAME !! " + audioName);
			String audioSplits[] = audioName.split("\\.");
			
			
			String audioFileTypeString =  audioSplits[audioSplits.length-1].toUpperCase();
			
			AudioType audioFileType = AudioType.valueOf(audioFileTypeString);
			Audio newAudio = new Audio(songs.get(i).toString(), audioID, MultimediaType.AUDIO, audioFileType);
			songsList.add(newAudio);
		}
		String movieID  = idDB.getNextMovieID(Loader.db);
		idDB.updateNextMovieID(Loader.db, movieID);
		String trailerlink = jObject.getString("trailer");
		
		if(trailerlink.contains("fakepath"))
		{
			System.out.println("YES FAKEPATH");
			String[] splitResult = trailerlink.split("\\\\");
			trailerlink = splitResult[splitResult.length-1];
		}
		
		String posterID = idDB.getNextMediaID(Loader.db);
		idDB.updateNextMediaID(Loader.db, posterID);
		
		String[] posterSplitResult = posterlink.split("\\.");
		String posterlinkString = posterSplitResult[posterSplitResult.length-1];
		
		ImageType posterType = ImageType.valueOf(posterlinkString.toUpperCase());
		Image poster = new Image(posterlink, posterID, MultimediaType.IMAGE, posterType);
		
		
		String videoID = idDB.getNextMediaID(Loader.db);
		idDB.updateNextMediaID(Loader.db, videoID);
		
		String[] trailerSplitResult = trailerlink.split("\\.");
		String trailerlinkString = trailerSplitResult[trailerSplitResult.length-1];
		
		
		VideoType videoType = VideoType.valueOf(trailerlinkString.toUpperCase());
		
		Video trailer = new Video(trailerlink, videoID, MultimediaType.VIDEO ,videoType);
		Movie movie = new Movie(movieID,title,language,releaseyear,duration,rating,plot,genreList,null,null,songsList,poster,trailer,null);
		MovieDB moviedb = new MovieDB();
		moviedb.addMovie(Loader.db,movie);
		
		
		JSONObject id = new JSONObject();
		
		
		id.put("movie_id",movieID);
		
		
		return id.toString();
		
	}
	
	@Path("/person")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String addPerson(@QueryParam("jsonData") String jsonData) throws ClassNotFoundException
	{
		
		try 
		{
			System.out.println("inside service add person");
			IDGeneratorDB idDB = new IDGeneratorDB();
			System.out.println("jsondata"+jsonData);		 
			JSONObject jObject;
			
				jObject = new JSONObject(jsonData);
	
			String name = jObject.getString("name"); 
			Gender gender = Gender.valueOf(jObject.getString("gender")); 
			String dobString = jObject.getString("dob");
			System.out.println("date string "+ dobString);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dob = format.parse(dobString);
				
			System.out.println("dob : "+ dob);
				
			int height = Integer.parseInt(jObject.getString("height"));
			String placeOfBirth =  jObject.getString("placeOfBirth"); 
			String photoString = jObject.getString("photo"); 
			
			if(photoString.contains("fakepath"))
			{
				System.out.println("YES FAKEPATH");
				String[] splitResult = photoString.split("\\\\");
				photoString = splitResult[splitResult.length-1];
			}
			
			String photoSplitResult[] =  photoString.split("\\.");
			
			String photoTypeString =  photoSplitResult[photoSplitResult.length-1].toUpperCase();
			ImageType photoType = ImageType.valueOf(photoTypeString);
			
			String photoID = idDB.getNextMediaID(Loader.db);
			idDB.updateNextMediaID(Loader.db, photoID);
			
			Image photo = new Image(photoString, photoID,MultimediaType.IMAGE, photoType);
	
	
			String personID = idDB.getNextPersonID(Loader.db);
			idDB.updateNextPersonID(Loader.db, personID);
			
			Person person = new Person(personID,name, gender, dob, height, placeOfBirth, null, null, null, photo);
			PersonDB persondb = new PersonDB();
			persondb.addPerson(Loader.db, person);
				
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

	
	@Path("/review")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void addReview(@QueryParam("jsonData") String jsonData) throws ClassNotFoundException, JSONException
	{
		System.out.println("inside add review service");
		
		System.out.println("jsondata" + jsonData);		 
		JSONObject jObject  = new JSONObject(jsonData); // json
		String author = jObject.getString("author"); 
		String description = jObject.getString("review");
		String movieID = jObject.getString("movie_id");
		
		MovieDB moviedb = new MovieDB();
		Movie movie = moviedb.getMovie(Loader.db, movieID);
		Review review = new Review(description, author, new Date());
		moviedb.addReview(Loader.db, review, movieID);
		
	}


	@Path("/award")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String addAward(@QueryParam("jsonData") String jsonData) throws ClassNotFoundException, JSONException
	{
		try 
		{
			boolean isValid = true;
			boolean flag = false;
			System.out.println("inside add award service");
			System.out.println("jsondata" + jsonData);
			JSONObject jObject  = new JSONObject(jsonData); // json
			String awardName = jObject.getString("awardName");
			
			String receivedOnString = jObject.getString("receivedOn");
			System.out.println("receivedOn string " + receivedOnString);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date receivedOn;
			
			receivedOn = format.parse(receivedOnString);
			Calendar cal = Calendar.getInstance();
		    cal.setTime(receivedOn);
		    int receivedYear = cal.get(Calendar.YEAR);

			
			System.out.println("receivedOn : " + receivedOn);
			
			
			
			String title = jObject.getString("title");
			String movieName = jObject.getString("movieName");
			String personName = jObject.getString("personName");
			
			//String songName = jObject.getString("songName");
			MovieDB moviedb = new MovieDB();
			Movie movie = moviedb.getMovie(Loader.db, null, movieName);
			System.out.println("movie id in add award " + movieName + " "+ movie.getMovieID() + " " + movie.getTitle());
			
			if(movie.getYearOfRelease() > receivedYear)
			{
				isValid = false;
				String message = "Check the date and try again.";
				JSONObject id = new JSONObject();	
				id.put("flag",isValid);
				id.put("message", message);
				return id.toString();
			}
			
			
			IDGeneratorDB idDB = new IDGeneratorDB();
			String awardID = idDB.getNextAwardID(Loader.db);
			idDB.updateNextAwardID(Loader.db, awardID);
			
			Award award;
			if(!(personName == null || personName.equals("")))
			{
				//  person award
				PersonDB persondb = new PersonDB();
				Person person = persondb.getPerson(Loader.db, null, personName);
				
				ArrayList<PersonMovie> crew = movie.getCrew();
				if(crew!=null)
				{
					System.out.println("crew not null  " + crew.size());
					for(int i=0; i<crew.size(); i++)
					{
						System.out.println("crew : "+ crew.get(i).getPerson().getName());
						if(crew.get(i).getPerson().equals(person) &&   title.toLowerCase().contains(crew.get(i).getRole().toLowerCase()) )
						{
							// CAST EXISTS - CORRECT , break
							flag = true;
							break;
						}
					}
					if(!flag)
					{
						// no cast , Not valid
						isValid = false;
						String message = "Cast not in the movie. Please try again.";
						JSONObject id = new JSONObject();	
						id.put("flag",isValid);
						id.put("message", message);
						return id.toString(); 
						
					}
					
				}
				award = new PersonAward(awardID, awardName, receivedOn, title, movie, AwardType.PERSON, person);
				
				
				// Add this award to person
				//persondb.addAward(db, award, personID);
				persondb.addAward(Loader.db, (PersonAward) award, person.getPersonID());
			}
			else
			{
				award = new Award(awardID, awardName, receivedOn, title, movie, AwardType.MOVIE);
			}
			
			// Add this award to movie
			moviedb.addAward(Loader.db, award, movie.getMovieID());
			
			JSONObject id = new JSONObject();	
			id.put("flag",isValid);
			id.put("message", "Added award successfully");
			return id.toString();

		}
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Path("/cast")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String addCast(@FormParam("json") String jsonData, @FormParam("movie_id") String movieID) throws ClassNotFoundException, JSONException
	{
		
		System.out.println("inside add cast service");
		System.out.println("jsondata" + jsonData);
		JSONArray jArray  = new JSONArray(jsonData); // json
		
		System.out.println("movie " + movieID);
		
		PersonDB persondb = new PersonDB();
		MovieDB moviedb = new MovieDB();
		
		
		for(int i=0; i<jArray.length(); i++)
		{
			JSONObject jObject = (JSONObject) jArray.get(i);
			String personID = jObject.getString("personid");
			String role = jObject.getString("role");
			//System.out.println("LLLL " + personID + " " + role );
			Movie movie = moviedb.getMovie(Loader.db, movieID);
			Person person = persondb.getPerson(Loader.db, personID);
			PersonMovie pm = new PersonMovie(person, movie, role);
			moviedb.addCrew(Loader.db, pm, movieID);
			persondb.addRole(Loader.db, pm, personID);
		}
		
		JSONObject id = new JSONObject();
		
		
		id.put("movie_id",movieID);
		
		
		return id.toString();
		
		
	}
	@Path("/role")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String addRole(@FormParam("json") String jsonData, @FormParam("person_id") String personID) throws ClassNotFoundException, JSONException
	{
		
		System.out.println("inside add role service");
		System.out.println("jsondata" + jsonData);
		JSONArray jArray  = new JSONArray(jsonData); // json
		
		System.out.println("personID " + personID);
		
		PersonDB persondb = new PersonDB();
		MovieDB moviedb = new MovieDB();
		
		
		for(int i=0; i<jArray.length(); i++)
		{
			JSONObject jObject = (JSONObject) jArray.get(i);
			String movieID = jObject.getString("movieid");
			String role = jObject.getString("role");
			//System.out.println("LLLL " + personID + " " + role );
			Movie movie = moviedb.getMovie(Loader.db, movieID);
			Person person = persondb.getPerson(Loader.db, personID);
			PersonMovie pm = new PersonMovie(person, movie, role);
			moviedb.addCrew(Loader.db, pm, movieID);
			persondb.addRole(Loader.db, pm, personID);
		}
		
		JSONObject id = new JSONObject();
		
		
		id.put("person_id",personID);
		
		
		return id.toString();
		
	}
}
