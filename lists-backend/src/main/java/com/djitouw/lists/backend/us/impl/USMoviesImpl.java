package com.djitouw.lists.backend.us.impl;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.djitouw.lists.backend.ms.MSMovies;
import com.djitouw.lists.backend.objects.movies.Movie;
import com.djitouw.lists.backend.objects.movies.MovieDetails;
import com.djitouw.lists.backend.us.USMovies;

/**
 * User Service for movies.
 */
@Path("movies")
@Service
public class USMoviesImpl implements USMovies {

	private static final Logger LOGGER = Logger.getLogger(USMoviesImpl.class);
	
	private MSMovies msMovies;

    /**
     * @return list of movies
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Movie> getList() {
    	LOGGER.info("GET /movies");
    	return msMovies.getList();
    }

    /**
     * @return details of one movie
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public MovieDetails getDetails(@PathParam("id") String id) {
    	LOGGER.info("GET /movies/" + id);
		return msMovies.getDetails(id);
    }

	public void setMsMovies(MSMovies msMovies) {
		this.msMovies = msMovies;
	}
    
}
