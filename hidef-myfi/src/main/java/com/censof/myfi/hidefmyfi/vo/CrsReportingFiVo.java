package com.censof.myfi.hidefmyfi.vo;

import java.math.BigInteger;
import java.util.List;

public class CrsReportingFiVo {
	
	private String documentTypeIndic;
	private String docRefId;
	private String corMmsgRefId;
	private String corDocRefId;
	private List<ResidentCountryVo> residentCountryList;
	private List<NameVo> nameList;
	private List<OrganisationInTypeVo> organisationInTypeList;
	private List<AddressVo> addressList;
	private AddressVo addressVo;
	private AddressVo viewAddressVo;
	private AddressVo editAddressVo;
	private BigInteger id;
	public String getDocumentTypeIndic() {
		return documentTypeIndic;
	}
	public void setDocumentTypeIndic(String documentTypeIndic) {
		this.documentTypeIndic = documentTypeIndic;
	}
	public String getDocRefId() {
		return docRefId;
	}
	public void setDocRefId(String docRefId) {
		this.docRefId = docRefId;
	}
	public String getCorMmsgRefId() {
		return corMmsgRefId;
	}
	public void setCorMmsgRefId(String corMmsgRefId) {
		this.corMmsgRefId = corMmsgRefId;
	}
	public String getCorDocRefId() {
		return corDocRefId;
	}
	public void setCorDocRefId(String corDocRefId) {
		this.corDocRefId = corDocRefId;
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
	public void setOrganisationInTypeList(
			List<OrganisationInTypeVo> organisationInTypeList) {
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
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	
	
	
	
	

}
