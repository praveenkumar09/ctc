package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the crspayldcountry database table.
 * 
 */
@Entity
@NamedQuery(name="Crspayldcountry.findAll", query="SELECT c FROM Crspayldcountry c")
public class Crspayldcountry implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CrspayldcountryPK id;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private String resCountryCode;

	public Crspayldcountry() {
	}

	public CrspayldcountryPK getId() {
		return this.id;
	}

	public void setId(CrspayldcountryPK id) {
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

	public String getResCountryCode() {
		return this.resCountryCode;
	}

	public void setResCountryCode(String resCountryCode) {
		this.resCountryCode = resCountryCode;
	}

}