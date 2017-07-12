package com.IMDB.beans;

public class PersonAudio {
	Person person;
	Audio audio;
	String role;
	public PersonAudio()
	{
		
	}
	public PersonAudio(Person person, Audio audio, String role) {
		super();
		this.person = person;
		this.audio = audio;
		this.role = role;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Audio getAudio() {
		return audio;
	}
	public void setAudio(Audio audio) {
		this.audio = audio;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
