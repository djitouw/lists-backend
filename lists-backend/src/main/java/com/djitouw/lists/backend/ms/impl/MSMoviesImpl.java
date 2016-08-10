package com.djitouw.lists.backend.ms.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.djitouw.lists.backend.factories.MovieFactory;
import com.djitouw.lists.backend.ms.MSMovies;
import com.djitouw.lists.backend.objects.movies.Movie;
import com.djitouw.lists.backend.objects.movies.MovieDetails;
import com.djitouw.lists.backend.provider.database.DatabaseProvider;
import com.djitouw.lists.backend.provider.database.objects.MovieDB;
import com.djitouw.lists.backend.provider.database.objects.URDDB;
import com.djitouw.lists.backend.provider.omdb.OMDbProvider;

@Service
public class MSMoviesImpl implements MSMovies {

	private DatabaseProvider databaseProvider;

	private OMDbProvider omdbProvider;

	private MovieFactory movieFactory;

	@Override
	public List<Movie> getList(String username) {
		List<Movie> movies = null;
		List<MovieDB> dbMovies = databaseProvider.getMovies();

		if (CollectionUtils.isNotEmpty(dbMovies)) {
			// if the user is authenticated, we need to retrieve the
			// user-related
			// informations about the movies
			if (StringUtils.isNotBlank(username)) {
				List<URDDB> urdDBs = databaseProvider
						.getURD(CollectionUtils.collect(dbMovies, new Transformer<MovieDB, String>() {

							@Override
							public String transform(MovieDB movieDB) {
								return movieDB.getLocalId();
							}
						}), username);
				movies = new ArrayList<>(CollectionUtils.collect(dbMovies, new Transformer<MovieDB, Movie>() {

					@Override
					public Movie transform(MovieDB movieDB) {
						return movieFactory.buildMovieDB(movieDB, IterableUtils.find(urdDBs, new Predicate<URDDB>() {

							@Override
							public boolean evaluate(URDDB urdDB) {
								return urdDB != null && StringUtils.isNotBlank(urdDB.getObjectLocalId())
										&& StringUtils.equals(urdDB.getObjectLocalId(), movieDB.getLocalId());
							}
						}));
					}
				}));
			} else {
				movies = new ArrayList<>(CollectionUtils.collect(dbMovies, new Transformer<MovieDB, Movie>() {

					@Override
					public Movie transform(MovieDB movieDB) {
						return movieFactory.buildMovieDB(movieDB, null);
					}
				}));
			}

		}
		return movies;
	}

	@Override
	public MovieDetails getDetails(String id) {
		// Retrieve the movie from the database
		MovieDB movieDB = databaseProvider.getMovie(id);
		if (movieDB != null) {
			return movieFactory
					.buildMovieDetailsOMDb(omdbProvider.getMovieDetails(movieDB.getOmdbId(), movieDB.getTitle()));
		}
		return null;
	}

	public void setDatabaseProvider(DatabaseProvider databaseProvider) {
		this.databaseProvider = databaseProvider;
	}

	public void setOmdbProvider(OMDbProvider omdbProvider) {
		this.omdbProvider = omdbProvider;
	}

	public void setMovieFactory(MovieFactory movieFactory) {
		this.movieFactory = movieFactory;
	}

}
