package com.censof.myfi.hidefmyfi.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.censof.myfi.hidefmyfi.CTSConstants;
import com.censof.myfi.hidefmyfi.entity.Cbcaddresstype;
import com.censof.myfi.hidefmyfi.entity.Cbcdocumenttypeindicator;
import com.censof.myfi.hidefmyfi.entity.Cbcnametype;
import com.censof.myfi.hidefmyfi.entity.Cbcreportingrole;
import com.censof.myfi.hidefmyfi.entity.Docrefid;
import com.censof.myfi.hidefmyfi.entity.Hicountry;
import com.censof.myfi.hidefmyfi.repository.CbcpayldaddressRepository;
import com.censof.myfi.hidefmyfi.repository.CbcpayldinRepository;
import com.censof.myfi.hidefmyfi.repository.CbcpayldnameRepository;
import com.censof.myfi.hidefmyfi.repository.CbcpayldrescountryRepository;
import com.censof.myfi.hidefmyfi.service.CtcDataSaveService;
import com.censof.myfi.hidefmyfi.service.CtccommonDropdownService;
import com.censof.myfi.hidefmyfi.vo.AddressVo;
import com.censof.myfi.hidefmyfi.vo.CBCRepotsVo;
import com.censof.myfi.hidefmyfi.vo.CommonDropdownGridBean;
import com.censof.myfi.hidefmyfi.vo.HidefVo;
import com.censof.myfi.hidefmyfi.vo.NameVo;
import com.censof.myfi.hidefmyfi.vo.OrganisationInTypeVo;
import com.censof.myfi.hidefmyfi.vo.ReportingEntityVo;
import com.censof.myfi.hidefmyfi.vo.ResidentCountryVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes("hidef")
public class CbcReportingEntityController {
	
	@Autowired
	private CtccommonDropdownService ctccommonDropdownService;
	
	@Autowired
	private CtcDataSaveService ctcDataSaveService;
	
	@Autowired
	private CbcpayldrescountryRepository cbcpayldrescountryRepository;
	
	@Autowired
	private CbcpayldnameRepository cbcpayldnameRepository;
	
	@Autowired
	private CbcpayldinRepository cbcpayldinRepository;
	
	
	@Autowired
	private CbcpayldaddressRepository cbcpayldaddressRepository;
	
	@ModelAttribute("hidef")
    public HidefVo getmetadata () {
        return new HidefVo();
    } 
	@GetMapping(value ="/admin/cbc/reportingEntity")
	public String getTabReportingEntity(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,Map<String, Object> map) {
		
		if(hidef.getReportingEntity()  == null) {
			hidef.setReportingEntity(new ReportingEntityVo());
		}
		
		hidef.setCurrentTab(CTSConstants.HIDEF_CTS_CBC_REPORTING_ENTITY);
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		List<Cbcdocumenttypeindicator> cbcdocumenttypeindicator = ctccommonDropdownService.getAllDocumentTypeIndicator();
		List<Cbcreportingrole> cbcreportingrole = ctccommonDropdownService.getAllReportingRoleType();
		List<Cbcnametype> cbcnametype = ctccommonDropdownService.getAllNameType();
		ObjectMapper mapper = new ObjectMapper();
		try {
			String arrayToJson = mapper.writeValueAsString(hicountryList);
			map.put("residentCountry", arrayToJson);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<CommonDropdownGridBean> gridBeans = new ArrayList<>();
		for(Hicountry residentCountry:hicountryList) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(residentCountry.getId());
			gridBean.setName(residentCountry.getCountryCode());
			gridBeans.add(gridBean);			

		}
		List<CommonDropdownGridBean> nameTypegridBeans = new ArrayList<>();
		for(Cbcnametype nameType:cbcnametype) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(new BigInteger(nameType.getId()));
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
		
		if(hidef.getDocRefId() == null){
		getDocRefId(hidef,"E","CBC");
		}
		
		
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		map.put("documentTypeIndicator", cbcdocumenttypeindicator);
		map.put("reportingRole", cbcreportingrole);
		map.put("nameType", cbcnametype);
		map.put("countryCode", hicountryList);

		model.addAttribute("hidef", hidef);
		return "cbcReportingEntity";
	}
	
	@PostMapping(value ="/admin/cbc/reportingEntityNext")
	public String reportingEntityNext(@ModelAttribute("hidef")HidefVo hidef,@RequestParam(required=true,value="newForm") int newFormId,
			 @RequestParam(required = false, value = "next2") String next,
			 @RequestParam(required = false, value = "previous1") String previous,
		      BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "cbcReportingEntity";
		}
		if(!StringUtils.isEmpty(previous)) {
			return "redirect:main";
		}
		
		if (!StringUtils.isEmpty(next)) {
			if(newFormId == 1) {
				hidef.setCbcReports(new CBCRepotsVo());
			}
	            return "redirect:cbcReports";
	    }
		model.addAttribute("hidef", hidef);
		return CTSConstants.tabSelected(hidef.getCurrentTab());
		//return "redirect:accountHolder";
	}	
	@GetMapping(value ="/admin/cbc/loadResidentCountryGrid",consumes="application/json")
	@ResponseBody
	public String loadResidentCountryGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		List<ResidentCountryVo> residentCountryList = new ArrayList<ResidentCountryVo>();
		String residentJson = mapper.writeValueAsString(residentCountryList);
		if(hidef.getReportingEntity() != null && hidef.getReportingEntity().getResidentCountryList() != null 
				&& hidef.getReportingEntity().getResidentCountryList().size() >0) {
			residentJson = mapper.writeValueAsString(hidef.getReportingEntity().getResidentCountryList());
		}
		
		
		
		
		
		
		model.addAttribute("hidef", hidef);
		return residentJson;
	}
	@PostMapping(value ="/admin/cbc/insertResidentCountryGrid",consumes="application/json")
	@ResponseBody
	public ResidentCountryVo insertResidentCountryGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String insertResident,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Random ran = new Random();
		ResidentCountryVo residentCountryVo =null;
		try {
			List<ResidentCountryVo> residentCountryList = new ArrayList<ResidentCountryVo>();
			residentCountryVo = mapper.readValue(insertResident, ResidentCountryVo.class);
			if(hidef.getReportingEntity() != null && hidef.getReportingEntity().getResidentCountryList() != null 
					&& hidef.getReportingEntity().getResidentCountryList().size() >0) {
				residentCountryVo.setId(ran.nextInt(10000));
				hidef.getReportingEntity().getResidentCountryList().add(residentCountryVo);
			}else {
				residentCountryVo.setId(ran.nextInt(10000));
				hidef.getReportingEntity().setResidentCountryList(residentCountryList);
				hidef.getReportingEntity().getResidentCountryList().add(residentCountryVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return residentCountryVo;
	}
	
	@PostMapping(value ="/admin/cbc/updateResidentCountryGrid",consumes="application/json")
	@ResponseBody
	public String updateResidentCountryGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String updateResident,@RequestParam(required = true) int id, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getReportingEntity() != null && hidef.getReportingEntity().getResidentCountryList() != null 
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
			
		}
		model.addAttribute("hidef", hidef);
		return "success";
	}
	@PostMapping(value ="/admin/cbc/deleteResidentCountryGrid",consumes="application/json")
	@ResponseBody
	public String deleteResidentCountryGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String deleteResident,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
			if(deleteResident != null && hidef.getReportingEntity() != null && hidef.getReportingEntity().getResidentCountryList() != null 
					&& hidef.getReportingEntity().getResidentCountryList().size() >0){
				try{
				ResidentCountryVo residentCountryVo = mapper.readValue(deleteResident, ResidentCountryVo.class);
				List<ResidentCountryVo> copyList = new ArrayList<ResidentCountryVo>(hidef.getReportingEntity().getResidentCountryList());
				for(ResidentCountryVo residentCountry: hidef.getReportingEntity().getResidentCountryList()){
					if(residentCountryVo.getId()==residentCountry.getId()){
						copyList.remove(residentCountry);
						if(hidef.getReportingEntity().getId() != null && residentCountry.getId() != 0) {
							cbcpayldrescountryRepository.deleteById(BigInteger.valueOf(residentCountry.getId()));
						}
						hidef.getReportingEntity().setResidentCountryList(copyList);
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
	
	@GetMapping(value ="/admin/cbc/loadNameGrid",consumes="application/json")
	@ResponseBody
	public String loadNameGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<NameVo> nameList = new ArrayList<NameVo>();
		String nameJson = mapper.writeValueAsString(nameList);
		if(hidef.getReportingEntity() != null && hidef.getReportingEntity().getNameList() != null 
				&& hidef.getReportingEntity().getNameList().size() >0) {
			nameJson = mapper.writeValueAsString(hidef.getReportingEntity().getNameList());
		}
		model.addAttribute("hidef", hidef);
		return nameJson;
	}
	@PostMapping(value ="/admin/cbc/insertNameGrid",consumes="application/json")
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
			if(hidef.getReportingEntity() != null && hidef.getReportingEntity().getNameList() != null 
					&& hidef.getReportingEntity().getNameList().size() >0) {
				hidef.getReportingEntity().getNameList().add(nameVo);
			}else {
				hidef.getReportingEntity().setNameList(nameList);
				hidef.getReportingEntity().getNameList().add(nameVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return nameVo;
	}
	
	@PostMapping(value ="/admin/cbc/updateNameGrid",consumes="application/json")
	@ResponseBody
	public String updateNameGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String updateNameGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getReportingEntity() != null && hidef.getReportingEntity().getNameList() != null 
					&& hidef.getReportingEntity().getNameList().size() >0) {
			try {
			NameVo updatedNameVo = mapper.readValue(updateNameGrid, NameVo.class);
			for(NameVo namevo : hidef.getReportingEntity().getNameList()) {
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
	@PostMapping(value ="/admin/cbc/deleteNameGrid",consumes="application/json")
	@ResponseBody
	public String deleteNameGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String deleteNameGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(deleteNameGrid != null && hidef.getReportingEntity() != null && hidef.getReportingEntity().getNameList() != null 
					&& hidef.getReportingEntity().getNameList().size() >0){
			try{
			NameVo updatedNameVo = mapper.readValue(deleteNameGrid, NameVo.class);
			List<NameVo> copyList = new ArrayList<NameVo>(hidef.getReportingEntity().getNameList());
			for(NameVo namevo:hidef.getReportingEntity().getNameList()){
				if(id==namevo.getId()){
					copyList.remove(namevo);
					if(hidef.getReportingEntity().getId() != null && namevo.getId() != 0) {
						cbcpayldnameRepository.deleteById(BigInteger.valueOf(namevo.getId()));
					}
					hidef.getReportingEntity().setNameList(copyList);
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
	
	@GetMapping(value ="/admin/cbc/loadOrganisationGrid",consumes="application/json")
	@ResponseBody
	public String loadOrganisationGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<OrganisationInTypeVo> organisationList = new ArrayList<OrganisationInTypeVo>();
		String organisationJson = mapper.writeValueAsString(organisationList);
		if(hidef.getReportingEntity() != null && hidef.getReportingEntity().getOrganisationInTypeList() != null 
				&& hidef.getReportingEntity().getOrganisationInTypeList().size() >0) {
			organisationJson = mapper.writeValueAsString(hidef.getReportingEntity().getOrganisationInTypeList());
		}
		model.addAttribute("hidef", hidef);
		return organisationJson;
	}
	@PostMapping(value ="/admin/cbc/insertOrganisationGrid",consumes="application/json")
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
			if(hidef.getReportingEntity() != null && hidef.getReportingEntity().getOrganisationInTypeList() != null 
					&& hidef.getReportingEntity().getOrganisationInTypeList().size() >0) {
				hidef.getReportingEntity().getOrganisationInTypeList().add(organisationVo);
			}else {
				hidef.getReportingEntity().setOrganisationInTypeList(organisationList);
				hidef.getReportingEntity().getOrganisationInTypeList().add(organisationVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return organisationVo;
	}
	
	@PostMapping(value ="/admin/cbc/updateOrganisationGrid",consumes="application/json")
	@ResponseBody
	public String updateOrganisationGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String updateOrganisationGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getReportingEntity() != null && hidef.getReportingEntity().getOrganisationInTypeList() != null 
				&& hidef.getReportingEntity().getOrganisationInTypeList().size() >0) {
			try{
			OrganisationInTypeVo updatedorganisationVo = mapper.readValue(updateOrganisationGrid, OrganisationInTypeVo.class);
			for(OrganisationInTypeVo organisationvo :hidef.getReportingEntity().getOrganisationInTypeList()){
				if(organisationvo.getId()==id){
					organisationvo.setIssuedBy(updatedorganisationVo.getIssuedBy());
					organisationvo.setInType(updatedorganisationVo.getInType());
					organisationvo.setIn(updatedorganisationVo.getIn());
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
	@PostMapping(value ="/admin/cbc/deleteOrganisationGrid",consumes="application/json")
	@ResponseBody
	public String deleteOrganisationGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String deleteOrganisationGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(deleteOrganisationGrid != null && hidef.getReportingEntity().getOrganisationInTypeList() != null){
			try{
			OrganisationInTypeVo updatedorganisationVo = mapper.readValue(deleteOrganisationGrid, OrganisationInTypeVo.class);
			List<OrganisationInTypeVo> copyList = new ArrayList<OrganisationInTypeVo>(hidef.getReportingEntity().getOrganisationInTypeList());
			for(OrganisationInTypeVo organisation:hidef.getReportingEntity().getOrganisationInTypeList()){
				if(id==organisation.getId()){
					copyList.remove(organisation);
					if(hidef.getReportingEntity().getId() != null && organisation.getId() != 0) {
						cbcpayldinRepository.deleteById(BigInteger.valueOf(organisation.getId()));
					}
					hidef.getReportingEntity().setOrganisationInTypeList(copyList);
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
	
	@GetMapping(value ="/admin/cbc/reportEntityinsertAddress")
	@ResponseBody
	public AddressVo addAddressGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		List<AddressVo> addressList = null;
		if(hidef.getReportingEntity() != null && hidef.getReportingEntity().getAddressList() != null && hidef.getReportingEntity().getAddressList().size()>0) {
			addressList = hidef.getReportingEntity().getAddressList();
			
		}else {
			addressList = new ArrayList<AddressVo>();
			hidef.getReportingEntity().setAddressList(addressList);
		}
		
		if(hidef.getReportingEntity() != null && hidef.getReportingEntity().getAddressVo() != null){
			AddressVo addressVo = new AddressVo();
			if(!StringUtils.isEmpty(hidef.getReportingEntity().getAddressVo().getAddressFree())){
			addressVo.setAddressFree(hidef.getReportingEntity().getAddressVo().getAddressFree());
			}
			if(!StringUtils.isEmpty(hidef.getReportingEntity().getAddressVo().getAddressType())){
			addressVo.setAddressType(hidef.getReportingEntity().getAddressVo().getAddressType());
			}
			if(!StringUtils.isEmpty(hidef.getReportingEntity().getAddressVo().getBuildingIdentifier())){
			addressVo.setBuildingIdentifier(hidef.getReportingEntity().getAddressVo().getBuildingIdentifier());
			}
			if(!StringUtils.isEmpty(hidef.getReportingEntity().getAddressVo().getCity())){
			addressVo.setCity(hidef.getReportingEntity().getAddressVo().getCity());
			}
			if(!StringUtils.isEmpty(hidef.getReportingEntity().getAddressVo().getCountryCode())){
			addressVo.setCountryCode(hidef.getReportingEntity().getAddressVo().getCountryCode());
			}
			if(!StringUtils.isEmpty(hidef.getReportingEntity().getAddressVo().getCountrySubentity())){
			addressVo.setCountrySubentity(hidef.getReportingEntity().getAddressVo().getCountrySubentity());
			}
			if(!StringUtils.isEmpty(hidef.getReportingEntity().getAddressVo().getDistrictName())){
			addressVo.setDistrictName(hidef.getReportingEntity().getAddressVo().getDistrictName());
			}
			if(!StringUtils.isEmpty(hidef.getReportingEntity().getAddressVo().getFloorIdentifier())){
			addressVo.setFloorIdentifier(hidef.getReportingEntity().getAddressVo().getFloorIdentifier());
			}
			if(!StringUtils.isEmpty(hidef.getReportingEntity().getAddressList()) && hidef.getReportingEntity().getAddressList().size()>0){
			addressVo.setId(hidef.getReportingEntity().getAddressList().size()+1);
			hidef.getReportingEntity().getAddressVo().setId(hidef.getReportingEntity().getAddressList().size()+1);
			}else{
				addressVo.setId(1);
				hidef.getReportingEntity().getAddressVo().setId(1);
			}
			if(!StringUtils.isEmpty(hidef.getReportingEntity().getAddressVo().getPob())){
			addressVo.setPob(hidef.getReportingEntity().getAddressVo().getPob());
			}
			if(!StringUtils.isEmpty(hidef.getReportingEntity().getAddressVo().getPostCode())){
			addressVo.setPostCode(hidef.getReportingEntity().getAddressVo().getPostCode());
			}
			if(!StringUtils.isEmpty(hidef.getReportingEntity().getAddressVo().getStreet())){
			addressVo.setStreet(hidef.getReportingEntity().getAddressVo().getStreet());
			}
			if(!StringUtils.isEmpty(hidef.getReportingEntity().getAddressVo().getSuitIdentifier())){
			addressVo.setSuitIdentifier(hidef.getReportingEntity().getAddressVo().getSuitIdentifier());
			}
			addressList.add(addressVo);
		}
		
		hidef.getReportingEntity().setAddressList(addressList);
		String arrayToJson = mapper.writeValueAsString(addressList);
		model.addAttribute("hidef", hidef);
        return hidef.getReportingEntity().getAddressVo();
	}
	
	
	@RequestMapping(value = "/admin/cbc/reportEntityviewAddress", method = RequestMethod.GET)
	public String viewAddressGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestParam(required = true) int id,Map<String, Object> map, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if(hidef.getReportingEntity() != null && hidef.getReportingEntity().getAddressList() != null && hidef.getReportingEntity().getAddressList().size()>0) {
			addressList = hidef.getReportingEntity().getAddressList();
			for(AddressVo address: addressList) {
				if(address.getId()==id) {
					addressView = address;
					hidef.getReportingEntity().setViewAddressVo(addressView);
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
        return "cbcReportingEntity";
	}
	@RequestMapping(value = "/admin/cbc/reportEntityeditAddress", method = RequestMethod.GET)
	public String editAddressGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestParam(required = true) int id, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response,Map<String, Object> map) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if(hidef.getReportingEntity() != null && hidef.getReportingEntity().getAddressList() != null && hidef.getReportingEntity().getAddressList().size()>0) {
			addressList = hidef.getReportingEntity().getAddressList();
			for(AddressVo address: addressList) {
				if(address.getId()==id) {
					addressView = address;
					hidef.getReportingEntity().setEditAddressVo(addressView);
				}
			}
			
		}
		
		String arrayToJson = mapper.writeValueAsString(addressView);
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		model.addAttribute("hidef", hidef);
        return "cbcReportingEntity";
	}
	
	
	@RequestMapping(value = "/admin/cbc/reportEntityeditSaveAddress", method = RequestMethod.GET)
	public String saveEditAddressGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if(hidef.getReportingEntity() != null && hidef.getReportingEntity().getAddressList() != null && hidef.getReportingEntity().getAddressList().size()>0) {
			addressList = hidef.getReportingEntity().getAddressList();
			for(AddressVo address: addressList) {
				if(address.getId()==hidef.getReportingEntity().getEditAddressVo().getId()) {
					if(!StringUtils.isEmpty(hidef.getReportingEntity().getEditAddressVo().getAddressFree())){
					address.setAddressFree(hidef.getReportingEntity().getEditAddressVo().getAddressFree());
					}
					if(!StringUtils.isEmpty(hidef.getReportingEntity().getEditAddressVo().getAddressType())){
					address.setAddressType(hidef.getReportingEntity().getEditAddressVo().getAddressType());
					address.setAddressTypeId(hidef.getReportingEntity().getEditAddressVo().getAddressType());
					}
					if(!StringUtils.isEmpty(hidef.getReportingEntity().getEditAddressVo().getBuildingIdentifier())){
					address.setBuildingIdentifier(hidef.getReportingEntity().getEditAddressVo().getBuildingIdentifier());
					}
					if(!StringUtils.isEmpty(hidef.getReportingEntity().getEditAddressVo().getCity())){
					address.setCity(hidef.getReportingEntity().getEditAddressVo().getCity());
					}
					if(!StringUtils.isEmpty(hidef.getReportingEntity().getEditAddressVo().getCountryCode())){
					address.setCountryCode(hidef.getReportingEntity().getEditAddressVo().getCountryCode());
					address.setCountryCodeId(hidef.getReportingEntity().getEditAddressVo().getCountryCode());
					}
					if(!StringUtils.isEmpty(hidef.getReportingEntity().getEditAddressVo().getCountrySubentity())){
					address.setCountrySubentity(hidef.getReportingEntity().getEditAddressVo().getCountrySubentity());
					}
					if(!StringUtils.isEmpty(hidef.getReportingEntity().getEditAddressVo().getDistrictName())){
					address.setDistrictName(hidef.getReportingEntity().getEditAddressVo().getDistrictName());
					}
					if(!StringUtils.isEmpty(hidef.getReportingEntity().getEditAddressVo().getFloorIdentifier())){
					address.setFloorIdentifier(hidef.getReportingEntity().getEditAddressVo().getFloorIdentifier());
					}
					
					address.setId(address.getId());
					if(!StringUtils.isEmpty(hidef.getReportingEntity().getEditAddressVo().getPob())){
					address.setPob(hidef.getReportingEntity().getEditAddressVo().getPob());
					}
					if(!StringUtils.isEmpty(hidef.getReportingEntity().getEditAddressVo().getPostCode())){
					address.setPostCode(hidef.getReportingEntity().getEditAddressVo().getPostCode());
					}
					if(!StringUtils.isEmpty(hidef.getReportingEntity().getEditAddressVo().getStreet())){
					address.setStreet(hidef.getReportingEntity().getEditAddressVo().getStreet());
					}
					if(!StringUtils.isEmpty(hidef.getReportingEntity().getEditAddressVo().getSuitIdentifier())){
					address.setSuitIdentifier(hidef.getReportingEntity().getEditAddressVo().getSuitIdentifier());
					}
				}
			}
			
		}
		
		String arrayToJson = mapper.writeValueAsString(addressView);
		model.addAttribute("hidef", hidef);
        return "cbcReportingEntity";
	}
	@RequestMapping(value = "/admin/cbc/reportEntitydeleteAddress", method = RequestMethod.GET)
	@ResponseBody
	public String deleteAddressGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestParam(required = true) int id, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if(hidef.getReportingEntity() != null && hidef.getReportingEntity().getAddressList() != null && hidef.getReportingEntity().getAddressList().size()>0) {
			
			addressList = hidef.getReportingEntity().getAddressList();
			for(AddressVo address: addressList) {
				if(address.getId()==id) {
					addressView = address;
					List<AddressVo> copyList = new ArrayList<AddressVo>(addressList);
					copyList.remove(address);
					if(hidef.getReportingEntity().getId() != null && address.getId() != 0) {
						cbcpayldaddressRepository.deleteById(BigInteger.valueOf(address.getId()));
					}
					hidef.getReportingEntity().setAddressList(copyList);
				}
			}
			
		}
		
		String arrayToJson = mapper.writeValueAsString(addressView);
		
        return arrayToJson;
	}

	
	
	@GetMapping(value ="/admin/cbc/loadReportingEntityAddress",consumes="application/json")
	@ResponseBody
	public String loadReportEntityAddressGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		List<AddressVo> addressList = new ArrayList<AddressVo>();
		String addressJson = mapper.writeValueAsString(addressList);
		if(hidef.getReportingEntity() != null && hidef.getReportingEntity().getAddressList() != null 
				&& hidef.getReportingEntity().getAddressList().size() >0) {
			addressJson = mapper.writeValueAsString(hidef.getReportingEntity().getAddressList());
		}
		model.addAttribute("hidef", hidef);
		return addressJson;
	}
	
	@GetMapping(value ="/admin/cbc/resetReportingEntityAddAddress")
	public String resetReportingEntityAddAddress(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response,Map<String, Object> map) throws JsonProcessingException {
		AddressVo addressVo = new AddressVo();
		hidef.getReportingEntity().setAddressVo(addressVo);
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		model.addAttribute("hidef", hidef);
		return "cbcReportingEntity";
	}
	
	
	@GetMapping(value ="/admin/cbc/getdocrefid")
	@ResponseBody
	public String getdocrefid(@ModelAttribute("hidef")HidefVo hidef, 
			@RequestParam(required = true) String type,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response,Map<String, Object> map) throws JsonProcessingException {
		String pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		Docrefid docrefid = ctccommonDropdownService.findDocRefIdByDate(date);
		String docRefId = "";
		if(docrefid != null){
			if(docrefid.getDocrefid() == "0001"){
				docRefId = "MY"+hidef.getMycbcId()+date+type+String.valueOf(docrefid.getDocrefid());	
			hidef.setDocRefId(String.valueOf(docrefid.getDocrefid()));
			}else{
				int sum = Integer.parseInt(docrefid.getDocrefid())+ 1;
				docRefId = "MY"+hidef.getMycbcId()+date+type+String.valueOf(converToString(sum));
				hidef.setDocRefId(String.valueOf(converToString(sum)));
			
			}
			
		}else{
			
			docrefid = ctcDataSaveService.saveDocRefIdDetails(date,type);
			docRefId = "MY"+hidef.getMycbcId()+date+type+String.valueOf(docrefid.getDocrefid());
			hidef.setDocRefId(String.valueOf(docrefid.getDocrefid()));
		}
		model.addAttribute("hidef", hidef);
		return docRefId;
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
	public HidefVo getDocRefId(HidefVo hidef,String Type,String communicationType){
		String pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		Docrefid docrefid = ctccommonDropdownService.findDocRefIdByDate(date);
		String docRefId = "";
		String docRefIdStatic = "MY"+hidef.getMycbcId()+date;
		if(docrefid != null){
			if(docrefid.getDocrefid().equals("0001")){
				docRefId = docRefIdStatic+"E"+String.valueOf(docrefid.getDocrefid());	
			hidef.setDocRefId(String.valueOf(docrefid.getDocrefid()));
			hidef.setDocRefIdStaticText(docRefIdStatic);
			}else{
				int sum = Integer.parseInt(docrefid.getDocrefid())+ 1;
				
				docRefId = docRefIdStatic+"E"+String.valueOf(converToString(sum));
				hidef.setDocRefId(String.valueOf(converToString(sum)));
				hidef.setDocRefIdStaticText(docRefIdStatic);
			
			}
			
		}else{
			
			docrefid = ctcDataSaveService.saveDocRefIdDetails(date,communicationType);
			docRefId = docRefIdStatic+"E"+String.valueOf(docrefid.getDocrefid());
			hidef.setDocRefId(String.valueOf(docrefid.getDocrefid()));
			hidef.setDocRefIdStaticText(docRefIdStatic);
		}
		
		if(hidef.getReportingEntity().getDocumentReferenceId() != null && !hidef.getReportingEntity().getDocumentReferenceId().isEmpty()) {
			hidef.getReportingEntity().setDocumentReferenceId(hidef.getReportingEntity().getDocumentReferenceId());
		}else {
		hidef.getReportingEntity().setDocumentReferenceId(docRefId);
		}
		return hidef;
	}


}
