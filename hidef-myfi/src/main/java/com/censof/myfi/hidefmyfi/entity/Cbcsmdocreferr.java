package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the cbcsmdocreferr database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcsmdocreferr.findAll", query="SELECT c FROM Cbcsmdocreferr c")
public class Cbcsmdocreferr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String docRefIDInError;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private BigInteger recErrID;

	public Cbcsmdocreferr() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
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

	public String getDocRefIDInError() {
		return this.docRefIDInError;
	}

	public void setDocRefIDInError(String docRefIDInError) {
		this.docRefIDInError = docRefIDInError;
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

	public BigInteger getRecErrID() {
		return this.recErrID;
	}

	public void setRecErrID(BigInteger recErrID) {
		this.recErrID = recErrID;
	}

}