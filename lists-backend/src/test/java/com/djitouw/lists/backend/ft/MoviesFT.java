package com.djitouw.lists.backend.ft;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

import io.restassured.response.ValidatableResponse;

/**
 * Functional tests of the Movies WS.
 * 
 * @author Djitouw
 *
 */
public class MoviesFT extends AbstractFT{
	
	// FIXME arousseau 03/08/2016: put this in conf
	private static final String URL_MOVIES_LIST = "http://localhost:8080/lists-backend/movies";

	/**
	 * Test of the path /movies
	 */
	@Test
	public void testMoviesGetList(){
		ValidatableResponse response = get(URL_MOVIES_LIST).then().log().ifValidationFails();
		response.statusCode(200);
		response.body("title", everyItem(allOf(notNullValue(), isA(String.class))));
		response.body("length", everyItem(allOf(notNullValue(), isA(Number.class))));
		response.body("release_date", everyItem(allOf(notNullValue(), isA(String.class))));
		response.body("directors", everyItem(hasSize(greaterThanOrEqualTo(1))));
		response.body("directors.name", everyItem(everyItem(allOf(notNullValue(), isA(String.class)))));
	}
	
	/**
	 * Test of the path /movies/{id}
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testMoviesGetDetails(){
		ValidatableResponse response = get(URL_MOVIES_LIST + "/1").then().log().ifValidationFails();
		response.statusCode(200);
		response.body("title", allOf(notNullValue(), isA(String.class)));
		response.body("synopsis", allOf(notNullValue(), isA(String.class)));
		response.body("url_poster", allOf(notNullValue(), isA(String.class)));
		response.body("tomato_meter", anyOf(nullValue(), greaterThanOrEqualTo(0)));
	}
}
