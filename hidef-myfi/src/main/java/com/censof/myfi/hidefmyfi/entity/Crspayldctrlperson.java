package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the crspayldctrlperson database table.
 * 
 */
@Entity
@NamedQuery(name="Crspayldctrlperson.findAll", query="SELECT c FROM Crspayldctrlperson c")
public class Crspayldctrlperson implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CrspayldctrlpersonPK id;

	private String birthCity;

	private String birthCitySubent;

	private String birthCountry;

	private String birthDate;

	private String birthFormerCountry;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String ctrlgPersonType;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	public Crspayldctrlperson() {
	}

	public CrspayldctrlpersonPK getId() {
		return this.id;
	}

	public void setId(CrspayldctrlpersonPK id) {
		this.id = id;
	}

	public String getBirthCity() {
		return this.birthCity;
	}

	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}

	public String getBirthCitySubent() {
		return this.birthCitySubent;
	}

	public void setBirthCitySubent(String birthCitySubent) {
		this.birthCitySubent = birthCitySubent;
	}

	public String getBirthCountry() {
		return this.birthCountry;
	}

	public void setBirthCountry(String birthCountry) {
		this.birthCountry = birthCountry;
	}

	public String getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthFormerCountry() {
		return this.birthFormerCountry;
	}

	public void setBirthFormerCountry(String birthFormerCountry) {
		this.birthFormerCountry = birthFormerCountry;
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

	public String getCtrlgPersonType() {
		return this.ctrlgPersonType;
	}

	public void setCtrlgPersonType(String ctrlgPersonType) {
		this.ctrlgPersonType = ctrlgPersonType;
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

}