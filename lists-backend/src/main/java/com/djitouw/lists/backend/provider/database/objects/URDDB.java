package com.djitouw.lists.backend.provider.database.objects;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * User-Related Details about an object.
 * 
 * @author Djitouw
 *
 */
@Document(collection = "urd")
public class URDDB {

	@Field("object_local_id")
	private String objectLocalId;

	@Field("flags")
	private List<String> flags;

	public String getObjectLocalId() {
		return objectLocalId;
	}

	public void setObjectLocalId(String objectLocalId) {
		this.objectLocalId = objectLocalId;
	}

	public List<String> getFlags() {
		return flags;
	}

	public void setFlags(List<String> flags) {
		this.flags = flags;
	}

}
