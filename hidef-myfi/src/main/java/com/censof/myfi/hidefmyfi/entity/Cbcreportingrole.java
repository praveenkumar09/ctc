package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cbcreportingrole database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcreportingrole.findAll", query="SELECT c FROM Cbcreportingrole c")
public class Cbcreportingrole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="cbcreprole")
	private String CBCRepRole;

	@Column(name="createby")
	private String createBy;

	@Column(name="createdatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	@Column(name="modifyby")
	private String modifyBy;

	@Column(name="modifydatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	public Cbcreportingrole() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCBCRepRole() {
		return this.CBCRepRole;
	}

	public void setCBCRepRole(String CBCRepRole) {
		this.CBCRepRole = CBCRepRole;
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

}