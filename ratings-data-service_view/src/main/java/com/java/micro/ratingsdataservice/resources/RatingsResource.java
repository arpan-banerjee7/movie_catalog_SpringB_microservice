package com.java.micro.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.micro.ratingsdataservice.model.Rating;
import com.java.micro.ratingsdataservice.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }
    
    @RequestMapping("/user/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId) {
    	List<Rating> ratings = Arrays.asList(new Rating("1234", 5), new Rating("3456", 4));
    	UserRating userRating = new UserRating();
    	userRating.setUserRating(ratings);
    	return userRating;
    	
    }
}