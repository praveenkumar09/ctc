package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the cbcmastconstentity database table.
 * 
 */
@Embeddable
public class CbcmastconstentityPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String id;

	private String reportID;

	public CbcmastconstentityPK() {
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReportID() {
		return this.reportID;
	}
	public void setReportID(String reportID) {
		this.reportID = reportID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CbcmastconstentityPK)) {
			return false;
		}
		CbcmastconstentityPK castOther = (CbcmastconstentityPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.reportID.equals(castOther.reportID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.reportID.hashCode();
		
		return hash;
	}
}