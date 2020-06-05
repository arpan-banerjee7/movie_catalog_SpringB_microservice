package com.java.micro.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.java.micro.moviecatalogservice.model.CatalogItem;
import com.java.micro.moviecatalogservice.model.UserRating;
import com.java.micro.moviecatalogservice.services.MovieInfo;
import com.java.micro.moviecatalogservice.services.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

	@Autowired
	private MovieInfo movieInfo;
	
	@Autowired
	private UserRatingInfo userRatingInfo;
	
	//WebClient.Builder builder=new Builder();
	@RequestMapping("/{userId}")
	
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		UserRating userRating = userRatingInfo.getUserRating(userId);
		return userRating.getUserRating().stream().map((rating) ->movieInfo.getCatalogItem(rating))
				.collect(Collectors.toList());

	}
	
	//no longer needed as we have implemented granularity
//	public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
//		return Arrays.asList(new CatalogItem("No movie","",0));
//	}
}
