package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
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

	@EmbeddedId
	private CrspayldnamegenerationPK id;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String generationIdentifier;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	public Crspayldnamegeneration() {
	}

	public CrspayldnamegenerationPK getId() {
		return this.id;
	}

	public void setId(CrspayldnamegenerationPK id) {
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

}