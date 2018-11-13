package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cbcmastin database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcmastin.findAll", query="SELECT c FROM Cbcmastin c")
public class Cbcmastin implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CbcmastinPK id;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String INType;

	private String issuedBy;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private String tin;

	public Cbcmastin() {
	}

	public CbcmastinPK getId() {
		return this.id;
	}

	public void setId(CbcmastinPK id) {
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

	public String getINType() {
		return this.INType;
	}

	public void setINType(String INType) {
		this.INType = INType;
	}

	public String getIssuedBy() {
		return this.issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
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

	public String getTin() {
		return this.tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

}