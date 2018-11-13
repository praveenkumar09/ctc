package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the cbcpayldbizactiv database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcpayldbizactiv.findAll", query="SELECT c FROM Cbcpayldbizactiv c")
public class Cbcpayldbizactiv implements Serializable {
	private static final long serialVersionUID = 1L;

	/*@EmbeddedId
	private CbcpayldbizactivPK id;*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger id;

	@Column(name="consentid")
	private BigInteger consentID;

	@Column(name="bizactivities")
	private String bizActivities;

	@Column(name="createby")
	private String createBy;

	@Column(name="createdatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	@Column(name="modifyby")
	private String modifyBy;

	@Column(name="modifydatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;
	
	@Column(name="is_deleted")
	private Integer isdeleted;

	public Cbcpayldbizactiv() {
	}

	

	public BigInteger getId() {
		return id;
	}



	public void setId(BigInteger id) {
		this.id = id;
	}



	public BigInteger getConsentID() {
		return consentID;
	}



	public void setConsentID(BigInteger consentID) {
		this.consentID = consentID;
	}



	public String getBizActivities() {
		return this.bizActivities;
	}

	public void setBizActivities(String bizActivities) {
		this.bizActivities = bizActivities;
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



	public Integer getIsdeleted() {
		return isdeleted;
	}



	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}
	

}