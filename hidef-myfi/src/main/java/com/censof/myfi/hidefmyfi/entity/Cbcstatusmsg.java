package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the cbcstatusmsg database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcstatusmsg.findAll", query="SELECT c FROM Cbcstatusmsg c")
public class Cbcstatusmsg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Lob
	private String actionTaken;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String isFileError;

	private String isRecordError;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date msgTimestamp;

	private String payldMessageRefId;

	private BigInteger payloadID;

	private String receivingCountry;

	private String resultStatus;

	private String SMFilename;

	private BigInteger smid;

	private String SMMessageRefId;

	private String transmissionID;

	private String transmittingCountry;

	public Cbcstatusmsg() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActionTaken() {
		return this.actionTaken;
	}

	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
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

	public String getIsFileError() {
		return this.isFileError;
	}

	public void setIsFileError(String isFileError) {
		this.isFileError = isFileError;
	}

	public String getIsRecordError() {
		return this.isRecordError;
	}

	public void setIsRecordError(String isRecordError) {
		this.isRecordError = isRecordError;
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

	public Date getMsgTimestamp() {
		return this.msgTimestamp;
	}

	public void setMsgTimestamp(Date msgTimestamp) {
		this.msgTimestamp = msgTimestamp;
	}

	public String getPayldMessageRefId() {
		return this.payldMessageRefId;
	}

	public void setPayldMessageRefId(String payldMessageRefId) {
		this.payldMessageRefId = payldMessageRefId;
	}

	public BigInteger getPayloadID() {
		return this.payloadID;
	}

	public void setPayloadID(BigInteger payloadID) {
		this.payloadID = payloadID;
	}

	public String getReceivingCountry() {
		return this.receivingCountry;
	}

	public void setReceivingCountry(String receivingCountry) {
		this.receivingCountry = receivingCountry;
	}

	public String getResultStatus() {
		return this.resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getSMFilename() {
		return this.SMFilename;
	}

	public void setSMFilename(String SMFilename) {
		this.SMFilename = SMFilename;
	}

	public BigInteger getSmid() {
		return this.smid;
	}

	public void setSmid(BigInteger smid) {
		this.smid = smid;
	}

	public String getSMMessageRefId() {
		return this.SMMessageRefId;
	}

	public void setSMMessageRefId(String SMMessageRefId) {
		this.SMMessageRefId = SMMessageRefId;
	}

	public String getTransmissionID() {
		return this.transmissionID;
	}

	public void setTransmissionID(String transmissionID) {
		this.transmissionID = transmissionID;
	}

	public String getTransmittingCountry() {
		return this.transmittingCountry;
	}

	public void setTransmittingCountry(String transmittingCountry) {
		this.transmittingCountry = transmittingCountry;
	}

}