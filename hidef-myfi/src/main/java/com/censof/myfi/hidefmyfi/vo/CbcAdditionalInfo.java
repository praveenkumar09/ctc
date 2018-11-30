package com.censof.myfi.hidefmyfi.vo;

import java.io.Serializable;
import java.util.List;

public class CbcAdditionalInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String documentTypeIndic;
	private String documentReferenceId;
	private String 	corrMessageRefId;
	private String corDocumentRefId;
	private String otherInfo;
	private List<ResidentCountryVo> residentCountryList;
	private List<SummaryVo> summaryList;
	private int id;
	
	private int excelId;
	
	public String getDocumentTypeIndic() {
		return documentTypeIndic;
	}
	public void setDocumentTypeIndic(String documentTypeIndic) {
		this.documentTypeIndic = documentTypeIndic;
	}
	public String getDocumentReferenceId() {
		return documentReferenceId;
	}
	public void setDocumentReferenceId(String documentReferenceId) {
		this.documentReferenceId = documentReferenceId;
	}
	public String getCorrMessageRefId() {
		return corrMessageRefId;
	}
	public void setCorrMessageRefId(String corrMessageRefId) {
		this.corrMessageRefId = corrMessageRefId;
	}
	public String getCorDocumentRefId() {
		return corDocumentRefId;
	}
	public void setCorDocumentRefId(String corDocumentRefId) {
		this.corDocumentRefId = corDocumentRefId;
	}
	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	public List<ResidentCountryVo> getResidentCountryList() {
		return residentCountryList;
	}
	public void setResidentCountryList(List<ResidentCountryVo> residentCountryList) {
		this.residentCountryList = residentCountryList;
	}
	public List<SummaryVo> getSummaryList() {
		return summaryList;
	}
	public void setSummaryList(List<SummaryVo> summaryList) {
		this.summaryList = summaryList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getExcelId() {
		return excelId;
	}
	public void setExcelId(int excelId) {
		this.excelId = excelId;
	}
	
	
	
	
	
	

}
