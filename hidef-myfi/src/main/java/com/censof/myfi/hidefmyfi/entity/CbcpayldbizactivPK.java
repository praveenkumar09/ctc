package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the cbcpayldbizactiv database table.
 * 
 */
@Embeddable
public class CbcpayldbizactivPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String id;

	private String consentID;

	public CbcpayldbizactivPK() {
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getConsentID() {
		return this.consentID;
	}
	public void setConsentID(String consentID) {
		this.consentID = consentID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CbcpayldbizactivPK)) {
			return false;
		}
		CbcpayldbizactivPK castOther = (CbcpayldbizactivPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.consentID.equals(castOther.consentID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.consentID.hashCode();
		
		return hash;
	}
}