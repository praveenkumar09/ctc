package com.censof.myfi.hidefmyfi.vo;

import java.math.BigInteger;
import java.util.List;

import javax.validation.constraints.NotEmpty;

public class MetaDataVo{
	
	@NotEmpty
	private String sendingCountry;
	private String receivingCountry;
	private String msgType;
	private String messageTypeIndic;
	private List<RecievingCountryVo> recievingCountryList;
	private String sendContactEmailAddress;
	private String warning;
	@NotEmpty(message="Contact person should not empty")
	private String contact;
	private String reportingPeriod;
	private String taxYear;
	private String formCreationTimeStamp;
	private String communicationType;
	private String senderFileId;
	private String fileFormatCode;
	private String binaryEncoding;
	private String messageRefId;
	private String senderContactEmailAddress;
	private String sendingCompanyIN;
	private String language;
	private BigInteger id;
	public String getSendingCountry() {
		return sendingCountry;
	}
	public void setSendingCountry(String sendingCountry) {
		this.sendingCountry = sendingCountry;
	}
	public String getReceivingCountry() {
		return receivingCountry;
	}
	public void setReceivingCountry(String receivingCountry) {
		this.receivingCountry = receivingCountry;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getSendContactEmailAddress() {
		return sendContactEmailAddress;
	}
	public void setSendContactEmailAddress(String sendContactEmailAddress) {
		this.sendContactEmailAddress = sendContactEmailAddress;
	}
	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
		this.warning = warning;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getReportingPeriod() {
		return reportingPeriod;
	}
	public void setReportingPeriod(String reportingPeriod) {
		this.reportingPeriod = reportingPeriod;
	}
	public String getTaxYear() {
		return taxYear;
	}
	public void setTaxYear(String taxYear) {
		this.taxYear = taxYear;
	}
	public String getFormCreationTimeStamp() {
		return formCreationTimeStamp;
	}
	public void setFormCreationTimeStamp(String formCreationTimeStamp) {
		this.formCreationTimeStamp = formCreationTimeStamp;
	}
	public String getCommunicationType() {
		return communicationType;
	}
	public void setCommunicationType(String communicationType) {
		this.communicationType = communicationType;
	}
	public String getSenderFileId() {
		return senderFileId;
	}
	public void setSenderFileId(String senderFileId) {
		this.senderFileId = senderFileId;
	}
	public String getFileFormatCode() {
		return fileFormatCode;
	}
	public void setFileFormatCode(String fileFormatCode) {
		this.fileFormatCode = fileFormatCode;
	}
	public String getBinaryEncoding() {
		return binaryEncoding;
	}
	public void setBinaryEncoding(String binaryEncoding) {
		this.binaryEncoding = binaryEncoding;
	}
	public String getMessageRefId() {
		return messageRefId;
	}
	public void setMessageRefId(String messageRefId) {
		this.messageRefId = messageRefId;
	}
	public String getSenderContactEmailAddress() {
		return senderContactEmailAddress;
	}
	public void setSenderContactEmailAddress(String senderContactEmailAddress) {
		this.senderContactEmailAddress = senderContactEmailAddress;
	}
	public String getSendingCompanyIN() {
		return sendingCompanyIN;
	}
	public void setSendingCompanyIN(String sendingCompanyIN) {
		this.sendingCompanyIN = sendingCompanyIN;
	}
	public String getMessageTypeIndic() {
		return messageTypeIndic;
	}
	public void setMessageTypeIndic(String messageTypeIndic) {
		this.messageTypeIndic = messageTypeIndic;
	}
	public List<RecievingCountryVo> getRecievingCountryList() {
		return recievingCountryList;
	}
	public void setRecievingCountryList(List<RecievingCountryVo> recievingCountryList) {
		this.recievingCountryList = recievingCountryList;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	
	
	
	
	
	
	
	

}
