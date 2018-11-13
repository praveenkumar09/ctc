package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the cbcmnelog database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcmnelog.findAll", query="SELECT c FROM Cbcmnelog c")
public class Cbcmnelog implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger id;

	private String logAction;

	@Temporal(TemporalType.TIMESTAMP)
	private Date logTime;

	private BigInteger mneid;

	public Cbcmnelog() {
	}

	public BigInteger getId() {
		return this.id;
	}

	public void setId(BigInteger id) {
		this.id = id;
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

	public BigInteger getMneid() {
		return this.mneid;
	}

	public void setMneid(BigInteger mneid) {
		this.mneid = mneid;
	}

}