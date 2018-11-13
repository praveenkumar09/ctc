package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the cbcpayldaddinfo database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcpayldaddinfo.findAll", query="SELECT c FROM Cbcpayldaddinfo c")
public class Cbcpayldaddinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/*@EmbeddedId
	private CbcpayldaddinfoPK id;*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger id;

	@Column(name="bodyid")
	private BigInteger bodyID;

	@Column(name="corrdocrefid")
	private String corrDocRefId;

	@Column(name="createby")
	private String createBy;

	@Column(name="createdatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	@Column(name="docrefid")
	private String docRefId;

	@Column(name="doctypeindic")
	private String docTypeIndic;

	@Column(name="hdrid")
	private BigInteger hdrID;

	@Column(name="modifyby")
	private String modifyBy;

	@Column(name="modifydatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	@Column(name="otherinfo")
	@Lob
	private String otherInfo;
	
	@Column(name="is_deleted")
	private Integer isdeleted;

	public Cbcpayldaddinfo() {
	}

	

	public BigInteger getId() {
		return id;
	}



	public void setId(BigInteger id) {
		this.id = id;
	}



	public BigInteger getBodyID() {
		return bodyID;
	}



	public void setBodyID(BigInteger bodyID) {
		this.bodyID = bodyID;
	}



	public String getCorrDocRefId() {
		return this.corrDocRefId;
	}

	public void setCorrDocRefId(String corrDocRefId) {
		this.corrDocRefId = corrDocRefId;
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

	public String getOtherInfo() {
		return this.otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}



	public Integer getIsdeleted() {
		return isdeleted;
	}



	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}
	

}