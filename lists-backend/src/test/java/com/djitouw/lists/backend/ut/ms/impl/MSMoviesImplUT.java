package com.djitouw.lists.backend.ut.ms.impl;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.djitouw.lists.backend.factories.MovieFactory;
import com.djitouw.lists.backend.factories.impl.MovieFactoryImpl;
import com.djitouw.lists.backend.ms.impl.MSMoviesImpl;
import com.djitouw.lists.backend.objects.movies.Movie;
import com.djitouw.lists.backend.objects.movies.MovieDetails;
import com.djitouw.lists.backend.provider.database.DatabaseProvider;
import com.djitouw.lists.backend.provider.database.objects.MovieDB;
import com.djitouw.lists.backend.provider.omdb.OMDbProvider;
import com.djitouw.lists.backend.provider.omdb.objects.MovieOMDb;
import com.djitouw.lists.backend.ut.AbstractUT;

/**
 * UTs for {@link MovieFactoryImpl}.
 * 
 * @author Djitouw
 *
 */
public class MSMoviesImplUT extends AbstractUT {
	
	private static MSMoviesImpl msMovies;
	private static DatabaseProvider databaseProvider;
	private static MovieFactory movieFactory;
	private static OMDbProvider omdbProvider;
	
	@BeforeClass
	public static void beforeClass(){
		
		// Mocks
		databaseProvider = EasyMock.createMock(DatabaseProvider.class);
		movieFactory = EasyMock.createMock(MovieFactory.class);
		omdbProvider = EasyMock.createMock(OMDbProvider.class);
		
		// Service tested
		msMovies = new MSMoviesImpl();
		msMovies.setDatabaseProvider(databaseProvider);
		msMovies.setMovieFactory(movieFactory);
		msMovies.setOmdbProvider(omdbProvider);
	}
	
	@Before
	public void before(){
		EasyMock.reset(databaseProvider, movieFactory, omdbProvider);
	}
	
	/**
	 * Case when the database provider returns null.
	 */
	@Test
	public void testGetListNull(){
		
		// Expects
		EasyMock.expect(databaseProvider.getMovies()).andReturn(null);
		
		EasyMock.replay(databaseProvider);
		List<Movie> movies = msMovies.getList();
		EasyMock.verify(databaseProvider);
		
		Assert.assertNull(movies);
	}
	
	/**
	 * Case OK.
	 */
	@Test
	public void testGetListOK(){
		
		// Objects
		MovieDB movieDB1 = new MovieDB();
		Movie movie1 = new Movie();
		MovieDB movieDB2 = new MovieDB();
		Movie movie2 = new Movie();
		List<MovieDB> moviesDB = Arrays.asList(movieDB1, movieDB2);
		
		// Expects
		EasyMock.expect(databaseProvider.getMovies()).andReturn(moviesDB);
		EasyMock.expect(movieFactory.buildMovieDB(movieDB1)).andReturn(movie1);
		EasyMock.expect(movieFactory.buildMovieDB(movieDB2)).andReturn(movie2);
		
		EasyMock.replay(databaseProvider, movieFactory);
		List<Movie> movies = msMovies.getList();
		EasyMock.verify(databaseProvider, movieFactory);
		
		Assert.assertNotNull(movies);
		Assert.assertEquals(2, movies.size());
		Assert.assertEquals(movie1, movies.get(0));
		Assert.assertEquals(movie2, movies.get(1));
	}
	
	/**
	 * Case when the database provider returns null.
	 */
	@Test
	public void testGetDetailsNull(){
		
		// Datas
		String id = "id";
		
		// Expects
		EasyMock.expect(databaseProvider.getMovie(id)).andReturn(null);
		
		EasyMock.replay(databaseProvider);
		MovieDetails movie = msMovies.getDetails(id);
		EasyMock.verify(databaseProvider);
		
		Assert.assertNull(movie);
	}
	
	/**
	 * Case OK.
	 */
	@Test
	public void testGetDetailsOK(){
		
		// Datas
		String id = "id";
		MovieDB movieDB = new MovieDB();
		movieDB.setOmdbId("omdb_id");
		movieDB.setTitle("title");
		MovieOMDb movieOMDb = new MovieOMDb();
		MovieDetails movieDetails = new MovieDetails();
		
		// Expects
		EasyMock.expect(databaseProvider.getMovie(id)).andReturn(movieDB);
		EasyMock.expect(omdbProvider.getMovieDetails(movieDB.getOmdbId(), movieDB.getTitle())).andReturn(movieOMDb);
		EasyMock.expect(movieFactory.buildMovieDetailsOMDb(movieOMDb)).andReturn(movieDetails);
		
		EasyMock.replay(databaseProvider, omdbProvider, movieFactory);
		MovieDetails res = msMovies.getDetails(id);
		EasyMock.verify(databaseProvider, omdbProvider, movieFactory);
		
		Assert.assertNotNull(res);
		Assert.assertEquals(movieDetails, res);
	}
	
}
