package com.censof.myfi.hidefmyfi.vo;

import java.math.BigInteger;
import java.util.List;

public class AccountHolderVo {
	
	private String  documentTypeIndic;
	private String documentRefId;
	private String corMessageRefId;
	private String corMessageDocRefId;
	private String accountNumber;
	private List<String> accountNumberType;
	private String currency;
	private String accountBalance;
	private String accountHolderType;
	private String individualaccountHolderType;
	private List<PaymentTypeVo> paymentList;
	
	private ControllingPersonVo  controllingPersonVo;
	private List<ControllingPersonVo> controllingPersonList;
	private List<ResidentCountryVo> controllingPersonResidentCountryList;
	private List<OrganisationInTypeVo> controllingPersonInTypeList;
	private List<AddressVo> controllingPersonAddressList;
	private AddressVo controllingPersonaddressVo;
	private AddressVo controllingPersonviewAddressVo;
	private AddressVo controllingPersoneditAddressVo;
	private String ctrlbirthDate;
	private String ctrlcity;
	private String ctrlcitySubEntity;
	private String ctrlcountryCode;
	private String ctrlcountryName;
	private List<TitleVo> ctrlPersontitleList;
	private List<MiddleNameVo> ctrlPersonmiddlenameList;
	private List<GenerationIdentifierVo> ctrlPersongenerateIdentifilerList;
	private List<SuffixVo> ctrlPersonsuffixList;
	private List<NameTypeVo> ctrlPersonNameList;
	private NameTypeVo ctrlPersonName;
	private NameTypeVo ctrlPersonViewName;
	private NameTypeVo ctrlPersonEditName;
	
	
	
	/*private AccountTypeIndividualVo individualVo;
	private List<AccountTypeIndividualVo> individualList;*/
	private List<ResidentCountryVo> individualResidentCountryList;
	private List<NameTypeVo> individualNameList;
	private NameTypeVo individualName;
	private NameTypeVo individualViewName;
	private NameTypeVo individualEditName;
	private List<TitleVo> titleList;
	private List<MiddleNameVo> middlenameList;
	private List<GenerationIdentifierVo> generateIdentifilerList;
	private List<SuffixVo> suffixList;
	
	private List<OrganisationInTypeVo> individualOrganisationInTypeList;
	private List<AddressVo> individualAddressList;
	private AddressVo individualaddressVo;
	private AddressVo individualviewAddressVo;
	private AddressVo individualeditAddressVo;
	private String birthDate;
	private String city;
	private String citySubEntity;
	private String countryCode;
	private String countryName;
	
	
	/*private AccountTypeOrganisationVo organisationVo;
	private List<AccountTypeOrganisationVo> organisationList;*/
	
	private List<ResidentCountryVo> organisationResidentCountryList;
	private List<NameTypeVo> organisationNameList;
	private List<NameVo> organisationList;
	private List<OrganisationInTypeVo> orgOrganisationInTypeList;
	private List<AddressVo> organisationAddressList;
	private AddressVo organisationaddressVo;
	private AddressVo organisationviewAddressVo;
	private AddressVo organisationeditAddressVo;
	
	
	private int id;
	
	/*Controlling Person*/
	
	public String getDocumentTypeIndic() {
		return documentTypeIndic;
	}
	public void setDocumentTypeIndic(String documentTypeIndic) {
		this.documentTypeIndic = documentTypeIndic;
	}
	public String getDocumentRefId() {
		return documentRefId;
	}
	public void setDocumentRefId(String documentRefId) {
		this.documentRefId = documentRefId;
	}
	public String getCorMessageRefId() {
		return corMessageRefId;
	}
	public void setCorMessageRefId(String corMessageRefId) {
		this.corMessageRefId = corMessageRefId;
	}
	public String getCorMessageDocRefId() {
		return corMessageDocRefId;
	}
	public void setCorMessageDocRefId(String corMessageDocRefId) {
		this.corMessageDocRefId = corMessageDocRefId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	/*public String getAccountNumberType() {
		return accountNumberType;
	}
	public void setAccountNumberType(String accountNumberType) {
		this.accountNumberType = accountNumberType;
	}*/
	
	public String getCurrency() {
		return currency;
	}
	public List<String> getAccountNumberType() {
		return accountNumberType;
	}
	public void setAccountNumberType(List<String> accountNumberType) {
		this.accountNumberType = accountNumberType;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getAccountHolderType() {
		return accountHolderType;
	}
	public void setAccountHolderType(String accountHolderType) {
		this.accountHolderType = accountHolderType;
	}
	public List<PaymentTypeVo> getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(List<PaymentTypeVo> paymentList) {
		this.paymentList = paymentList;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ControllingPersonVo getControllingPersonVo() {
		return controllingPersonVo;
	}
	public void setControllingPersonVo(ControllingPersonVo controllingPersonVo) {
		this.controllingPersonVo = controllingPersonVo;
	}
	public List<ControllingPersonVo> getControllingPersonList() {
		return controllingPersonList;
	}
	public void setControllingPersonList(
			List<ControllingPersonVo> controllingPersonList) {
		this.controllingPersonList = controllingPersonList;
	}
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
	public AddressVo getIndividualaddressVo() {
		return individualaddressVo;
	}
	public void setIndividualaddressVo(AddressVo individualaddressVo) {
		this.individualaddressVo = individualaddressVo;
	}
	public AddressVo getIndividualviewAddressVo() {
		return individualviewAddressVo;
	}
	public void setIndividualviewAddressVo(AddressVo individualviewAddressVo) {
		this.individualviewAddressVo = individualviewAddressVo;
	}
	public AddressVo getIndividualeditAddressVo() {
		return individualeditAddressVo;
	}
	public void setIndividualeditAddressVo(AddressVo individualeditAddressVo) {
		this.individualeditAddressVo = individualeditAddressVo;
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
	public AddressVo getOrganisationaddressVo() {
		return organisationaddressVo;
	}
	public void setOrganisationaddressVo(AddressVo organisationaddressVo) {
		this.organisationaddressVo = organisationaddressVo;
	}
	public AddressVo getOrganisationviewAddressVo() {
		return organisationviewAddressVo;
	}
	public void setOrganisationviewAddressVo(AddressVo organisationviewAddressVo) {
		this.organisationviewAddressVo = organisationviewAddressVo;
	}
	public AddressVo getOrganisationeditAddressVo() {
		return organisationeditAddressVo;
	}
	public void setOrganisationeditAddressVo(AddressVo organisationeditAddressVo) {
		this.organisationeditAddressVo = organisationeditAddressVo;
	}
	public NameTypeVo getIndividualName() {
		return individualName;
	}
	public void setIndividualName(NameTypeVo individualName) {
		this.individualName = individualName;
	}
	public NameTypeVo getIndividualViewName() {
		return individualViewName;
	}
	public void setIndividualViewName(NameTypeVo individualViewName) {
		this.individualViewName = individualViewName;
	}
	public NameTypeVo getIndividualEditName() {
		return individualEditName;
	}
	public void setIndividualEditName(NameTypeVo individualEditName) {
		this.individualEditName = individualEditName;
	}
	public List<TitleVo> getTitleList() {
		return titleList;
	}
	public void setTitleList(List<TitleVo> titleList) {
		this.titleList = titleList;
	}
	public List<MiddleNameVo> getMiddlenameList() {
		return middlenameList;
	}
	public void setMiddlenameList(List<MiddleNameVo> middlenameList) {
		this.middlenameList = middlenameList;
	}
	public List<GenerationIdentifierVo> getGenerateIdentifilerList() {
		return generateIdentifilerList;
	}
	public void setGenerateIdentifilerList(
			List<GenerationIdentifierVo> generateIdentifilerList) {
		this.generateIdentifilerList = generateIdentifilerList;
	}
	public List<SuffixVo> getSuffixList() {
		return suffixList;
	}
	public void setSuffixList(List<SuffixVo> suffixList) {
		this.suffixList = suffixList;
	}
	public List<NameVo> getOrganisationList() {
		return organisationList;
	}
	public void setOrganisationList(List<NameVo> organisationList) {
		this.organisationList = organisationList;
	}
	public List<ResidentCountryVo> getControllingPersonResidentCountryList() {
		return controllingPersonResidentCountryList;
	}
	public void setControllingPersonResidentCountryList(
			List<ResidentCountryVo> controllingPersonResidentCountryList) {
		this.controllingPersonResidentCountryList = controllingPersonResidentCountryList;
	}
	public List<OrganisationInTypeVo> getControllingPersonInTypeList() {
		return controllingPersonInTypeList;
	}
	public void setControllingPersonInTypeList(
			List<OrganisationInTypeVo> controllingPersonInTypeList) {
		this.controllingPersonInTypeList = controllingPersonInTypeList;
	}
	public List<AddressVo> getControllingPersonAddressList() {
		return controllingPersonAddressList;
	}
	public void setControllingPersonAddressList(
			List<AddressVo> controllingPersonAddressList) {
		this.controllingPersonAddressList = controllingPersonAddressList;
	}
	public AddressVo getControllingPersonaddressVo() {
		return controllingPersonaddressVo;
	}
	public void setControllingPersonaddressVo(AddressVo controllingPersonaddressVo) {
		this.controllingPersonaddressVo = controllingPersonaddressVo;
	}
	public AddressVo getControllingPersonviewAddressVo() {
		return controllingPersonviewAddressVo;
	}
	public void setControllingPersonviewAddressVo(
			AddressVo controllingPersonviewAddressVo) {
		this.controllingPersonviewAddressVo = controllingPersonviewAddressVo;
	}
	public AddressVo getControllingPersoneditAddressVo() {
		return controllingPersoneditAddressVo;
	}
	public void setControllingPersoneditAddressVo(
			AddressVo controllingPersoneditAddressVo) {
		this.controllingPersoneditAddressVo = controllingPersoneditAddressVo;
	}
	public String getCtrlbirthDate() {
		return ctrlbirthDate;
	}
	public void setCtrlbirthDate(String ctrlbirthDate) {
		this.ctrlbirthDate = ctrlbirthDate;
	}
	public String getCtrlcity() {
		return ctrlcity;
	}
	public void setCtrlcity(String ctrlcity) {
		this.ctrlcity = ctrlcity;
	}
	public String getCtrlcitySubEntity() {
		return ctrlcitySubEntity;
	}
	public void setCtrlcitySubEntity(String ctrlcitySubEntity) {
		this.ctrlcitySubEntity = ctrlcitySubEntity;
	}
	public String getCtrlcountryCode() {
		return ctrlcountryCode;
	}
	public void setCtrlcountryCode(String ctrlcountryCode) {
		this.ctrlcountryCode = ctrlcountryCode;
	}
	public String getCtrlcountryName() {
		return ctrlcountryName;
	}
	public void setCtrlcountryName(String ctrlcountryName) {
		this.ctrlcountryName = ctrlcountryName;
	}
	public String getIndividualaccountHolderType() {
		return individualaccountHolderType;
	}
	public void setIndividualaccountHolderType(String individualaccountHolderType) {
		this.individualaccountHolderType = individualaccountHolderType;
	}
	public List<TitleVo> getCtrlPersontitleList() {
		return ctrlPersontitleList;
	}
	public void setCtrlPersontitleList(List<TitleVo> ctrlPersontitleList) {
		this.ctrlPersontitleList = ctrlPersontitleList;
	}
	public List<MiddleNameVo> getCtrlPersonmiddlenameList() {
		return ctrlPersonmiddlenameList;
	}
	public void setCtrlPersonmiddlenameList(
			List<MiddleNameVo> ctrlPersonmiddlenameList) {
		this.ctrlPersonmiddlenameList = ctrlPersonmiddlenameList;
	}
	public List<GenerationIdentifierVo> getCtrlPersongenerateIdentifilerList() {
		return ctrlPersongenerateIdentifilerList;
	}
	public void setCtrlPersongenerateIdentifilerList(
			List<GenerationIdentifierVo> ctrlPersongenerateIdentifilerList) {
		this.ctrlPersongenerateIdentifilerList = ctrlPersongenerateIdentifilerList;
	}
	public List<SuffixVo> getCtrlPersonsuffixList() {
		return ctrlPersonsuffixList;
	}
	public void setCtrlPersonsuffixList(List<SuffixVo> ctrlPersonsuffixList) {
		this.ctrlPersonsuffixList = ctrlPersonsuffixList;
	}
	public List<NameTypeVo> getCtrlPersonNameList() {
		return ctrlPersonNameList;
	}
	public void setCtrlPersonNameList(List<NameTypeVo> ctrlPersonNameList) {
		this.ctrlPersonNameList = ctrlPersonNameList;
	}
	public NameTypeVo getCtrlPersonName() {
		return ctrlPersonName;
	}
	public void setCtrlPersonName(NameTypeVo ctrlPersonName) {
		this.ctrlPersonName = ctrlPersonName;
	}
	public NameTypeVo getCtrlPersonViewName() {
		return ctrlPersonViewName;
	}
	public void setCtrlPersonViewName(NameTypeVo ctrlPersonViewName) {
		this.ctrlPersonViewName = ctrlPersonViewName;
	}
	public NameTypeVo getCtrlPersonEditName() {
		return ctrlPersonEditName;
	}
	public void setCtrlPersonEditName(NameTypeVo ctrlPersonEditName) {
		this.ctrlPersonEditName = ctrlPersonEditName;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
