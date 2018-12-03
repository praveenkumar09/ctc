package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the crspayldnamemiddle database table.
 * 
 */
@Entity
@NamedQuery(name="Crspayldnamemiddle.findAll", query="SELECT c FROM Crspayldnamemiddle c")
public class Crspayldnamemiddle implements Serializable {
	private static final long serialVersionUID = 1L;

	/*@EmbeddedId
	private CrspayldnamemiddlePK id;*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger id;

	@Column(name="nameid")
	private BigInteger nameID;

	@Column(name="createby")
	private String createBy;

	@Column(name="createdatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	@Column(name="middlename")
	private String middleName;

	@Column(name="modifyby")
	private String modifyBy;

	@Column(name="modifydatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	public Crspayldnamemiddle() {
	}

	/*public CrspayldnamemiddlePK getId() {
		return this.id;
	}

	public void setId(CrspayldnamemiddlePK id) {
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

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getNameID() {
		return nameID;
	}

	public void setNameID(BigInteger nameID) {
		this.nameID = nameID;
	}
	

}