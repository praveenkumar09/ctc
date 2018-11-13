package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;


/**
 * The persistent class for the docrefid database table.
 * 
 */
@Entity
@NamedQuery(name="Docrefid.findAll", query="SELECT d FROM Docrefid d")
public class Docrefid implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger id;

	@Column(name="communicationtype")
	private String communicationtype;

	@Column(name="date")
	private String date;

	@Column(name="docrefid")
	private String docrefid;

	public Docrefid() {
	}

	

	public BigInteger getId() {
		return id;
	}



	public void setId(BigInteger id) {
		this.id = id;
	}



	public String getCommunicationtype() {
		return this.communicationtype;
	}

	public void setCommunicationtype(String communicationtype) {
		this.communicationtype = communicationtype;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDocrefid() {
		return this.docrefid;
	}

	public void setDocrefid(String docrefid) {
		this.docrefid = docrefid;
	}

}