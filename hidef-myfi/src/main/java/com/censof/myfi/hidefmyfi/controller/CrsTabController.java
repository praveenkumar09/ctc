package com.censof.myfi.hidefmyfi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.censof.myfi.hidefmyfi.model.Article;

@Controller
public class CrsTabController {
	
	@RequestMapping(value ="/admin/tabs/metadata", method = RequestMethod.GET)
	public String getTabMetaData() {
		return "crsmetadata";
	}
	
	@RequestMapping(value ="/admin/tabs/reportingFI", method = RequestMethod.GET)
	public String getTabReportingFI() {
		return "crsreportingFI";
	}
	
	@RequestMapping(value ="/admin/tabs/accountHolder", method = RequestMethod.GET)
	public String getTabAccountHolder() {
		return "crsAccountHolder";
	}
	
	
	@RequestMapping(value ="/admin/tabs/accountHolder/saveAddress", method = RequestMethod.GET)
	@ResponseBody
	public String getTabAccountHolderAndSaveAddress() {
		return "success";
	}
	
	
	@RequestMapping(value ="/admin/tabs/config", method = RequestMethod.GET)
	public String getTabConfig() {
		return "crsconfig";
	}
	
	@RequestMapping(value ="/admin/view/Address", method = RequestMethod.GET)
	public String getTabConfig(ModelMap model) {
		Article article = new Article();
		article.setAddressFree("something here");
		model.addAttribute("article",article);
		return "viewSampleAddress";
	}

}
