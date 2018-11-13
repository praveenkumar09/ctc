package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the cbcmnecert database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcmnecert.findAll", query="SELECT c FROM Cbcmnecert c")
public class Cbcmnecert implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String certPath;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String fileName;

	private BigDecimal fileSize;

	private String issuer;

	private BigInteger mneid;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private String myCBCID;

	private String organization;

	private String serialNumber;

	@Temporal(TemporalType.TIMESTAMP)
	private Date validFrom;

	@Temporal(TemporalType.TIMESTAMP)
	private Date validTo;

	public Cbcmnecert() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCertPath() {
		return this.certPath;
	}

	public void setCertPath(String certPath) {
		this.certPath = certPath;
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

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public BigDecimal getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(BigDecimal fileSize) {
		this.fileSize = fileSize;
	}

	public String getIssuer() {
		return this.issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public BigInteger getMneid() {
		return this.mneid;
	}

	public void setMneid(BigInteger mneid) {
		this.mneid = mneid;
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

	public String getMyCBCID() {
		return this.myCBCID;
	}

	public void setMyCBCID(String myCBCID) {
		this.myCBCID = myCBCID;
	}

	public String getOrganization() {
		return this.organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Date getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return this.validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

}