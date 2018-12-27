package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the cbcpayldhdr database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcpayldhdr.findAll", query="SELECT c FROM Cbcpayldhdr c")
public class Cbcpayldhdr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")//
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger id;

	@Column(name="filename")
	private String filename;
	
	@Column(name="binaryencodingschemecd")
	private String binaryEncodingSchemeCd;

	@Column(name="communicationtypecd")
	private String communicationTypeCd;

	@Column(name="contact")
	private String contact;

	@Column(name="createby")
	private String createBy;

	@Column(name="createdatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	@Column(name="filecreatets")
	private String fileCreateTs;

	@Column(name="fileformatcd")
	private String fileFormatCD;

	@Column(name="filerevisionind")
	private int fileRevisionInd;

	@Column(name="language")
	private String language;

	@Column(name="messagerefid")
	private String messageRefId;

	@Column(name="messagetype")
	private String messageType;

	@Column(name="messagetypeindic")
	private String messageTypeIndic;

	@Column(name="modifyby")
	private String modifyBy;

	@Column(name="modifydatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	@Column(name="msgtimestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date msgTimestamp;

	@Column(name="mycbcid")
	private String myCBCID;
	
	
	@Column(name="originalctstransmissionid")
	private String originalCTSTransmissionId;

	@Column(name="reportingperiod")
	private String reportingPeriod;

	@Column(name="sendercontactemailaddresstxt")
	private String senderContactEmailAddressTxt;

	@Column(name="sendercountrycd")
	private String senderCountryCd;

	@Column(name="senderfileid")
	private String senderFileId;

	@Column(name="sendingentityin")
	private String sendingEntityIN;

	@Column(name="taxyear")
	private int taxYear;

	@Column(name="transmittingcountry")
	private String transmittingCountry;

	@Column(name="warning")
	@Lob
	private String warning;
	
	@Column(name="is_deleted")
	private Integer isdeleted;
	
	@Column(name="filetypeindic")
	private String filetypeindic;

	public Cbcpayldhdr() {
	}

	

	public BigInteger getId() {
		return id;
	}



	public void setId(BigInteger id) {
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

	public String getFileCreateTs() {
		return this.fileCreateTs;
	}

	public void setFileCreateTs(String fileCreateTs) {
		this.fileCreateTs = fileCreateTs;
	}

	public String getFileFormatCD() {
		return this.fileFormatCD;
	}

	public void setFileFormatCD(String fileFormatCD) {
		this.fileFormatCD = fileFormatCD;
	}

	public int getFileRevisionInd() {
		return this.fileRevisionInd;
	}

	public void setFileRevisionInd(int fileRevisionInd) {
		this.fileRevisionInd = fileRevisionInd;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
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

	public String getMyCBCID() {
		return this.myCBCID;
	}

	public void setMyCBCID(String myCBCID) {
		this.myCBCID = myCBCID;
	}

	public String getOriginalCTSTransmissionId() {
		return this.originalCTSTransmissionId;
	}

	public void setOriginalCTSTransmissionId(String originalCTSTransmissionId) {
		this.originalCTSTransmissionId = originalCTSTransmissionId;
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

	public String getSenderFileId() {
		return this.senderFileId;
	}

	public void setSenderFileId(String senderFileId) {
		this.senderFileId = senderFileId;
	}

	public String getSendingEntityIN() {
		return this.sendingEntityIN;
	}

	public void setSendingEntityIN(String sendingEntityIN) {
		this.sendingEntityIN = sendingEntityIN;
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

	public String getWarning() {
		return this.warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}



	public Integer getIsdeleted() {
		return isdeleted;
	}



	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}



	public String getFilename() {
		return filename;
	}



	public void setFilename(String filename) {
		this.filename = filename;
	}



	public String getFiletypeindic() {
		return filetypeindic;
	}



	public void setFiletypeindic(String filetypeindic) {
		this.filetypeindic = filetypeindic;
	}
	
	
	
	
	

}