package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the crsregenlog database table.
 * 
 */
@Entity
@NamedQuery(name="Crsregenlog.findAll", query="SELECT c FROM Crsregenlog c")
public class Crsregenlog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Lob
	private String country;

	private String logBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date logTime;

	private String messageRefId;

	private int taxYear;

	public Crsregenlog() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLogBy() {
		return this.logBy;
	}

	public void setLogBy(String logBy) {
		this.logBy = logBy;
	}

	public Date getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getMessageRefId() {
		return this.messageRefId;
	}

	public void setMessageRefId(String messageRefId) {
		this.messageRefId = messageRefId;
	}

	public int getTaxYear() {
		return this.taxYear;
	}

	public void setTaxYear(int taxYear) {
		this.taxYear = taxYear;
	}

}