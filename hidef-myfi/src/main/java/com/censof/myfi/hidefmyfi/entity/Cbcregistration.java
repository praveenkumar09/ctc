package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cbcregistration database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcregistration.findAll", query="SELECT c FROM Cbcregistration c")
public class Cbcregistration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String address1;

	private String address2;

	private String address3;

	private String answer1;

	private String answer2;

	private String approvalBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date approvalDate;

	private String approvalStatus;

	private String branchCode;

	private String city;

	private String countryCode;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String email;

	private String fax;

	private String financialYearEnd;

	private String financialYearStart;

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

	private String password;

	private String phone;

	private String postalCode;

	private int question1;

	private int question2;

	private String registrationNo;

	private String rejectCode;

	private String reportingEntityStatus;

	private String state;

	@Temporal(TemporalType.TIMESTAMP)
	private Date submissionDate;

	private String submissionStatus;

	private String tacNo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date tacRequestTime;

	private int tacTrial;

	private int taxYear;

	private String ultimateCompCountryCode;

	private String ultimateCompName;

	private String ultimateCompTin;

	private String ultimateCompTinType;

	private String validationStatus;

	private String website;

	public Cbcregistration() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getAnswer1() {
		return this.answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return this.answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getApprovalBy() {
		return this.approvalBy;
	}

	public void setApprovalBy(String approvalBy) {
		this.approvalBy = approvalBy;
	}

	public Date getApprovalDate() {
		return this.approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getQuestion1() {
		return this.question1;
	}

	public void setQuestion1(int question1) {
		this.question1 = question1;
	}

	public int getQuestion2() {
		return this.question2;
	}

	public void setQuestion2(int question2) {
		this.question2 = question2;
	}

	public String getRegistrationNo() {
		return this.registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getRejectCode() {
		return this.rejectCode;
	}

	public void setRejectCode(String rejectCode) {
		this.rejectCode = rejectCode;
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

	public Date getSubmissionDate() {
		return this.submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getSubmissionStatus() {
		return this.submissionStatus;
	}

	public void setSubmissionStatus(String submissionStatus) {
		this.submissionStatus = submissionStatus;
	}

	public String getTacNo() {
		return this.tacNo;
	}

	public void setTacNo(String tacNo) {
		this.tacNo = tacNo;
	}

	public Date getTacRequestTime() {
		return this.tacRequestTime;
	}

	public void setTacRequestTime(Date tacRequestTime) {
		this.tacRequestTime = tacRequestTime;
	}

	public int getTacTrial() {
		return this.tacTrial;
	}

	public void setTacTrial(int tacTrial) {
		this.tacTrial = tacTrial;
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

	public String getValidationStatus() {
		return this.validationStatus;
	}

	public void setValidationStatus(String validationStatus) {
		this.validationStatus = validationStatus;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

}