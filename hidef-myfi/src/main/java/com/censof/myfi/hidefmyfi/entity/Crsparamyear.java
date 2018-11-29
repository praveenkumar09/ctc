package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the crsparamyear database table.
 * 
 */
@Entity
@NamedQuery(name="Crsparamyear.findAll", query="SELECT c FROM Crsparamyear c")
public class Crsparamyear implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private int daysToApproved;

	private String endCorrectionDate;

	private int firstReminder;

	private int fourthReminder;

	private String initialProcessDate;

	private String initialStatus;

	private int lateSubmission;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private String MYFIDateline;

	private int secondReminder;

	private String startCorrectionDate;

	private String sysCloseFromDate;

	private String sysCloseToDate;

	private int taxYear;

	private int thirdReminder;

	public Crsparamyear() {
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

	public String getEndCorrectionDate() {
		return this.endCorrectionDate;
	}

	public void setEndCorrectionDate(String endCorrectionDate) {
		this.endCorrectionDate = endCorrectionDate;
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

	public String getInitialProcessDate() {
		return this.initialProcessDate;
	}

	public void setInitialProcessDate(String initialProcessDate) {
		this.initialProcessDate = initialProcessDate;
	}

	public String getInitialStatus() {
		return this.initialStatus;
	}

	public void setInitialStatus(String initialStatus) {
		this.initialStatus = initialStatus;
	}

	public int getLateSubmission() {
		return this.lateSubmission;
	}

	public void setLateSubmission(int lateSubmission) {
		this.lateSubmission = lateSubmission;
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

	public String getMYFIDateline() {
		return this.MYFIDateline;
	}

	public void setMYFIDateline(String MYFIDateline) {
		this.MYFIDateline = MYFIDateline;
	}

	public int getSecondReminder() {
		return this.secondReminder;
	}

	public void setSecondReminder(int secondReminder) {
		this.secondReminder = secondReminder;
	}

	public String getStartCorrectionDate() {
		return this.startCorrectionDate;
	}

	public void setStartCorrectionDate(String startCorrectionDate) {
		this.startCorrectionDate = startCorrectionDate;
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