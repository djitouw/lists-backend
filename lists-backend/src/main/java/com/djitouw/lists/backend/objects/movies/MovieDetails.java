package com.djitouw.lists.backend.objects.movies;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "")
public class MovieDetails extends Movie {

	@XmlElement(name = "synopsis")
	private String synopsis;

	@XmlElement(name = "url_poster")
	private String urlPoster;

	@XmlElement(name = "tomato_meter")
	private Integer tomatoMeter;

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getUrlPoster() {
		return urlPoster;
	}

	public void setUrlPoster(String urlPoster) {
		this.urlPoster = urlPoster;
	}

	public Integer getTomatoMeter() {
		return tomatoMeter;
	}

	public void setTomatoMeter(Integer tomatoMeter) {
		this.tomatoMeter = tomatoMeter;
	}

}
