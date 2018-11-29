package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the crspayldcormsg database table.
 * 
 */
@Entity
@NamedQuery(name="Crspayldcormsg.findAll", query="SELECT c FROM Crspayldcormsg c")
public class Crspayldcormsg implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CrspayldcormsgPK id;

	private String corrMessageRefId;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	public Crspayldcormsg() {
	}

	public CrspayldcormsgPK getId() {
		return this.id;
	}

	public void setId(CrspayldcormsgPK id) {
		this.id = id;
	}

	public String getCorrMessageRefId() {
		return this.corrMessageRefId;
	}

	public void setCorrMessageRefId(String corrMessageRefId) {
		this.corrMessageRefId = corrMessageRefId;
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