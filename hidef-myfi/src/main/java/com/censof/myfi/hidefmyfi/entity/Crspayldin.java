package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the crspayldin database table.
 * 
 */
@Entity
@NamedQuery(name="Crspayldin.findAll", query="SELECT c FROM Crspayldin c")
public class Crspayldin implements Serializable {
	private static final long serialVersionUID = 1L;

	/*@EmbeddedId
	private CrspayldinPK id;*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger id;

	@Column(name="srctype")
	private String srcType;

	@Column(name="objectid")
	private BigInteger objectID;

	@Column(name="createby")
	private String createBy;

	@Column(name="createdatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	@Column(name="intype")
	private String INType;

	@Column(name="issuedby")
	private String issuedBy;

	@Column(name="modifyby")
	private String modifyBy;

	@Column(name="modifydatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	@Column(name="tin")
	private String tin;

	public Crspayldin() {
	}

	/*public CrspayldinPK getId() {
		return this.id;
	}

	public void setId(CrspayldinPK id) {
		this.id = id;
	}*/

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

	public String getINType() {
		return this.INType;
	}

	public void setINType(String INType) {
		this.INType = INType;
	}

	public String getIssuedBy() {
		return this.issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
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

	public String getTin() {
		return this.tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getSrcType() {
		return srcType;
	}

	public void setSrcType(String srcType) {
		this.srcType = srcType;
	}

	public BigInteger getObjectID() {
		return objectID;
	}

	public void setObjectID(BigInteger objectID) {
		this.objectID = objectID;
	}
	

}