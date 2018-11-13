package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cbcmastbizactiv database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcmastbizactiv.findAll", query="SELECT c FROM Cbcmastbizactiv c")
public class Cbcmastbizactiv implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CbcmastbizactivPK id;

	private String bizActivities;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	public Cbcmastbizactiv() {
	}

	public CbcmastbizactivPK getId() {
		return this.id;
	}

	public void setId(CbcmastbizactivPK id) {
		this.id = id;
	}

	public String getBizActivities() {
		return this.bizActivities;
	}

	public void setBizActivities(String bizActivities) {
		this.bizActivities = bizActivities;
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