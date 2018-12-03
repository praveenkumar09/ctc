package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import java.math.BigInteger;

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

	/*@EmbeddedId
	private CrspayldctrlpersonPK id;*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger id;

	@Column(name="acctrepid")
	private BigInteger acctRepID;

	@Column(name="birthcity")
	private String birthCity;

	@Column(name="birthcitysubent")
	private String birthCitySubent;

	@Column(name="birthcountry")
	private String birthCountry;

	@Column(name="birthdate")
	private String birthDate;

	@Column(name="birthformercountry")
	private String birthFormerCountry;

	@Column(name="createby")
	private String createBy;

	@Column(name="createdatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	@Column(name="ctrlgpersontype")
	private String ctrlgPersonType;

	@Column(name="modifyby")
	private String modifyBy;

	@Column(name="modifydatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	public Crspayldctrlperson() {
	}

	/*public CrspayldctrlpersonPK getId() {
		return this.id;
	}

	public void setId(CrspayldctrlpersonPK id) {
		this.id = id;
	}*/

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

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getAcctRepID() {
		return acctRepID;
	}

	public void setAcctRepID(BigInteger acctRepID) {
		this.acctRepID = acctRepID;
	}
	

}