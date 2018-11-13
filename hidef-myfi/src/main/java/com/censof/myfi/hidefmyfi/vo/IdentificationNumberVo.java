package com.censof.myfi.hidefmyfi.vo;

import java.io.Serializable;

public class IdentificationNumberVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String idNumber;
	private String inType;
	private String issuedBy;
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getInType() {
		return inType;
	}
	public void setInType(String inType) {
		this.inType = inType;
	}
	public String getIssuedBy() {
		return issuedBy;
	}
	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	

}
