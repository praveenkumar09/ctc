package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cbcmneinfo database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcmneinfo.findAll", query="SELECT c FROM Cbcmneinfo c")
public class Cbcmneinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String activeFlag;

	private String address1;

	private String address2;

	private String address3;

	private String branchCode;

	private String city;

	private String countryCode;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String createdInST;

	private String email1;

	private String email2;

	private String fax;

	private String financialYearEnd;

	private String financialYearStart;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastSubmission;

	private int lastSubmissionTaxYear;

	private String MNEName;

	private String MNETin;

	private String MNETinType;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private String myCBCID;

	private String notification;

	private String notificationDate;

	private String notificationDateReceived;

	private String phone;

	private String postalCode;

	@Temporal(TemporalType.TIMESTAMP)
	private Date registrationDate;

	private String registrationNo;

	private int reminderCount;

	private String reportingEntityStatus;

	private String state;

	private int taxYear;

	private String ultimateCompCountryCode;

	private String ultimateCompName;

	private String ultimateCompTin;

	private String ultimateCompTinType;

	private String website;

	public Cbcmneinfo() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return this.address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getBranchCode() {
		return this.branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
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

	public String getCreatedInST() {
		return this.createdInST;
	}

	public void setCreatedInST(String createdInST) {
		this.createdInST = createdInST;
	}

	public String getEmail1() {
		return this.email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return this.email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFinancialYearEnd() {
		return this.financialYearEnd;
	}

	public void setFinancialYearEnd(String financialYearEnd) {
		this.financialYearEnd = financialYearEnd;
	}

	public String getFinancialYearStart() {
		return this.financialYearStart;
	}

	public void setFinancialYearStart(String financialYearStart) {
		this.financialYearStart = financialYearStart;
	}

	public Date getLastSubmission() {
		return this.lastSubmission;
	}

	public void setLastSubmission(Date lastSubmission) {
		this.lastSubmission = lastSubmission;
	}

	public int getLastSubmissionTaxYear() {
		return this.lastSubmissionTaxYear;
	}

	public void setLastSubmissionTaxYear(int lastSubmissionTaxYear) {
		this.lastSubmissionTaxYear = lastSubmissionTaxYear;
	}

	public String getMNEName() {
		return this.MNEName;
	}

	public void setMNEName(String MNEName) {
		this.MNEName = MNEName;
	}

	public String getMNETin() {
		return this.MNETin;
	}

	public void setMNETin(String MNETin) {
		this.MNETin = MNETin;
	}

	public String getMNETinType() {
		return this.MNETinType;
	}

	public void setMNETinType(String MNETinType) {
		this.MNETinType = MNETinType;
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

	public String getNotification() {
		return this.notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getNotificationDate() {
		return this.notificationDate;
	}

	public void setNotificationDate(String notificationDate) {
		this.notificationDate = notificationDate;
	}

	public String getNotificationDateReceived() {
		return this.notificationDateReceived;
	}

	public void setNotificationDateReceived(String notificationDateReceived) {
		this.notificationDateReceived = notificationDateReceived;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getRegistrationNo() {
		return this.registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public int getReminderCount() {
		return this.reminderCount;
	}

	public void setReminderCount(int reminderCount) {
		this.reminderCount = reminderCount;
	}

	public String getReportingEntityStatus() {
		return this.reportingEntityStatus;
	}

	public void setReportingEntityStatus(String reportingEntityStatus) {
		this.reportingEntityStatus = reportingEntityStatus;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getTaxYear() {
		return this.taxYear;
	}

	public void setTaxYear(int taxYear) {
		this.taxYear = taxYear;
	}

	public String getUltimateCompCountryCode() {
		return this.ultimateCompCountryCode;
	}

	public void setUltimateCompCountryCode(String ultimateCompCountryCode) {
		this.ultimateCompCountryCode = ultimateCompCountryCode;
	}

	public String getUltimateCompName() {
		return this.ultimateCompName;
	}

	public void setUltimateCompName(String ultimateCompName) {
		this.ultimateCompName = ultimateCompName;
	}

	public String getUltimateCompTin() {
		return this.ultimateCompTin;
	}

	public void setUltimateCompTin(String ultimateCompTin) {
		this.ultimateCompTin = ultimateCompTin;
	}

	public String getUltimateCompTinType() {
		return this.ultimateCompTinType;
	}

	public void setUltimateCompTinType(String ultimateCompTinType) {
		this.ultimateCompTinType = ultimateCompTinType;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

}