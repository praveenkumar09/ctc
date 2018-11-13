package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cbcsmhdr database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcsmhdr.findAll", query="SELECT c FROM Cbcsmhdr c")
public class Cbcsmhdr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String contact;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String filename;

	private String messageRefId;

	private String messageType;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date msgTimestamp;

	private String receivingCountry;

	private String sendingCompanyIN;

	private String transmittingCountry;

	@Lob
	private String warning;

	public Cbcsmhdr() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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

	public String getMessageType() {
		return this.messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
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

	public String getSendingCompanyIN() {
		return this.sendingCompanyIN;
	}

	public void setSendingCompanyIN(String sendingCompanyIN) {
		this.sendingCompanyIN = sendingCompanyIN;
	}

	public String getTransmittingCountry() {
		return this.transmittingCountry;
	}

	public void setTransmittingCountry(String transmittingCountry) {
		this.transmittingCountry = transmittingCountry;
	}

	public String getWarning() {
		return this.warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

}