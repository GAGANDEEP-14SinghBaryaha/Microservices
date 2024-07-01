package com.example.movie_catalog_service.resource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.movie_catalog_service.model.CatalogItem;
import com.example.movie_catalog_service.model.Movie;
import com.example.movie_catalog_service.model.Rating;
import com.example.movie_catalog_service.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	//@RequestMapping(method = { RequestMethod.GET })
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
 		//get all rated movie IDs
 		
		//RestTemplate restTemplate = new RestTemplate();
		//restTemplate.getForObject("http://localhost:6969/movies/foo" , Movie.class);
 		//WebClient.Builder builder = WebClient.builder();
 		
 		
		UserRating ratings = restTemplate.getForObject("http://movie-data-service/ratingData/user/"+ userId , UserRating.class);
		
		//ParameterizedTypeReference<List<Rating>> = 
		System.out.println(discoveryClient.getInstances(userId));
		
		return ratings.getUserRating().stream().map(rating->{
			//Movie movie = restTemplate.getForObject("http://localhost:6969/movies/"+ rating.getMovieId() , Movie.class);
			//for each movie ID, call movie info service and get details
//			Movie movie = webClientBuilder.build()
//			.get().uri("http://localhost:6969/movies/"+ rating.getMovieId())
//			.retrieve()
//			.bodyToMono(Movie.class).block();
			
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+ rating.getMovieId() , Movie.class);
			//put them all together
			return new CatalogItem(movie.getName(), "Test", rating.getRating());
		}).collect(Collectors.toList());
		
		
		
//		//return Collections.singletonList(
//				new CatalogItem("Transformers", "Test", 4));
		
		
		
	}

}
