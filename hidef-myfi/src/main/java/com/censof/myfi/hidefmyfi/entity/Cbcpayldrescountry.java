package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the cbcpayldrescountry database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcpayldrescountry.findAll", query="SELECT c FROM Cbcpayldrescountry c")
public class Cbcpayldrescountry implements Serializable {
	private static final long serialVersionUID = 1L;

	/*@EmbeddedId
	private CbcpayldrescountryPK id;*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger id;

	@Column(name="srctype")
	private String srcType;

	@Column(name="objectid")
	private BigInteger objectID;

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

	@Column(name="rescountrycode")
	private String resCountryCode;
	
	@Column(name="is_deleted")
	private Integer isdeleted;

	public Cbcpayldrescountry() {
	}

	/*public CbcpayldrescountryPK getId() {
		return this.id;
	}

	public void setId(CbcpayldrescountryPK id) {
		this.id = id;
	}*/
	

	public String getCreateBy() {
		return this.createBy;
	}

	

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public void setObjectID(BigInteger objectID) {
		this.objectID = objectID;
	}

	public String getSrcType() {
		return srcType;
	}

	public void setSrcType(String srcType) {
		this.srcType = srcType;
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

	public String getResCountryCode() {
		return this.resCountryCode;
	}

	public void setResCountryCode(String resCountryCode) {
		this.resCountryCode = resCountryCode;
	}

	public Integer getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}

	public BigInteger getObjectID() {
		return objectID;
	}
	

}