package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cbcmnetaxyear database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcmnetaxyear.findAll", query="SELECT c FROM Cbcmnetaxyear c")
public class Cbcmnetaxyear implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private int daysLate;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private String myCBCID;

	private String repFlag;

	private String submittedDate;

	private int taxYear;

	public Cbcmnetaxyear() {
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

	public int getDaysLate() {
		return this.daysLate;
	}

	public void setDaysLate(int daysLate) {
		this.daysLate = daysLate;
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

	public String getRepFlag() {
		return this.repFlag;
	}

	public void setRepFlag(String repFlag) {
		this.repFlag = repFlag;
	}

	public String getSubmittedDate() {
		return this.submittedDate;
	}

	public void setSubmittedDate(String submittedDate) {
		this.submittedDate = submittedDate;
	}

	public int getTaxYear() {
		return this.taxYear;
	}

	public void setTaxYear(int taxYear) {
		this.taxYear = taxYear;
	}

}