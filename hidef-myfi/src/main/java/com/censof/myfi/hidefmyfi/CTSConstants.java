package com.censof.myfi.hidefmyfi;

import org.springframework.util.StringUtils;

public class CTSConstants {
	
	public static final String HIDEF_CTS_FORM_KEY 									= "hidef";
	public static final String HIDEF_CTS_CRS_METADATA								="metadata";
	public static final String HIDEF_CTS_CRS_REPORTINGFI							="reportingFI";
	public static final String HIDEF_CTS_CRS_ACCOUNTHOLDER							="accountHolder";
	public static final String HIDEF_CTS_USER_PROFILE                               ="userProfile";
	
	public static final String HIDEF_CTS_CRS_REPORTINGFI_REDIRECT_GET_URL				="redirect:reportingFI";
	public static final String HIDEF_CTS_CRS_METADATA_REDIRECT_GET_URL				="redirect:metadata";
	public static final String HIDEF_CTS_CRS_ACCOUNTHOLDER_REDIRECT_GET_URL				="redirect:accountHolder";
	
	
	public static final String HIDEF_CTS_CBC_METADATA								="metadata";
	public static final String HIDEF_CTS_CBC_REPORTING_ENTITY							="reportingEntity";
	public static final String HIDEF_CTS_CBC_REPORTS							="cbcReports";
	public static final String HIDEF_CTS_CBC_ADDITIONAL_INFO							="additionalInfo";
	
	
	public static String tabSelected(String selectedTab){
		
		 if(!StringUtils.isEmpty(selectedTab)){
				if(selectedTab.equalsIgnoreCase(HIDEF_CTS_CRS_METADATA)){
					return HIDEF_CTS_CRS_REPORTINGFI_REDIRECT_GET_URL;
				}else if(selectedTab.equalsIgnoreCase(HIDEF_CTS_CRS_REPORTINGFI)) {
					return HIDEF_CTS_CRS_ACCOUNTHOLDER_REDIRECT_GET_URL;
				}else if(selectedTab.equalsIgnoreCase(HIDEF_CTS_CRS_ACCOUNTHOLDER)) {
					return HIDEF_CTS_CRS_ACCOUNTHOLDER_REDIRECT_GET_URL;
				}
		  }
		
		 return "";
	}

}
