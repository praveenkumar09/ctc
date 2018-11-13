package com.censof.myfi.hidefmyfi.vo;

import java.util.List;

/**
 * 
 * @author CSM-Ven
 *
 */
public class ReportinFIVo {
	
	private String docTypeIndicatorReportingFI;
	private String docRefId;
	private String corMessageRefId;
	private String corDocRefId;
	private String address;
	private List<NameVo> nameList;
	private List<IdentificationNumberVo> identityList;
	private List<ResidentCountryCodeVo> residentCountryList;
	private List<AddressVo> addressList;
	private AddressVo addressVo;
	private AddressVo viewAddressVo;
	
	public String getDocTypeIndicatorReportingFI() {
		return docTypeIndicatorReportingFI;
	}
	public void setDocTypeIndicatorReportingFI(String docTypeIndicatorReportingFI) {
		this.docTypeIndicatorReportingFI = docTypeIndicatorReportingFI;
	}
	public String getDocRefId() {
		return docRefId;
	}
	public void setDocRefId(String docRefId) {
		this.docRefId = docRefId;
	}
	public String getCorMessageRefId() {
		return corMessageRefId;
	}
	public void setCorMessageRefId(String corMessageRefId) {
		this.corMessageRefId = corMessageRefId;
	}
	public String getCorDocRefId() {
		return corDocRefId;
	}
	public void setCorDocRefId(String corDocRefId) {
		this.corDocRefId = corDocRefId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<NameVo> getNameList() {
		return nameList;
	}
	public void setNameList(List<NameVo> nameList) {
		this.nameList = nameList;
	}
	public List<IdentificationNumberVo> getIdentityList() {
		return identityList;
	}
	public void setIdentityList(List<IdentificationNumberVo> identityList) {
		this.identityList = identityList;
	}
	public List<ResidentCountryCodeVo> getResidentCountryList() {
		return residentCountryList;
	}
	public void setResidentCountryList(List<ResidentCountryCodeVo> residentCountryList) {
		this.residentCountryList = residentCountryList;
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
	
	
	
	
	
	
	
	

}
