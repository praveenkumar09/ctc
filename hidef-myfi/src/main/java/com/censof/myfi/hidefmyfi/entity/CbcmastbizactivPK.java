package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the cbcmastbizactiv database table.
 * 
 */
@Embeddable
public class CbcmastbizactivPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String id;

	private String consEntID;

	public CbcmastbizactivPK() {
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getConsEntID() {
		return this.consEntID;
	}
	public void setConsEntID(String consEntID) {
		this.consEntID = consEntID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CbcmastbizactivPK)) {
			return false;
		}
		CbcmastbizactivPK castOther = (CbcmastbizactivPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.consEntID.equals(castOther.consEntID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.consEntID.hashCode();
		
		return hash;
	}
}