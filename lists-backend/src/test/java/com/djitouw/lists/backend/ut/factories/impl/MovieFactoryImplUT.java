package com.djitouw.lists.backend.ut.factories.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Assert;
import org.junit.Test;

import com.djitouw.lists.backend.factories.impl.MovieFactoryImpl;
import com.djitouw.lists.backend.objects.Person;
import com.djitouw.lists.backend.objects.movies.Movie;
import com.djitouw.lists.backend.objects.movies.MovieDetails;
import com.djitouw.lists.backend.provider.database.objects.MovieDB;
import com.djitouw.lists.backend.provider.omdb.objects.MovieOMDb;
import com.djitouw.lists.backend.ut.AbstractUT;

/**
 * UTs for {@link MovieFactoryImpl}.
 * 
 * @author Djitouw
 *
 */
public class MovieFactoryImplUT extends AbstractUT {

	private MovieFactoryImpl movieFactory = new MovieFactoryImpl();

	/**
	 * Null case.
	 */
	@Test
	public void testBuildMovieNull() {
		Movie movie = movieFactory.buildMovieDB(null);
		Assert.assertNull(movie);
	}

	/**
	 * Local id OK case.
	 */
	@Test
	public void testBuildMovieLocalIdOK() {
		MovieDB movieDB = new MovieDB();
		movieDB.setLocalId("localId");
		Movie movie = movieFactory.buildMovieDB(movieDB);
		Assert.assertNotNull(movie);
		Assert.assertEquals(movieDB.getLocalId(), movie.getLocalId());
	}

	/**
	 * Title OK case.
	 */
	@Test
	public void testBuildMovieTitleOK() {
		MovieDB movieDB = new MovieDB();
		movieDB.setTitle("title");
		Movie movie = movieFactory.buildMovieDB(movieDB);
		Assert.assertNotNull(movie);
		Assert.assertEquals(movieDB.getTitle(), movie.getTitle());
	}

	/**
	 * Length OK case.
	 */
	@Test
	public void testBuildMovieLengthOK() {
		MovieDB movieDB = new MovieDB();
		movieDB.setLength(12d);
		Movie movie = movieFactory.buildMovieDB(movieDB);
		Assert.assertNotNull(movie);
		Assert.assertEquals(movieDB.getLength(), movie.getLength(), 0);
	}

	/**
	 * Length OK case.
	 */
	@Test
	public void testBuildMovieDateIncorrectFormat() {
		MovieDB movieDB = new MovieDB();
		movieDB.setReleaseDate("date incorrecte");
		Movie movie = movieFactory.buildMovieDB(movieDB);
		Assert.assertNotNull(movie);
		Assert.assertNull(movie.getReleaseDate());
	}

	/**
	 * Length OK case.
	 */
	@Test
	public void testBuildMovieDateOK() {
		MovieDB movieDB = new MovieDB();
		movieDB.setReleaseDate("1972-10-04");
		Movie movie = movieFactory.buildMovieDB(movieDB);
		Assert.assertNotNull(movie);
		Date releaseDate = movie.getReleaseDate();
		Assert.assertNotNull(releaseDate);
		Assert.assertEquals(movieDB.getReleaseDate(), DateFormatUtils.format(releaseDate, "yyyy-MM-dd"));
	}

	/**
	 * Length OK case.
	 */
	@Test
	public void testBuildMovieDirectorsOK() {
		MovieDB movieDB = new MovieDB();
		movieDB.setDirectors(Arrays.asList("director1", "director2"));
		Movie movie = movieFactory.buildMovieDB(movieDB);
		Assert.assertNotNull(movie);
		List<Person> directors = movie.getDirectors();
		Assert.assertNotNull(directors);
		Assert.assertEquals("director1", directors.get(0).getName());
		Assert.assertEquals("director2", directors.get(1).getName());
	}

	/**
	 * Null case.
	 */
	@Test
	public void testBuildMovieDetailsOMDbNull() {
		MovieDetails movieDetails = movieFactory.buildMovieDetailsOMDb(null);
		Assert.assertNull(movieDetails);
	}

	/**
	 * Case title OK.
	 */
	@Test
	public void testBuildMovieDetailsOMDbTitleOK() {
		MovieOMDb movieOMDb = new MovieOMDb();
		movieOMDb.setTitle("title");
		MovieDetails movieDetails = movieFactory.buildMovieDetailsOMDb(movieOMDb);
		Assert.assertNotNull(movieDetails);
		Assert.assertEquals(movieOMDb.getTitle(), movieDetails.getTitle());
	}

	/**
	 * Case plot OK.
	 */
	@Test
	public void testBuildMovieDetailsOMDbPlotOK() {
		MovieOMDb movieOMDb = new MovieOMDb();
		movieOMDb.setPlot("plot");
		MovieDetails movieDetails = movieFactory.buildMovieDetailsOMDb(movieOMDb);
		Assert.assertNotNull(movieDetails);
		Assert.assertEquals(movieOMDb.getPlot(), movieDetails.getSynopsis());
	}

	/**
	 * Case url poster OK.
	 */
	@Test
	public void testBuildMovieDetailsOMDbUrlPosterOK() {
		MovieOMDb movieOMDb = new MovieOMDb();
		movieOMDb.setUrlPoster("url_poster");
		MovieDetails movieDetails = movieFactory.buildMovieDetailsOMDb(movieOMDb);
		Assert.assertNotNull(movieDetails);
		Assert.assertEquals(movieOMDb.getUrlPoster(), movieDetails.getUrlPoster());
	}

	/**
	 * Case tomato meter OK.
	 */
	@Test
	public void testBuildMovieDetailsOMDbTomatoMeterOK() {
		MovieOMDb movieOMDb = new MovieOMDb();
		movieOMDb.setTomatoMeter(94);
		MovieDetails movieDetails = movieFactory.buildMovieDetailsOMDb(movieOMDb);
		Assert.assertNotNull(movieDetails);
		Assert.assertEquals(movieOMDb.getTomatoMeter(), movieDetails.getTomatoMeter(), 0);
	}

}
