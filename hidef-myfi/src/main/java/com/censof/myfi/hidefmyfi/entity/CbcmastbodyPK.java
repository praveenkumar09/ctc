package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the cbcmastbody database table.
 * 
 */
@Embeddable
public class CbcmastbodyPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String id;

	private String hdrID;

	public CbcmastbodyPK() {
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHdrID() {
		return this.hdrID;
	}
	public void setHdrID(String hdrID) {
		this.hdrID = hdrID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CbcmastbodyPK)) {
			return false;
		}
		CbcmastbodyPK castOther = (CbcmastbodyPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.hdrID.equals(castOther.hdrID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.hdrID.hashCode();
		
		return hash;
	}
}