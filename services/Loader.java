package com.IMDB.services;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import com.IMDB.beans.Movie;
import com.IMDB.beans.Person;


public class Loader implements ServletContextListener
{

	public static ObjectContainer db;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener OODB destroyed");
		db.close();
	}

    //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0) 
	{
		//String DB4OFILENAME = "/home/ramesh/iiitb/2_sem/DM/Project/oodb/movie_test_delete1.db4o";
		String DB4OFILENAME = "/home/ramesh/maneesha/movie_test3.db4o";
		EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();

		configuration.common().objectClass(Movie.class).objectField("reviews").cascadeOnDelete(true);
		configuration.common().objectClass(Movie.class).objectField("crew").cascadeOnDelete(true);
		configuration.common().objectClass(Person.class).objectField("role").cascadeOnDelete(true);
		db = Db4oEmbedded.openFile(configuration, DB4OFILENAME);
		
		System.out.println("ServletContextListener of OODB started");

	}
}
