package com.IMDB.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

import com.IMDB.beans.*;

public class PersonMovieDB 
{
	public void addPersonMovie(ObjectContainer db, PersonMovie pm)
	{
		db.store(pm);
	}
}
