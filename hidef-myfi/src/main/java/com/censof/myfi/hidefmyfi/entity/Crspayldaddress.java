package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the crspayldaddress database table.
 * 
 */
@Entity
@NamedQuery(name="Crspayldaddress.findAll", query="SELECT c FROM Crspayldaddress c")
public class Crspayldaddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CrspayldaddressPK id;

	@Lob
	private String addressFree;

	private String buildingIdentifier;

	private String city;

	private String countryCode;

	private String countrySubentity;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String districtName;

	private String floorIdentifier;

	private String legalAddressType;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private String pob;

	private String postCode;

	private String street;

	private String suiteIdentifier;

	public Crspayldaddress() {
	}

	public CrspayldaddressPK getId() {
		return this.id;
	}

	public void setId(CrspayldaddressPK id) {
		this.id = id;
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

}