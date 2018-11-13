package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the cbcpayldsumref database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcpayldsumref.findAll", query="SELECT c FROM Cbcpayldsumref c")
public class Cbcpayldsumref implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="addinfoid")
	private BigInteger addinfoID;

	@Column(name="createby")
	private String createBy;

	@Column(name="createdatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger id;

	@Column(name="modifyby")
	private String modifyBy;

	@Column(name="modifydatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	@Column(name="summaryref")
	private String summaryRef;
	
	@Column(name="is_deleted")
	private Integer isdeleted;

	public Cbcpayldsumref() {
	}

	public BigInteger getAddinfoID() {
		return this.addinfoID;
	}

	public void setAddinfoID(BigInteger addinfoID) {
		this.addinfoID = addinfoID;
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

	public Integer getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}
	

}