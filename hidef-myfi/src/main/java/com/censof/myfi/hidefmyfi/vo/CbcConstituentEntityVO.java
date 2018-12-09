package com.censof.myfi.hidefmyfi.vo;

import java.io.Serializable;
import java.util.List;

public class CbcConstituentEntityVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tin;
	private String tinType;
	private String issuedBy;
	private String incorpCountryCode;
	private String otherEntityInfo;
	private List<ResidentCountryVo> residentCountryList;
	private List<NameVo> nameList;
	private List<AddressVo> addressList;
	private List<OrganisationInTypeVo> organisationInTypeList;
	private List<BizActivitiesTypeVo> bizActivitiesList;
	private int consId;
	private AddressVo addressVo;
	private AddressVo viewAddressVo;
	private AddressVo editAddressVo;
	private int excelId;
	private String ceIndicator;
	
	public String getTin() {
		return tin;
	}
	public void setTin(String tin) {
		this.tin = tin;
	}
	public String getTinType() {
		return tinType;
	}
	public void setTinType(String tinType) {
		this.tinType = tinType;
	}
	public String getIssuedBy() {
		return issuedBy;
	}
	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}
	public String getIncorpCountryCode() {
		return incorpCountryCode;
	}
	public void setIncorpCountryCode(String incorpCountryCode) {
		this.incorpCountryCode = incorpCountryCode;
	}
	public String getOtherEntityInfo() {
		return otherEntityInfo;
	}
	public void setOtherEntityInfo(String otherEntityInfo) {
		this.otherEntityInfo = otherEntityInfo;
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
	public List<AddressVo> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<AddressVo> addressList) {
		this.addressList = addressList;
	}
	public List<OrganisationInTypeVo> getOrganisationInTypeList() {
		return organisationInTypeList;
	}
	public void setOrganisationInTypeList(List<OrganisationInTypeVo> organisationInTypeList) {
		this.organisationInTypeList = organisationInTypeList;
	}
	public List<BizActivitiesTypeVo> getBizActivitiesList() {
		return bizActivitiesList;
	}
	public void setBizActivitiesList(List<BizActivitiesTypeVo> bizActivitiesList) {
		this.bizActivitiesList = bizActivitiesList;
	}
	public int getConsId() {
		return consId;
	}
	public void setConsId(int consId) {
		this.consId = consId;
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
	public int getExcelId() {
		return excelId;
	}
	public void setExcelId(int excelId) {
		this.excelId = excelId;
	}
	public String getCeIndicator() {
		return ceIndicator;
	}
	public void setCeIndicator(String ceIndicator) {
		this.ceIndicator = ceIndicator;
	}

}
