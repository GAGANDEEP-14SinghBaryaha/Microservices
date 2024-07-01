package com.example.movie_data_service.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie_data_service.model.Rating;
import com.example.movie_data_service.model.UserRating;

@RestController
@RequestMapping("/ratingData")
public class MovieRatingDataResource {

	@RequestMapping("/{movieId}")
	public Rating getRatingInfo(@PathVariable("movieId") String movieId) {
		return new Rating(movieId,4);
		
	}
	
	@RequestMapping("/user/{userId}")
	public UserRating getRating(@PathVariable("userId") String userId) {
		List<Rating> ratingList = Arrays.asList(
				new Rating("1234",4),
				new Rating("5678",3)
				);
		
		UserRating userRating = new UserRating();
		userRating.setUserRating(ratingList);
		
		return userRating;
		
	}
}
