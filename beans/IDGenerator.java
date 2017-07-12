package com.IMDB.beans;

public class IDGenerator 
{
	private int currentMovieID;
	private int currentPersonID;
	private int currentMediaID;
	private int currentAwardID;
	
	public IDGenerator()
	{
		
	}

	public IDGenerator(int currentMovieID, int currnetPersonID, int currentMediaID, int currentAwardID) {
		super();
		this.currentMovieID = currentMovieID;
		this.currentPersonID = currnetPersonID;
		this.currentMediaID = currentMediaID;
		this.currentAwardID = currentAwardID;
	}

	public int getCurrentMovieID() {
		return currentMovieID;
	}

	public void setCurrentMovieID(int currentMovieID) {
		this.currentMovieID = currentMovieID;
	}

	public int getCurrentPersonID() {
		return currentPersonID;
	}

	public void setCurrentPersonID(int currnetPersonID) {
		this.currentPersonID = currnetPersonID;
	}

	public int getCurrentMediaID() {
		return currentMediaID;
	}

	public void setCurrentMediaID(int currentMediaID) {
		this.currentMediaID = currentMediaID;
	}

	public int getCurrentAwardID() {
		return currentAwardID;
	}

	public void setCurrentAwardID(int currentAwardID) {
		this.currentAwardID = currentAwardID;
	}
	
	
	
}
