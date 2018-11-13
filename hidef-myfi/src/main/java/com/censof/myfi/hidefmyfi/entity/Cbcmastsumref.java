package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the cbcmastsumref database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcmastsumref.findAll", query="SELECT c FROM Cbcmastsumref c")
public class Cbcmastsumref implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigInteger addInfoID;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger id;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private String summaryRef;

	public Cbcmastsumref() {
	}

	public BigInteger getAddInfoID() {
		return this.addInfoID;
	}

	public void setAddInfoID(BigInteger addInfoID) {
		this.addInfoID = addInfoID;
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

	public BigInteger getId() {
		return this.id;
	}

	public void setId(BigInteger id) {
		this.id = id;
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

	public String getSummaryRef() {
		return this.summaryRef;
	}

	public void setSummaryRef(String summaryRef) {
		this.summaryRef = summaryRef;
	}

}