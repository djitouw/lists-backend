package com.djitouw.lists.backend.objects;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User-Related Details about an object.
 * 
 * @author Djitouw
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class URD {

	@XmlElement(name = "flags")
	private List<Flag> flags;

	public List<Flag> getFlags() {
		return flags;
	}

	public void setFlags(List<Flag> flags) {
		this.flags = flags;
	}

}
