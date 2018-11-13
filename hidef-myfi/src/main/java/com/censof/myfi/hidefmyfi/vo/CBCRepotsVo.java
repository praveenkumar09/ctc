package com.censof.myfi.hidefmyfi.vo;

import java.io.Serializable;
import java.util.List;

public class CBCRepotsVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String documentTypeIndicator;
	private String documentRefId;
	private String corrMessageRefId;
	private String corrDocRefId;
	private String residentCountryCode;
	private String nbEmployees;
	private String unrelateCurrCode;
	private String unrelatedAmount;
	private String relatedCurrCode;
	private String relatedAmount;
	private String totalRevenueCurrCode;
	private String totalRevenueAmount;
	private String profitorlossCurrCode;
	private String prfitotloassAmount;
	private String 	taxpiadCurrCode;
	private String taxpaidAmount;
	private String taxaccruedCurrCode;
	private String taxaccruedAmount;
	private String capitalCurrCode;
	private String capitalAmount;
	private String earningCurrCode;
	private String earningAmount;
	private String assertCurrCode;
	private String assertAmount;
 
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
	private AddressVo addressVo;
	private AddressVo viewAddressVo;
	private AddressVo editAddressVo;
	private int id;
	private int consId;
	
	public String getDocumentTypeIndicator() {
		return documentTypeIndicator;
	}
	public void setDocumentTypeIndicator(String documentTypeIndicator) {
		this.documentTypeIndicator = documentTypeIndicator;
	}
	public String getDocumentRefId() {
		return documentRefId;
	}
	public void setDocumentRefId(String documentRefId) {
		this.documentRefId = documentRefId;
	}
	public String getCorrMessageRefId() {
		return corrMessageRefId;
	}
	public void setCorrMessageRefId(String corrMessageRefId) {
		this.corrMessageRefId = corrMessageRefId;
	}
	public String getCorrDocRefId() {
		return corrDocRefId;
	}
	public void setCorrDocRefId(String corrDocRefId) {
		this.corrDocRefId = corrDocRefId;
	}
	public String getResidentCountryCode() {
		return residentCountryCode;
	}
	public void setResidentCountryCode(String residentCountryCode) {
		this.residentCountryCode = residentCountryCode;
	}
	public String getNbEmployees() {
		return nbEmployees;
	}
	public void setNbEmployees(String nbEmployees) {
		this.nbEmployees = nbEmployees;
	}
	
	public String getRelatedAmount() {
		return relatedAmount;
	}
	public void setRelatedAmount(String relatedAmount) {
		this.relatedAmount = relatedAmount;
	}
	
	public String getTotalRevenueAmount() {
		return totalRevenueAmount;
	}
	public void setTotalRevenueAmount(String totalRevenueAmount) {
		this.totalRevenueAmount = totalRevenueAmount;
	}
	
	public String getPrfitotloassAmount() {
		return prfitotloassAmount;
	}
	public void setPrfitotloassAmount(String prfitotloassAmount) {
		this.prfitotloassAmount = prfitotloassAmount;
	}
	
	public String getTaxpaidAmount() {
		return taxpaidAmount;
	}
	public void setTaxpaidAmount(String taxpaidAmount) {
		this.taxpaidAmount = taxpaidAmount;
	}
	
	public String getTaxaccruedAmount() {
		return taxaccruedAmount;
	}
	public void setTaxaccruedAmount(String taxaccruedAmount) {
		this.taxaccruedAmount = taxaccruedAmount;
	}
	
	public String getCapitalAmount() {
		return capitalAmount;
	}
	public void setCapitalAmount(String capitalAmount) {
		this.capitalAmount = capitalAmount;
	}
	
	public String getEarningAmount() {
		return earningAmount;
	}
	public void setEarningAmount(String earningAmount) {
		this.earningAmount = earningAmount;
	}
	
	public String getAssertAmount() {
		return assertAmount;
	}
	public void setAssertAmount(String assertAmount) {
		this.assertAmount = assertAmount;
	}
	public String getTin() {
		return tin;
	}
	public void setTin(String tin) {
		this.tin = tin;
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
	public void setOrganisationInTypeList(
			List<OrganisationInTypeVo> organisationInTypeList) {
		this.organisationInTypeList = organisationInTypeList;
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
	public List<BizActivitiesTypeVo> getBizActivitiesList() {
		return bizActivitiesList;
	}
	public void setBizActivitiesList(List<BizActivitiesTypeVo> bizActivitiesList) {
		this.bizActivitiesList = bizActivitiesList;
	}
	public String getUnrelateCurrCode() {
		return unrelateCurrCode;
	}
	public void setUnrelateCurrCode(String unrelateCurrCode) {
		this.unrelateCurrCode = unrelateCurrCode;
	}
	public String getUnrelatedAmount() {
		return unrelatedAmount;
	}
	public void setUnrelatedAmount(String unrelatedAmount) {
		this.unrelatedAmount = unrelatedAmount;
	}
	public String getRelatedCurrCode() {
		return relatedCurrCode;
	}
	public void setRelatedCurrCode(String relatedCurrCode) {
		this.relatedCurrCode = relatedCurrCode;
	}
	public String getTotalRevenueCurrCode() {
		return totalRevenueCurrCode;
	}
	public void setTotalRevenueCurrCode(String totalRevenueCurrCode) {
		this.totalRevenueCurrCode = totalRevenueCurrCode;
	}
	public String getProfitorlossCurrCode() {
		return profitorlossCurrCode;
	}
	public void setProfitorlossCurrCode(String profitorlossCurrCode) {
		this.profitorlossCurrCode = profitorlossCurrCode;
	}
	public String getTaxpiadCurrCode() {
		return taxpiadCurrCode;
	}
	public void setTaxpiadCurrCode(String taxpiadCurrCode) {
		this.taxpiadCurrCode = taxpiadCurrCode;
	}
	public String getTaxaccruedCurrCode() {
		return taxaccruedCurrCode;
	}
	public void setTaxaccruedCurrCode(String taxaccruedCurrCode) {
		this.taxaccruedCurrCode = taxaccruedCurrCode;
	}
	public String getCapitalCurrCode() {
		return capitalCurrCode;
	}
	public void setCapitalCurrCode(String capitalCurrCode) {
		this.capitalCurrCode = capitalCurrCode;
	}
	public String getEarningCurrCode() {
		return earningCurrCode;
	}
	public void setEarningCurrCode(String earningCurrCode) {
		this.earningCurrCode = earningCurrCode;
	}
	public String getAssertCurrCode() {
		return assertCurrCode;
	}
	public void setAssertCurrCode(String assertCurrCode) {
		this.assertCurrCode = assertCurrCode;
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
	public int getConsId() {
		return consId;
	}
	public void setConsId(int consId) {
		this.consId = consId;
	}
	
	
	
	
	
	
	
	
	

}
