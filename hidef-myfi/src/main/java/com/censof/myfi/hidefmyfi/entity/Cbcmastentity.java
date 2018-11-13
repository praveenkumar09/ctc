package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the cbcmastentity database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcmastentity.findAll", query="SELECT c FROM Cbcmastentity c")
public class Cbcmastentity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CbcmastentityPK id;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String docRefId;

	private BigInteger hdrID;

	private String issuedBy;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private String reportingRole;

	private String tin;

	public Cbcmastentity() {
	}

	public CbcmastentityPK getId() {
		return this.id;
	}

	public void setId(CbcmastentityPK id) {
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

	public String getDocRefId() {
		return this.docRefId;
	}

	public void setDocRefId(String docRefId) {
		this.docRefId = docRefId;
	}

	public BigInteger getHdrID() {
		return this.hdrID;
	}

	public void setHdrID(BigInteger hdrID) {
		this.hdrID = hdrID;
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

	public String getReportingRole() {
		return this.reportingRole;
	}

	public void setReportingRole(String reportingRole) {
		this.reportingRole = reportingRole;
	}

	public String getTin() {
		return this.tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

}