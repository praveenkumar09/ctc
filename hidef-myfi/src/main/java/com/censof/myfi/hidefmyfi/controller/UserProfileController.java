package com.censof.myfi.hidefmyfi.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.censof.myfi.hidefmyfi.CTSConstants;
import com.censof.myfi.hidefmyfi.entity.Cbcbinaryencodingschemecd;
import com.censof.myfi.hidefmyfi.entity.Cbcfileformatcd;
import com.censof.myfi.hidefmyfi.entity.Cbcfiletypeindic;
import com.censof.myfi.hidefmyfi.entity.Ctscommunicationtypecd;
import com.censof.myfi.hidefmyfi.entity.Docrefid;
import com.censof.myfi.hidefmyfi.entity.Hicountry;
import com.censof.myfi.hidefmyfi.entity.Messagerefid;
import com.censof.myfi.hidefmyfi.service.CtcDataSaveService;
import com.censof.myfi.hidefmyfi.service.CtccommonDropdownService;
import com.censof.myfi.hidefmyfi.vo.CommonDropdownGridBean;
import com.censof.myfi.hidefmyfi.vo.HidefVo;
import com.censof.myfi.hidefmyfi.vo.RecievingCountryVo;
import com.censof.myfi.hidefmyfi.vo.ResidentCountryVo;
import com.censof.myfi.hidefmyfi.vo.UserProfileVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes("hidef")
public class UserProfileController {
	
	@Autowired
	private CtccommonDropdownService ctccommonDropdownService;
	
	@Autowired
	private CtcDataSaveService ctcDataSaveService;
	
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ModelAttribute("hidef")
    public HidefVo getmetadata () {
        return new HidefVo(); 
    }
	
	@RequestMapping(value ="/admin/profile", method = RequestMethod.GET)
	public String getUserProfile(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,Map<String, Object> map) {
		hidef.setUserProfileSaved(false);
		
		ObjectMapper mapper = new ObjectMapper();
		
		hidef = ctcDataSaveService.getUserProfileByMycbcId(hidef);
		
		if(hidef.getUserprofile() == null) {
			hidef.setUserprofile(new UserProfileVo());
			//getMessageRefId(hidef,"CBC");
		}
		
		List<Hicountry> country = ctccommonDropdownService.getAllCountries();
		List<Cbcbinaryencodingschemecd> binaryencoding = ctccommonDropdownService.getAllBinaryEncodingSchemesType();
		List<Ctscommunicationtypecd> communicationType = ctccommonDropdownService.getAllCommunicationTypeCd();
		List<Cbcfileformatcd> fileformatcd = ctccommonDropdownService.getAllFileFormatCdType();
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
	
		
		hidef.setCurrentTab(CTSConstants.HIDEF_CTS_USER_PROFILE);
		map.put("countryList", country);
		map.put("binaryencodingList", binaryencoding);
		map.put("fileformatcodeList", fileformatcd);
		map.put("comminicationtype", communicationType);
		map.put("fileTypeIndicList",fileTypeIndic);
		
		model.addAttribute("hidef", hidef);
		return "userProfile";
	}
	
	@GetMapping(value ="/admin/loadReceivingCountryList",consumes="application/json")
	@ResponseBody
	public String loadReceivingCountryGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		List<ResidentCountryVo> recievingCountryList = new ArrayList<ResidentCountryVo>();
		String recievingCountryJson = mapper.writeValueAsString(recievingCountryList);
		if(hidef.getUserprofile() != null && hidef.getUserprofile().getRecievingCountryList()!= null
				&& !hidef.getUserprofile().getRecievingCountryList().isEmpty()) {
			recievingCountryJson = mapper.writeValueAsString(hidef.getUserprofile().getRecievingCountryList());
			}		
		model.addAttribute("hidef", hidef);
		return recievingCountryJson;
	}
	
	@PostMapping(value ="/admin/insertReceivingCountryGrid",consumes="application/json")
	@ResponseBody
	public RecievingCountryVo insertReceivingCountryGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String insertResident,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Random ran = new Random();
		RecievingCountryVo residentCountryVo =null;
		try {
			List<RecievingCountryVo> residentCountryList = new ArrayList<RecievingCountryVo>();
			residentCountryVo = mapper.readValue(insertResident, RecievingCountryVo.class);
			/*if(hidef.getReportingEntity() != null && hidef.getReportingEntity().getResidentCountryList() != null 
					&& hidef.getReportingEntity().getResidentCountryList().size() >0) {
				residentCountryVo.setId(ran.nextInt(10000));
				hidef.getReportingEntity().getResidentCountryList().add(residentCountryVo);
			}else {
				residentCountryVo.setId(ran.nextInt(10000));
				hidef.getReportingEntity().setResidentCountryList(residentCountryList);
				hidef.getReportingEntity().getResidentCountryList().add(residentCountryVo);
			}*/
			
			if(hidef.getUserprofile() != null && hidef.getUserprofile().getRecievingCountryList()!=null
					&& !hidef.getUserprofile().getRecievingCountryList().isEmpty()) {
				residentCountryVo.setId(ran.nextInt(10000));
				hidef.getUserprofile().getRecievingCountryList().add(residentCountryVo);
			}else {
				residentCountryVo.setId(ran.nextInt(10000));
				hidef.getUserprofile().setRecievingCountryList(residentCountryList);
				hidef.getUserprofile().getRecievingCountryList().add(residentCountryVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return residentCountryVo;
	}
	
	@PostMapping(value ="/admin/updateReceivingCountryGrid",consumes="application/json")
	@ResponseBody
	public String updateResidentCountryGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String updateResident,@RequestParam(required = true) int id, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		/*if(hidef.getReportingEntity() != null && hidef.getReportingEntity().getResidentCountryList() != null 
					&& hidef.getReportingEntity().getResidentCountryList().size() >0) {
			try{
			ResidentCountryVo residentCountryVo = mapper.readValue(updateResident, ResidentCountryVo.class);
			for(ResidentCountryVo residentvo :hidef.getReportingEntity().getResidentCountryList() ){
				if(residentvo.getId()==id){
					residentvo.setResidentCountryCode(residentCountryVo.getResidentCountryCode());
					break;
				}
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else {
			
		}*/
		
		if(hidef.getUserprofile() != null && hidef.getUserprofile().getRecievingCountryList()!= null
				&& !hidef.getUserprofile().getRecievingCountryList().isEmpty()) {
			try {
				RecievingCountryVo residentCountryVo = mapper.readValue(updateResident, RecievingCountryVo.class);
				for(RecievingCountryVo residentvo :hidef.getUserprofile().getRecievingCountryList() ){
					if(residentvo.getId()==id){
						//residentvo.setResidentCountryCode(residentCountryVo.getResidentCountryCode());
						residentvo.setRecievingCountry(residentCountryVo.getRecievingCountry());
						break;
					}
				}
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		model.addAttribute("hidef", hidef);
		return "success";
	}
	
	@PostMapping(value ="/admin/cbc/deleteReceivingCountryGrid",consumes="application/json")
	@ResponseBody
	public String deleteReceivingCountryGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String deleteResident,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		/*	if(deleteResident != null && hidef.getReportingEntity() != null && hidef.getReportingEntity().getResidentCountryList() != null 
					&& hidef.getReportingEntity().getResidentCountryList().size() >0){
				try{
				ResidentCountryVo residentCountryVo = mapper.readValue(deleteResident, ResidentCountryVo.class);
				List<ResidentCountryVo> copyList = new ArrayList<ResidentCountryVo>(hidef.getReportingEntity().getResidentCountryList());
				for(ResidentCountryVo residentCountry: hidef.getReportingEntity().getResidentCountryList()){
					if(residentCountryVo.getId()==residentCountry.getId()){
						copyList.remove(residentCountry);
						hidef.getReportingEntity().setResidentCountryList(copyList);
						break;
					}
				}
				}catch(Exception e){
					e.printStackTrace();
				}
			}*/
		
		if(deleteResident != null && hidef.getUserprofile().getRecievingCountryList() != null
				&& !hidef.getUserprofile().getRecievingCountryList().isEmpty()) {
			try {
				RecievingCountryVo residentCountryVo = mapper.readValue(deleteResident, RecievingCountryVo.class);
				List<RecievingCountryVo> copyList = new ArrayList<RecievingCountryVo>(hidef.getUserprofile().getRecievingCountryList());
				for(RecievingCountryVo residentCountry : hidef.getUserprofile().getRecievingCountryList()) {
					if(residentCountryVo.getId()==residentCountry.getId()){
						copyList.remove(residentCountry);
						if(hidef.getUserprofile().getUserProfileId() != null && residentCountry.getId() != 0) {
							ctcDataSaveService.deleteUserProfileRecievingCountry(hidef.getUserprofile().getUserProfileId(),residentCountry.getId());
						}
						hidef.getUserprofile().setRecievingCountryList(copyList);
						
						break;
					}
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		model.addAttribute("hidef", hidef);
		return "success";
	}
	
	@RequestMapping(value ="/admin/profile", method = RequestMethod.POST)
	public String saveUserProfile(@ModelAttribute("hidef")HidefVo hidef,  HttpServletRequest request,	
		      BindingResult result, ModelMap model,Map<String, Object> map) {
		
		MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest)request;
        Iterator<String> it=multipartRequest.getFileNames();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info(auth.getName());
        
        while(it.hasNext()) {
        	String fileName = it.next();
        	MultipartFile file = multipartRequest.getFile(fileName);
        	fileName = file.getOriginalFilename();
        	if(file.getOriginalFilename().contains(".p12")) {
        		hidef.getUserprofile().setConfigurationFile(file);
        		hidef.getUserprofile().setConfigurationFileText(auth.getName()+"_X509.p12");
        	}else if(file.getOriginalFilename().contains(".cer")) {
        		hidef.getUserprofile().setPublicCertPath(file);
        		hidef.getUserprofile().setPublicCertFileName(auth.getName()+"_X509.cer");
        	}else if(file.getOriginalFilename().contains(".crt")) {
        		hidef.getUserprofile().setPublicCertPath(file);
        		hidef.getUserprofile().setPublicCertFileName(auth.getName()+"_X509.crt");
        	}else {
        		logger.info(fileName);
        	}
        }
        
        /**
         * Save User Profile Data
         */
        ctcDataSaveService.saveUserprofileData(hidef);
        
        
        hidef.setUserProfileSaved(true);
		model.addAttribute("hidef",hidef);
		return "redirect:home";
	}
	
	@RequestMapping(value ="/admin/import/excel", method = RequestMethod.GET)
	@ResponseBody
	public String getExcelPopUp(@ModelAttribute("hidef")HidefVo hidef,  HttpServletRequest request,	
		      BindingResult result, ModelMap model,Map<String, Object> map) {
		model.addAttribute("hidef",hidef);
		return "success";
	}
	
	
	@RequestMapping(value ="/admin/import/excel", method = RequestMethod.POST)
	public String saveExcel(@ModelAttribute("hidef")HidefVo hidef,  HttpServletRequest request,	
		      BindingResult result, ModelMap model,Map<String, Object> map) throws IllegalStateException, IOException, ParseException {
		HidefVo newHidefVo = new HidefVo();
		MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest)request;
        Iterator<String> it=multipartRequest.getFileNames();
        
        while(it.hasNext()) {
        	String fileName = it.next();
        	MultipartFile file = multipartRequest.getFile(fileName);
        	fileName = file.getOriginalFilename();
        	hidef.setImportExcelFileName(fileName);
        	hidef.setImportExcelFile(file);
        }
        newHidefVo = ctcDataSaveService.saveExcelFile(hidef);
        ctcDataSaveService.saveCtcExcelData(newHidefVo);
        model.addAttribute("hidef",hidef);
		return "redirect:/admin/home";		
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
	
	
	public HidefVo getMessageRefId(HidefVo hidef,String communicationType){
		String pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		Messagerefid messageRefId = ctccommonDropdownService.findMessageRefIdByDate(date);
		String messageRefIdNew = "";
		String messageRefIdStatic = "MY"+hidef.getMycbcId()+date;
		if(messageRefId != null){
			if(messageRefId.getMessagerefid().equals("0001")){
				messageRefIdNew = messageRefIdStatic+messageRefId.getMessagerefid(); 
			}else{
				int sum = Integer.parseInt(messageRefId.getMessagerefid())+ 1;				
				messageRefIdNew = messageRefIdStatic+String.valueOf(converToString(sum));			
			}
			
		}else{
			
			messageRefId = ctcDataSaveService.saveMessageRefId(date,communicationType);
			messageRefIdNew = messageRefIdStatic+String.valueOf(messageRefId.getMessagerefid());
		}
		
		if(hidef.getUserprofile() != null && hidef.getUserprofile().getMessageRefID() != null && !hidef.getUserprofile().getMessageRefID().isEmpty()) {
			hidef.getUserprofile().setMessageRefID(hidef.getUserprofile().getMessageRefID());
		}else {
		hidef.getUserprofile().setMessageRefID(messageRefIdNew);
		}
		return hidef;
	}
	
	


}
