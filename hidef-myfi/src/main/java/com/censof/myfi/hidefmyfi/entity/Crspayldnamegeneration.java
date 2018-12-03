package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the crspayldnamegeneration database table.
 * 
 */
@Entity
@NamedQuery(name="Crspayldnamegeneration.findAll", query="SELECT c FROM Crspayldnamegeneration c")
public class Crspayldnamegeneration implements Serializable {
	private static final long serialVersionUID = 1L;

	/*@EmbeddedId
	private CrspayldnamegenerationPK id;*/
	
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

	@Column(name="generationidentifier")
	private String generationIdentifier;

	@Column(name="modifyby")
	private String modifyBy;

	@Column(name="modifydatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	public Crspayldnamegeneration() {
	}

	/*public CrspayldnamegenerationPK getId() {
		return this.id;
	}

	public void setId(CrspayldnamegenerationPK id) {
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

	public String getGenerationIdentifier() {
		return this.generationIdentifier;
	}

	public void setGenerationIdentifier(String generationIdentifier) {
		this.generationIdentifier = generationIdentifier;
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