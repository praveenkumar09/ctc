package com.censof.myfi.hidefmyfi.vo;

import java.math.BigInteger;


public class CRSSummaryGridVo {
	private int id;
	private String sendingCountry;
	private String messageType;
	private BigInteger hrdId;
	private String filename;
	private String createdDateTime;
	
	/*private HidefVo hidefvo;*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSendingCountry() {
		return sendingCountry;
	}
	public void setSendingCountry(String sendingCountry) {
		this.sendingCountry = sendingCountry;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	/*public HidefVo getHidefvo() {
		return hidefvo;
	}
	public void setHidefvo(HidefVo hidefvo) {
		this.hidefvo = hidefvo;
	}*/
	public BigInteger getHrdId() {
		return hrdId;
	}
	public void setHrdId(BigInteger hrdId) {
		this.hrdId = hrdId;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	
	
	
	
	
	
	

}
