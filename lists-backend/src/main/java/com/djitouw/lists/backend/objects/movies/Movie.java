package com.djitouw.lists.backend.objects.movies;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.djitouw.lists.backend.objects.ListElement;
import com.djitouw.lists.backend.objects.Person;

@XmlType(name="")
public class Movie extends ListElement{

	@XmlElement(name="title")
	private String title;

	@XmlElement(name="release_date")
	private Date releaseDate;
	
	@XmlElement(name="directors")
	private List<Person> directors;
	
	@XmlElement(name="length")
	private Double length;

	private boolean champCache;
	
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

	public boolean isChampCache() {
		return champCache;
	}

	public void setChampCache(boolean champCache) {
		this.champCache = champCache;
	}
	
	
	
}
