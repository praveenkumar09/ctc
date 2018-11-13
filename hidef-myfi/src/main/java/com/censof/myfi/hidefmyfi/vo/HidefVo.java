package com.censof.myfi.hidefmyfi.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author CSM-Ven
 *
 */
public class HidefVo {

	private MetaDataVo metadata;
	private ReportinFIVo reportingfi;
	private UserProfileVo userprofile;
	private ReportingEntityVo reportingEntity;
	private CBCRepotsVo cbcReports;
	private CbcAdditionalInfo cbcAddionalInfo;
	private String currentTab;
	private List<CBCRepotsVo> listCBCReports;
	private List<CbcAdditionalInfo> addInfoList;
	private boolean isUserProfileSaved;
	private List<CBCSummaryGridVo> cbcsummary;
	private UserVo user;
	private String checkEmail;
	private String importExcelFileName;
	private MultipartFile importExcelFile;
	private String MycbcId;
	private String metaDataFileName;
	private String payloadFileName;
	private String docRefId;
	private String docRefIdStaticText;
	private String isSummaryView;
	
	

	public MetaDataVo getMetadata() {
		return metadata;
	}

	public void setMetadata(MetaDataVo metadata) {
		this.metadata = metadata;
	}

	public ReportinFIVo getReportingfi() {
		return reportingfi;
	}

	public void setReportingfi(ReportinFIVo reportingfi) {
		this.reportingfi = reportingfi;
	}

	public UserProfileVo getUserprofile() {
		return userprofile;
	}

	public void setUserprofile(UserProfileVo userprofile) {
		this.userprofile = userprofile;
	}

	public String getCurrentTab() {
		return currentTab;
	}

	public void setCurrentTab(String currentTab) {
		this.currentTab = currentTab;
	}

	public ReportingEntityVo getReportingEntity() {
		return reportingEntity;
	}

	public void setReportingEntity(ReportingEntityVo reportingEntity) {
		this.reportingEntity = reportingEntity;
	}

	public CBCRepotsVo getCbcReports() {
		return cbcReports;
	}

	public void setCbcReports(CBCRepotsVo cbcReports) {
		this.cbcReports = cbcReports;
	}

	public CbcAdditionalInfo getCbcAddionalInfo() {
		return cbcAddionalInfo;
	}

	public void setCbcAddionalInfo(CbcAdditionalInfo cbcAddionalInfo) {
		this.cbcAddionalInfo = cbcAddionalInfo;
	}

	public List<CBCRepotsVo> getListCBCReports() {
		return listCBCReports;
	}

	public void setListCBCReports(List<CBCRepotsVo> listCBCReports) {
		this.listCBCReports = listCBCReports;
	}

	public List<CbcAdditionalInfo> getAddInfoList() {
		return addInfoList;
	}

	public void setAddInfoList(List<CbcAdditionalInfo> addInfoList) {
		this.addInfoList = addInfoList;
	}

	public boolean isUserProfileSaved() {
		return isUserProfileSaved;
	}

	public void setUserProfileSaved(boolean isUserProfileSaved) {
		this.isUserProfileSaved = isUserProfileSaved;
	}

	public List<CBCSummaryGridVo> getCbcsummary() {
		return cbcsummary;
	}

	public void setCbcsummary(List<CBCSummaryGridVo> cbcsummary) {
		this.cbcsummary = cbcsummary;
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public String getCheckEmail() {
		return checkEmail;
	}

	public void setCheckEmail(String checkEmail) {
		this.checkEmail = checkEmail;
	}

	public String getImportExcelFileName() {
		return importExcelFileName;
	}

	public void setImportExcelFileName(String importExcelFileName) {
		this.importExcelFileName = importExcelFileName;
	}

	public MultipartFile getImportExcelFile() {
		return importExcelFile;
	}

	public void setImportExcelFile(MultipartFile importExcelFile) {
		this.importExcelFile = importExcelFile;
	}

	public String getMycbcId() {
		return MycbcId;
	}

	public void setMycbcId(String mycbcId) {
		MycbcId = mycbcId;
	}

	public String getMetaDataFileName() {
		return metaDataFileName;
	}

	public void setMetaDataFileName(String metaDataFileName) {
		this.metaDataFileName = metaDataFileName;
	}

	public String getPayloadFileName() {
		return payloadFileName;
	}

	public void setPayloadFileName(String payloadFileName) {
		this.payloadFileName = payloadFileName;
	}

	public String getDocRefId() {
		return docRefId;
	}

	public void setDocRefId(String docRefId) {
		this.docRefId = docRefId;
	}

	public String getDocRefIdStaticText() {
		return docRefIdStaticText;
	}

	public void setDocRefIdStaticText(String docRefIdStaticText) {
		this.docRefIdStaticText = docRefIdStaticText;
	}

	public String getIsSummaryView() {
		return isSummaryView;
	}

	public void setIsSummaryView(String isSummaryView) {
		this.isSummaryView = isSummaryView;
	}
	
	

	
	
	
	
	
	
	

}
