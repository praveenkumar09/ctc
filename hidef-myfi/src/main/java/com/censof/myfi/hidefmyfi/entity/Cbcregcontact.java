package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the cbcregcontact database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcregcontact.findAll", query="SELECT c FROM Cbcregcontact c")
public class Cbcregcontact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String authorisedPerson;

	private String company;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String department;

	private String designation;

	private String email;

	private String emailAddress;

	private String ICNo;

	private BigInteger mneid;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private String name;

	private String phone;

	private String phoneNo;

	public Cbcregcontact() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthorisedPerson() {
		return this.authorisedPerson;
	}

	public void setAuthorisedPerson(String authorisedPerson) {
		this.authorisedPerson = authorisedPerson;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getICNo() {
		return this.ICNo;
	}

	public void setICNo(String ICNo) {
		this.ICNo = ICNo;
	}

	public BigInteger getMneid() {
		return this.mneid;
	}

	public void setMneid(BigInteger mneid) {
		this.mneid = mneid;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}