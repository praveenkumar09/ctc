package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the crsregcontact database table.
 * 
 */
@Entity
@NamedQuery(name="Crsregcontact.findAll", query="SELECT c FROM Crsregcontact c")
public class Crsregcontact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String department;

	private String designation;

	private String email;

	private BigInteger fiid;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private String name;

	private String phone;

	public Crsregcontact() {
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

	public BigInteger getFiid() {
		return this.fiid;
	}

	public void setFiid(BigInteger fiid) {
		this.fiid = fiid;
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

}