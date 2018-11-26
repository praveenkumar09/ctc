package com.censof.myfi.hidefmyfi.vo;

import java.util.List;

public class ControllingPersonVo {
	
	private String controllingPersonType;
	private List<ResidentCountryVo> controllingResidentCountryList;
	private List<OrganisationInTypeVo> controllingOrganisationInTypeList;
	private List<NameTypeVo> nameTypeList;
	private List<AddressVo> controllingPersonAddressList;
	private String birthDate;
	private String city;
	private String citySubEntity;
	private String countryCode;
	private String countryName;
	private int id;
	public String getControllingPersonType() {
		return controllingPersonType;
	}
	public void setControllingPersonType(String controllingPersonType) {
		this.controllingPersonType = controllingPersonType;
	}
	public List<ResidentCountryVo> getControllingResidentCountryList() {
		return controllingResidentCountryList;
	}
	public void setControllingResidentCountryList(
			List<ResidentCountryVo> controllingResidentCountryList) {
		this.controllingResidentCountryList = controllingResidentCountryList;
	}
	public List<OrganisationInTypeVo> getControllingOrganisationInTypeList() {
		return controllingOrganisationInTypeList;
	}
	public void setControllingOrganisationInTypeList(
			List<OrganisationInTypeVo> controllingOrganisationInTypeList) {
		this.controllingOrganisationInTypeList = controllingOrganisationInTypeList;
	}
	public List<NameTypeVo> getNameTypeList() {
		return nameTypeList;
	}
	public void setNameTypeList(List<NameTypeVo> nameTypeList) {
		this.nameTypeList = nameTypeList;
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
	public List<AddressVo> getControllingPersonAddressList() {
		return controllingPersonAddressList;
	}
	public void setControllingPersonAddressList(
			List<AddressVo> controllingPersonAddressList) {
		this.controllingPersonAddressList = controllingPersonAddressList;
	}
	
	
	
	

}
