package com.IMDB.beans;
import java.util.*;


public class Person
{
	private String personID;
	private String name;
	private Gender gender;
	private Date dob;
	private int height;
	private String placeOfBirth;
	private ArrayList<PersonMovie> role;
	private ArrayList<PersonAudio> audio;
	private ArrayList<PersonAward> awards;
	private Image photo;
	
	public Person()
	{
		this.role = new ArrayList<PersonMovie>();
		this.audio = new ArrayList<PersonAudio>();
		this.awards = new ArrayList<PersonAward>();
	}
	public Person(String personID)
	{
		this.personID = personID;
		this.role = new ArrayList<PersonMovie>();
		this.audio = new ArrayList<PersonAudio>();
		this.awards = new ArrayList<PersonAward>();
	}
	public Person(String personID, String name)
	{
		this.personID = personID;
		this.name = name;
		this.role = new ArrayList<PersonMovie>();
		this.audio = new ArrayList<PersonAudio>();
		this.awards = new ArrayList<PersonAward>();
	}
	

	public Person(String personID, String name, Gender gender, Date dob, int height, String placeOfBirth,
			ArrayList<PersonMovie> role, ArrayList<PersonAudio> audio, ArrayList<PersonAward> awards, Image photo) {
		super();
		this.personID = personID;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.height = height;
		this.placeOfBirth = placeOfBirth;
		this.role = role;
		this.audio = audio;
		this.awards = awards;
		this.photo = photo;
	}
	public String getPersonID() {
		return personID;
	}
	public void setPersonID(String personID) {
		this.personID = personID;
	}
	public ArrayList<PersonMovie> getRole() {
		return role;
	}
	public void setRole(ArrayList<PersonMovie> role) {
		this.role = role;
	}
	public ArrayList<PersonAudio> getAudio() {
		return audio;
	}
	public void setAudio(ArrayList<PersonAudio> audio) {
		this.audio = audio;
	}
	
	public Image getPhoto()
	{
		return this.photo;
	}
	
	public void setPhoto(Image photo)
	{
		this.photo = photo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	public ArrayList<PersonAward> getAwards() {
		return awards;
	}
	public void setAwards(ArrayList<PersonAward> awards) {
		this.awards = awards;
	}
}
