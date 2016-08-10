package com.djitouw.lists.backend.factories;

import com.djitouw.lists.backend.objects.movies.Movie;
import com.djitouw.lists.backend.objects.movies.MovieDetails;
import com.djitouw.lists.backend.provider.database.objects.MovieDB;
import com.djitouw.lists.backend.provider.database.objects.URDDB;
import com.djitouw.lists.backend.provider.omdb.objects.MovieOMDb;

/**
 * Factory that builds {@link Movie} and {@link MovieDetails}.
 * 
 * @author Djitouw
 *
 */
public interface MovieFactory {

	/**
	 * @param movieDB movie datas that come from the database
	 * @return the movie presented to the user
	 */
	Movie buildMovieDB(MovieDB movieDB, URDDB urdDB);
	
	/**
	 * @param rtMovie movie datas that come from OMDb
	 * @return the movie details presented to the user
	 */
	MovieDetails buildMovieDetailsOMDb(MovieOMDb rtMovie);
}
