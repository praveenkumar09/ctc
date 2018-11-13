package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="senderfileid")
public class Senderfileid implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger id;

	@Column(name="communicationtype")
	private String communicationtype;

	@Column(name="date")
	private String date;

	@Column(name="senderfileid")
	private String senderfileid;	

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



	public String getSenderfileid() {
		return senderfileid;
	}



	public void setSenderfileid(String senderfileid) {
		this.senderfileid = senderfileid;
	}


	
}
