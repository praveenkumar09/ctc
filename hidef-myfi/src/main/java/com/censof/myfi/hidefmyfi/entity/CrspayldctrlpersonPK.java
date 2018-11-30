package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the crspayldctrlperson database table.
 * 
 */
@Embeddable
public class CrspayldctrlpersonPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String id;

	private String acctRepID;

	public CrspayldctrlpersonPK() {
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAcctRepID() {
		return this.acctRepID;
	}
	public void setAcctRepID(String acctRepID) {
		this.acctRepID = acctRepID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CrspayldctrlpersonPK)) {
			return false;
		}
		CrspayldctrlpersonPK castOther = (CrspayldctrlpersonPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.acctRepID.equals(castOther.acctRepID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.acctRepID.hashCode();
		
		return hash;
	}
}