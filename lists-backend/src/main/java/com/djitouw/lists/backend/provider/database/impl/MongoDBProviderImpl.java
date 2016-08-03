package com.djitouw.lists.backend.provider.database.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.djitouw.lists.backend.provider.database.DatabaseProvider;
import com.djitouw.lists.backend.provider.database.objects.MovieDB;

/**
 * MongoDB provider.
 */
@Repository
public class MongoDBProviderImpl implements DatabaseProvider {

	private static final Logger LOGGER = Logger.getLogger(MongoDBProviderImpl.class);

	private static final ApplicationContext CTX = new GenericXmlApplicationContext("spring-context.xml");

	private static final MongoOperations MONGO_OPERATIONS = (MongoOperations) CTX.getBean("mongoTemplate");

	@Override
	public List<MovieDB> getMovies() {
		try {
			LOGGER.info("Retrieving movies from MongoDB");
			List<MovieDB> movies = MONGO_OPERATIONS.findAll(MovieDB.class);
			return movies;
		} catch (RuntimeException e) {
			LOGGER.error("Error retrieving movies from MongoDB : " + e.getMessage());
			return null;
		}
	}

	@Override
	public MovieDB getMovie(String id) {
		try {
			LOGGER.info("Retrieving movie(local_id=" + id + ") from MongoDB");
			MovieDB movie = MONGO_OPERATIONS.findOne(new Query(Criteria.where("local_id").is(id)), MovieDB.class);
			return movie;
		} catch (RuntimeException e) {
			LOGGER.error("Error retrieving movie from MongoDB : " + e.getMessage());
			return null;
		}
	}

}
