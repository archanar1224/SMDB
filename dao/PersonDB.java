package com.IMDB.dao;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;


import com.IMDB.beans.*;

public class PersonDB 
{
	public void addPerson(ObjectContainer db, Person person)
	{	
		db.store(person);	
	}
	public Person getPerson(ObjectContainer db, String personID)
	{
		ObjectSet result = db.queryByExample(new Person(personID));
		Person person = (Person) result.next();
		return person;
	}
	public Person getPerson(ObjectContainer db, String personID, String personName)
	{
		ObjectSet result = db.queryByExample(new Person(personID, personName));
		Person person = (Person) result.next();
		return person;
	}
	

	
	public void displayPerson(ObjectContainer db, String personID)
	{
		ObjectSet result = db.queryByExample(new Person(personID));
		System.out.println("PERSON SIZE " +result.size());
		Person person = (Person) result.next();
		System.out.println(person.getName());	
		//System.out.println(person.getAwards().size());
		//System.out.println(person.getPhoto().getLink());
	}
	
	public List<Person> getPeople(ObjectContainer db)
	{
		List<Person> people = new ArrayList<Person>();
		ObjectSet result = db.queryByExample(Person.class);
		
		System.out.println(result.size());
		while(result.hasNext()) 
		{
			Person p = (Person) result.next();
			people.add(p);
			
		}
		
		return people;
	}
	
	public ArrayList<PersonAward> getAwards(ObjectContainer db, String personID)
	{
		ObjectSet result = db.queryByExample(new Person(personID));
		Person person = (Person) result.next();
		return person.getAwards();
	
	}
	
	public List<Person> getFeaturedPeople(ObjectContainer db)
	{
		List<Person> people = new ArrayList<Person>();
		int numberOfPeople = 4;
		ObjectSet result = db.queryByExample(Person.class);
		System.out.println("People list size "+ result.size());
		if(result.size() < numberOfPeople)
		{
			numberOfPeople = result.size(); 
		}
		int count=0;
		Random r = new Random();
		int low = 0;
		int high = result.size();
		
		while(count<numberOfPeople) 
		{
			int index = r.nextInt(high-low) + low;
			System.out.println("Index "+ index);
			
			Person p = (Person) result.get(index);
			if(!people.contains(p))
			{
				people.add(p);
				count++;
			}
			
			
		}
		
		return people;
	}
	
	public void addPhoto(ObjectContainer db, Image photo, String personID)
	{
		ObjectSet result = db.queryByExample(new Person(personID));
		Person person = (Person) result.next();
		person.setPhoto(photo);
		
		db.store(person);
		System.out.println("Added photo  for " + person);
	}
	
	public void addRole(ObjectContainer db, PersonMovie pm, String personID)
	{
		boolean alreadyThere = false;
		ObjectSet result = db.queryByExample(new Person(personID));
		Person person = (Person) result.next();
		ArrayList<PersonMovie> roles = person.getRole();
		if(roles == null)
		{
			roles = new ArrayList<PersonMovie>();
		}
		System.out.println("before roles size " + roles.size());
			
		for(int i=0; i<roles.size(); i++)
		{
			if(roles.get(i).getMovie().equals(pm.getMovie()) && roles.get(i).getRole().equals(pm.getRole()))
			{
					System.out.println("already there ");
					alreadyThere = true;
					break;
			}
		}
		if(!alreadyThere)
		{
			roles.add(pm);
			person.setRole(roles);
			System.out.println("not there");
			db.store(person);
		}	
		System.out.println("Added role  for " + person);		
	}
	
	public void addAward(ObjectContainer db, PersonAward award, String personID)
	{
		ObjectSet result = db.queryByExample(new Person(personID));
		Person person = (Person) result.next();
		ArrayList<PersonAward> awards = person.getAwards();
		if(awards == null)
		{
			awards = new ArrayList<PersonAward>();
		}
		System.out.println("before award size " + awards.size());
		awards.add(award);
		person.setAwards(awards);
			
		db.store(person);
		System.out.println("Added award for person " + person);
	}
	
	public void addAward(ObjectContainer db, PersonAward award, Person person)
	{
		//ObjectSet result = db.queryByExample(new Movie(movieID));
		//ObjectSet result = db.queryByExample(new Movie(movieID,null,null,0,0,0.0,null,null,null,null,null,null,null,null));
		//Movie movie = (Movie) result.next();
		ArrayList<PersonAward> awards = person.getAwards();
		if(awards == null)
		{
			awards = new ArrayList<PersonAward>();
		}
		System.out.println("before award size " + awards.size());
		awards.add(award);
		person.setAwards(awards);
			
		db.store(person);
		System.out.println("Added award  for " + person);
		
	}
	
	public void setRole(ObjectContainer db, ArrayList<PersonMovie> roles,  String personID)
	{
		ObjectSet result = db.queryByExample(new Person(personID));
		Person person = (Person) result.next();
		person.setRole(roles);
		db.store(person);
		System.out.println("Added roles for person " + person);
		
	}
	
	
	public void updateRole(ObjectContainer db, String personID, String movieID)
	{
		ObjectSet result = db.queryByExample(new Person(personID));
		Person person = (Person) result.next();
		
		System.out.println("Before updating role : " + person.getRole().size());
		
		//result = db.queryByExample(PersonMovie.class);
		
		
		for(int i=0; i<person.getRole().size(); i++)
		{
			if(person.getRole().get(i).getMovie().getMovieID().equals(movieID))
			{
				System.out.println("del matched");
				person.getRole().remove(i);
			}
		}
		
		/*for(int i=0; i<person.getRole().size(); i++)
		{	
			System.out.println("movie title " + person.getRole().get(i).getMovie().getTitle());
			result = db.queryByExample(new Movie(person.getRole().get(i).getMovie().getMovieID()));
			Movie delMovie = (Movie) result.next();
			System.out.println(delMovie.getTitle());
			
		}
		person.getRole().removeAll(Collections.singleton(null)); */
		
		System.out.println("after updating role : " + person.getRole().size());

		person.setRole(person.getRole());
		db.store(person);
		
	}
	
	public ArrayList<Movie> deletePerson(ObjectContainer db, String personID)
	{
		ObjectSet result = db.queryByExample(new Person(personID));
		Person person = (Person) result.next();
		
		ArrayList<PersonMovie> roles = person.getRole();
		ArrayList<Movie> movies = new ArrayList<Movie>();
		if(roles!=null)
		{		
			for(int i=0; i<roles.size(); i++)
			{
				movies.add(roles.get(i).getMovie());
			}
		}
		
		db.delete(person);
		db.commit();
		System.out.println("after delete person");
		
		// result = db.queryByExample(new Movie(movieID));
		 //movie = (Movie) result.next();
		//System.out.println("still there?? " + movie.getTitle());
		
		
		return movies;
	}
	
	/*public void deletePerson(ObjectContainer db, String personID)
	{
		ObjectSet result = db.queryByExample(new Person(personID));
		Person person = (Person) result.next();
		db.delete(person);
		
	}*/
}
