package com.djitouw.lists.backend.provider.omdb;

import com.djitouw.lists.backend.provider.omdb.objects.MovieOMDb;

/**
 * Provider that allows to retrieve information from OMDb.
 * 
 * @author Djitouw
 *
 */
public interface OMDbProvider {
	
	/**
	 * @param id the OMDb id of the movie
	 * @param title the title of the movie
	 * @return movie details from OMDb
	 */
	MovieOMDb getMovieDetails(String id, String title);

}
