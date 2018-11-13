package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cbcmastconstentity database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcmastconstentity.findAll", query="SELECT c FROM Cbcmastconstentity c")
public class Cbcmastconstentity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CbcmastconstentityPK id;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String incorpCountryCode;

	private String INType;

	private String issuedBy;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	@Lob
	private String otherEntityInfo;

	private String tin;

	public Cbcmastconstentity() {
	}

	public CbcmastconstentityPK getId() {
		return this.id;
	}

	public void setId(CbcmastconstentityPK id) {
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

	public String getIncorpCountryCode() {
		return this.incorpCountryCode;
	}

	public void setIncorpCountryCode(String incorpCountryCode) {
		this.incorpCountryCode = incorpCountryCode;
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

	public String getOtherEntityInfo() {
		return this.otherEntityInfo;
	}

	public void setOtherEntityInfo(String otherEntityInfo) {
		this.otherEntityInfo = otherEntityInfo;
	}

	public String getTin() {
		return this.tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

}