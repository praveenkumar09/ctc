package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the crsfilog database table.
 * 
 */
@Entity
@NamedQuery(name="Crsfilog.findAll", query="SELECT c FROM Crsfilog c")
public class Crsfilog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger id;

	@Column(name="createby")
	private BigInteger fiid;

	@Column(name="logaction")
	private String logAction;

	@Column(name="logTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTime;

	public Crsfilog() {
	}

	

	public BigInteger getId() {
		return id;
	}



	public void setId(BigInteger id) {
		this.id = id;
	}



	public BigInteger getFiid() {
		return this.fiid;
	}

	public void setFiid(BigInteger fiid) {
		this.fiid = fiid;
	}

	public String getLogAction() {
		return this.logAction;
	}

	public void setLogAction(String logAction) {
		this.logAction = logAction;
	}

	public Date getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

}