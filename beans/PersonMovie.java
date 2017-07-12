package com.IMDB.beans;


public class PersonMovie 
{
	Person person;
	Movie movie;
	String role;
	public PersonMovie()
	{
		
	}
	public PersonMovie(Person person, Movie movie, String role) {
		super();
		this.person = person;
		this.movie = movie;
		this.role = role;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	


	
	
}
