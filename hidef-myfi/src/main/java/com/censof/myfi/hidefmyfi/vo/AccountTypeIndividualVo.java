package com.censof.myfi.hidefmyfi.vo;

import java.util.List;

public class AccountTypeIndividualVo {
	
	private List<ResidentCountryVo> individualResidentCountryList;
	private List<NameTypeVo> individualNameList;
	private List<OrganisationInTypeVo> individualOrganisationInTypeList;
	private List<AddressVo> individualAddressList;
	private AddressVo addressVo;
	private AddressVo viewAddressVo;
	private AddressVo editAddressVo;
	private String birthDate;
	private String city;
	private String citySubEntity;
	private String countryCode;
	private String countryName;
	private int id;
	public List<ResidentCountryVo> getIndividualResidentCountryList() {
		return individualResidentCountryList;
	}
	public void setIndividualResidentCountryList(
			List<ResidentCountryVo> individualResidentCountryList) {
		this.individualResidentCountryList = individualResidentCountryList;
	}
	public List<NameTypeVo> getIndividualNameList() {
		return individualNameList;
	}
	public void setIndividualNameList(List<NameTypeVo> individualNameList) {
		this.individualNameList = individualNameList;
	}
	public List<OrganisationInTypeVo> getIndividualOrganisationInTypeList() {
		return individualOrganisationInTypeList;
	}
	public void setIndividualOrganisationInTypeList(
			List<OrganisationInTypeVo> individualOrganisationInTypeList) {
		this.individualOrganisationInTypeList = individualOrganisationInTypeList;
	}
	public List<AddressVo> getIndividualAddressList() {
		return individualAddressList;
	}
	public void setIndividualAddressList(List<AddressVo> individualAddressList) {
		this.individualAddressList = individualAddressList;
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
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCitySubEntity() {
		return citySubEntity;
	}
	public void setCitySubEntity(String citySubEntity) {
		this.citySubEntity = citySubEntity;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	

}
