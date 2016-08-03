package com.djitouw.lists.backend.provider.omdb.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.djitouw.lists.backend.provider.omdb.OMDbProvider;
import com.djitouw.lists.backend.provider.omdb.objects.MovieOMDb;

@Repository
public class OMDbProviderImpl implements OMDbProvider {

	private static final Logger LOGGER = Logger.getLogger(OMDbProviderImpl.class);
	
	private String url;

	@Override
	public MovieOMDb getMovieDetails(String id, String title) {
		
		// at least one parameter has to be present
		if(StringUtils.isBlank(id) && StringUtils.isBlank(title)){
			LOGGER.error("Both id and title are blank");
			return null;
		}
		
		Client client = ClientBuilder.newClient();
		WebTarget webResource = client.target(url).queryParam("tomatoes", true).queryParam("plot", "long");
		// if we have the id we use it, otherwise we use the title
		if(StringUtils.isNotBlank(id)){
			webResource = webResource.queryParam("i", id);
		} else if(StringUtils.isNotBlank(title)){
			webResource = webResource.queryParam("t", title);
		}
		
		LOGGER.info("Calling OMDb - url: " + webResource.getUri());
		Response response = webResource.request().get();
		
		MovieOMDb omdbMovie = null;
		if(response != null){
			try{
				return response.readEntity(MovieOMDb.class);
			} catch(RuntimeException e){
				LOGGER.error("Error while mapping the response : " + response.readEntity(String.class));
				LOGGER.error(e.getMessage());
			}
		}
		return omdbMovie;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
