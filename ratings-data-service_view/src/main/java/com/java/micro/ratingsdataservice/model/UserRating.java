package com.java.micro.ratingsdataservice.model;

import java.util.List;

public class UserRating {
	private List<Rating> userRating;

	public UserRating() {
		super();
	}

	public List<Rating> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<Rating> userRating) {
		this.userRating = userRating;
	}
	
	
	

}
