package com.java.micro.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.java.micro.moviecatalogservice.model.CatalogItem;
import com.java.micro.moviecatalogservice.model.Movie;
import com.java.micro.moviecatalogservice.model.Rating;
import com.java.micro.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

	@Autowired
	private RestTemplate restTemplate;
	
	//WebClient.Builder builder=new Builder();
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		
 
		UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratingsdata/user/44", UserRating.class);

	

		return userRating.getUserRating().stream().map((rating) -> {
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
			
			//Alternative WebClient way
//			Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/"+ rating.getMovieId())
//			.retrieve().bodyToMono(Movie.class).block();
			
			return new CatalogItem(movie.getName(), "Test Desc", rating.getRating());
		}).collect(Collectors.toList());

	}
}
