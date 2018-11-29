package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the crspayldtransdtl database table.
 * 
 */
@Entity
@NamedQuery(name="Crspayldtransdtl.findAll", query="SELECT c FROM Crspayldtransdtl c")
public class Crspayldtransdtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String docRefId;

	private String docTypeIndic;

	private BigInteger hdrID;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private BigInteger objectID;

	private String receivingCountry;

	private String srcType;

	public Crspayldtransdtl() {
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

	public String getDocRefId() {
		return this.docRefId;
	}

	public void setDocRefId(String docRefId) {
		this.docRefId = docRefId;
	}

	public String getDocTypeIndic() {
		return this.docTypeIndic;
	}

	public void setDocTypeIndic(String docTypeIndic) {
		this.docTypeIndic = docTypeIndic;
	}

	public BigInteger getHdrID() {
		return this.hdrID;
	}

	public void setHdrID(BigInteger hdrID) {
		this.hdrID = hdrID;
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

	public BigInteger getObjectID() {
		return this.objectID;
	}

	public void setObjectID(BigInteger objectID) {
		this.objectID = objectID;
	}

	public String getReceivingCountry() {
		return this.receivingCountry;
	}

	public void setReceivingCountry(String receivingCountry) {
		this.receivingCountry = receivingCountry;
	}

	public String getSrcType() {
		return this.srcType;
	}

	public void setSrcType(String srcType) {
		this.srcType = srcType;
	}

}