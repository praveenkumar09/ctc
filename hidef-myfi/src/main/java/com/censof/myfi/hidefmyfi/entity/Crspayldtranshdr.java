package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the crspayldtranshdr database table.
 * 
 */
@Entity
@NamedQuery(name="Crspayldtranshdr.findAll", query="SELECT c FROM Crspayldtranshdr c")
public class Crspayldtranshdr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String messageRefId;

	private String messageTypeIndic;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date msgTimestamp;

	private String receivingCountry;

	private String reportingPeriod;

	private String SMMessageRefId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date SMMsgTimestamp;

	private String SMTransmissionId;

	private int taxYear;

	private String transmissionId;

	public Crspayldtranshdr() {
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

	public String getMessageRefId() {
		return this.messageRefId;
	}

	public void setMessageRefId(String messageRefId) {
		this.messageRefId = messageRefId;
	}

	public String getMessageTypeIndic() {
		return this.messageTypeIndic;
	}

	public void setMessageTypeIndic(String messageTypeIndic) {
		this.messageTypeIndic = messageTypeIndic;
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

	public String getReceivingCountry() {
		return this.receivingCountry;
	}

	public void setReceivingCountry(String receivingCountry) {
		this.receivingCountry = receivingCountry;
	}

	public String getReportingPeriod() {
		return this.reportingPeriod;
	}

	public void setReportingPeriod(String reportingPeriod) {
		this.reportingPeriod = reportingPeriod;
	}

	public String getSMMessageRefId() {
		return this.SMMessageRefId;
	}

	public void setSMMessageRefId(String SMMessageRefId) {
		this.SMMessageRefId = SMMessageRefId;
	}

	public Date getSMMsgTimestamp() {
		return this.SMMsgTimestamp;
	}

	public void setSMMsgTimestamp(Date SMMsgTimestamp) {
		this.SMMsgTimestamp = SMMsgTimestamp;
	}

	public String getSMTransmissionId() {
		return this.SMTransmissionId;
	}

	public void setSMTransmissionId(String SMTransmissionId) {
		this.SMTransmissionId = SMTransmissionId;
	}

	public int getTaxYear() {
		return this.taxYear;
	}

	public void setTaxYear(int taxYear) {
		this.taxYear = taxYear;
	}

	public String getTransmissionId() {
		return this.transmissionId;
	}

	public void setTransmissionId(String transmissionId) {
		this.transmissionId = transmissionId;
	}

}