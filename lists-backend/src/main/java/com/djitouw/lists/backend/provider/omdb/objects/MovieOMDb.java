package com.djitouw.lists.backend.provider.omdb.objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Movie informations from OMDb.
 * 
 * @author Djitouw
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class MovieOMDb {

	@XmlElement(name = "Title")
	private String title;

	@XmlElement(name = "Plot")
	private String plot;

	@XmlElement(name = "Poster")
	private String urlPoster;

	@XmlElement(name = "tomatoMeter")
	private Integer tomatoMeter;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String synopsis) {
		this.plot = synopsis;
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
