package com.censof.myfi.hidefmyfi.vo;

import java.io.Serializable;
import java.util.List;

public class CrsMetadataVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sendingCountry;
	private String receivingCountry;
	private String messageTypeIndic;
	private String messageType;
	private String warning;
	private String contact;
	private String reportingPeriod;
	private String taxYear;
	private String fileCreationTimestramp;
	private String communicationType;
	private String senderFileId;
	private String fileFormatCode;
	private String binaryEncoding;
	private String messageReferenceId;
	private String senderContactEmail;
	private String sendingCompanyIn;
	private String corMessageReferenceId;
	private List<RecievingCountryVo> recievingCountryList;
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
	public String getMessageTypeIndic() {
		return messageTypeIndic;
	}
	public void setMessageTypeIndic(String messageTypeIndic) {
		this.messageTypeIndic = messageTypeIndic;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
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
	public String getFileCreationTimestramp() {
		return fileCreationTimestramp;
	}
	public void setFileCreationTimestramp(String fileCreationTimestramp) {
		this.fileCreationTimestramp = fileCreationTimestramp;
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
	public String getMessageReferenceId() {
		return messageReferenceId;
	}
	public void setMessageReferenceId(String messageReferenceId) {
		this.messageReferenceId = messageReferenceId;
	}
	public String getSenderContactEmail() {
		return senderContactEmail;
	}
	public void setSenderContactEmail(String senderContactEmail) {
		this.senderContactEmail = senderContactEmail;
	}
	public String getSendingCompanyIn() {
		return sendingCompanyIn;
	}
	public void setSendingCompanyIn(String sendingCompanyIn) {
		this.sendingCompanyIn = sendingCompanyIn;
	}
	public List<RecievingCountryVo> getRecievingCountryList() {
		return recievingCountryList;
	}
	public void setRecievingCountryList(
			List<RecievingCountryVo> recievingCountryList) {
		this.recievingCountryList = recievingCountryList;
	}
	public String getCorMessageReferenceId() {
		return corMessageReferenceId;
	}
	public void setCorMessageReferenceId(String corMessageReferenceId) {
		this.corMessageReferenceId = corMessageReferenceId;
	}
	
	
	
	
	
	
	

}
