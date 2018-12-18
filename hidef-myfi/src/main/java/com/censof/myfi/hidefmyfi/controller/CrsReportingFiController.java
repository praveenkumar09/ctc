package com.censof.myfi.hidefmyfi.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.censof.myfi.hidefmyfi.CTSConstants;
import com.censof.myfi.hidefmyfi.entity.Cbcaddresstype;
import com.censof.myfi.hidefmyfi.entity.Cbcdocumenttypeindicator;
import com.censof.myfi.hidefmyfi.entity.Cbcnametype;
import com.censof.myfi.hidefmyfi.entity.Hicountry;
import com.censof.myfi.hidefmyfi.service.CtcDataSaveService;
import com.censof.myfi.hidefmyfi.service.CtccommonDropdownService;
import com.censof.myfi.hidefmyfi.vo.AccountHolderVo;
import com.censof.myfi.hidefmyfi.vo.AddressVo;
import com.censof.myfi.hidefmyfi.vo.CommonDropdownGridBean;
import com.censof.myfi.hidefmyfi.vo.HidefVo;
import com.censof.myfi.hidefmyfi.vo.NameVo;
import com.censof.myfi.hidefmyfi.vo.OrganisationInTypeVo;
import com.censof.myfi.hidefmyfi.vo.ResidentCountryVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes("hidef")
public class CrsReportingFiController {
	
	@Autowired
	private CtccommonDropdownService ctccommonDropdownService;
	
	@Autowired
	private CtcDataSaveService ctcDataSaveService;
	
	@ModelAttribute("hidef")
    public HidefVo getmetadata () {
        return new HidefVo(); 
    }
	
	@RequestMapping(value ="/admin/crs/reportingFi", method = RequestMethod.GET)
	public String getreportingFi(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,Map<String, Object> map) {
		ObjectMapper mapper = new ObjectMapper();
		List<Hicountry> country = ctccommonDropdownService.getAllCountries();
		List<Cbcdocumenttypeindicator> cbcdocumenttypeindicator = ctccommonDropdownService.getAllDocumentTypeIndicator();
		List<Cbcnametype> cbcnametype = ctccommonDropdownService.getAllNameType();
		
		List<CommonDropdownGridBean> gridBeans = new ArrayList<>();
		for(Hicountry residentCountry:country) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(residentCountry.getId());
			gridBean.setName(residentCountry.getCountryCode());
			gridBeans.add(gridBean);			

		}
		List<CommonDropdownGridBean> nameTypegridBeans = new ArrayList<>();
		for(Cbcnametype nameType:cbcnametype) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(nameType.getId());
			gridBean.setName(nameType.getNameType());
			nameTypegridBeans.add(gridBean);			

		}
		
		try {
			String arrayToJson = mapper.writeValueAsString(gridBeans);
			map.put("residentCountry", arrayToJson);
			map.put("nameTypeList", mapper.writeValueAsString(nameTypegridBeans));
		} catch (JsonProcessingException e) {
			e.printStackTrace();

		}
		map.put("countryList", country);
		map.put("documentTypeIndicator", cbcdocumenttypeindicator);
		model.addAttribute("hidef", hidef);
		hidef.setCurrentTab(CTSConstants.HIDEF_CTS_CRS_REPORTINGFI);
		return "crsreportingFI";
	}
	@PostMapping(value ="/admin/crs/reportingFiNextOrPrevious")
	public String reportingFiNextOrPrevious(@ModelAttribute("hidef")HidefVo hidef, BindingResult result,
			@RequestParam(required=true,value="newForm") int newFormId,
			 @RequestParam(required = false, value = "next1") String next,
			 @RequestParam(required = false, value = "previous1") String previous,
		     ModelMap model,RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "crsreportingFI";
		}
		if(!StringUtils.isEmpty(previous)) {
			return "redirect:main";
		}
		
		if (!StringUtils.isEmpty(next)) {
			if(newFormId == 1){
				hidef.setAccountholder(new AccountHolderVo());
			}
	            return "redirect:accountHolder";
	    }
		model.addAttribute("hidef", hidef);
		return CTSConstants.tabSelected(hidef.getCurrentTab());
	}
	@GetMapping(value ="/admin/crs/resetReportingFiAddAddress")
	public String resetReportingEntityAddAddress(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response,Map<String, Object> map) throws JsonProcessingException {
		AddressVo addressVo = new AddressVo();
		hidef.getCrsreportingfi().setAddressVo(addressVo);
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		model.addAttribute("hidef", hidef);
		return "crsreportingFI";
	}
	@GetMapping(value ="/admin/crs/loadReportingFiAddress",consumes="application/json")
	@ResponseBody
	public String loadReportingFiAddressGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		List<AddressVo> addressList = new ArrayList<AddressVo>();
		String addressJson = mapper.writeValueAsString(addressList);
		if(hidef.getCrsreportingfi() != null && hidef.getCrsreportingfi().getAddressList() != null 
				&& hidef.getCrsreportingfi().getAddressList().size() >0) {
			addressJson = mapper.writeValueAsString(hidef.getCrsreportingfi().getAddressList());
		}
		model.addAttribute("hidef", hidef);
		return addressJson;
	}
	@GetMapping(value ="/admin/crs/reportingFiinsertAddress")
	@ResponseBody
	public AddressVo addAddressGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		List<AddressVo> addressList = null;
		if(hidef.getCrsreportingfi() != null && hidef.getCrsreportingfi().getAddressList() != null && hidef.getCrsreportingfi().getAddressList().size()>0) {
			addressList = hidef.getCrsreportingfi().getAddressList();
			
		}else {
			addressList = new ArrayList<AddressVo>();
			hidef.getCrsreportingfi().setAddressList(addressList);
		}
		
		if(hidef.getCrsreportingfi() != null && hidef.getCrsreportingfi().getAddressVo() != null){
			AddressVo addressVo = new AddressVo();
			if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getAddressVo().getAddressFree())){
			addressVo.setAddressFree(hidef.getCrsreportingfi().getAddressVo().getAddressFree());
			}
			if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getAddressVo().getAddressType())){
			addressVo.setAddressType(hidef.getCrsreportingfi().getAddressVo().getAddressType());
			}
			if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getAddressVo().getBuildingIdentifier())){
			addressVo.setBuildingIdentifier(hidef.getCrsreportingfi().getAddressVo().getBuildingIdentifier());
			}
			if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getAddressVo().getCity())){
			addressVo.setCity(hidef.getCrsreportingfi().getAddressVo().getCity());
			}
			if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getAddressVo().getCountryCode())){
			addressVo.setCountryCode(hidef.getCrsreportingfi().getAddressVo().getCountryCode());
			}
			if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getAddressVo().getCountrySubentity())){
			addressVo.setCountrySubentity(hidef.getCrsreportingfi().getAddressVo().getCountrySubentity());
			}
			if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getAddressVo().getDistrictName())){
			addressVo.setDistrictName(hidef.getCrsreportingfi().getAddressVo().getDistrictName());
			}
			if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getAddressVo().getFloorIdentifier())){
			addressVo.setFloorIdentifier(hidef.getCrsreportingfi().getAddressVo().getFloorIdentifier());
			}
			if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getAddressList()) && hidef.getCrsreportingfi().getAddressList().size()>0){
			addressVo.setId(hidef.getCrsreportingfi().getAddressList().size()+1);
			hidef.getCrsreportingfi().getAddressVo().setId(hidef.getCrsreportingfi().getAddressList().size()+1);
			}else{
				addressVo.setId(1);
				hidef.getCrsreportingfi().getAddressVo().setId(1);
			}
			if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getAddressVo().getPob())){
			addressVo.setPob(hidef.getCrsreportingfi().getAddressVo().getPob());
			}
			if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getAddressVo().getPostCode())){
			addressVo.setPostCode(hidef.getCrsreportingfi().getAddressVo().getPostCode());
			}
			if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getAddressVo().getStreet())){
			addressVo.setStreet(hidef.getCrsreportingfi().getAddressVo().getStreet());
			}
			if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getAddressVo().getSuitIdentifier())){
			addressVo.setSuitIdentifier(hidef.getCrsreportingfi().getAddressVo().getSuitIdentifier());
			}
			addressList.add(addressVo);
		}
		
		hidef.getCrsreportingfi().setAddressList(addressList);
		String arrayToJson = mapper.writeValueAsString(addressList);
		model.addAttribute("hidef", hidef);
        return hidef.getCrsreportingfi().getAddressVo();
	}
	
	
	@RequestMapping(value = "/admin/crs/reportingFiviewAddress", method = RequestMethod.GET)
	public String viewAddressGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestParam(required = true) int id,Map<String, Object> map, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if(hidef.getCrsreportingfi() != null && hidef.getCrsreportingfi().getAddressList() != null && hidef.getCrsreportingfi().getAddressList().size()>0) {
			addressList = hidef.getCrsreportingfi().getAddressList();
			for(AddressVo address: addressList) {
				if(address.getId()==id) {
					addressView = address;
					hidef.getCrsreportingfi().setViewAddressVo(addressView);
				}
			}
			
		}
		String arrayToJson = mapper.writeValueAsString(addressView);
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		model.addAttribute("hidef", hidef);
		map.put("viewAddress", addressView);
        return "crsreportingFI";
	}
	@RequestMapping(value = "/admin/crs/reportingFieditAddress", method = RequestMethod.GET)
	public String editAddressGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestParam(required = true) int id, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response,Map<String, Object> map) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if(hidef.getCrsreportingfi() != null && hidef.getCrsreportingfi().getAddressList() != null && hidef.getCrsreportingfi().getAddressList().size()>0) {
			addressList = hidef.getCrsreportingfi().getAddressList();
			for(AddressVo address: addressList) {
				if(address.getId()==id) {
					addressView = address;
					hidef.getCrsreportingfi().setEditAddressVo(addressView);
				}
			}
			
		}
		
		String arrayToJson = mapper.writeValueAsString(addressView);
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		model.addAttribute("hidef", hidef);
        return "crsreportingFI";
	}
	
	
	@RequestMapping(value = "/admin/crs/reportingFieditSaveAddress", method = RequestMethod.GET)
	public String saveEditAddressGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if(hidef.getCrsreportingfi() != null && hidef.getCrsreportingfi().getAddressList() != null && hidef.getCrsreportingfi().getAddressList().size()>0) {
			addressList = hidef.getCrsreportingfi().getAddressList();
			for(AddressVo address: addressList) {
				if(address.getId()==hidef.getCrsreportingfi().getEditAddressVo().getId()) {
					if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getEditAddressVo().getAddressFree())){
					address.setAddressFree(hidef.getCrsreportingfi().getEditAddressVo().getAddressFree());
					}
					if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getEditAddressVo().getAddressType())){
					address.setAddressType(hidef.getCrsreportingfi().getEditAddressVo().getAddressType());
					address.setAddressTypeId(hidef.getCrsreportingfi().getEditAddressVo().getAddressType());
					}
					if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getEditAddressVo().getBuildingIdentifier())){
					address.setBuildingIdentifier(hidef.getCrsreportingfi().getEditAddressVo().getBuildingIdentifier());
					}
					if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getEditAddressVo().getCity())){
					address.setCity(hidef.getCrsreportingfi().getEditAddressVo().getCity());
					}
					if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getEditAddressVo().getCountryCode())){
					address.setCountryCode(hidef.getCrsreportingfi().getEditAddressVo().getCountryCode());
					address.setCountryCodeId(hidef.getCrsreportingfi().getEditAddressVo().getCountryCode());
					}
					if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getEditAddressVo().getCountrySubentity())){
					address.setCountrySubentity(hidef.getCrsreportingfi().getEditAddressVo().getCountrySubentity());
					}
					if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getEditAddressVo().getDistrictName())){
					address.setDistrictName(hidef.getCrsreportingfi().getEditAddressVo().getDistrictName());
					}
					if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getEditAddressVo().getFloorIdentifier())){
					address.setFloorIdentifier(hidef.getCrsreportingfi().getEditAddressVo().getFloorIdentifier());
					}
					
					address.setId(address.getId());
					if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getEditAddressVo().getPob())){
					address.setPob(hidef.getCrsreportingfi().getEditAddressVo().getPob());
					}
					if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getEditAddressVo().getPostCode())){
					address.setPostCode(hidef.getCrsreportingfi().getEditAddressVo().getPostCode());
					}
					if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getEditAddressVo().getStreet())){
					address.setStreet(hidef.getCrsreportingfi().getEditAddressVo().getStreet());
					}
					if(!StringUtils.isEmpty(hidef.getCrsreportingfi().getEditAddressVo().getSuitIdentifier())){
					address.setSuitIdentifier(hidef.getCrsreportingfi().getEditAddressVo().getSuitIdentifier());
					}
				}
			}
			
		}
		
		String arrayToJson = mapper.writeValueAsString(addressView);
		model.addAttribute("hidef", hidef);
        return "crsreportingFI";
	}
	@RequestMapping(value = "/admin/crs/reportingFideleteAddress", method = RequestMethod.GET)
	@ResponseBody
	public String deleteAddressGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestParam(required = true) int id, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if(hidef.getCrsreportingfi() != null && hidef.getCrsreportingfi().getAddressList() != null && hidef.getCrsreportingfi().getAddressList().size()>0) {
			
			addressList = hidef.getCrsreportingfi().getAddressList();
			for(AddressVo address: addressList) {
				if(address.getId()==id) {
					addressView = address;
					List<AddressVo> copyList = new ArrayList<AddressVo>(addressList);
					copyList.remove(address);
					hidef.getReportingEntity().setAddressList(copyList);
				}
			}
			
		}
		
		String arrayToJson = mapper.writeValueAsString(addressView);
		
        return arrayToJson;
	}
	
	
	
	@GetMapping(value ="/admin/crs/loadResidentCountryGrid",consumes="application/json")
	@ResponseBody
	public String loadResidentCountryGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		List<ResidentCountryVo> residentCountryList = new ArrayList<ResidentCountryVo>();
		String residentJson = mapper.writeValueAsString(residentCountryList);
		if(hidef.getCrsreportingfi() != null && hidef.getCrsreportingfi().getResidentCountryList() != null 
				&& hidef.getCrsreportingfi().getResidentCountryList().size() >0) {
			residentJson = mapper.writeValueAsString(hidef.getCrsreportingfi().getResidentCountryList());
		}
		
		
		
		
		
		
		model.addAttribute("hidef", hidef);
		return residentJson;
	}
	@PostMapping(value ="/admin/crs/insertResidentCountryGrid",consumes="application/json")
	@ResponseBody
	public ResidentCountryVo insertResidentCountryGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String insertResident,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Random ran = new Random();
		ResidentCountryVo residentCountryVo =null;
		try {
			List<ResidentCountryVo> residentCountryList = new ArrayList<ResidentCountryVo>();
			residentCountryVo = mapper.readValue(insertResident, ResidentCountryVo.class);
			if(hidef.getCrsreportingfi() != null && hidef.getCrsreportingfi().getResidentCountryList() != null 
					&& hidef.getCrsreportingfi().getResidentCountryList().size() >0) {
				residentCountryVo.setId(ran.nextInt(10000));
				hidef.getCrsreportingfi().getResidentCountryList().add(residentCountryVo);
			}else {
				residentCountryVo.setId(ran.nextInt(10000));
				hidef.getCrsreportingfi().setResidentCountryList(residentCountryList);
				hidef.getCrsreportingfi().getResidentCountryList().add(residentCountryVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return residentCountryVo;
	}
	
	@PostMapping(value ="/admin/crs/updateResidentCountryGrid",consumes="application/json")
	@ResponseBody
	public String updateResidentCountryGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String updateResident,@RequestParam(required = true) int id, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getCrsreportingfi() != null && hidef.getCrsreportingfi().getResidentCountryList() != null 
					&& hidef.getCrsreportingfi().getResidentCountryList().size() >0) {
			try{
			ResidentCountryVo residentCountryVo = mapper.readValue(updateResident, ResidentCountryVo.class);
			for(ResidentCountryVo residentvo :hidef.getCrsreportingfi().getResidentCountryList() ){
				if(residentvo.getId()==id){
					residentvo.setResidentCountryCode(residentCountryVo.getResidentCountryCode());
					break;
				}
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else {
			
		}
		model.addAttribute("hidef", hidef);
		return "success";
	}
	@PostMapping(value ="/admin/crs/deleteResidentCountryGrid",consumes="application/json")
	@ResponseBody
	public String deleteResidentCountryGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String deleteResident,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
			if(deleteResident != null && hidef.getCrsreportingfi() != null && hidef.getCrsreportingfi().getResidentCountryList() != null 
					&& hidef.getCrsreportingfi().getResidentCountryList().size() >0){
				try{
				ResidentCountryVo residentCountryVo = mapper.readValue(deleteResident, ResidentCountryVo.class);
				List<ResidentCountryVo> copyList = new ArrayList<ResidentCountryVo>(hidef.getCrsreportingfi().getResidentCountryList());
				for(ResidentCountryVo residentCountry: hidef.getCrsreportingfi().getResidentCountryList()){
					if(residentCountryVo.getId()==residentCountry.getId()){
						copyList.remove(residentCountry);
						hidef.getCrsreportingfi().setResidentCountryList(copyList);
						break;
					}
				}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		model.addAttribute("hidef", hidef);
		return "success";
	}
	
	@GetMapping(value ="/admin/crs/loadNameGrid",consumes="application/json")
	@ResponseBody
	public String loadNameGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<NameVo> nameList = new ArrayList<NameVo>();
		String nameJson = mapper.writeValueAsString(nameList);
		if(hidef.getCrsreportingfi() != null && hidef.getCrsreportingfi().getNameList() != null 
				&& hidef.getCrsreportingfi().getNameList().size() >0) {
			nameJson = mapper.writeValueAsString(hidef.getCrsreportingfi().getNameList());
		}
		model.addAttribute("hidef", hidef);
		return nameJson;
	}
	@PostMapping(value ="/admin/crs/insertNameGrid",consumes="application/json")
	@ResponseBody
	public NameVo insertNameGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String insertNameGrid, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Random ran = new Random();
		NameVo nameVo = null;
		try {
			List<NameVo> nameList = new ArrayList<NameVo>();
			nameVo = mapper.readValue(insertNameGrid, NameVo.class);
			nameVo.setId(ran.nextInt(10000));
			if(hidef.getCrsreportingfi() != null && hidef.getCrsreportingfi().getNameList() != null 
					&& hidef.getCrsreportingfi().getNameList().size() >0) {
				hidef.getCrsreportingfi().getNameList().add(nameVo);
			}else {
				hidef.getCrsreportingfi().setNameList(nameList);
				hidef.getCrsreportingfi().getNameList().add(nameVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return nameVo;
	}
	
	@PostMapping(value ="/admin/crs/updateNameGrid",consumes="application/json")
	@ResponseBody
	public String updateNameGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String updateNameGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getCrsreportingfi() != null && hidef.getCrsreportingfi().getNameList() != null 
					&& hidef.getCrsreportingfi().getNameList().size() >0) {
			try {
			NameVo updatedNameVo = mapper.readValue(updateNameGrid, NameVo.class);
			for(NameVo namevo : hidef.getCrsreportingfi().getNameList()) {
				if(namevo.getId()==id) {
					namevo.setLastName(updatedNameVo.getLastName());
					namevo.setFirstName(updatedNameVo.getFirstName());
					namevo.setNameType(namevo.getNameType());
					break;
				}
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			
		}
		
		model.addAttribute("hidef", hidef);
		return "success";
	}
	@PostMapping(value ="/admin/crs/deleteNameGrid",consumes="application/json")
	@ResponseBody
	public String deleteNameGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String deleteNameGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(deleteNameGrid != null && hidef.getCrsreportingfi() != null && hidef.getCrsreportingfi().getNameList() != null 
					&& hidef.getCrsreportingfi().getNameList().size() >0){
			try{
			NameVo updatedNameVo = mapper.readValue(deleteNameGrid, NameVo.class);
			List<NameVo> copyList = new ArrayList<NameVo>(hidef.getCrsreportingfi().getNameList());
			for(NameVo namevo:hidef.getCrsreportingfi().getNameList()){
				if(id==namevo.getId()){
					copyList.remove(namevo);
					hidef.getCrsreportingfi().setNameList(copyList);
					break;
				}
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else{
			
		}
		model.addAttribute("hidef", hidef);
		return "success";
	}
	
	@GetMapping(value ="/admin/crs/loadOrganisationGrid",consumes="application/json")
	@ResponseBody
	public String loadOrganisationGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<OrganisationInTypeVo> organisationList = new ArrayList<OrganisationInTypeVo>();
		String organisationJson = mapper.writeValueAsString(organisationList);
		if(hidef.getCrsreportingfi() != null && hidef.getCrsreportingfi().getOrganisationInTypeList() != null 
				&& hidef.getCrsreportingfi().getOrganisationInTypeList().size() >0) {
			organisationJson = mapper.writeValueAsString(hidef.getCrsreportingfi().getOrganisationInTypeList());
		}
		model.addAttribute("hidef", hidef);
		return organisationJson;
	}
	@PostMapping(value ="/admin/crs/insertOrganisationGrid",consumes="application/json")
	@ResponseBody
	public OrganisationInTypeVo insertOrganisationGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String insertOrganisationGrid, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		OrganisationInTypeVo organisationVo  = null;
		Random ran = new Random();
		try {
			List<OrganisationInTypeVo> organisationList = new ArrayList<OrganisationInTypeVo>();
			organisationVo = mapper.readValue(insertOrganisationGrid, OrganisationInTypeVo.class);
			organisationVo.setId(ran.nextInt(10000));
			if(hidef.getCrsreportingfi() != null && hidef.getCrsreportingfi().getOrganisationInTypeList() != null 
					&& hidef.getCrsreportingfi().getOrganisationInTypeList().size() >0) {
				hidef.getCrsreportingfi().getOrganisationInTypeList().add(organisationVo);
			}else {
				hidef.getCrsreportingfi().setOrganisationInTypeList(organisationList);
				hidef.getCrsreportingfi().getOrganisationInTypeList().add(organisationVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return organisationVo;
	}
	
	@PostMapping(value ="/admin/crs/updateOrganisationGrid",consumes="application/json")
	@ResponseBody
	public String updateOrganisationGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String updateOrganisationGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getCrsreportingfi() != null && hidef.getCrsreportingfi().getOrganisationInTypeList() != null 
				&& hidef.getCrsreportingfi().getOrganisationInTypeList().size() >0) {
			try{
			OrganisationInTypeVo updatedorganisationVo = mapper.readValue(updateOrganisationGrid, OrganisationInTypeVo.class);
			for(OrganisationInTypeVo organisationvo :hidef.getCrsreportingfi().getOrganisationInTypeList()){
				if(organisationvo.getId()==id){
					organisationvo.setIssuedBy(updatedorganisationVo.getIssuedBy());
					organisationvo.setIn(updatedorganisationVo.getIn());
					organisationvo.setInType(updatedorganisationVo.getInType());
					break;
				}
			}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			
		}
		model.addAttribute("hidef", hidef);
		return "success";
	}
	@PostMapping(value ="/admin/crs/deleteOrganisationGrid",consumes="application/json")
	@ResponseBody
	public String deleteOrganisationGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String deleteOrganisationGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(deleteOrganisationGrid != null && hidef.getCrsreportingfi().getOrganisationInTypeList() != null){
			try{
			OrganisationInTypeVo updatedorganisationVo = mapper.readValue(deleteOrganisationGrid, OrganisationInTypeVo.class);
			List<OrganisationInTypeVo> copyList = new ArrayList<OrganisationInTypeVo>(hidef.getCrsreportingfi().getOrganisationInTypeList());
			for(OrganisationInTypeVo organisation:hidef.getCrsreportingfi().getOrganisationInTypeList()){
				if(id==organisation.getId()){
					copyList.remove(organisation);
					hidef.getCrsreportingfi().setOrganisationInTypeList(copyList);
					break;
				}
			}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		model.addAttribute("hidef", hidef);
		return "success";
	}


}
