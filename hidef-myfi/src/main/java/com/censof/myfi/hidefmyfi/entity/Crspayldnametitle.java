package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the crspayldnametitle database table.
 * 
 */
@Entity
@NamedQuery(name="Crspayldnametitle.findAll", query="SELECT c FROM Crspayldnametitle c")
public class Crspayldnametitle implements Serializable {
	private static final long serialVersionUID = 1L;

	/*@EmbeddedId
	private CrspayldnametitlePK id;*/
	
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

	@Column(name="modifyby")
	private String modifyBy;

	@Column(name="modifydatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	@Column(name="title")
	private String title;

	public Crspayldnametitle() {
	}

	/*public CrspayldnametitlePK getId() {
		return this.id;
	}

	public void setId(CrspayldnametitlePK id) {
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

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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