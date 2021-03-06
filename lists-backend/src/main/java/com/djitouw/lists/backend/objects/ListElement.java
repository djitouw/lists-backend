package com.djitouw.lists.backend.objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class ListElement {

	@XmlElement(name = "urd")
	private URD urd;

	public URD getUrd() {
		return urd;
	}

	public void setUrd(URD urd) {
		this.urd = urd;
	}

}
