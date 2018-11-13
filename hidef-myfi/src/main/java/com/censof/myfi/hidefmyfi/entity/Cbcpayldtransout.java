package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the cbcpayldtransout database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcpayldtransout.findAll", query="SELECT c FROM Cbcpayldtransout c")
public class Cbcpayldtransout implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String filename;

	private String messageRefId;

	private String messageTypeIndic;

	private BigInteger MNEHdrID;

	private String MNEMessageRefId;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date msgTimestamp;

	private String receivingCountry;

	private String SMMessageRefId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date SMMsgTimestamp;

	private String SMTransmissionID;

	private int taxYear;

	public Cbcpayldtransout() {
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

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
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

	public BigInteger getMNEHdrID() {
		return this.MNEHdrID;
	}

	public void setMNEHdrID(BigInteger MNEHdrID) {
		this.MNEHdrID = MNEHdrID;
	}

	public String getMNEMessageRefId() {
		return this.MNEMessageRefId;
	}

	public void setMNEMessageRefId(String MNEMessageRefId) {
		this.MNEMessageRefId = MNEMessageRefId;
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

	public String getSMTransmissionID() {
		return this.SMTransmissionID;
	}

	public void setSMTransmissionID(String SMTransmissionID) {
		this.SMTransmissionID = SMTransmissionID;
	}

	public int getTaxYear() {
		return this.taxYear;
	}

	public void setTaxYear(int taxYear) {
		this.taxYear = taxYear;
	}

}