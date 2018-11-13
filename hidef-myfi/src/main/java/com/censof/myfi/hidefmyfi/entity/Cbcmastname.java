package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cbcmastname database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcmastname.findAll", query="SELECT c FROM Cbcmastname c")
public class Cbcmastname implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CbcmastnamePK id;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private String nameOrganisation;

	public Cbcmastname() {
	}

	public CbcmastnamePK getId() {
		return this.id;
	}

	public void setId(CbcmastnamePK id) {
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

	public String getNameOrganisation() {
		return this.nameOrganisation;
	}

	public void setNameOrganisation(String nameOrganisation) {
		this.nameOrganisation = nameOrganisation;
	}

}