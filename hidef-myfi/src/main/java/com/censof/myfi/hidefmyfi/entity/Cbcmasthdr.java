package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cbcmasthdr database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcmasthdr.findAll", query="SELECT c FROM Cbcmasthdr c")
public class Cbcmasthdr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String binaryEncodingSchemeCd;

	private String communicationTypeCd;

	private String contact;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String fileFormatCD;

	private String language;

	private String messageType;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private String myCBCID;

	private String reportingPeriod;

	private String senderContactEmailAddressTxt;

	private String senderCountryCd;

	private int taxYear;

	private String transmittingCountry;

	public Cbcmasthdr() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBinaryEncodingSchemeCd() {
		return this.binaryEncodingSchemeCd;
	}

	public void setBinaryEncodingSchemeCd(String binaryEncodingSchemeCd) {
		this.binaryEncodingSchemeCd = binaryEncodingSchemeCd;
	}

	public String getCommunicationTypeCd() {
		return this.communicationTypeCd;
	}

	public void setCommunicationTypeCd(String communicationTypeCd) {
		this.communicationTypeCd = communicationTypeCd;
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

	public String getFileFormatCD() {
		return this.fileFormatCD;
	}

	public void setFileFormatCD(String fileFormatCD) {
		this.fileFormatCD = fileFormatCD;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
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

	public String getMyCBCID() {
		return this.myCBCID;
	}

	public void setMyCBCID(String myCBCID) {
		this.myCBCID = myCBCID;
	}

	public String getReportingPeriod() {
		return this.reportingPeriod;
	}

	public void setReportingPeriod(String reportingPeriod) {
		this.reportingPeriod = reportingPeriod;
	}

	public String getSenderContactEmailAddressTxt() {
		return this.senderContactEmailAddressTxt;
	}

	public void setSenderContactEmailAddressTxt(String senderContactEmailAddressTxt) {
		this.senderContactEmailAddressTxt = senderContactEmailAddressTxt;
	}

	public String getSenderCountryCd() {
		return this.senderCountryCd;
	}

	public void setSenderCountryCd(String senderCountryCd) {
		this.senderCountryCd = senderCountryCd;
	}

	public int getTaxYear() {
		return this.taxYear;
	}

	public void setTaxYear(int taxYear) {
		this.taxYear = taxYear;
	}

	public String getTransmittingCountry() {
		return this.transmittingCountry;
	}

	public void setTransmittingCountry(String transmittingCountry) {
		this.transmittingCountry = transmittingCountry;
	}

}