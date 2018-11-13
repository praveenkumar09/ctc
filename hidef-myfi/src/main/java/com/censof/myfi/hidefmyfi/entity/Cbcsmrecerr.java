package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the cbcsmrecerr database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcsmrecerr.findAll", query="SELECT c FROM Cbcsmrecerr c")
public class Cbcsmrecerr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	@Lob
	private String details;

	private BigInteger hdrID;

	private String language;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private String recordErrorCode;

	public Cbcsmrecerr() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public BigInteger getHdrID() {
		return this.hdrID;
	}

	public void setHdrID(BigInteger hdrID) {
		this.hdrID = hdrID;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDateTime() {
		return this.modifyDateTime;
	}

	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public String getRecordErrorCode() {
		return this.recordErrorCode;
	}

	public void setRecordErrorCode(String recordErrorCode) {
		this.recordErrorCode = recordErrorCode;
	}

}