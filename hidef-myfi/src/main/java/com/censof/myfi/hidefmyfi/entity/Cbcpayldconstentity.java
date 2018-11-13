package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the cbcpayldconstentity database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcpayldconstentity.findAll", query="SELECT c FROM Cbcpayldconstentity c")
public class Cbcpayldconstentity implements Serializable {
	private static final long serialVersionUID = 1L;

	/*@EmbeddedId
	private CbcpayldconstentityPK id;*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger id;
	
	@Column(name="reportid")
	private BigInteger reportID;

	@Column(name="createby")
	private String createBy;

	@Column(name="createdatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	@Column(name="incorpcountrycode")
	private String incorpCountryCode;

	@Column(name="intype")
	private String INType;

	@Column(name="issuedby")
	private String issuedBy;

	@Column(name="modifyby")
	private String modifyBy;

	@Column(name="modifydatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	@Column(name="otherentityinfo")
	@Lob
	private String otherEntityInfo;

	@Column(name="tin")
	private String tin;
	
	@Column(name="is_deleted")
	private Integer isdeleted;

	public Cbcpayldconstentity() {
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



	public BigInteger getId() {
		return id;
	}



	public void setId(BigInteger id) {
		this.id = id;
	}



	public BigInteger getReportID() {
		return reportID;
	}



	public void setReportID(BigInteger reportID) {
		this.reportID = reportID;
	}



	public Integer getIsdeleted() {
		return isdeleted;
	}



	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	

}