# movie_catalog_SpringB_microservice
A sample Spring Boot microservice architecture, having three spring boot apps.
1) movie-catalog-service
Input->UserId || Output->movie list with details
2)movie-info-service
3)ratings-data-service

movie-catalog-service-> calls the other two microservices and returns a consolidated response.

It recieves UserId and calls ratings-data-service-> which returns the list of movies the user has watched
along with the ratings.
And then for each movieId, it calls the movie-info-service-> which returns the movie details.

Final response--
{
"id":"ArpanB",
"name":"Arpan Banerjee,
"movies":"[
{"id":"1","name":"XYZ","desc":"xxyyzz",rating:"5"},
{"id":"2","name":"ABC","desc":"aabbcc",rating:"4"},
...]"
}



TODO
# add more functionalities.
