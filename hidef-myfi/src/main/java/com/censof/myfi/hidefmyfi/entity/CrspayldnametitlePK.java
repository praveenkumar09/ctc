package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the crspayldnametitle database table.
 * 
 */
@Embeddable
public class CrspayldnametitlePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String id;

	private String nameID;

	public CrspayldnametitlePK() {
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNameID() {
		return this.nameID;
	}
	public void setNameID(String nameID) {
		this.nameID = nameID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CrspayldnametitlePK)) {
			return false;
		}
		CrspayldnametitlePK castOther = (CrspayldnametitlePK)other;
		return 
			this.id.equals(castOther.id)
			&& this.nameID.equals(castOther.nameID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.nameID.hashCode();
		
		return hash;
	}
}