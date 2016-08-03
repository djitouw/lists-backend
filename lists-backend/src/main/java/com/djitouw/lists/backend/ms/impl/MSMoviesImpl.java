package com.djitouw.lists.backend.ms.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.springframework.stereotype.Service;

import com.djitouw.lists.backend.factories.MovieFactory;
import com.djitouw.lists.backend.ms.MSMovies;
import com.djitouw.lists.backend.objects.movies.Movie;
import com.djitouw.lists.backend.objects.movies.MovieDetails;
import com.djitouw.lists.backend.provider.database.DatabaseProvider;
import com.djitouw.lists.backend.provider.database.objects.MovieDB;
import com.djitouw.lists.backend.provider.omdb.OMDbProvider;

@Service
public class MSMoviesImpl implements MSMovies {

	private DatabaseProvider databaseProvider;

	private OMDbProvider omdbProvider;

	private MovieFactory movieFactory;

	@Override
	public List<Movie> getList() {
		List<Movie> movies = null;
		List<MovieDB> dbMovies = databaseProvider.getMovies();
		if (CollectionUtils.isNotEmpty(dbMovies)) {
			movies = new ArrayList<>(CollectionUtils.collect(dbMovies, new Transformer<MovieDB, Movie>() {

				@Override
				public Movie transform(MovieDB input) {
					return movieFactory.buildMovieDB(input);
				}
			}));
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
