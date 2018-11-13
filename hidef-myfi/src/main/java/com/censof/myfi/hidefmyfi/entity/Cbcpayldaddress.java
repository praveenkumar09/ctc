package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the cbcpayldaddress database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcpayldaddress.findAll", query="SELECT c FROM Cbcpayldaddress c")
public class Cbcpayldaddress implements Serializable {
	private static final long serialVersionUID = 1L;

	/*@EmbeddedId
	private CbcpayldaddressPK id;*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger id;

	@Column(name="srctype")
	private String srcType;

	@Column(name="objectid")
	private BigInteger objectID;

	@Column(name="addressfree")
	@Lob
	private String addressFree;

	@Column(name="buildingidentifier")
	private String buildingIdentifier;

	@Column(name="city")
	private String city;

	@Column(name="countrycode")
	private String countryCode;

	@Column(name="countrysubentity")
	private String countrySubentity;

	@Column(name="createby")
	private String createBy;

	@Column(name="createdatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	@Column(name="districtname")
	private String districtName;

	@Column(name="flooridentifier")
	private String floorIdentifier;

	@Column(name="legaladdresstype")
	private String legalAddressType;

	@Column(name="modifyby")
	private String modifyBy;

	@Column(name="modifydatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	@Column(name="pob")
	private String pob;

	@Column(name="postcode")
	private String postCode;
	
	@Column(name="street")
	private String street;

	@Column(name="suiteidentifier")
	private String suiteIdentifier;
	
	@Column(name="is_deleted")
	private Integer isdeleted;

	public Cbcpayldaddress() {
	}

	

	public BigInteger getId() {
		return id;
	}



	public void setId(BigInteger id) {
		this.id = id;
	}



	public String getSrcType() {
		return srcType;
	}



	public void setSrcType(String srcType) {
		this.srcType = srcType;
	}



	public BigInteger getObjectID() {
		return objectID;
	}



	public void setObjectID(BigInteger objectID) {
		this.objectID = objectID;
	}



	public String getAddressFree() {
		return this.addressFree;
	}

	public void setAddressFree(String addressFree) {
		this.addressFree = addressFree;
	}

	public String getBuildingIdentifier() {
		return this.buildingIdentifier;
	}

	public void setBuildingIdentifier(String buildingIdentifier) {
		this.buildingIdentifier = buildingIdentifier;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountrySubentity() {
		return this.countrySubentity;
	}

	public void setCountrySubentity(String countrySubentity) {
		this.countrySubentity = countrySubentity;
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

	public String getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getFloorIdentifier() {
		return this.floorIdentifier;
	}

	public void setFloorIdentifier(String floorIdentifier) {
		this.floorIdentifier = floorIdentifier;
	}

	public String getLegalAddressType() {
		return this.legalAddressType;
	}

	public void setLegalAddressType(String legalAddressType) {
		this.legalAddressType = legalAddressType;
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

	public String getPob() {
		return this.pob;
	}

	public void setPob(String pob) {
		this.pob = pob;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getSuiteIdentifier() {
		return this.suiteIdentifier;
	}

	public void setSuiteIdentifier(String suiteIdentifier) {
		this.suiteIdentifier = suiteIdentifier;
	}



	public Integer getIsdeleted() {
		return isdeleted;
	}



	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}
	

}