package com.djitouw.lists.backend.factories.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.djitouw.lists.backend.factories.MovieFactory;
import com.djitouw.lists.backend.objects.Person;
import com.djitouw.lists.backend.objects.movies.Movie;
import com.djitouw.lists.backend.objects.movies.MovieDetails;
import com.djitouw.lists.backend.provider.database.objects.MovieDB;
import com.djitouw.lists.backend.provider.database.objects.URDDB;
import com.djitouw.lists.backend.provider.omdb.objects.MovieOMDb;

@Component
public class MovieFactoryImpl extends AbstractFactory implements MovieFactory {

	private static final Logger LOGGER = Logger.getLogger(MovieFactoryImpl.class);

	@Override
	public Movie buildMovieDB(MovieDB movieDB, URDDB urdDB) {
		Movie movie = null;
		if (movieDB != null) {
			movie = new Movie();
			movie.setLocalId(movieDB.getLocalId());
			movie.setTitle(movieDB.getTitle());
			String releaseDate = movieDB.getReleaseDate();
			if (StringUtils.isNotBlank(releaseDate)) {
				try {
					movie.setReleaseDate(DateUtils.parseDate(releaseDate, "yyyy-MM-dd"));
				} catch (ParseException e) {
					LOGGER.error("Impossible to parse date " + releaseDate);
				}
			}
			movie.setLength(movieDB.getLength());
			// Directors
			List<String> mongoDBdirectors = movieDB.getDirectors();
			if (CollectionUtils.isNotEmpty(mongoDBdirectors)) {
				List<Person> directors = new ArrayList<>(
						CollectionUtils.collect(mongoDBdirectors, new Transformer<String, Person>() {

							@Override
							public Person transform(String name) {
								Person person = new Person();
								person.setName(name);
								return person;
							}
						}));
				movie.setDirectors(directors);
			}

			// URD
			if (urdDB != null) {
				movie.setUrd(buildURDDB(urdDB));
			}
		}
		return movie;
	}

	@Override
	public MovieDetails buildMovieDetailsOMDb(MovieOMDb movieOMDb) {
		MovieDetails movieDetails = null;
		if (movieOMDb != null) {
			movieDetails = new MovieDetails();
			movieDetails.setSynopsis(movieOMDb.getPlot());
			movieDetails.setUrlPoster(movieOMDb.getUrlPoster());
			movieDetails.setTomatoMeter(movieOMDb.getTomatoMeter());
		}
		return movieDetails;
	}

}
