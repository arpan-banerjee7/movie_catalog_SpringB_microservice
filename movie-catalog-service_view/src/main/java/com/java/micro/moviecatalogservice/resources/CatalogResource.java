package com.java.micro.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.java.micro.moviecatalogservice.model.CatalogItem;
import com.java.micro.moviecatalogservice.model.Movie;
import com.java.micro.moviecatalogservice.model.Rating;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		RestTemplate restTemplate = new RestTemplate();
 
		List<Rating> ratings = Arrays.asList(new Rating("1234", 5), new Rating("3456", 4)

		);

		return ratings.stream().map((rating) -> {
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), "Test Desc", rating.getRating());
		}).collect(Collectors.toList());

	}
}
