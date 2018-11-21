package com.censof.myfi.hidefmyfi.vo;

import java.util.List;

public class AccountTypeOrganisationVo {
	
	private List<ResidentCountryVo> organisationResidentCountryList;
	private List<NameTypeVo> organisationNameList;
	private List<OrganisationInTypeVo> orgOrganisationInTypeList;
	private List<AddressVo> organisationAddressList;
	private AddressVo addressVo;
	private AddressVo viewAddressVo;
	private AddressVo editAddressVo;
	private int id;
	public List<ResidentCountryVo> getOrganisationResidentCountryList() {
		return organisationResidentCountryList;
	}
	public void setOrganisationResidentCountryList(
			List<ResidentCountryVo> organisationResidentCountryList) {
		this.organisationResidentCountryList = organisationResidentCountryList;
	}
	public List<NameTypeVo> getOrganisationNameList() {
		return organisationNameList;
	}
	public void setOrganisationNameList(List<NameTypeVo> organisationNameList) {
		this.organisationNameList = organisationNameList;
	}
	public List<OrganisationInTypeVo> getOrgOrganisationInTypeList() {
		return orgOrganisationInTypeList;
	}
	public void setOrgOrganisationInTypeList(
			List<OrganisationInTypeVo> orgOrganisationInTypeList) {
		this.orgOrganisationInTypeList = orgOrganisationInTypeList;
	}
	public List<AddressVo> getOrganisationAddressList() {
		return organisationAddressList;
	}
	public void setOrganisationAddressList(List<AddressVo> organisationAddressList) {
		this.organisationAddressList = organisationAddressList;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	

}
