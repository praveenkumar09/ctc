package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the cbcpayldtransin database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcpayldtransin.findAll", query="SELECT c FROM Cbcpayldtransin c")
public class Cbcpayldtransin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private BigInteger hdrID;

	private BigInteger masterID;

	private String messageRefId;

	private String messageTypeIndic;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private String sendingEntityIN;

	private String SMMessageRefId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date SMMsgTimestamp;

	private String SMTransmissionID;

	private int taxYear;

	private String transmissionID;

	private String transmittingCountry;

	public Cbcpayldtransin() {
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

	public BigInteger getHdrID() {
		return this.hdrID;
	}

	public void setHdrID(BigInteger hdrID) {
		this.hdrID = hdrID;
	}

	public BigInteger getMasterID() {
		return this.masterID;
	}

	public void setMasterID(BigInteger masterID) {
		this.masterID = masterID;
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

	public String getSendingEntityIN() {
		return this.sendingEntityIN;
	}

	public void setSendingEntityIN(String sendingEntityIN) {
		this.sendingEntityIN = sendingEntityIN;
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