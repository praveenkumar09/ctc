package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the cbcmessagetypeindic database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcmessagetypeindic.findAll", query="SELECT c FROM Cbcmessagetypeindic c")
public class Cbcmessagetypeindic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private BigInteger id;

	@Column(name="createby")
	private String createBy;

	@Column(name="createdatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	@Column(name="indic")
	private String indic;

	@Column(name="modifyby")
	private String modifyBy;

	@Column(name="modifydatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	public Cbcmessagetypeindic() {
	}

	

	public BigInteger getId() {
		return id;
	}



	public void setId(BigInteger id) {
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

	public String getIndic() {
		return this.indic;
	}

	public void setIndic(String indic) {
		this.indic = indic;
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