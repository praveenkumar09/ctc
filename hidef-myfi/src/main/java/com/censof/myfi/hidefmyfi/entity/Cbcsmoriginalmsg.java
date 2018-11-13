package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the cbcsmoriginalmsg database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcsmoriginalmsg.findAll", query="SELECT c FROM Cbcsmoriginalmsg c")
public class Cbcsmoriginalmsg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date CTSSendingTimeStamp;

	private String CTSTransmissionID;

	private BigInteger hdrID;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private String originalMessageRefID;

	private int uncompressedFileSizeKBQty;

	private String validationResultStatus;

	public Cbcsmoriginalmsg() {
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

	public Date getCTSSendingTimeStamp() {
		return this.CTSSendingTimeStamp;
	}

	public void setCTSSendingTimeStamp(Date CTSSendingTimeStamp) {
		this.CTSSendingTimeStamp = CTSSendingTimeStamp;
	}

	public String getCTSTransmissionID() {
		return this.CTSTransmissionID;
	}

	public void setCTSTransmissionID(String CTSTransmissionID) {
		this.CTSTransmissionID = CTSTransmissionID;
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

	public String getOriginalMessageRefID() {
		return this.originalMessageRefID;
	}

	public void setOriginalMessageRefID(String originalMessageRefID) {
		this.originalMessageRefID = originalMessageRefID;
	}

	public int getUncompressedFileSizeKBQty() {
		return this.uncompressedFileSizeKBQty;
	}

	public void setUncompressedFileSizeKBQty(int uncompressedFileSizeKBQty) {
		this.uncompressedFileSizeKBQty = uncompressedFileSizeKBQty;
	}

	public String getValidationResultStatus() {
		return this.validationResultStatus;
	}

	public void setValidationResultStatus(String validationResultStatus) {
		this.validationResultStatus = validationResultStatus;
	}

}