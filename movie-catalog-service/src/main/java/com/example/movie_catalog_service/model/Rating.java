package com.example.movie_catalog_service.model;

public class Rating {
	
	private String movieId;
	private int rating;
	
	public Rating() {
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

	
}
