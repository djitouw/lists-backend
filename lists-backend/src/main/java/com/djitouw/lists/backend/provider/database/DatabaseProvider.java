package com.djitouw.lists.backend.provider.database;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.djitouw.lists.backend.provider.database.objects.MovieDB;
import com.djitouw.lists.backend.provider.database.objects.URDDB;

/**
 * Provider that allows to retrieve information from the database.
 * 
 * @author Djitouw
 *
 */
@Repository
public interface DatabaseProvider {

	/**
	 * @return a list of movies
	 */
	List<MovieDB> getMovies();

	/**
	 * @param username
	 * @return User-Related Details of the objects with ids in parameter
	 */
	List<URDDB> getURD(Collection<String> idsObjects, String username);

	/**
	 * @param id
	 * @return the movie with the id in parameter
	 */
	MovieDB getMovie(String id);
}
