package com.censof.myfi.hidefmyfi.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class ReportingEntityVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tin;
	private String tinIssuedBy;
	private String tinType;
	private String reportingRole;
	private String documentTypeIndicator;
	private String documentReferenceId;
	private String corMessageReferenceId;
	private String corDocReferenceId;
	private List<ResidentCountryVo> residentCountryList;
	private List<NameVo> nameList;
	private List<OrganisationInTypeVo> organisationInTypeList;
	private List<AddressVo> addressList;
	private AddressVo addressVo;
	private AddressVo viewAddressVo;
	private AddressVo editAddressVo;
	private BigInteger id;
	public String getTin() {
		return tin;
	}
	public void setTin(String tin) {
		this.tin = tin;
	}
	public String getReportingRole() {
		return reportingRole;
	}
	public void setReportingRole(String reportingRole) {
		this.reportingRole = reportingRole;
	}
	public String getDocumentTypeIndicator() {
		return documentTypeIndicator;
	}
	public void setDocumentTypeIndicator(String documentTypeIndicator) {
		this.documentTypeIndicator = documentTypeIndicator;
	}
	public String getDocumentReferenceId() {
		return documentReferenceId;
	}
	public void setDocumentReferenceId(String documentReferenceId) {
		this.documentReferenceId = documentReferenceId;
	}
	public String getCorMessageReferenceId() {
		return corMessageReferenceId;
	}
	public void setCorMessageReferenceId(String corMessageReferenceId) {
		this.corMessageReferenceId = corMessageReferenceId;
	}
	public String getCorDocReferenceId() {
		return corDocReferenceId;
	}
	public void setCorDocReferenceId(String corDocReferenceId) {
		this.corDocReferenceId = corDocReferenceId;
	}
	public List<ResidentCountryVo> getResidentCountryList() {
		return residentCountryList;
	}
	public void setResidentCountryList(List<ResidentCountryVo> residentCountryList) {
		this.residentCountryList = residentCountryList;
	}
	public List<NameVo> getNameList() {
		return nameList;
	}
	public void setNameList(List<NameVo> nameList) {
		this.nameList = nameList;
	}
	public List<OrganisationInTypeVo> getOrganisationInTypeList() {
		return organisationInTypeList;
	}
	public void setOrganisationInTypeList(List<OrganisationInTypeVo> organisationInTypeList) {
		this.organisationInTypeList = organisationInTypeList;
	}
	public List<AddressVo> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<AddressVo> addressList) {
		this.addressList = addressList;
	}
	public AddressVo getAddressVo() {
		return addressVo;
	}
	public void setAddressVo(AddressVo addressVo) {
		this.addressVo = addressVo;
	}
	public AddressVo getViewAddressVo() {
		return viewAddressVo;
	}
	public void setViewAddressVo(AddressVo viewAddressVo) {
		this.viewAddressVo = viewAddressVo;
	}
	public AddressVo getEditAddressVo() {
		return editAddressVo;
	}
	public void setEditAddressVo(AddressVo editAddressVo) {
		this.editAddressVo = editAddressVo;
	}
	public String getTinIssuedBy() {
		return tinIssuedBy;
	}
	public void setTinIssuedBy(String tinIssuedBy) {
		this.tinIssuedBy = tinIssuedBy;
	}
	public String getTinType() {
		return tinType;
	}
	public void setTinType(String tinType) {
		this.tinType = tinType;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	
	
	
	
	
	
	
	
	
	

}
