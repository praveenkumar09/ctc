package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the crspayldaddress database table.
 * 
 */
@Embeddable
public class CrspayldaddressPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String id;

	private String srcType;

	private String objectID;

	public CrspayldaddressPK() {
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSrcType() {
		return this.srcType;
	}
	public void setSrcType(String srcType) {
		this.srcType = srcType;
	}
	public String getObjectID() {
		return this.objectID;
	}
	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CrspayldaddressPK)) {
			return false;
		}
		CrspayldaddressPK castOther = (CrspayldaddressPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.srcType.equals(castOther.srcType)
			&& this.objectID.equals(castOther.objectID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.srcType.hashCode();
		hash = hash * prime + this.objectID.hashCode();
		
		return hash;
	}
}