package com.censof.myfi.hidefmyfi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CbcTabController {
	
	@RequestMapping(value ="/cbc/tabs/metadata", method = RequestMethod.GET)
	public String getTabMetaData() {
		return "cbcmetadata";
	}
	
	@RequestMapping(value="/cbc/tabs/reportingEntity",method = RequestMethod.GET)
	public String getTabReportingEntity(){
		return "cbcReportingEntity";
	}
	
	@RequestMapping(value ="/cbc/tabs/reportingEntity/saveAddress", method = RequestMethod.GET)
	@ResponseBody
	public String getTabAccountHolderAndSaveAddress() {
		return "success";
	}
	
	@RequestMapping(value ="/cbc/view/address", method = RequestMethod.GET)
	@ResponseBody
	public String viewAddress() {
		return "success";
	}
	
	@RequestMapping(value ="/cbc/tabs/cbcReports", method = RequestMethod.GET)
	public String getTabCbcReports() {
		return "cbcReports";
	}
	
	@RequestMapping(value ="/cbc/tabs/cbcReports/saveAddress", method = RequestMethod.GET)
	@ResponseBody
	public String saveCbcReportsAddress() {
		return "success";
	}
	
	@RequestMapping(value ="/cbc/tabs/cbcAddInfo", method = RequestMethod.GET)
	public String getTabcbcAddInfo() {
		return "cbcAddInfo";
	}

}
