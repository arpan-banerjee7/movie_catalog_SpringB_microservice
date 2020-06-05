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
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

	@Autowired
	private RestTemplate restTemplate;
	
	//WebClient.Builder builder=new Builder();
	@RequestMapping("/{userId}")
	
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		UserRating userRating = getUserRating(userId);
		return userRating.getUserRating().stream().map((rating) ->getCatalogItem(rating))
				.collect(Collectors.toList());

	}

	@HystrixCommand(fallbackMethod="getFallbackCatalogItem")
	private CatalogItem getCatalogItem(Rating rating) {
		Movie movie= restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getName(),movie.getDescription(), rating.getRating());
	}
	
	private CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie not found","", rating.getRating());
	}

	@HystrixCommand(fallbackMethod="getFallbackUserRating")
	private UserRating getUserRating(@PathVariable("userId") String userId) {
		return restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/"+userId, UserRating.class);
	}
	
	private UserRating getFallbackUserRating(@PathVariable("userId") String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setUserRating(Arrays.asList(new Rating("0", 0)));
		return userRating;
	}
	
	//no longer needed as we have implemented granularity
//	public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
//		return Arrays.asList(new CatalogItem("No movie","",0));
//	}
}
