package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the cbcpayldbody database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcpayldbody.findAll", query="SELECT c FROM Cbcpayldbody c")
public class Cbcpayldbody implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger id;

	@Column(name="hdrid")
	private BigInteger hdrID;

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
	
	@Column(name="is_deleted")
	private Integer isdeleted;

	public Cbcpayldbody() {
	}

	

	public BigInteger getId() {
		return id;
	}



	public void setId(BigInteger id) {
		this.id = id;
	}



	public BigInteger getHdrID() {
		return hdrID;
	}



	public void setHdrID(BigInteger hdrID) {
		this.hdrID = hdrID;
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



	public Integer getIsdeleted() {
		return isdeleted;
	}



	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}
	

}