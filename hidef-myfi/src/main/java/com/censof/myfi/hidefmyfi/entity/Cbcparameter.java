package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cbcparameter database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcparameter.findAll", query="SELECT c FROM Cbcparameter c")
public class Cbcparameter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String HCTAContactEmailAddress;

	private String HCTACountryCode;

	private String HCTALanguage;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private int recordErrorLimit;

	private String schemaValidatedBy;

	public Cbcparameter() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
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

	public String getHCTAContactEmailAddress() {
		return this.HCTAContactEmailAddress;
	}

	public void setHCTAContactEmailAddress(String HCTAContactEmailAddress) {
		this.HCTAContactEmailAddress = HCTAContactEmailAddress;
	}

	public String getHCTACountryCode() {
		return this.HCTACountryCode;
	}

	public void setHCTACountryCode(String HCTACountryCode) {
		this.HCTACountryCode = HCTACountryCode;
	}

	public String getHCTALanguage() {
		return this.HCTALanguage;
	}

	public void setHCTALanguage(String HCTALanguage) {
		this.HCTALanguage = HCTALanguage;
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

	public int getRecordErrorLimit() {
		return this.recordErrorLimit;
	}

	public void setRecordErrorLimit(int recordErrorLimit) {
		this.recordErrorLimit = recordErrorLimit;
	}

	public String getSchemaValidatedBy() {
		return this.schemaValidatedBy;
	}

	public void setSchemaValidatedBy(String schemaValidatedBy) {
		this.schemaValidatedBy = schemaValidatedBy;
	}

}