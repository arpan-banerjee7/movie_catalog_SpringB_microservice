package com.java.micro.movieinfoservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.java.micro.movieinfoservice.model.Movie;
import com.java.micro.movieinfoservice.model.MovieSummary;


@RestController
@RequestMapping("/movies")
public class MovieResource {

	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	public RestTemplate restTemplate;
	
    @RequestMapping("/{movieId}")
    public Movie getCatalog(@PathVariable("movieId") String movieId) {
    	//https://api.themoviedb.org/3/movie/550?api_key=7773088b1166295626c3a9b88fe215cb"
    	
    	MovieSummary movieSummary=restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+ apiKey, MovieSummary.class);
        return new Movie(movieId, movieSummary.getOriginal_title(),movieSummary.getOverview());
        		
    }
}
