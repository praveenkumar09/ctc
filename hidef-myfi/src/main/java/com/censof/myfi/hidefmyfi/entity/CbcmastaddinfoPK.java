package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the cbcmastaddinfo database table.
 * 
 */
@Embeddable
public class CbcmastaddinfoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String id;

	private String bodyID;

	public CbcmastaddinfoPK() {
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBodyID() {
		return this.bodyID;
	}
	public void setBodyID(String bodyID) {
		this.bodyID = bodyID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CbcmastaddinfoPK)) {
			return false;
		}
		CbcmastaddinfoPK castOther = (CbcmastaddinfoPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.bodyID.equals(castOther.bodyID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.bodyID.hashCode();
		
		return hash;
	}
}