package com.djitouw.lists.backend.provider.database.objects;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Movie stored in Database.
 * 
 * @author Djitouw
 */
@Document(collection = "movies")
public class MovieDB {

	@Field("local_id")
	private String localId;

	@Field("omdb_id")
	private String omdbId;

	@Field("title")
	private String title;

	@Field("release_date")
	private String releaseDate;

	@Field("directors")
	private List<String> directors;

	@Field("length")
	private Double length;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public List<String> getDirectors() {
		return directors;
	}

	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public String getLocalId() {
		return localId;
	}

	public void setLocalId(String id) {
		this.localId = id;
	}

	public String getOmdbId() {
		return omdbId;
	}

	public void setOmdbId(String omdbId) {
		this.omdbId = omdbId;
	}

}
