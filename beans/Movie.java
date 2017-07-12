package com.IMDB.beans;
import java.util.ArrayList;
import java.util.Date;


public class Movie 
{
	private String movieID;
	private String title;
	private String language;
	private int yearOfRelease;
	private int duration;
	private double rating;
	private String plotSummary;
	private ArrayList<String> genre;
	private ArrayList<PersonMovie> crew;
	private ArrayList<Review> reviews;

	private ArrayList<Audio> songs;
	private Image poster;
	private Video trailer;
	private ArrayList<Award> awards;

	public Movie()
	{
		this.genre = new ArrayList<String>();
		this.crew = new ArrayList<PersonMovie>();
		this.reviews = new ArrayList<Review>();
		this.songs = new ArrayList<Audio>();
		this.awards = new ArrayList<Award>();
	}
	public Movie(String movieID)
	{
		this.movieID = movieID;
		
		this.genre = new ArrayList<String>();
		this.crew = new ArrayList<PersonMovie>();
		this.reviews = new ArrayList<Review>();
		this.songs = new ArrayList<Audio>();
		this.awards = new ArrayList<Award>();
	}
	public Movie(String movieID, String title)
	{
		this.movieID = movieID;
		this.title = title;
		this.genre = new ArrayList<String>();
		this.crew = new ArrayList<PersonMovie>();
		this.reviews = new ArrayList<Review>();
		this.songs = new ArrayList<Audio>();
		this.awards = new ArrayList<Award>();
	}
	public Movie(String movieID, String title, String language, int yearOfRelease, int duration, double rating,
			String plotSummary, ArrayList<String> genre, ArrayList<PersonMovie> crew, ArrayList<Review> reviews,
			ArrayList<Audio> songs, Image poster, Video trailer, ArrayList<Award> awards) {
		super();
		this.movieID = movieID;
		this.title = title;
		this.language = language;
		this.yearOfRelease = yearOfRelease;
		this.duration = duration;
		this.rating = rating;
		this.plotSummary = plotSummary;
		this.genre = genre;
		this.crew = crew;
		this.reviews = reviews;
		this.songs = songs;
		this.poster = poster;
		this.trailer = trailer;
		this.awards = awards;
		
	}



	public String getMovieID() {
		return this.movieID;
	}



	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}


	
	
	
	public String getTitle() {
		return this.title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getLanguage() {
		return this.language;
	}



	public void setLanguage(String language) {
		this.language = language;
	}



	public int getYearOfRelease() {
		return this.yearOfRelease;
	}



	public void setYearOfRelease(int yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}



	public int getDuration() {
		return this.duration;
	}



	public void setDuration(int duration) {
		this.duration = duration;
	}



	public double getRating() {
		return this.rating;
	}



	public void setRating(double rating) {
		this.rating = rating;
	}



	public String getPlotSummary() {
		return this.plotSummary;
	}



	public void setPlotSummary(String plotSummary) {
		this.plotSummary = plotSummary;
	}



	public ArrayList<String> getGenre() {
		return this.genre;
	}



	public void setGenre(ArrayList<String> genre) {
		this.genre = genre;
	}



	public ArrayList<PersonMovie> getCrew() {
		return this.crew;
	}



	public void setCrew(ArrayList<PersonMovie> crew) {
		this.crew = crew;
	}



	public ArrayList<Review> getReviews() {
		return this.reviews;
	}



	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}



	public ArrayList<Audio> getSongs() {
		return this.songs;
	}



	public void setSongs(ArrayList<Audio> songs) {
		this.songs = songs;
	}



	public Image getPoster() {
		return this.poster;
	}



	public void setPoster(Image poster) {
		this.poster = poster;
	}



	public Video getTrailer() {
		return this.trailer;
	}



	public void setTrailer(Video trailer) 
	{
		this.trailer = trailer;
	}



	public ArrayList<Award> getAwards() {
		//System.out.println("HELP "+ this.awards.size());
		return this.awards;
	}



	public void setAwards(ArrayList<Award> awards) {
		this.awards = awards;
	}

	
}