package com.java.micro.movieinfoservice.resources;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.micro.movieinfoservice.model.Movie;

@RestController
@RequestMapping("/movie")
public class MovieResource {

    @RequestMapping("/{movieId}")
    public List<Movie> getCatalog(@PathVariable("movieId") String userId) {
        return Collections.singletonList(new Movie("Don", "Test Desc"));
    }
}
