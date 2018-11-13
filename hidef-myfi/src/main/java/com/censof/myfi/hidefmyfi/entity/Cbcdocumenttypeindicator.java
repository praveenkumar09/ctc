package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cbcdocumenttypeindicator database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcdocumenttypeindicator.findAll", query="SELECT c FROM Cbcdocumenttypeindicator c")
public class Cbcdocumenttypeindicator implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@Column(name="cbcdocumenttype")
	private String CBCDocumentType;

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

	public Cbcdocumenttypeindicator() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCBCDocumentType() {
		return this.CBCDocumentType;
	}

	public void setCBCDocumentType(String CBCDocumentType) {
		this.CBCDocumentType = CBCDocumentType;
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