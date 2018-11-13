package com.censof.myfi.hidefmyfi.vo;

import java.io.File;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
/**
 * 
 * @author CSM-Ven
 *
 */
public class UserProfileVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileTypeIndic;
	private String sendingcountry;
	private String receivingcountry;
	private List<RecievingCountryVo> recievingCountryList;
	private String msgType;
	private String sendContactEmailAddress;
	private String communicationType;
	private String fileformatCode;
	private String binaryEncoding;
	private String messageRefID;
	private MultipartFile configurationFile;
	private String configurationFileText;
	private MultipartFile publicCertPath;
	private String publicCertFileName;
	private String docRefID;
	private List<NameVo> nameList;
	private List<IdentificationNumberVo> identityList;
	private String ctsTransId;
	private BigInteger userProfileId;
	
	public String getSendingcountry() {
		return sendingcountry;
	}
	public void setSendingcountry(String sendingcountry) {
		this.sendingcountry = sendingcountry;
	}
	public String getReceivingcountry() {
		return receivingcountry;
	}
	public void setReceivingcountry(String receivingcountry) {
		this.receivingcountry = receivingcountry;
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
	public String getCommunicationType() {
		return communicationType;
	}
	public void setCommunicationType(String communicationType) {
		this.communicationType = communicationType;
	}
	public String getFileformatCode() {
		return fileformatCode;
	}
	public void setFileformatCode(String fileformatCode) {
		this.fileformatCode = fileformatCode;
	}
	public String getBinaryEncoding() {
		return binaryEncoding;
	}
	public void setBinaryEncoding(String binaryEncoding) {
		this.binaryEncoding = binaryEncoding;
	}
	public String getMessageRefID() {
		return messageRefID;
	}
	public void setMessageRefID(String messageRefID) {
		this.messageRefID = messageRefID;
	}
	public MultipartFile getConfigurationFile() {
		return configurationFile;
	}
	public void setConfigurationFile(MultipartFile configurationFile) {
		this.configurationFile = configurationFile;
	}
	public MultipartFile getPublicCertPath() {
		return publicCertPath;
	}
	public void setPublicCertPath(MultipartFile publicCertPath) {
		this.publicCertPath = publicCertPath;
	}
	public String getDocRefID() {
		return docRefID;
	}
	public void setDocRefID(String docRefID) {
		this.docRefID = docRefID;
	}
	public List<NameVo> getNameList() {
		return nameList;
	}
	public void setNameList(List<NameVo> nameList) {
		this.nameList = nameList;
	}
	public List<IdentificationNumberVo> getIdentityList() {
		return identityList;
	}
	public void setIdentityList(List<IdentificationNumberVo> identityList) {
		this.identityList = identityList;
	}
	public String getConfigurationFileText() {
		return configurationFileText;
	}
	public void setConfigurationFileText(String configurationFileText) {
		this.configurationFileText = configurationFileText;
	}
	public String getCtsTransId() {
		return ctsTransId;
	}
	public void setCtsTransId(String ctsTransId) {
		this.ctsTransId = ctsTransId;
	}
	public String getPublicCertFileName() {
		return publicCertFileName;
	}
	public void setPublicCertFileName(String publicCertFileName) {
		this.publicCertFileName = publicCertFileName;
	}
	public String getFileTypeIndic() {
		return fileTypeIndic;
	}
	public void setFileTypeIndic(String fileTypeIndic) {
		this.fileTypeIndic = fileTypeIndic;
	}
	public List<RecievingCountryVo> getRecievingCountryList() {
		return recievingCountryList;
	}
	public void setRecievingCountryList(List<RecievingCountryVo> recievingCountryList) {
		this.recievingCountryList = recievingCountryList;
	}
	public BigInteger getUserProfileId() {
		return userProfileId;
	}
	public void setUserProfileId(BigInteger userProfileId) {
		this.userProfileId = userProfileId;
	}
	
	
	
	

}
