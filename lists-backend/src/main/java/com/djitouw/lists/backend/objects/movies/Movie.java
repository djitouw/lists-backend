package com.djitouw.lists.backend.objects.movies;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.djitouw.lists.backend.objects.ListElement;
import com.djitouw.lists.backend.objects.Person;

@XmlType(name = "")
public class Movie extends ListElement {

	@XmlElement(name = "local_id")
	private String localId;

	@XmlElement(name = "title")
	private String title;

	@XmlElement(name = "release_date")
	private Date releaseDate;

	@XmlElement(name = "directors")
	private List<Person> directors;

	@XmlElement(name = "length")
	private Double length;

	@XmlElement(name = "details")
	private MovieDetails movieDetails;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public List<Person> getDirectors() {
		return directors;
	}

	public void setDirectors(List<Person> directors) {
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

	public void setLocalId(String localId) {
		this.localId = localId;
	}

	public MovieDetails getMovieDetails() {
		return movieDetails;
	}

	public void setMovieDetails(MovieDetails movieDetails) {
		this.movieDetails = movieDetails;
	}

}
