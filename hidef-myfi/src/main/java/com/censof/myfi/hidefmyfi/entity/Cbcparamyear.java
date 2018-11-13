package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cbcparamyear database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcparamyear.findAll", query="SELECT c FROM Cbcparamyear c")
public class Cbcparamyear implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private int daysToApproved;

	private int firstReminder;

	private int fourthReminder;

	private int lateSubmission;

	private String MNEDateline;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private int secondReminder;

	private String sysCloseFromDate;

	private String sysCloseToDate;

	private int taxYear;

	private int thirdReminder;

	public Cbcparamyear() {
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

	public int getDaysToApproved() {
		return this.daysToApproved;
	}

	public void setDaysToApproved(int daysToApproved) {
		this.daysToApproved = daysToApproved;
	}

	public int getFirstReminder() {
		return this.firstReminder;
	}

	public void setFirstReminder(int firstReminder) {
		this.firstReminder = firstReminder;
	}

	public int getFourthReminder() {
		return this.fourthReminder;
	}

	public void setFourthReminder(int fourthReminder) {
		this.fourthReminder = fourthReminder;
	}

	public int getLateSubmission() {
		return this.lateSubmission;
	}

	public void setLateSubmission(int lateSubmission) {
		this.lateSubmission = lateSubmission;
	}

	public String getMNEDateline() {
		return this.MNEDateline;
	}

	public void setMNEDateline(String MNEDateline) {
		this.MNEDateline = MNEDateline;
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

	public int getSecondReminder() {
		return this.secondReminder;
	}

	public void setSecondReminder(int secondReminder) {
		this.secondReminder = secondReminder;
	}

	public String getSysCloseFromDate() {
		return this.sysCloseFromDate;
	}

	public void setSysCloseFromDate(String sysCloseFromDate) {
		this.sysCloseFromDate = sysCloseFromDate;
	}

	public String getSysCloseToDate() {
		return this.sysCloseToDate;
	}

	public void setSysCloseToDate(String sysCloseToDate) {
		this.sysCloseToDate = sysCloseToDate;
	}

	public int getTaxYear() {
		return this.taxYear;
	}

	public void setTaxYear(int taxYear) {
		this.taxYear = taxYear;
	}

	public int getThirdReminder() {
		return this.thirdReminder;
	}

	public void setThirdReminder(int thirdReminder) {
		this.thirdReminder = thirdReminder;
	}

}