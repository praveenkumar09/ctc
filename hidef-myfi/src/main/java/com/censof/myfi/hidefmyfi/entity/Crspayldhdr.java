package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the crspayldhdr database table.
 * 
 */
@Entity
@NamedQuery(name="Crspayldhdr.findAll", query="SELECT c FROM Crspayldhdr c")
public class Crspayldhdr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	/*@TableGenerator(name="TABLE_GEN", table="SYS_SEQ", pkColumnName="SEQ_NAME", valueColumnName="SEQ_VALUE", pkColumnValue="GEN_BEN_CLAIM_DETAIL", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	@Column(name="id")*/
	private BigInteger id;

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

	@Column(name="crsid")
	private BigInteger crsid;

	@Column(name="filecreatets")
	private String fileCreateTs;

	@Column(name="fileformatcd")
	private String fileFormatCD;

	@Column(name="filerevisionind")
	private Integer fileRevisionInd;

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

	@Column(name="originalidestransmissionid")
	private String originalIDESTransmissionId;

	@Column(name="receivercountrycd")
	private String receiverCountryCd;

	@Column(name="receivingcountry")
	private String receivingCountry;

	@Column(name="reportingperiod")
	private String reportingPeriod;

	@Column(name="sendercontactemailaddresstxt")
	private String senderContactEmailAddressTxt;

	@Column(name="sendercountrycd")
	private String senderCountryCd;

	@Column(name="senderfileid")
	private String senderFileId;

	@Column(name="sendingcompanyin")
	private String sendingCompanyIN;

	@Column(name="taxyear")
	private Integer taxYear;

	@Column(name="transmittingcountry")
	private String transmittingCountry;

	@Lob
	@Column(name="warning")
	private String warning;

	public Crspayldhdr() {
	}

	

	public BigInteger getId() {
		return id;
	}



	public void setId(BigInteger id) {
		this.id = id;
	}



	public void setCrsid(BigInteger crsid) {
		this.crsid = crsid;
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

	
	public BigInteger getCrsid() {
		return crsid;
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

	

	public Integer getFileRevisionInd() {
		return fileRevisionInd;
	}



	public void setFileRevisionInd(Integer fileRevisionInd) {
		this.fileRevisionInd = fileRevisionInd;
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

	public String getOriginalIDESTransmissionId() {
		return this.originalIDESTransmissionId;
	}

	public void setOriginalIDESTransmissionId(String originalIDESTransmissionId) {
		this.originalIDESTransmissionId = originalIDESTransmissionId;
	}

	public String getReceiverCountryCd() {
		return this.receiverCountryCd;
	}

	public void setReceiverCountryCd(String receiverCountryCd) {
		this.receiverCountryCd = receiverCountryCd;
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

	public String getSendingCompanyIN() {
		return this.sendingCompanyIN;
	}

	public void setSendingCompanyIN(String sendingCompanyIN) {
		this.sendingCompanyIN = sendingCompanyIN;
	}

	

	public Integer getTaxYear() {
		return taxYear;
	}



	public void setTaxYear(Integer taxYear) {
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

}