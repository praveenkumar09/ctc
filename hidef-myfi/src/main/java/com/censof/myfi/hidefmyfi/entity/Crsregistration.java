package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the crsregistration database table.
 * 
 */
@Entity
@NamedQuery(name="Crsregistration.findAll", query="SELECT c FROM Crsregistration c")
public class Crsregistration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String address1;

	private String address2;

	private String answer1;

	private String answer2;

	private String approvalBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date approvalDate;

	private String approvalStatus;

	private String city;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String email;

	private String fax;

	private int FICategory;

	private String FIName;

	private String FITin;

	private String FITinType;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private String myCRSID;

	private String password;

	private String phone;

	private String postalCode;

	private int question1;

	private int question2;

	private String rejectCode;

	private String state;

	@Temporal(TemporalType.TIMESTAMP)
	private Date submissionDate;

	private String submissionStatus;

	private String tacNo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date tacRequestTime;

	private int tacTrial;

	private String validationStatus;

	public Crsregistration() {
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

	public String getRejectCode() {
		return this.rejectCode;
	}

	public void setRejectCode(String rejectCode) {
		this.rejectCode = rejectCode;
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

	public String getValidationStatus() {
		return this.validationStatus;
	}

	public void setValidationStatus(String validationStatus) {
		this.validationStatus = validationStatus;
	}

}