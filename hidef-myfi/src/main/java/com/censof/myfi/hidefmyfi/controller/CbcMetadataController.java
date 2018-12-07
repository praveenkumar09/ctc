package com.censof.myfi.hidefmyfi.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.censof.myfi.hidefmyfi.CTSConstants;
import com.censof.myfi.hidefmyfi.entity.Cbcbinaryencodingschemecd;
import com.censof.myfi.hidefmyfi.entity.Cbcfileformatcd;
import com.censof.myfi.hidefmyfi.entity.Cbcfiletypeindic;
import com.censof.myfi.hidefmyfi.entity.Cbcmessagetypeindic;
import com.censof.myfi.hidefmyfi.entity.Ctscommunicationtypecd;
import com.censof.myfi.hidefmyfi.entity.Hicountry;
import com.censof.myfi.hidefmyfi.entity.Messagerefid;
import com.censof.myfi.hidefmyfi.entity.Senderfileid;
import com.censof.myfi.hidefmyfi.service.CtcDataSaveService;
import com.censof.myfi.hidefmyfi.service.CtccommonDropdownService;
import com.censof.myfi.hidefmyfi.vo.CommonDropdownGridBean;
import com.censof.myfi.hidefmyfi.vo.HidefVo;
import com.censof.myfi.hidefmyfi.vo.MetaDataVo;
import com.censof.myfi.hidefmyfi.vo.RecievingCountryVo;
import com.censof.myfi.hidefmyfi.vo.ResidentCountryVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes("hidef")
public class CbcMetadataController {
	
	@Autowired
	private CtccommonDropdownService ctccommonDropdownService;
	
	@Autowired
	private CtcDataSaveService ctcDataSaveService;
	
	@ModelAttribute("hidef")
    public HidefVo getmetadata () {
        return new HidefVo(); 
    }
	
	
	@GetMapping(value ="/admin/cbc/main")
	public String loadMenu(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,Map<String, Object> map) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		hidef = ctcDataSaveService.getUserProfileByMycbcId(hidef);
		
		
		if(hidef.getMetadata() == null) {
			hidef.setMetadata(new MetaDataVo());
			getSenderFileID(hidef,"CBC");
			if(hidef.getUserprofile() != null) {
				hidef = setUserProfileDataForMetaData(hidef);
			}
		}else {
			if(hidef.getUserprofile() != null) {
				hidef = setUserProfileDataForMetaData(hidef);
			}
		}
		
		List<Hicountry> country = ctccommonDropdownService.getAllCountries();
		List<Cbcbinaryencodingschemecd> binaryencoding = ctccommonDropdownService.getAllBinaryEncodingSchemesType();
		List<Ctscommunicationtypecd> communicationType = ctccommonDropdownService.getAllCommunicationTypeCd();
		List<Cbcfileformatcd> fileformatcd = ctccommonDropdownService.getAllFileFormatCdType();
		List<Cbcmessagetypeindic> cbcMessageTypeIndic = ctccommonDropdownService.findAllCbcmessagetypeindic();
		List<Cbcfiletypeindic> fileTypeIndic = ctccommonDropdownService.getAllFileTypeIndic();
		
		
		List<CommonDropdownGridBean> gridBeans = new ArrayList<>();
		for(Hicountry residentCountry:country) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(residentCountry.getId());
			gridBean.setName(residentCountry.getCountryCode());
			gridBeans.add(gridBean);			

		}
		
		try {
			String arrayToJson = mapper.writeValueAsString(gridBeans);
			map.put("recievingCountry", arrayToJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();

		}
	
		
		hidef.setCurrentTab(CTSConstants.HIDEF_CTS_CBC_METADATA);
		map.put("countryList", country);
		map.put("binaryencodingList", binaryencoding);
		map.put("fileformatcodeList", fileformatcd);
		map.put("comminicationtype", communicationType);
		map.put("cbcMessageTypeindic", cbcMessageTypeIndic);
		map.put("fileTypeIndicList",fileTypeIndic);
		//map.put("recievingCountry",country);
		
		model.addAttribute("hidef", hidef);
		return "cbcmetadata";
	}
	
	
	@GetMapping(value ="/admin/cbc/new/metadata")
	public String loadNewMetadata(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,Map<String, Object> map) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		hidef = ctcDataSaveService.getUserProfileByMycbcId(hidef);
		
		HidefVo newHidefVo = new HidefVo();
		if(hidef != null && hidef.getUserprofile() != null) {
		newHidefVo.setUserprofile(hidef.getUserprofile());
		newHidefVo.setMycbcId(hidef.getMycbcId());
		}else {
			newHidefVo = hidef;
		}
		
		if(newHidefVo.getMetadata() == null) {
			newHidefVo.setMetadata(new MetaDataVo());
			newHidefVo.getMetadata().setSendingCompanyIN(newHidefVo.getMycbcId());
			/*newHidefVo = getSenderFileID(newHidefVo,"CBC");*/
			/*newHidefVo = getMessageRefId(newHidefVo,"CBC");*/
			if(newHidefVo.getUserprofile() != null) {
				newHidefVo = setUserProfileDataForMetaData(newHidefVo);
			}
		}else {
			if(newHidefVo.getUserprofile() != null) {
				newHidefVo = setUserProfileDataForMetaData(newHidefVo);
			}
		}
		
		List<Hicountry> country = ctccommonDropdownService.getAllCountries();
		List<Cbcbinaryencodingschemecd> binaryencoding = ctccommonDropdownService.getAllBinaryEncodingSchemesType();
		List<Ctscommunicationtypecd> communicationType = ctccommonDropdownService.getAllCommunicationTypeCd();
		List<Cbcfileformatcd> fileformatcd = ctccommonDropdownService.getAllFileFormatCdType();
		List<Cbcmessagetypeindic> cbcMessageTypeIndic = ctccommonDropdownService.findAllCbcmessagetypeindic();
		List<Cbcfiletypeindic> fileTypeIndic = ctccommonDropdownService.getAllFileTypeIndic();
		
		List<CommonDropdownGridBean> gridBeans = new ArrayList<>();
		for(Hicountry residentCountry:country) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(residentCountry.getId());
			gridBean.setName(residentCountry.getCountryCode());
			gridBeans.add(gridBean);			

		}
		
		try {
			String arrayToJson = mapper.writeValueAsString(gridBeans);
			map.put("recievingCountry", arrayToJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();

		}
	
		
		hidef.setCurrentTab(CTSConstants.HIDEF_CTS_CBC_METADATA);
		map.put("countryList", country);
		map.put("binaryencodingList", binaryencoding);
		map.put("fileformatcodeList", fileformatcd);
		map.put("comminicationtype", communicationType);
		map.put("cbcMessageTypeindic", cbcMessageTypeIndic);
		map.put("fileTypeIndicList",fileTypeIndic);
		//map.put("recievingCountry",country);
		
		model.addAttribute("hidef", newHidefVo);
		return "cbcmetadata";
	}
	
	
	@GetMapping(value ="/admin/metadata/loadReceivingCountryList",consumes="application/json")
	@ResponseBody
	public String loadReceivingCountryGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		List<ResidentCountryVo> recievingCountryList = new ArrayList<ResidentCountryVo>();
		String recievingCountryJson = mapper.writeValueAsString(recievingCountryList);
		if(hidef.getMetadata() != null && hidef.getMetadata().getRecievingCountryList()!= null
				&& !hidef.getMetadata().getRecievingCountryList().isEmpty()) {
			recievingCountryJson = mapper.writeValueAsString(hidef.getMetadata().getRecievingCountryList());
			}		
		model.addAttribute("hidef", hidef);
		return recievingCountryJson;
	}
	
	public String getTabMetaData(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model) {
		//List<Crsmastcountry> crsmastcountry = crsmastcountryService.findAllCrsmastcountry();
		
		Map<String,String> countryMap = new LinkedHashMap<String,String>();
		countryMap.put("1", "United Stated");
		countryMap.put("2", "China");
		countryMap.put("3", "Singapore");
		countryMap.put("4", "Malaysia");
		
		Map<String,String> binaryEncodingMap = new LinkedHashMap<String,String>();
		binaryEncodingMap.put("1", "BASE64");
		binaryEncodingMap.put("2", "NONE");
		
		Map<String,String> fileFormatCodeMap = new LinkedHashMap<String,String>();
		fileFormatCodeMap.put("1", "JPG");
		fileFormatCodeMap.put("2", "PDF");
		fileFormatCodeMap.put("3", "RTF");
		fileFormatCodeMap.put("4", "TXT");
		fileFormatCodeMap.put("5", "XML");
		
	
		
		hidef.setCurrentTab(CTSConstants.HIDEF_CTS_CBC_METADATA);
		model.put("countryList", countryMap);
		model.put("binaryencodingList", binaryEncodingMap);
		model.put("fileformatcodeList", fileFormatCodeMap);
		
		model.addAttribute("hidef", hidef);
		return "cbcmetadata";
	}
	
	@PostMapping(value ="/admin/cbc/metadataNext")
	public String MetaDataNext(@Valid @ModelAttribute("hidef")HidefVo hidef, BindingResult result,
			 @RequestParam(required = false, value = "next1") String next,
		     ModelMap model,RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "cbcmetadata";
		}
		
		if (!StringUtils.isEmpty(next)) {
	            return "redirect:reportingEntity";
	    }
		model.addAttribute("hidef", hidef);
		return CTSConstants.tabSelected(hidef.getCurrentTab());
	}
	@PostMapping(value ="/admin/tabs/metadataPrevious")
	public String MetaDataPrevious(@Valid @ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "crsmetadata";
		}
		model.addAttribute("hidef", hidef);
		return "crsreportingFI";
	}
	
	public HidefVo setUserProfileDataForMetaData(HidefVo hidef) {
		if(hidef.getUserprofile().getSendingcountry()!= null && !hidef.getUserprofile().getSendingcountry().isEmpty()) {
			hidef.getMetadata().setSendingCountry(hidef.getUserprofile().getSendingcountry());
		}
		
		if(hidef.getUserprofile().getReceivingcountry()!= null && !hidef.getUserprofile().getReceivingcountry().isEmpty()) {
			hidef.getMetadata().setReceivingCountry(hidef.getUserprofile().getReceivingcountry());
		}
		
		if(hidef.getUserprofile().getMsgType() != null && !hidef.getUserprofile().getMsgType().isEmpty()) {
			hidef.getMetadata().setMsgType(hidef.getUserprofile().getMsgType());
		}
		
		if(hidef.getUserprofile().getSendContactEmailAddress()!= null && !hidef.getUserprofile().getSendContactEmailAddress().isEmpty()) {
			hidef.getMetadata().setSenderContactEmailAddress(hidef.getUserprofile().getSendContactEmailAddress());
		}
		
		if(hidef.getUserprofile().getCommunicationType()!= null && !hidef.getUserprofile().getCommunicationType().isEmpty()) {
			hidef.getMetadata().setCommunicationType(hidef.getUserprofile().getCommunicationType());
		}
		
		if(hidef.getUserprofile().getFileformatCode()!= null && !hidef.getUserprofile().getFileformatCode().isEmpty()) {
			hidef.getMetadata().setFileFormatCode(hidef.getUserprofile().getFileformatCode());
		}
		
		if(hidef.getUserprofile().getBinaryEncoding()!= null && !hidef.getUserprofile().getBinaryEncoding().isEmpty()) {
			hidef.getMetadata().setBinaryEncoding(hidef.getUserprofile().getBinaryEncoding());
		}
		
		if(hidef.getUserprofile().getMessageRefID()!= null && !hidef.getUserprofile().getMessageRefID().isEmpty()) {
			hidef.getMetadata().setMessageRefId(hidef.getUserprofile().getMessageRefID());
		}
		
		if(hidef.getUserprofile().getRecievingCountryList() != null && !hidef.getUserprofile().getRecievingCountryList().isEmpty()) {
			hidef.getMetadata().setRecievingCountryList(hidef.getUserprofile().getRecievingCountryList());
		}else {
			hidef.getMetadata().setRecievingCountryList(new ArrayList<RecievingCountryVo> ());
		}
		
		return hidef;

	}
	
	@GetMapping(value ="/admin/cbc/generateMessageRef")
	@ResponseBody
	public String generateMessageReferenceId(@ModelAttribute("hidef")HidefVo hidef, 
		/*	@RequestParam(required = true) String year,*/
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		/*hidef.getMetadata().setTaxYear(year);*/
		hidef = getMessageRefId(hidef,"CBC");
		model.addAttribute("hidef", hidef);
		return hidef.getMetadata().getMessageRefId();
	}
	@GetMapping(value ="/admin/cbc/generateSenderFileID")
	@ResponseBody
	public String generateSenderFileID(@ModelAttribute("hidef")HidefVo hidef, 
		/*	@RequestParam(required = true) String year,*/
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		hidef = getSenderFileID(hidef,"CBC");
		model.addAttribute("hidef", hidef);
		return hidef.getMetadata().getSenderFileId();
	}
	
	public String converToString(int i){
		String convertedNum = "";
	    if(String.valueOf(i).length() == 1){
	    	convertedNum ="000"+i;
	    } else if (String.valueOf(i).length() == 2){
	    	convertedNum ="00"+i;
	    }
	    else if (String.valueOf(i).length() == 3){
	    	convertedNum ="0"+i;
	    } 
	    
	    else {
	    	convertedNum =String.valueOf(i);
	    }

	    return convertedNum;
	}
	
	
	public HidefVo getSenderFileID(HidefVo hidef,String communicationType){
		String pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		Senderfileid senderFileId = ctccommonDropdownService.findSenderFileIdByDate(date);
		String senderFileIdNew = "";
		String senderFileIdStatic = communicationType+"_MY"+hidef.getMetadata().getTaxYear()+"-"+hidef.getMycbcId()+date;
		if(senderFileId != null){
			
				int sum = Integer.parseInt(senderFileId.getSenderfileid())+ 1;				
				senderFileIdNew = senderFileIdStatic+String.valueOf(converToString(sum));	
				
			
			
		}else{
			
			senderFileId = ctcDataSaveService.saveSenderFileId(date, communicationType);
			senderFileIdNew = senderFileIdStatic+String.valueOf(senderFileId.getSenderfileid());
		}
		
		/*if(hidef.getMetadata() != null && hidef.getMetadata().getSenderFileId() != null && !hidef.getMetadata().getSenderFileId().isEmpty()) {
			hidef.getMetadata().setSenderFileId(hidef.getMetadata().getSenderFileId());
		}else {*/
		hidef.getMetadata().setSenderFileId(senderFileIdNew);
		/*}*/
		return hidef;
	}
	
	public HidefVo getMessageRefId(HidefVo hidef,String communicationType){
		/*String pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		Messagerefid messageRefId = ctccommonDropdownService.findMessageRefIdByDate(date);
		String messageRefIdNew = "";
		String messageRefIdStatic = communicationType+"_MY"+hidef.getMetadata().getTaxYear()+"-"+hidef.getMycbcId()+date;
		if(messageRefId != null){
			
				int sum = Integer.parseInt(messageRefId.getMessagerefid())+ 1;				
				messageRefIdNew = messageRefIdStatic+String.valueOf(converToString(sum));	
				
						
		}else{
			
			messageRefId = ctcDataSaveService.saveMessageRefId(date,communicationType);
			messageRefIdNew = messageRefIdStatic+String.valueOf(messageRefId.getMessagerefid());
		}*/
		
		String pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		Senderfileid senderFileId = ctccommonDropdownService.findSenderFileIdByDate(date);
		String messageRefId = "";
		String messageRefIdStatic = communicationType+"_MY"+hidef.getMetadata().getTaxYear()+"-"+hidef.getMycbcId()+date;
		if(senderFileId != null){
			
				int sum = Integer.parseInt(senderFileId.getSenderfileid())+ 1;				
				messageRefId = messageRefIdStatic+String.valueOf(converToString(sum+1));	
				
			
			
		}else{
			
			senderFileId = ctcDataSaveService.saveSenderFileId(date, communicationType);
			messageRefId = messageRefIdStatic+String.valueOf(senderFileId.getSenderfileid());
		}
		
		/*if(hidef.getMetadata() != null && hidef.getMetadata().getMessageRefId() != null && !hidef.getMetadata().getMessageRefId().isEmpty()) {
			hidef.getMetadata().setMessageRefId(hidef.getMetadata().getMessageRefId());
		}else {*/
		hidef.getMetadata().setMessageRefId(messageRefId);
		/*}*/
		return hidef;
	}

}
