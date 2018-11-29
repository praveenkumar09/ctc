package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the crsfiinfo database table.
 * 
 */
@Entity
@NamedQuery(name="Crsfiinfo.findAll", query="SELECT c FROM Crsfiinfo c")
public class Crsfiinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Column(name="activeflag")
	private String activeFlag;

	@Column(name="address1")
	private String address1;

	@Column(name="address2")
	private String address2;

	@Column(name="allowupdatecategory")
	private String allowUpdateCategory;

	@Column(name="allowupdatedatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date allowUpdateDateTime;

	@Column(name="allowupdatefield")
	private String allowUpdateField;

	@Column(name="allowupdatename")
	private String allowUpdateName;

	@Column(name="allowupdatetin")
	private String allowUpdateTin;

	@Column(name="answer1")
	private String answer1;

	@Column(name="answer2")
	private String answer2;

	@Column(name="city")
	private String city;

	@Column(name="createby")
	private String createBy;

	@Column(name="createdatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	@Column(name="createdinst")
	private String createdInST;

	@Column(name="email1")
	private String email1;

	@Column(name="email2")
	private String email2;

	@Column(name="fax")
	private String fax;

	@Column(name="ficategory")
	private int FICategory;

	@Column(name="finame")
	private String FIName;

	@Column(name="fitin")
	private String FITin;

	@Column(name="fitintype")
	private String FITinType;

	@Column(name="lastsubmission")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastSubmission;

	@Column(name="lastsubmissiontaxyear")
	private int lastSubmissionTaxYear;

	@Column(name="modifyby")
	private String modifyBy;

	@Column(name="modifydatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	@Column(name="mycrsid")
	private String myCRSID;
	@Column(name="phone")
	private String phone;

	@Column(name="postalcode")
	private String postalCode;

	@Column(name="question1")
	private String question1;

	@Column(name="question2")
	private String question2;

	@Column(name="registrationdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date registrationDate;

	@Column(name="remindercount")
	private int reminderCount;

	@Column(name="state")
	private String state;

	public Crsfiinfo() {
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

	public String getAllowUpdateCategory() {
		return this.allowUpdateCategory;
	}

	public void setAllowUpdateCategory(String allowUpdateCategory) {
		this.allowUpdateCategory = allowUpdateCategory;
	}

	public Date getAllowUpdateDateTime() {
		return this.allowUpdateDateTime;
	}

	public void setAllowUpdateDateTime(Date allowUpdateDateTime) {
		this.allowUpdateDateTime = allowUpdateDateTime;
	}

	public String getAllowUpdateField() {
		return this.allowUpdateField;
	}

	public void setAllowUpdateField(String allowUpdateField) {
		this.allowUpdateField = allowUpdateField;
	}

	public String getAllowUpdateName() {
		return this.allowUpdateName;
	}

	public void setAllowUpdateName(String allowUpdateName) {
		this.allowUpdateName = allowUpdateName;
	}

	public String getAllowUpdateTin() {
		return this.allowUpdateTin;
	}

	public void setAllowUpdateTin(String allowUpdateTin) {
		this.allowUpdateTin = allowUpdateTin;
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

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public int getFICategory() {
		return this.FICategory;
	}

	public void setFICategory(int FICategory) {
		this.FICategory = FICategory;
	}

	public String getFIName() {
		return this.FIName;
	}

	public void setFIName(String FIName) {
		this.FIName = FIName;
	}

	public String getFITin() {
		return this.FITin;
	}

	public void setFITin(String FITin) {
		this.FITin = FITin;
	}

	public String getFITinType() {
		return this.FITinType;
	}

	public void setFITinType(String FITinType) {
		this.FITinType = FITinType;
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

	public String getMyCRSID() {
		return this.myCRSID;
	}

	public void setMyCRSID(String myCRSID) {
		this.myCRSID = myCRSID;
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

	public String getQuestion1() {
		return this.question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	public String getQuestion2() {
		return this.question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public int getReminderCount() {
		return this.reminderCount;
	}

	public void setReminderCount(int reminderCount) {
		this.reminderCount = reminderCount;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}