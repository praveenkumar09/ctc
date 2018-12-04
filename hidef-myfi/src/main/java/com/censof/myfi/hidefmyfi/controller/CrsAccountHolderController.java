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
import org.springframework.boot.json.JsonParseException;
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
import com.censof.myfi.hidefmyfi.entity.CbcCurrency;
import com.censof.myfi.hidefmyfi.entity.Cbcaddresstype;
import com.censof.myfi.hidefmyfi.entity.Cbcdocumenttypeindicator;
import com.censof.myfi.hidefmyfi.entity.Cbcnametype;
import com.censof.myfi.hidefmyfi.entity.Crsaccounthodertype;
import com.censof.myfi.hidefmyfi.entity.Crsctrlpersontype;
import com.censof.myfi.hidefmyfi.entity.Crspaymenttype;
import com.censof.myfi.hidefmyfi.entity.Hicountry;
import com.censof.myfi.hidefmyfi.service.CtcDataSaveService;
import com.censof.myfi.hidefmyfi.service.CtccommonDropdownService;
import com.censof.myfi.hidefmyfi.vo.AccountHolderVo;
import com.censof.myfi.hidefmyfi.vo.AddressVo;
import com.censof.myfi.hidefmyfi.vo.CBCRepotsVo;
import com.censof.myfi.hidefmyfi.vo.CbcConstituentEntityVO;
import com.censof.myfi.hidefmyfi.vo.CommonDropdownGridBean;
import com.censof.myfi.hidefmyfi.vo.ControllingPersonVo;
import com.censof.myfi.hidefmyfi.vo.GenerationIdentifierVo;
import com.censof.myfi.hidefmyfi.vo.HidefVo;
import com.censof.myfi.hidefmyfi.vo.MiddleNameVo;
import com.censof.myfi.hidefmyfi.vo.NameTypeVo;
import com.censof.myfi.hidefmyfi.vo.NameVo;
import com.censof.myfi.hidefmyfi.vo.OrganisationInTypeVo;
import com.censof.myfi.hidefmyfi.vo.PaymentTypeVo;
import com.censof.myfi.hidefmyfi.vo.ResidentCountryVo;
import com.censof.myfi.hidefmyfi.vo.SuffixVo;
import com.censof.myfi.hidefmyfi.vo.TitleVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes("hidef")
public class CrsAccountHolderController {
	
	@Autowired
	private CtccommonDropdownService ctccommonDropdownService;
	
	@Autowired
	private CtcDataSaveService ctcDataSaveService;
	
	@ModelAttribute("hidef")
    public HidefVo getmetadata () {
        return new HidefVo(); 
    }
	
	@RequestMapping(value ="/admin/crs/accountHolder", method = RequestMethod.GET)
	public String getaccountHolder(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,Map<String, Object> map) {
		if(hidef.getAccountholder() == null){
			hidef.setAccountholder(new AccountHolderVo());
		}
		
		ObjectMapper mapper = new ObjectMapper();
		List<Hicountry> country = ctccommonDropdownService.getAllCountries();
		List<Cbcdocumenttypeindicator> cbcdocumenttypeindicator = ctccommonDropdownService.getAllDocumentTypeIndicator();
		List<CbcCurrency> currencyRef = ctccommonDropdownService.getAllCurrencyreference();
		List<Crspaymenttype> crspaymentType = ctccommonDropdownService.findAllPaymentType();
		List<Cbcnametype> cbcnametype = ctccommonDropdownService.getAllNameType();
		List<Crsaccounthodertype> accountHolderType = ctccommonDropdownService.findAllAccountHolderType();
		List<Crsctrlpersontype> controllingPersonType = ctccommonDropdownService.findAllControllingPersonType();
		
		List<CommonDropdownGridBean> gridBeans = new ArrayList<>();
		for(Crspaymenttype paymentType:crspaymentType) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(paymentType.getId());
			gridBean.setName(paymentType.getPaymentType());
			gridBeans.add(gridBean);			

		}
		List<CommonDropdownGridBean> countrygridBeans = new ArrayList<>();
		for(Hicountry coun:country) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(coun.getId());
			gridBean.setName(coun.getCountryCode());
			countrygridBeans.add(gridBean);			

		}
		List<CommonDropdownGridBean> currencygridBeans = new ArrayList<>();
		for(CbcCurrency currency:currencyRef) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(BigInteger.valueOf(currency.getId()));
			gridBean.setName(currency.getCode());
			currencygridBeans.add(gridBean);			

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
			map.put("paymentType", arrayToJson);
			map.put("currency", mapper.writeValueAsString(currencygridBeans));
			map.put("country", mapper.writeValueAsString(countrygridBeans));
			map.put("nameTypeList", mapper.writeValueAsString(nameTypegridBeans));
		} catch (JsonProcessingException e) {
			e.printStackTrace();

		}
		map.put("countryList", country);
		map.put("documentTypeIndicator", cbcdocumenttypeindicator);
		map.put("currencyList", currencyRef);
		map.put("accountHolderType", accountHolderType);
		map.put("controllingPersonType", controllingPersonType);
		model.addAttribute("hidef", hidef);
		hidef.setCurrentTab(CTSConstants.HIDEF_CTS_CRS_ACCOUNTHOLDER);
		return "crsAccountHolder";
	}
	@PostMapping(value ="/admin/crs/accountHolderPrevious")
	public String accountHolderPrevious(@ModelAttribute("hidef")HidefVo hidef, BindingResult result,
			 @RequestParam(required = false, value = "next1") String next,
			 @RequestParam(required = false, value = "previous2") String previous,
		     ModelMap model,RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "crsAccountHolder";
		}
		if(!StringUtils.isEmpty(previous)) {
			return "redirect:reportingFi";
		}
		
		/*if (!StringUtils.isEmpty(next)) {
	            return "redirect:reportingEntity";
	    }*/
		model.addAttribute("hidef", hidef);
		return CTSConstants.tabSelected(hidef.getCurrentTab());
	}
	
	@GetMapping(value ="/admin/crs/loadPaymentGrid",consumes="application/json")
	@ResponseBody
	public String loadPaymentGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<PaymentTypeVo> paymentList = new ArrayList<PaymentTypeVo>();
		String paymentJson = mapper.writeValueAsString(paymentList);
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getPaymentList() != null 
				&& hidef.getAccountholder().getPaymentList().size() >0) {
			paymentJson = mapper.writeValueAsString(hidef.getAccountholder().getPaymentList());
		}
		model.addAttribute("hidef", hidef);
		return paymentJson;
	}
	@PostMapping(value ="/admin/crs/insertPaymentGrid",consumes="application/json")
	@ResponseBody
	public PaymentTypeVo insertPaymentGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String insertpaymentGrid, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		PaymentTypeVo paymentVo  = null;
		Random ran = new Random();
		try {
			List<PaymentTypeVo> paymentList = new ArrayList<PaymentTypeVo>();
			paymentVo = mapper.readValue(insertpaymentGrid, PaymentTypeVo.class);
			paymentVo.setId(ran.nextInt(10000));
			if(hidef.getAccountholder() != null && hidef.getAccountholder().getPaymentList() != null 
					&& hidef.getAccountholder().getPaymentList().size() >0) {
				hidef.getAccountholder().getPaymentList().add(paymentVo);
			}else {
				hidef.getAccountholder().setPaymentList(paymentList);
				hidef.getAccountholder().getPaymentList().add(paymentVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return paymentVo;
	}
	
	@PostMapping(value ="/admin/crs/updatePaymentGrid",consumes="application/json")
	@ResponseBody
	public String updatePaymentGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String updatePaymentGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getPaymentList() != null 
				&& hidef.getAccountholder().getPaymentList().size() >0) {
			try{
				PaymentTypeVo updatedpaymentVo = mapper.readValue(updatePaymentGrid, PaymentTypeVo.class);
			for(PaymentTypeVo paymentvo :hidef.getAccountholder().getPaymentList()){
				if(paymentvo.getId()==id){
					paymentvo.setAmount(updatedpaymentVo.getAmount());
					paymentvo.setCurrency(updatedpaymentVo.getCurrency());
					paymentvo.setPaymentType(updatedpaymentVo.getPaymentType());
					
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
	@PostMapping(value ="/admin/crs/deletePaymentGrid",consumes="application/json")
	@ResponseBody
	public String deletePaymentGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String deletePaymentGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(deletePaymentGrid != null && hidef.getAccountholder().getPaymentList() != null){
			try{
			PaymentTypeVo deletePaymentVo = mapper.readValue(deletePaymentGrid, PaymentTypeVo.class);
			List<PaymentTypeVo> copyList = new ArrayList<PaymentTypeVo>(hidef.getAccountholder().getPaymentList());
			for(PaymentTypeVo payment:hidef.getAccountholder().getPaymentList()){
				if(id==payment.getId()){
					copyList.remove(payment);
					hidef.getAccountholder().setPaymentList(copyList);
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
	
	@GetMapping(value ="/admin/crs/loadIndividualResidentCountryGrid",consumes="application/json")
	@ResponseBody
	public String loadIndividualResidentCountryGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		List<ResidentCountryVo> residentCountryList = new ArrayList<ResidentCountryVo>();
		String residentJson = mapper.writeValueAsString(residentCountryList);
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualResidentCountryList() != null 
				&& hidef.getAccountholder().getIndividualResidentCountryList().size() >0) {
			residentJson = mapper.writeValueAsString(hidef.getAccountholder().getIndividualResidentCountryList());
		}
		

		
		model.addAttribute("hidef", hidef);
		return residentJson;
	}
	@PostMapping(value ="/admin/crs/insertIndividualResidentCountryGrid",consumes="application/json")
	@ResponseBody
	public ResidentCountryVo insertIndividualResidentCountryGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String insertResident,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Random ran = new Random();
		ResidentCountryVo residentCountryVo =null;
		try {
			List<ResidentCountryVo> residentCountryList = new ArrayList<ResidentCountryVo>();
			residentCountryVo = mapper.readValue(insertResident, ResidentCountryVo.class);
			if(hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualResidentCountryList() != null 
					&& hidef.getAccountholder().getIndividualResidentCountryList().size() >0) {
				residentCountryVo.setId(ran.nextInt(10000));
				hidef.getAccountholder().getIndividualResidentCountryList().add(residentCountryVo);
			}else {
				residentCountryVo.setId(ran.nextInt(10000));
				hidef.getAccountholder().setIndividualResidentCountryList(residentCountryList);
				hidef.getAccountholder().getIndividualResidentCountryList().add(residentCountryVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return residentCountryVo;
	}
	
	@PostMapping(value ="/admin/crs/updateIndividualResidentCountryGrid",consumes="application/json")
	@ResponseBody
	public String updateIndividualResidentCountryGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String updateResident,@RequestParam(required = true) int id, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualResidentCountryList() != null 
					&& hidef.getAccountholder().getIndividualResidentCountryList().size() >0) {
			try{
			ResidentCountryVo residentCountryVo = mapper.readValue(updateResident, ResidentCountryVo.class);
			for(ResidentCountryVo residentvo :hidef.getAccountholder().getIndividualResidentCountryList() ){
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
	@PostMapping(value ="/admin/crs/deleteIndividualResidentCountryGrid",consumes="application/json")
	@ResponseBody
	public String deleteIndividualResidentCountryGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String deleteResident,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
			if(deleteResident != null && hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualResidentCountryList() != null 
					&& hidef.getAccountholder().getIndividualResidentCountryList().size() >0){
				try{
				ResidentCountryVo residentCountryVo = mapper.readValue(deleteResident, ResidentCountryVo.class);
				List<ResidentCountryVo> copyList = new ArrayList<ResidentCountryVo>(hidef.getAccountholder().getIndividualResidentCountryList());
				for(ResidentCountryVo residentCountry: hidef.getAccountholder().getIndividualResidentCountryList()){
					if(residentCountryVo.getId()==residentCountry.getId()){
						copyList.remove(residentCountry);
						hidef.getAccountholder().setIndividualResidentCountryList(copyList);
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
	
	
	
	
	@GetMapping(value ="/admin/crs/loadIndividualOrganisationGrid",consumes="application/json")
	@ResponseBody
	public String loadIndividualOrganisationGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<OrganisationInTypeVo> organisationList = new ArrayList<OrganisationInTypeVo>();
		String organisationJson = mapper.writeValueAsString(organisationList);
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualOrganisationInTypeList() != null 
				&& hidef.getAccountholder().getIndividualOrganisationInTypeList().size() >0) {
			organisationJson = mapper.writeValueAsString(hidef.getAccountholder().getIndividualOrganisationInTypeList());
		}
		model.addAttribute("hidef", hidef);
		return organisationJson;
	}
	@PostMapping(value ="/admin/crs/insertIndividualOrganisationGrid",consumes="application/json")
	@ResponseBody
	public OrganisationInTypeVo insertIndividualOrganisationGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String insertOrganisationGrid, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		OrganisationInTypeVo organisationVo  = null;
		Random ran = new Random();
		try {
			List<OrganisationInTypeVo> organisationList = new ArrayList<OrganisationInTypeVo>();
			organisationVo = mapper.readValue(insertOrganisationGrid, OrganisationInTypeVo.class);
			organisationVo.setId(ran.nextInt(10000));
			if(hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualOrganisationInTypeList() != null 
					&& hidef.getAccountholder().getIndividualOrganisationInTypeList().size() >0) {
				hidef.getAccountholder().getIndividualOrganisationInTypeList().add(organisationVo);
			}else {
				hidef.getAccountholder().setIndividualOrganisationInTypeList(organisationList);
				hidef.getAccountholder().getIndividualOrganisationInTypeList().add(organisationVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return organisationVo;
	}
	
	@PostMapping(value ="/admin/crs/updateIndividualOrganisationGrid",consumes="application/json")
	@ResponseBody
	public String updateIndividualOrganisationGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String updateOrganisationGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualOrganisationInTypeList() != null 
				&& hidef.getAccountholder().getIndividualOrganisationInTypeList().size() >0) {
			try{
			OrganisationInTypeVo updatedorganisationVo = mapper.readValue(updateOrganisationGrid, OrganisationInTypeVo.class);
			for(OrganisationInTypeVo organisationvo :hidef.getAccountholder().getIndividualOrganisationInTypeList()){
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
	@PostMapping(value ="/admin/crs/deleteIndividualOrganisationGrid",consumes="application/json")
	@ResponseBody
	public String deleteIndividualOrganisationGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String deleteOrganisationGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(deleteOrganisationGrid != null && hidef.getAccountholder().getIndividualOrganisationInTypeList() != null){
			try{
			OrganisationInTypeVo updatedorganisationVo = mapper.readValue(deleteOrganisationGrid, OrganisationInTypeVo.class);
			List<OrganisationInTypeVo> copyList = new ArrayList<OrganisationInTypeVo>(hidef.getAccountholder().getIndividualOrganisationInTypeList());
			for(OrganisationInTypeVo organisation:hidef.getAccountholder().getIndividualOrganisationInTypeList()){
				if(id==organisation.getId()){
					copyList.remove(organisation);
					hidef.getAccountholder().setIndividualOrganisationInTypeList(copyList);
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
	
	
	
	
	@GetMapping(value ="/admin/crs/resetIndividualAddAddress")
	public String resetIndividualAddAddress(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response,Map<String, Object> map) throws JsonProcessingException {
		AddressVo addressVo = new AddressVo();
		hidef.getAccountholder().setIndividualaddressVo(addressVo);
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		model.addAttribute("hidef", hidef);
		return "crsAccountHolder";
	}
	@GetMapping(value ="/admin/crs/loadIndividualAddress",consumes="application/json")
	@ResponseBody
	public String loadIndividualAddress(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		List<AddressVo> addressList = new ArrayList<AddressVo>();
		String addressJson = mapper.writeValueAsString(addressList);
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualAddressList() != null 
				&& hidef.getAccountholder().getIndividualAddressList().size() >0) {
			addressJson = mapper.writeValueAsString(hidef.getAccountholder().getIndividualAddressList());
		}
		model.addAttribute("hidef", hidef);
		return addressJson;
	}
	@GetMapping(value ="/admin/crs/individualInsertAddress")
	@ResponseBody
	public AddressVo individualInsertAddress(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		List<AddressVo> addressList = null;
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualAddressList() != null && hidef.getAccountholder().getIndividualAddressList().size()>0) {
			addressList = hidef.getAccountholder().getIndividualAddressList();
			
		}else {
			addressList = new ArrayList<AddressVo>();
			hidef.getAccountholder().setIndividualAddressList(addressList);
		}
		
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualaddressVo() != null){
			AddressVo addressVo = new AddressVo();
			if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualaddressVo().getAddressFree())){
			addressVo.setAddressFree(hidef.getAccountholder().getIndividualaddressVo().getAddressFree());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualaddressVo().getAddressType())){
			addressVo.setAddressType(hidef.getAccountholder().getIndividualaddressVo().getAddressType());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualaddressVo().getBuildingIdentifier())){
			addressVo.setBuildingIdentifier(hidef.getAccountholder().getIndividualaddressVo().getBuildingIdentifier());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualaddressVo().getCity())){
			addressVo.setCity(hidef.getAccountholder().getIndividualaddressVo().getCity());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualaddressVo().getCountryCode())){
			addressVo.setCountryCode(hidef.getAccountholder().getIndividualaddressVo().getCountryCode());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualaddressVo().getCountrySubentity())){
			addressVo.setCountrySubentity(hidef.getAccountholder().getIndividualaddressVo().getCountrySubentity());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualaddressVo().getDistrictName())){
			addressVo.setDistrictName(hidef.getAccountholder().getIndividualaddressVo().getDistrictName());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualaddressVo().getFloorIdentifier())){
			addressVo.setFloorIdentifier(hidef.getAccountholder().getIndividualaddressVo().getFloorIdentifier());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualAddressList()) && hidef.getAccountholder().getIndividualAddressList().size()>0){
			addressVo.setId(hidef.getAccountholder().getIndividualAddressList().size()+1);
			hidef.getAccountholder().getIndividualaddressVo().setId(hidef.getAccountholder().getIndividualAddressList().size()+1);
			}else{
				addressVo.setId(1);
				hidef.getAccountholder().getIndividualaddressVo().setId(1);
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualaddressVo().getPob())){
			addressVo.setPob(hidef.getAccountholder().getIndividualaddressVo().getPob());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualaddressVo().getPostCode())){
			addressVo.setPostCode(hidef.getAccountholder().getIndividualaddressVo().getPostCode());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualaddressVo().getStreet())){
			addressVo.setStreet(hidef.getAccountholder().getIndividualaddressVo().getStreet());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualaddressVo().getSuitIdentifier())){
			addressVo.setSuitIdentifier(hidef.getAccountholder().getIndividualaddressVo().getSuitIdentifier());
			}
			addressList.add(addressVo);
		}
		
		hidef.getAccountholder().setIndividualAddressList(addressList);
		String arrayToJson = mapper.writeValueAsString(addressList);
		model.addAttribute("hidef", hidef);
        return hidef.getAccountholder().getIndividualaddressVo();
	}
	
	
	@RequestMapping(value = "/admin/crs/individualViewAddress", method = RequestMethod.GET)
	public String individualViewAddress(@ModelAttribute("hidef")HidefVo hidef,@RequestParam(required = true) int id,Map<String, Object> map, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualAddressList() != null && hidef.getAccountholder().getIndividualAddressList().size()>0) {
			addressList = hidef.getAccountholder().getIndividualAddressList();
			for(AddressVo address: addressList) {
				if(address.getId()==id) {
					addressView = address;
					hidef.getAccountholder().setIndividualviewAddressVo(addressView);
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
        return "crsAccountHolder";
	}
	@RequestMapping(value = "/admin/crs/individualEditAddress", method = RequestMethod.GET)
	public String individualEditAddress(@ModelAttribute("hidef")HidefVo hidef,@RequestParam(required = true) int id, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response,Map<String, Object> map) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualAddressList() != null && hidef.getAccountholder().getIndividualAddressList().size()>0) {
			addressList = hidef.getAccountholder().getIndividualAddressList();
			for(AddressVo address: addressList) {
				if(address.getId()==id) {
					addressView = address;
					hidef.getAccountholder().setIndividualeditAddressVo(addressView);
				}
			}
			
		}
		
		String arrayToJson = mapper.writeValueAsString(addressView);
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		model.addAttribute("hidef", hidef);
        return "crsAccountHolder";
	}
	
	
	@RequestMapping(value = "/admin/crs/individualEditSaveAddress", method = RequestMethod.GET)
	public String individualEditSaveAddress(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response,Map<String, Object> map) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualAddressList() != null && hidef.getAccountholder().getIndividualAddressList().size()>0) {
			addressList = hidef.getAccountholder().getIndividualAddressList();
			for(AddressVo address: addressList) {
				if(address.getId()==hidef.getAccountholder().getIndividualeditAddressVo().getId()) {
					if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualeditAddressVo().getAddressFree())){
					address.setAddressFree(hidef.getAccountholder().getIndividualeditAddressVo().getAddressFree());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualeditAddressVo().getAddressType())){
					address.setAddressType(hidef.getAccountholder().getIndividualeditAddressVo().getAddressType());
					address.setAddressTypeId(hidef.getAccountholder().getIndividualeditAddressVo().getAddressType());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualeditAddressVo().getBuildingIdentifier())){
					address.setBuildingIdentifier(hidef.getAccountholder().getIndividualeditAddressVo().getBuildingIdentifier());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualeditAddressVo().getCity())){
					address.setCity(hidef.getAccountholder().getIndividualeditAddressVo().getCity());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualeditAddressVo().getCountryCode())){
					address.setCountryCode(hidef.getAccountholder().getIndividualeditAddressVo().getCountryCode());
					address.setCountryCodeId(hidef.getAccountholder().getIndividualeditAddressVo().getCountryCode());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualeditAddressVo().getCountrySubentity())){
					address.setCountrySubentity(hidef.getAccountholder().getIndividualeditAddressVo().getCountrySubentity());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualeditAddressVo().getDistrictName())){
					address.setDistrictName(hidef.getAccountholder().getIndividualeditAddressVo().getDistrictName());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualeditAddressVo().getFloorIdentifier())){
					address.setFloorIdentifier(hidef.getAccountholder().getIndividualeditAddressVo().getFloorIdentifier());
					}
					
					address.setId(address.getId());
					if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualeditAddressVo().getPob())){
					address.setPob(hidef.getAccountholder().getIndividualeditAddressVo().getPob());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualeditAddressVo().getPostCode())){
					address.setPostCode(hidef.getAccountholder().getIndividualeditAddressVo().getPostCode());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualeditAddressVo().getStreet())){
					address.setStreet(hidef.getAccountholder().getIndividualeditAddressVo().getStreet());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualeditAddressVo().getSuitIdentifier())){
					address.setSuitIdentifier(hidef.getAccountholder().getIndividualeditAddressVo().getSuitIdentifier());
					}
				}
			}
			
		}
		
		String arrayToJson = mapper.writeValueAsString(addressView);
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		model.addAttribute("hidef", hidef);
        return "crsAccountHolder";
	}
	@RequestMapping(value = "/admin/crs/individualDeleteAddress", method = RequestMethod.GET)
	@ResponseBody
	public String individualDeleteAddress(@ModelAttribute("hidef")HidefVo hidef,@RequestParam(required = true) int id, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualAddressList() != null && hidef.getAccountholder().getIndividualAddressList().size()>0) {
			
			addressList = hidef.getAccountholder().getIndividualAddressList();
			for(AddressVo address: addressList) {
				if(address.getId()==id) {
					addressView = address;
					List<AddressVo> copyList = new ArrayList<AddressVo>(addressList);
					copyList.remove(address);
					hidef.getAccountholder().setIndividualAddressList(copyList);
				}
			}
			
		}
		
		String arrayToJson = mapper.writeValueAsString(addressView);
		
        return arrayToJson;
	}
	
	@GetMapping(value ="/admin/crs/resetIndividualNameGrid")
	public String resetIndividualNameGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response,Map<String, Object> map) throws JsonProcessingException {
		NameTypeVo  nameVo = new NameTypeVo();
		hidef.getAccountholder().setIndividualName(nameVo);
		hidef.getAccountholder().setTitleList(new ArrayList<TitleVo>());
		hidef.getAccountholder().setMiddlenameList(new ArrayList<MiddleNameVo>());
		hidef.getAccountholder().setGenerateIdentifilerList(new ArrayList<GenerationIdentifierVo>());
		hidef.getAccountholder().setSuffixList(new ArrayList<SuffixVo>());
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		List<Cbcnametype> cbcnametype = ctccommonDropdownService.getAllNameType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		map.put("nameType", cbcnametype);
		model.addAttribute("hidef", hidef);
		return "crsAccountHolder";
	}
	
	
	@GetMapping(value ="/admin/crs/loadIndividualNameTitleGrid",consumes="application/json")
	@ResponseBody
	public String loadIndividualNameTitleGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<TitleVo> titleList = new ArrayList<TitleVo>();
		String titleJson = mapper.writeValueAsString(titleList);
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getTitleList() != null 
				&& hidef.getAccountholder().getTitleList().size() >0) {
			titleJson = mapper.writeValueAsString(hidef.getAccountholder().getTitleList());
		}
		model.addAttribute("hidef", hidef);
		return titleJson;
	}
	
	
	@PostMapping(value ="/admin/crs/insertIndividualNameTitleGrid",consumes="application/json")
	@ResponseBody
	public TitleVo insertIndividualNameTitleGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String insertTitleGrid, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		TitleVo titleVo  = null;
		Random ran = new Random();
		try {
			List<TitleVo> titleList = new ArrayList<TitleVo>();
			titleVo = mapper.readValue(insertTitleGrid, TitleVo.class);
			titleVo.setId(ran.nextInt(10000));
			if(hidef.getAccountholder() != null && hidef.getAccountholder().getTitleList() != null 
					&& hidef.getAccountholder().getTitleList().size() >0) {
				hidef.getAccountholder().getTitleList().add(titleVo);
			}else {
				hidef.getAccountholder().setTitleList(titleList);
				hidef.getAccountholder().getTitleList().add(titleVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return titleVo;
	}
	
	@PostMapping(value ="/admin/crs/updateIndividualNameTitleGrid",consumes="application/json")
	@ResponseBody
	public String updateIndividualNameTitleGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String updateTitleGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getTitleList() != null 
				&& hidef.getAccountholder().getTitleList().size() >0) {
			try{
			TitleVo updatedTitleVo = mapper.readValue(updateTitleGrid, TitleVo.class);
			for(TitleVo title :hidef.getAccountholder().getTitleList()){
				if(title.getId()==id){
					title.setName(updatedTitleVo.getName());
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
	@PostMapping(value ="/admin/crs/deleteIndividualNameTitleGrid",consumes="application/json")
	@ResponseBody
	public String deleteIndividualNameTitleGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String deleteTitleGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(deleteTitleGrid != null && hidef.getAccountholder().getTitleList() != null){
			try{
			TitleVo deleteTitleVo = mapper.readValue(deleteTitleGrid, TitleVo.class);
			List<TitleVo> copyList = new ArrayList<TitleVo>(hidef.getAccountholder().getTitleList());
			for(TitleVo title:hidef.getAccountholder().getTitleList()){
				if(id==title.getId()){
					copyList.remove(title);
					hidef.getAccountholder().setTitleList(copyList);
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
	
	@GetMapping(value ="/admin/crs/loadIndividualMiddileNameGrid",consumes="application/json")
	@ResponseBody
	public String loadIndividualMiddileNameGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<MiddleNameVo> middileNameList = new ArrayList<MiddleNameVo>();
		String titleJson = mapper.writeValueAsString(middileNameList);
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getMiddlenameList() != null 
				&& hidef.getAccountholder().getMiddlenameList().size() >0) {
			titleJson = mapper.writeValueAsString(hidef.getAccountholder().getMiddlenameList());
		}
		model.addAttribute("hidef", hidef);
		return titleJson;
	}
	
	
	@PostMapping(value ="/admin/crs/insertIndividualMiddileNameGrid",consumes="application/json")
	@ResponseBody
	public MiddleNameVo insertIndividualMiddileNameGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String insertMiddleNameGrid, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		MiddleNameVo middleNameVo  = null;
		Random ran = new Random();
		try {
			List<MiddleNameVo> middleNameList = new ArrayList<MiddleNameVo>();
			middleNameVo = mapper.readValue(insertMiddleNameGrid, MiddleNameVo.class);
			middleNameVo.setId(ran.nextInt(10000));
			if(hidef.getAccountholder() != null && hidef.getAccountholder().getMiddlenameList() != null 
					&& hidef.getAccountholder().getMiddlenameList().size() >0) {
				hidef.getAccountholder().getMiddlenameList().add(middleNameVo);
			}else {
				hidef.getAccountholder().setMiddlenameList(middleNameList);
				hidef.getAccountholder().getMiddlenameList().add(middleNameVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return middleNameVo;
	}
	
	@PostMapping(value ="/admin/crs/updateIndividualMiddileNameGrid",consumes="application/json")
	@ResponseBody
	public String updateIndividualMiddileNameGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String updateMiddleNameGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getMiddlenameList() != null 
				&& hidef.getAccountholder().getMiddlenameList().size() >0) {
			try{
			MiddleNameVo middlenameVo = mapper.readValue(updateMiddleNameGrid, MiddleNameVo.class);
			for(MiddleNameVo middlename :hidef.getAccountholder().getMiddlenameList()){
				if(middlename.getId()==id){
					middlename.setMiddleName(middlenameVo.getMiddleName());
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
	@PostMapping(value ="/admin/crs/deleteIndividualMiddileNameGrid",consumes="application/json")
	@ResponseBody
	public String deleteIndividualMiddileNameGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String deleteMiddleNameGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(deleteMiddleNameGrid != null && hidef.getAccountholder().getMiddlenameList() != null){
			try{
			MiddleNameVo deleteMiddleNameVo = mapper.readValue(deleteMiddleNameGrid, MiddleNameVo.class);
			List<MiddleNameVo> copyList = new ArrayList<MiddleNameVo>(hidef.getAccountholder().getMiddlenameList());
			for(MiddleNameVo middlename:hidef.getAccountholder().getMiddlenameList()){
				if(id==middlename.getId()){
					copyList.remove(middlename);
					hidef.getAccountholder().setMiddlenameList(copyList);
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
	
	@GetMapping(value ="/admin/crs/loadIndividualGenIdGrid",consumes="application/json")
	@ResponseBody
	public String loadIndividualGenIdGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<GenerationIdentifierVo> genIdenList = new ArrayList<GenerationIdentifierVo>();
		String titleJson = mapper.writeValueAsString(genIdenList);
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getGenerateIdentifilerList() != null 
				&& hidef.getAccountholder().getGenerateIdentifilerList().size() >0) {
			titleJson = mapper.writeValueAsString(hidef.getAccountholder().getGenerateIdentifilerList());
		}
		model.addAttribute("hidef", hidef);
		return titleJson;
	}
	
	
	@PostMapping(value ="/admin/crs/insertIndividualGenIdGrid",consumes="application/json")
	@ResponseBody
	public GenerationIdentifierVo insertIndividualGenIdGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String insertGenIdenGrid, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		GenerationIdentifierVo genIdenVo  = null;
		Random ran = new Random();
		try {
			List<GenerationIdentifierVo> genIdenList = new ArrayList<GenerationIdentifierVo>();
			genIdenVo = mapper.readValue(insertGenIdenGrid, GenerationIdentifierVo.class);
			genIdenVo.setId(ran.nextInt(10000));
			if(hidef.getAccountholder() != null && hidef.getAccountholder().getGenerateIdentifilerList() != null 
					&& hidef.getAccountholder().getGenerateIdentifilerList().size() >0) {
				hidef.getAccountholder().getGenerateIdentifilerList().add(genIdenVo);
			}else {
				hidef.getAccountholder().setGenerateIdentifilerList(genIdenList);
				hidef.getAccountholder().getGenerateIdentifilerList().add(genIdenVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return genIdenVo;
	}
	
	@PostMapping(value ="/admin/crs/updateIndividualGenIdGrid",consumes="application/json")
	@ResponseBody
	public String updateIndividualGenIdGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String updateGenIdenGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getGenerateIdentifilerList() != null 
				&& hidef.getAccountholder().getGenerateIdentifilerList().size() >0) {
			try{
				GenerationIdentifierVo middlenameVo = mapper.readValue(updateGenIdenGrid, GenerationIdentifierVo.class);
			for(GenerationIdentifierVo geniden :hidef.getAccountholder().getGenerateIdentifilerList()){
				if(geniden.getId()==id){
					geniden.setGenerateIdentifier(middlenameVo.getGenerateIdentifier());
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
	@PostMapping(value ="/admin/crs/deleteIndividualGenIdGrid",consumes="application/json")
	@ResponseBody
	public String deleteIndividualGenIdGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String deleteGenIdenGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(deleteGenIdenGrid != null && hidef.getAccountholder().getGenerateIdentifilerList() != null){
			try{
			GenerationIdentifierVo deleteGenIdenVo = mapper.readValue(deleteGenIdenGrid, GenerationIdentifierVo.class);
			List<GenerationIdentifierVo> copyList = new ArrayList<GenerationIdentifierVo>(hidef.getAccountholder().getGenerateIdentifilerList());
			for(GenerationIdentifierVo geniden:hidef.getAccountholder().getGenerateIdentifilerList()){
				if(id==geniden.getId()){
					copyList.remove(geniden);
					hidef.getAccountholder().setGenerateIdentifilerList(copyList);
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
	
	@GetMapping(value ="/admin/crs/loadIndividualSuffixGrid",consumes="application/json")
	@ResponseBody
	public String loadIndividualSuffixGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<SuffixVo> suffixList = new ArrayList<SuffixVo>();
		String titleJson = mapper.writeValueAsString(suffixList);
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getSuffixList() != null 
				&& hidef.getAccountholder().getSuffixList().size() >0) {
			titleJson = mapper.writeValueAsString(hidef.getAccountholder().getSuffixList());
		}
		model.addAttribute("hidef", hidef);
		return titleJson;
	}
	
	
	@PostMapping(value ="/admin/crs/insertIndividualSuffixGrid",consumes="application/json")
	@ResponseBody
	public SuffixVo insertIndividualSuffixGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String insertSuffixGrid, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		SuffixVo suffixVo  = null;
		Random ran = new Random();
		try {
			List<SuffixVo> SuffixVo = new ArrayList<SuffixVo>();
			suffixVo = mapper.readValue(insertSuffixGrid, SuffixVo.class);
			suffixVo.setId(ran.nextInt(10000));
			if(hidef.getAccountholder() != null && hidef.getAccountholder().getSuffixList() != null 
					&& hidef.getAccountholder().getSuffixList().size() >0) {
				hidef.getAccountholder().getSuffixList().add(suffixVo);
			}else {
				hidef.getAccountholder().setSuffixList(SuffixVo);
				hidef.getAccountholder().getSuffixList().add(suffixVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return suffixVo;
	}
	
	@PostMapping(value ="/admin/crs/updateIndividualSuffixGrid",consumes="application/json")
	@ResponseBody
	public String updateIndividualSuffixGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String updateGenIdenGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getSuffixList() != null 
				&& hidef.getAccountholder().getSuffixList().size() >0) {
			try{
				SuffixVo suffixVo = mapper.readValue(updateGenIdenGrid, SuffixVo.class);
			for(SuffixVo suffix :hidef.getAccountholder().getSuffixList()){
				if(suffix.getId()==id){
					suffix.setSuffix(suffixVo.getSuffix());
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
	@PostMapping(value ="/admin/crs/deleteIndividualSuffixGrid",consumes="application/json")
	@ResponseBody
	public String deleteIndividualSuffixGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String deleteSuffixGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(deleteSuffixGrid != null && hidef.getAccountholder().getGenerateIdentifilerList() != null){
			try{
			SuffixVo deleteSuffixVo = mapper.readValue(deleteSuffixGrid, SuffixVo.class);
			List<SuffixVo> copyList = new ArrayList<SuffixVo>(hidef.getAccountholder().getSuffixList());
			for(SuffixVo suffix:hidef.getAccountholder().getSuffixList()){
				if(id==suffix.getId()){
					copyList.remove(suffix);
					hidef.getAccountholder().setSuffixList(copyList);
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
	@GetMapping(value ="/admin/crs/individualInsertNameGrid")
	@ResponseBody
	public NameTypeVo individualInsertNameGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		List<NameTypeVo> NameTypeList = null;
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualNameList() != null && hidef.getAccountholder().getIndividualNameList().size()>0) {
			NameTypeList = hidef.getAccountholder().getIndividualNameList();
			
		}else {
			NameTypeList = new ArrayList<NameTypeVo>();
			hidef.getAccountholder().setIndividualNameList(NameTypeList);
		}
		
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualName() != null){
			NameTypeVo nameTypeVo = new NameTypeVo();
			if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualNameList()) && hidef.getAccountholder().getIndividualNameList().size()>0){
				nameTypeVo.setId(hidef.getAccountholder().getIndividualNameList().size()+1);
				hidef.getAccountholder().getIndividualName().setId(hidef.getAccountholder().getIndividualNameList().size()+1);
				}else{
					nameTypeVo.setId(1);
					hidef.getAccountholder().getIndividualName().setId(1);
				}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualName().getFirstName())){
			nameTypeVo.setFirstName(hidef.getAccountholder().getIndividualName().getFirstName());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualName().getGeneralSuffix())){
			nameTypeVo.setGeneralSuffix(hidef.getAccountholder().getIndividualName().getGeneralSuffix());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualName().getLastName())){
				nameTypeVo.setLastName(hidef.getAccountholder().getIndividualName().getLastName());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualName().getName())){
				nameTypeVo.setName(hidef.getAccountholder().getIndividualName().getName());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualName().getNamePrefix())){
				nameTypeVo.setNamePrefix(hidef.getAccountholder().getIndividualName().getNamePrefix());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualName().getNameType())){
				nameTypeVo.setNameType(hidef.getAccountholder().getIndividualName().getNameType());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualName().getPrecedingTitle())){
				nameTypeVo.setPrecedingTitle(hidef.getAccountholder().getIndividualName().getPrecedingTitle());
			}
			/*if(!StringUtils.isEmpty(hidef.getAccountholder().getIndividualName().getPrecedingTitle())){
				nameTypeVo.set
			}*/
			nameTypeVo.setTitleList(hidef.getAccountholder().getTitleList());
			nameTypeVo.setMiddlenameList(hidef.getAccountholder().getMiddlenameList());
			nameTypeVo.setGenerateIdentifilerList(hidef.getAccountholder().getGenerateIdentifilerList());
			nameTypeVo.setSuffixList(hidef.getAccountholder().getSuffixList());
			NameTypeList.add(nameTypeVo);
		
		}
		
		hidef.getAccountholder().setIndividualNameList(NameTypeList);
		String arrayToJson = mapper.writeValueAsString(NameTypeList);
		model.addAttribute("hidef", hidef);
        return hidef.getAccountholder().getIndividualName();
	}
	@GetMapping(value ="/admin/crs/loadNameTypeMainGrid",consumes="application/json")
	@ResponseBody
	public String loadNameTypeMainGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		List<NameTypeVo> nameTypeList = new ArrayList<NameTypeVo>();
		String nameTypeJson = mapper.writeValueAsString(nameTypeList);
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualNameList() != null 
				&& hidef.getAccountholder().getIndividualNameList().size() >0) {
			nameTypeJson = mapper.writeValueAsString(hidef.getAccountholder().getIndividualNameList());
		}
		model.addAttribute("hidef", hidef);
		return nameTypeJson;
	}
	
	
	@GetMapping(value ="/admin/crs/loadOrganisationResidentCountryGrid",consumes="application/json")
	@ResponseBody
	public String loadOrganisationResidentCountryGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		List<ResidentCountryVo> residentCountryList = new ArrayList<ResidentCountryVo>();
		String residentJson = mapper.writeValueAsString(residentCountryList);
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getOrganisationResidentCountryList() != null 
				&& hidef.getAccountholder().getOrganisationResidentCountryList().size() >0) {
			residentJson = mapper.writeValueAsString(hidef.getAccountholder().getOrganisationResidentCountryList());
		}
		

		
		model.addAttribute("hidef", hidef);
		return residentJson;
	}
	@PostMapping(value ="/admin/crs/insertOrganisationResidentCountryGrid",consumes="application/json")
	@ResponseBody
	public ResidentCountryVo insertOrganisationResidentCountryGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String insertResident,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Random ran = new Random();
		ResidentCountryVo residentCountryVo =null;
		try {
			List<ResidentCountryVo> residentCountryList = new ArrayList<ResidentCountryVo>();
			residentCountryVo = mapper.readValue(insertResident, ResidentCountryVo.class);
			if(hidef.getAccountholder() != null && hidef.getAccountholder().getOrganisationResidentCountryList() != null 
					&& hidef.getAccountholder().getOrganisationResidentCountryList().size() >0) {
				residentCountryVo.setId(ran.nextInt(10000));
				hidef.getAccountholder().getOrganisationResidentCountryList().add(residentCountryVo);
			}else {
				residentCountryVo.setId(ran.nextInt(10000));
				hidef.getAccountholder().setOrganisationResidentCountryList(residentCountryList);
				hidef.getAccountholder().getOrganisationResidentCountryList().add(residentCountryVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return residentCountryVo;
	}
	
	@PostMapping(value ="/admin/crs/updateOrganisationResidentCountryGrid",consumes="application/json")
	@ResponseBody
	public String updateOrganisationResidentCountryGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String updateResident,@RequestParam(required = true) int id, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getOrganisationResidentCountryList() != null 
					&& hidef.getAccountholder().getOrganisationResidentCountryList().size() >0) {
			try{
			ResidentCountryVo residentCountryVo = mapper.readValue(updateResident, ResidentCountryVo.class);
			for(ResidentCountryVo residentvo :hidef.getAccountholder().getOrganisationResidentCountryList() ){
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
	@PostMapping(value ="/admin/crs/deleteOrganisationResidentCountryGrid",consumes="application/json")
	@ResponseBody
	public String deleteOrganisationResidentCountryGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String deleteResident,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
			if(deleteResident != null && hidef.getAccountholder() != null && hidef.getAccountholder().getOrganisationResidentCountryList() != null 
					&& hidef.getAccountholder().getOrganisationResidentCountryList().size() >0){
				try{
				ResidentCountryVo residentCountryVo = mapper.readValue(deleteResident, ResidentCountryVo.class);
				List<ResidentCountryVo> copyList = new ArrayList<ResidentCountryVo>(hidef.getAccountholder().getOrganisationResidentCountryList());
				for(ResidentCountryVo residentCountry: hidef.getAccountholder().getOrganisationResidentCountryList()){
					if(residentCountryVo.getId()==residentCountry.getId()){
						copyList.remove(residentCountry);
						hidef.getAccountholder().setOrganisationResidentCountryList(copyList);
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
	
	@GetMapping(value ="/admin/crs/loadOrgOrganisationGrid",consumes="application/json")
	@ResponseBody
	public String loadOrgOrganisationGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<OrganisationInTypeVo> organisationList = new ArrayList<OrganisationInTypeVo>();
		String organisationJson = mapper.writeValueAsString(organisationList);
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getOrgOrganisationInTypeList() != null 
				&& hidef.getAccountholder().getOrgOrganisationInTypeList().size() >0) {
			organisationJson = mapper.writeValueAsString(hidef.getAccountholder().getOrgOrganisationInTypeList());
		}
		model.addAttribute("hidef", hidef);
		return organisationJson;
	}
	@PostMapping(value ="/admin/crs/insertOrgOrganisationGrid",consumes="application/json")
	@ResponseBody
	public OrganisationInTypeVo insertOrgOrganisationGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String insertOrganisationGrid, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		OrganisationInTypeVo organisationVo  = null;
		Random ran = new Random();
		try {
			List<OrganisationInTypeVo> organisationList = new ArrayList<OrganisationInTypeVo>();
			organisationVo = mapper.readValue(insertOrganisationGrid, OrganisationInTypeVo.class);
			organisationVo.setId(ran.nextInt(10000));
			if(hidef.getAccountholder() != null && hidef.getAccountholder().getOrgOrganisationInTypeList() != null 
					&& hidef.getAccountholder().getOrgOrganisationInTypeList().size() >0) {
				hidef.getAccountholder().getOrgOrganisationInTypeList().add(organisationVo);
			}else {
				hidef.getAccountholder().setOrgOrganisationInTypeList(organisationList);
				hidef.getAccountholder().getOrgOrganisationInTypeList().add(organisationVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return organisationVo;
	}
	
	@PostMapping(value ="/admin/crs/updateOrgOrganisationGrid",consumes="application/json")
	@ResponseBody
	public String updateOrgOrganisationGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String updateOrganisationGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getOrgOrganisationInTypeList() != null 
				&& hidef.getAccountholder().getOrgOrganisationInTypeList().size() >0) {
			try{
			OrganisationInTypeVo updatedorganisationVo = mapper.readValue(updateOrganisationGrid, OrganisationInTypeVo.class);
			for(OrganisationInTypeVo organisationvo :hidef.getAccountholder().getOrgOrganisationInTypeList()){
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
	@PostMapping(value ="/admin/crs/deleteOrgOrganisationGrid",consumes="application/json")
	@ResponseBody
	public String deleteOrgOrganisationGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String deleteOrganisationGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(deleteOrganisationGrid != null && hidef.getAccountholder().getOrgOrganisationInTypeList() != null){
			try{
			OrganisationInTypeVo updatedorganisationVo = mapper.readValue(deleteOrganisationGrid, OrganisationInTypeVo.class);
			List<OrganisationInTypeVo> copyList = new ArrayList<OrganisationInTypeVo>(hidef.getAccountholder().getOrgOrganisationInTypeList());
			for(OrganisationInTypeVo organisation:hidef.getAccountholder().getOrgOrganisationInTypeList()){
				if(id==organisation.getId()){
					copyList.remove(organisation);
					hidef.getAccountholder().setOrgOrganisationInTypeList(copyList);
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
	
	
	@GetMapping(value ="/admin/crs/loadOrgNameGrid",consumes="application/json")
	@ResponseBody
	public String loadOrgNameGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<NameVo> nameList = new ArrayList<NameVo>();
		String nameJson = mapper.writeValueAsString(nameList);
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getOrganisationList() != null 
				&& hidef.getAccountholder().getOrganisationList().size() >0) {
			nameJson = mapper.writeValueAsString(hidef.getAccountholder().getOrganisationList());
		}
		model.addAttribute("hidef", hidef);
		return nameJson;
	}
	
	@PostMapping(value ="/admin/crs/insertOrgNameGrid",consumes="application/json")
	@ResponseBody
	public NameVo insertOrgNameGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String insertNameGrid, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Random ran = new Random();
		NameVo nameVo = null;
		try {
			List<NameVo> nameList = new ArrayList<NameVo>();
			nameVo = mapper.readValue(insertNameGrid, NameVo.class);
			nameVo.setId(ran.nextInt(10000));
			if(hidef.getAccountholder() != null && hidef.getAccountholder().getOrganisationList() != null 
					&& hidef.getAccountholder().getOrganisationList().size() >0) {
				hidef.getAccountholder().getOrganisationList().add(nameVo);
			}else {
				hidef.getAccountholder().setOrganisationList(nameList);
				hidef.getAccountholder().getOrganisationList().add(nameVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return nameVo;
	}
	
	@PostMapping(value ="/admin/crs/updateOrgNameGrid",consumes="application/json")
	@ResponseBody
	public String updateOrgNameGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String updateNameGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getOrganisationList() != null 
					&& hidef.getAccountholder().getOrganisationList().size() >0) {
			try {
			NameVo updatedNameVo = mapper.readValue(updateNameGrid, NameVo.class);
			for(NameVo namevo : hidef.getAccountholder().getOrganisationList()) {
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
	@PostMapping(value ="/admin/crs/deleteOrgNameGrid",consumes="application/json")
	@ResponseBody
	public String deleteOrgNameGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String deleteNameGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(deleteNameGrid != null && hidef.getAccountholder() != null && hidef.getAccountholder().getOrganisationList() != null 
					&& hidef.getAccountholder().getOrganisationList().size() >0){
			try{
			NameVo updatedNameVo = mapper.readValue(deleteNameGrid, NameVo.class);
			List<NameVo> copyList = new ArrayList<NameVo>(hidef.getAccountholder().getOrganisationList());
			for(NameVo namevo:hidef.getAccountholder().getOrganisationList()){
				if(id==namevo.getId()){
					copyList.remove(namevo);
					hidef.getAccountholder().setOrganisationList(copyList);
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
	
	
	
	@GetMapping(value ="/admin/crs/resetOrgAddAddress")
	public String resetOrgAddAddress(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response,Map<String, Object> map) throws JsonProcessingException {
		AddressVo addressVo = new AddressVo();
		hidef.getAccountholder().setOrganisationaddressVo(addressVo);
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		model.addAttribute("hidef", hidef);
		return "crsAccountHolder";
	}
	@GetMapping(value ="/admin/crs/loadOrganisationAddress",consumes="application/json")
	@ResponseBody
	public String loadOrganisationAddress(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		List<AddressVo> addressList = new ArrayList<AddressVo>();
		String addressJson = mapper.writeValueAsString(addressList);
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getOrganisationAddressList() != null 
				&& hidef.getAccountholder().getOrganisationAddressList().size() >0) {
			addressJson = mapper.writeValueAsString(hidef.getAccountholder().getOrganisationAddressList());
		}
		model.addAttribute("hidef", hidef);
		return addressJson;
	}
	@GetMapping(value ="/admin/crs/orgInsertAddress")
	@ResponseBody
	public AddressVo orgInsertAddress(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		List<AddressVo> addressList = null;
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getOrganisationAddressList() != null && hidef.getAccountholder().getOrganisationAddressList().size()>0) {
			addressList = hidef.getAccountholder().getOrganisationAddressList();
			
		}else {
			addressList = new ArrayList<AddressVo>();
			hidef.getAccountholder().setIndividualAddressList(addressList);
		}
		
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getOrganisationaddressVo() != null){
			AddressVo addressVo = new AddressVo();
			if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationaddressVo().getAddressFree())){
			addressVo.setAddressFree(hidef.getAccountholder().getOrganisationaddressVo().getAddressFree());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationaddressVo().getAddressType())){
			addressVo.setAddressType(hidef.getAccountholder().getOrganisationaddressVo().getAddressType());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationaddressVo().getBuildingIdentifier())){
			addressVo.setBuildingIdentifier(hidef.getAccountholder().getOrganisationaddressVo().getBuildingIdentifier());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationaddressVo().getCity())){
			addressVo.setCity(hidef.getAccountholder().getOrganisationaddressVo().getCity());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationaddressVo().getCountryCode())){
			addressVo.setCountryCode(hidef.getAccountholder().getOrganisationaddressVo().getCountryCode());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationaddressVo().getCountrySubentity())){
			addressVo.setCountrySubentity(hidef.getAccountholder().getOrganisationaddressVo().getCountrySubentity());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationaddressVo().getDistrictName())){
			addressVo.setDistrictName(hidef.getAccountholder().getOrganisationaddressVo().getDistrictName());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationaddressVo().getFloorIdentifier())){
			addressVo.setFloorIdentifier(hidef.getAccountholder().getOrganisationaddressVo().getFloorIdentifier());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationAddressList()) && hidef.getAccountholder().getOrganisationAddressList().size()>0){
			addressVo.setId(hidef.getAccountholder().getOrganisationAddressList().size()+1);
			hidef.getAccountholder().getOrganisationaddressVo().setId(hidef.getAccountholder().getOrganisationAddressList().size()+1);
			}else{
				addressVo.setId(1);
				hidef.getAccountholder().getOrganisationaddressVo().setId(1);
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationaddressVo().getPob())){
			addressVo.setPob(hidef.getAccountholder().getOrganisationaddressVo().getPob());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationaddressVo().getPostCode())){
			addressVo.setPostCode(hidef.getAccountholder().getOrganisationaddressVo().getPostCode());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationaddressVo().getStreet())){
			addressVo.setStreet(hidef.getAccountholder().getOrganisationaddressVo().getStreet());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationaddressVo().getSuitIdentifier())){
			addressVo.setSuitIdentifier(hidef.getAccountholder().getOrganisationaddressVo().getSuitIdentifier());
			}
			addressList.add(addressVo);
		}
		
		hidef.getAccountholder().setOrganisationAddressList(addressList);
		String arrayToJson = mapper.writeValueAsString(addressList);
		model.addAttribute("hidef", hidef);
        return hidef.getAccountholder().getOrganisationaddressVo();
	}
	
	
	@RequestMapping(value = "/admin/crs/organisationViewAddress", method = RequestMethod.GET)
	public String organisationViewAddress(@ModelAttribute("hidef")HidefVo hidef,@RequestParam(required = true) int id,Map<String, Object> map, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualAddressList() != null && hidef.getAccountholder().getIndividualAddressList().size()>0) {
			addressList = hidef.getAccountholder().getIndividualAddressList();
			for(AddressVo address: addressList) {
				if(address.getId()==id) {
					addressView = address;
					hidef.getAccountholder().setOrganisationviewAddressVo(addressView);
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
        return "crsAccountHolder";
	}
	@RequestMapping(value = "/admin/crs/orgEditAddress", method = RequestMethod.GET)
	public String orgEditAddress(@ModelAttribute("hidef")HidefVo hidef,@RequestParam(required = true) int id, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response,Map<String, Object> map) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getOrganisationAddressList() != null && hidef.getAccountholder().getOrganisationAddressList().size()>0) {
			addressList = hidef.getAccountholder().getOrganisationAddressList();
			for(AddressVo address: addressList) {
				if(address.getId()==id) {
					addressView = address;
					hidef.getAccountholder().setOrganisationeditAddressVo(addressView);
				}
			}
			
		}
		
		String arrayToJson = mapper.writeValueAsString(addressView);
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		model.addAttribute("hidef", hidef);
        return "crsAccountHolder";
	}
	
	
	@RequestMapping(value = "/admin/crs/orgEditSaveAddress", method = RequestMethod.GET)
	public String orgEditSaveAddress(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response,Map<String, Object> map) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getOrganisationAddressList() != null && hidef.getAccountholder().getOrganisationAddressList().size()>0) {
			addressList = hidef.getAccountholder().getOrganisationAddressList();
			for(AddressVo address: addressList) {
				if(address.getId()==hidef.getAccountholder().getOrganisationeditAddressVo().getId()) {
					if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationeditAddressVo().getAddressFree())){
					address.setAddressFree(hidef.getAccountholder().getOrganisationeditAddressVo().getAddressFree());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationeditAddressVo().getAddressType())){
					address.setAddressType(hidef.getAccountholder().getOrganisationeditAddressVo().getAddressType());
					address.setAddressTypeId(hidef.getAccountholder().getOrganisationeditAddressVo().getAddressType());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationeditAddressVo().getBuildingIdentifier())){
					address.setBuildingIdentifier(hidef.getAccountholder().getOrganisationeditAddressVo().getBuildingIdentifier());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationeditAddressVo().getCity())){
					address.setCity(hidef.getAccountholder().getOrganisationeditAddressVo().getCity());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationeditAddressVo().getCountryCode())){
					address.setCountryCode(hidef.getAccountholder().getOrganisationeditAddressVo().getCountryCode());
					address.setCountryCodeId(hidef.getAccountholder().getOrganisationeditAddressVo().getCountryCode());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationeditAddressVo().getCountrySubentity())){
					address.setCountrySubentity(hidef.getAccountholder().getOrganisationeditAddressVo().getCountrySubentity());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationeditAddressVo().getDistrictName())){
					address.setDistrictName(hidef.getAccountholder().getOrganisationeditAddressVo().getDistrictName());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationeditAddressVo().getFloorIdentifier())){
					address.setFloorIdentifier(hidef.getAccountholder().getOrganisationeditAddressVo().getFloorIdentifier());
					}
					
					address.setId(address.getId());
					if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationeditAddressVo().getPob())){
					address.setPob(hidef.getAccountholder().getOrganisationeditAddressVo().getPob());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationeditAddressVo().getPostCode())){
					address.setPostCode(hidef.getAccountholder().getOrganisationeditAddressVo().getPostCode());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationeditAddressVo().getStreet())){
					address.setStreet(hidef.getAccountholder().getOrganisationeditAddressVo().getStreet());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getOrganisationeditAddressVo().getSuitIdentifier())){
					address.setSuitIdentifier(hidef.getAccountholder().getOrganisationeditAddressVo().getSuitIdentifier());
					}
				}
			}
			
		}
		
		String arrayToJson = mapper.writeValueAsString(addressView);
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		model.addAttribute("hidef", hidef);
        return "crsAccountHolder";
	}
	@RequestMapping(value = "/admin/crs/orgDeleteAddress", method = RequestMethod.GET)
	@ResponseBody
	public String orgDeleteAddress(@ModelAttribute("hidef")HidefVo hidef,@RequestParam(required = true) int id, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getOrganisationAddressList() != null && hidef.getAccountholder().getOrganisationAddressList().size()>0) {
			
			addressList = hidef.getAccountholder().getOrganisationAddressList();
			for(AddressVo address: addressList) {
				if(address.getId()==id) {
					addressView = address;
					List<AddressVo> copyList = new ArrayList<AddressVo>(addressList);
					copyList.remove(address);
					hidef.getAccountholder().setOrganisationAddressList(copyList);
				}
			}
			
		}
		
		String arrayToJson = mapper.writeValueAsString(addressView);
		
        return arrayToJson;
	}
	
	
	@GetMapping(value ="/admin/crs/loadctrlResidentCountryGrid",consumes="application/json")
	@ResponseBody
	public String loadctrlResidentCountryGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		List<ResidentCountryVo> residentCountryList = new ArrayList<ResidentCountryVo>();
		String residentJson = mapper.writeValueAsString(residentCountryList);
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null && hidef.getAccountholder().getControllingPersonVo().getControllingResidentCountryList() != null 
				&& hidef.getAccountholder().getControllingPersonVo().getControllingResidentCountryList().size() >0) {
			residentJson = mapper.writeValueAsString(hidef.getAccountholder().getControllingPersonVo().getControllingResidentCountryList());
		}
		

		
		model.addAttribute("hidef", hidef);
		return residentJson;
	}
	@PostMapping(value ="/admin/crs/insertctrlResidentCountryGrid",consumes="application/json")
	@ResponseBody
	public ResidentCountryVo insertctrlResidentCountryGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String insertResident,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Random ran = new Random();
		ResidentCountryVo residentCountryVo =null;
		try {
			List<ResidentCountryVo> residentCountryList = new ArrayList<ResidentCountryVo>();
			residentCountryVo = mapper.readValue(insertResident, ResidentCountryVo.class);
			if(hidef.getAccountholder() != null &&hidef.getAccountholder().getControllingPersonVo() != null &&  hidef.getAccountholder().getControllingPersonVo().getControllingResidentCountryList() != null 
					&& hidef.getAccountholder().getControllingPersonVo().getControllingResidentCountryList().size() >0) {
				residentCountryVo.setId(ran.nextInt(10000));
				hidef.getAccountholder().getControllingPersonVo().getControllingResidentCountryList().add(residentCountryVo);
			}else {
				residentCountryVo.setId(ran.nextInt(10000));
				hidef.getAccountholder().getControllingPersonVo().setControllingResidentCountryList(residentCountryList);
				hidef.getAccountholder().getControllingPersonVo().getControllingResidentCountryList().add(residentCountryVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return residentCountryVo;
	}
	
	@PostMapping(value ="/admin/crs/updatectrlResidentCountryGrid",consumes="application/json")
	@ResponseBody
	public String updatectrlResidentCountryGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String updateResident,@RequestParam(required = true) int id, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getAccountholder() != null &&hidef.getAccountholder().getControllingPersonVo() != null &&  hidef.getAccountholder().getControllingPersonVo().getControllingResidentCountryList() != null 
				&& hidef.getAccountholder().getControllingPersonVo().getControllingResidentCountryList().size() >0) {
			try{
			ResidentCountryVo residentCountryVo = mapper.readValue(updateResident, ResidentCountryVo.class);
			for(ResidentCountryVo residentvo :hidef.getAccountholder().getControllingPersonVo().getControllingResidentCountryList() ){
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
	@PostMapping(value ="/admin/crs/deletectrlResidentCountryGrid",consumes="application/json")
	@ResponseBody
	public String deletectrlResidentCountryGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String deleteResident,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
			if(deleteResident != null && hidef.getAccountholder() != null &&hidef.getAccountholder().getControllingPersonVo() != null &&  hidef.getAccountholder().getControllingPersonVo().getControllingResidentCountryList() != null 
					&& hidef.getAccountholder().getControllingPersonVo().getControllingResidentCountryList().size() >0){
				try{
				ResidentCountryVo residentCountryVo = mapper.readValue(deleteResident, ResidentCountryVo.class);
				List<ResidentCountryVo> copyList = new ArrayList<ResidentCountryVo>(hidef.getAccountholder().getControllingPersonVo().getControllingResidentCountryList());
				for(ResidentCountryVo residentCountry: hidef.getAccountholder().getControllingPersonVo().getControllingResidentCountryList()){
					if(residentCountryVo.getId()==residentCountry.getId()){
						copyList.remove(residentCountry);
						hidef.getAccountholder().getControllingPersonVo().setControllingResidentCountryList(copyList);
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
	
	
	@GetMapping(value ="/admin/crs/loadctrlOrganisationGrid",consumes="application/json")
	@ResponseBody
	public String loadctrlOrganisationGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<OrganisationInTypeVo> organisationList = new ArrayList<OrganisationInTypeVo>();
		String organisationJson = mapper.writeValueAsString(organisationList);
		if(hidef.getAccountholder() != null &&hidef.getAccountholder().getControllingPersonVo() != null && hidef.getAccountholder().getControllingPersonVo().getControllingOrganisationInTypeList() != null 
				&& hidef.getAccountholder().getControllingPersonVo().getControllingOrganisationInTypeList().size() >0) {
			organisationJson = mapper.writeValueAsString(hidef.getAccountholder().getControllingPersonVo().getControllingOrganisationInTypeList());
		}
		model.addAttribute("hidef", hidef);
		return organisationJson;
	}
	@PostMapping(value ="/admin/crs/insertctrlOrganisationGrid",consumes="application/json")
	@ResponseBody
	public OrganisationInTypeVo insertctrlOrganisationGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String insertCtrlOrganisationGrid, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		OrganisationInTypeVo organisationVo  = null;
		Random ran = new Random();
		try {
			List<OrganisationInTypeVo> organisationList = new ArrayList<OrganisationInTypeVo>();
			organisationVo = mapper.readValue(insertCtrlOrganisationGrid, OrganisationInTypeVo.class);
			organisationVo.setId(ran.nextInt(10000));
			if(hidef.getAccountholder() != null &&hidef.getAccountholder().getControllingPersonVo() != null && hidef.getAccountholder().getControllingPersonVo().getControllingOrganisationInTypeList() != null 
					&& hidef.getAccountholder().getControllingPersonVo().getControllingOrganisationInTypeList().size() >0) {
				hidef.getAccountholder().getControllingPersonVo().getControllingOrganisationInTypeList().add(organisationVo);
			}else {
				hidef.getAccountholder().getControllingPersonVo().setControllingOrganisationInTypeList(organisationList);
				hidef.getAccountholder().getControllingPersonVo().getControllingOrganisationInTypeList().add(organisationVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return organisationVo;
	}
	
	@PostMapping(value ="/admin/crs/updatectrlOrganisationGrid",consumes="application/json")
	@ResponseBody
	public String updatectrlOrganisationGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String updateCtrlOrganisationGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getAccountholder() != null &&hidef.getAccountholder().getControllingPersonVo() != null && hidef.getAccountholder().getControllingPersonVo().getControllingOrganisationInTypeList() != null 
				&& hidef.getAccountholder().getControllingPersonVo().getControllingOrganisationInTypeList().size() >0) {
			try{
			OrganisationInTypeVo updatedorganisationVo = mapper.readValue(updateCtrlOrganisationGrid, OrganisationInTypeVo.class);
			for(OrganisationInTypeVo organisationvo :hidef.getAccountholder().getControllingPersonVo().getControllingOrganisationInTypeList()){
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
	@PostMapping(value ="/admin/crs/deletectrlOrganisationGrid",consumes="application/json")
	@ResponseBody
	public String deletectrlOrganisationGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String deleteCtrlOrganisationGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(deleteCtrlOrganisationGrid != null && hidef.getAccountholder() != null &&hidef.getAccountholder().getControllingPersonVo() != null && hidef.getAccountholder().getControllingPersonVo().getControllingOrganisationInTypeList() != null 
				&& hidef.getAccountholder().getControllingPersonVo().getControllingOrganisationInTypeList().size() >0){
			try{
			OrganisationInTypeVo updatedorganisationVo = mapper.readValue(deleteCtrlOrganisationGrid, OrganisationInTypeVo.class);
			List<OrganisationInTypeVo> copyList = new ArrayList<OrganisationInTypeVo>(hidef.getAccountholder().getControllingPersonVo().getControllingOrganisationInTypeList());
			for(OrganisationInTypeVo organisation:hidef.getAccountholder().getControllingPersonVo().getControllingOrganisationInTypeList()){
				if(id==organisation.getId()){
					copyList.remove(organisation);
					hidef.getAccountholder().getControllingPersonVo().setControllingOrganisationInTypeList(copyList);
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
	
	
	
	
	@GetMapping(value ="/admin/crs/resetCtrlPersonAddAddress")
	public String resetCtrlPersonAddAddress(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response,Map<String, Object> map) throws JsonProcessingException {
		AddressVo addressVo = new AddressVo();
		hidef.getAccountholder().setControllingPersonaddressVo(addressVo);
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		model.addAttribute("hidef", hidef);
		return "crsAccountHolder";
	}
	@GetMapping(value ="/admin/crs/loadCtrlPersonAddress",consumes="application/json")
	@ResponseBody
	public String loadCtrlPersonAddress(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		List<AddressVo> addressList = new ArrayList<AddressVo>();
		String addressJson = mapper.writeValueAsString(addressList);
		if(hidef.getAccountholder() != null &&  hidef.getAccountholder().getControllingPersonVo() != null && 
				hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList() != null && hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList() != null 
				&& hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList().size() >0) {
			addressJson = mapper.writeValueAsString(hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList());
		}
		model.addAttribute("hidef", hidef);
		return addressJson;
	}
	@GetMapping(value ="/admin/crs/ctrlPersonInsertAddress")
	@ResponseBody
	public AddressVo ctrlPersonInsertAddress(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		List<AddressVo> addressList = null;
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null && hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList() != null 
				&& hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList().size()>0) {
			addressList = hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList();
			
		}else {
			addressList = new ArrayList<AddressVo>();
			hidef.getAccountholder().getControllingPersonVo().setControllingPersonAddressList(addressList);
		}
		
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList() != null){
			AddressVo addressVo = new AddressVo();
			if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersonaddressVo().getAddressFree())){
			addressVo.setAddressFree(hidef.getAccountholder().getControllingPersonaddressVo().getAddressFree());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersonaddressVo().getAddressType())){
			addressVo.setAddressType(hidef.getAccountholder().getControllingPersonaddressVo().getAddressType());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersonaddressVo().getBuildingIdentifier())){
			addressVo.setBuildingIdentifier(hidef.getAccountholder().getControllingPersonaddressVo().getBuildingIdentifier());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersonaddressVo().getCity())){
			addressVo.setCity(hidef.getAccountholder().getControllingPersonaddressVo().getCity());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersonaddressVo().getCountryCode())){
			addressVo.setCountryCode(hidef.getAccountholder().getControllingPersonaddressVo().getCountryCode());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersonaddressVo().getCountrySubentity())){
			addressVo.setCountrySubentity(hidef.getAccountholder().getControllingPersonaddressVo().getCountrySubentity());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersonaddressVo().getDistrictName())){
			addressVo.setDistrictName(hidef.getAccountholder().getControllingPersonaddressVo().getDistrictName());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersonaddressVo().getFloorIdentifier())){
			addressVo.setFloorIdentifier(hidef.getAccountholder().getControllingPersonaddressVo().getFloorIdentifier());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList()) && hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList().size()>0){
			addressVo.setId(hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList().size()+1);
			hidef.getAccountholder().getControllingPersonaddressVo().setId(hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList().size()+1);
			}else{
				addressVo.setId(1);
				hidef.getAccountholder().getControllingPersonaddressVo().setId(1);
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersonaddressVo().getPob())){
			addressVo.setPob(hidef.getAccountholder().getControllingPersonaddressVo().getPob());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersonaddressVo().getPostCode())){
			addressVo.setPostCode(hidef.getAccountholder().getControllingPersonaddressVo().getPostCode());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersonaddressVo().getStreet())){
			addressVo.setStreet(hidef.getAccountholder().getControllingPersonaddressVo().getStreet());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersonaddressVo().getSuitIdentifier())){
			addressVo.setSuitIdentifier(hidef.getAccountholder().getControllingPersonaddressVo().getSuitIdentifier());
			}
			addressList.add(addressVo);
		}
		
		hidef.getAccountholder().getControllingPersonVo().setControllingPersonAddressList(addressList);
		String arrayToJson = mapper.writeValueAsString(addressList);
		model.addAttribute("hidef", hidef);
        return hidef.getAccountholder().getControllingPersonaddressVo();
	}
	
	
	@RequestMapping(value = "/admin/crs/ctrlPersonViewAddress", method = RequestMethod.GET)
	public String ctrlPersonViewAddress(@ModelAttribute("hidef")HidefVo hidef,@RequestParam(required = true) int id,Map<String, Object> map, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null && hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList() != null && hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList().size()>0) {
			addressList = hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList();
			for(AddressVo address: addressList) {
				if(address.getId()==id) {
					addressView = address;
					hidef.getAccountholder().setControllingPersonviewAddressVo(addressView);
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
        return "crsAccountHolder";
	}
	@RequestMapping(value = "/admin/crs/ctrlPersonEditAddress", method = RequestMethod.GET)
	public String ctrlPersonEditAddress(@ModelAttribute("hidef")HidefVo hidef,@RequestParam(required = true) int id, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response,Map<String, Object> map) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null && hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList() != null && hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList().size()>0) {
			addressList = hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList();
			for(AddressVo address: addressList) {
				if(address.getId()==id) {
					addressView = address;
					hidef.getAccountholder().setControllingPersoneditAddressVo(addressView);
				}
			}
			
		}
		
		String arrayToJson = mapper.writeValueAsString(addressView);
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		model.addAttribute("hidef", hidef);
        return "crsAccountHolder";
	}
	
	
	@RequestMapping(value = "/admin/crs/ctrlPersoneditSaveAddress", method = RequestMethod.GET)
	public String ctrlPersoneditSaveAddress(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response,Map<String, Object> map) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null && hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList() != null && hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList().size()>0) {
			addressList = hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList();
			for(AddressVo address: addressList) {
				if(address.getId()==hidef.getAccountholder().getControllingPersoneditAddressVo().getId()) {
					if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersoneditAddressVo().getAddressFree())){
					address.setAddressFree(hidef.getAccountholder().getControllingPersoneditAddressVo().getAddressFree());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersoneditAddressVo().getAddressType())){
					address.setAddressType(hidef.getAccountholder().getControllingPersoneditAddressVo().getAddressType());
					address.setAddressTypeId(hidef.getAccountholder().getControllingPersoneditAddressVo().getAddressType());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersoneditAddressVo().getBuildingIdentifier())){
					address.setBuildingIdentifier(hidef.getAccountholder().getControllingPersoneditAddressVo().getBuildingIdentifier());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersoneditAddressVo().getCity())){
					address.setCity(hidef.getAccountholder().getControllingPersoneditAddressVo().getCity());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersoneditAddressVo().getCountryCode())){
					address.setCountryCode(hidef.getAccountholder().getControllingPersoneditAddressVo().getCountryCode());
					address.setCountryCodeId(hidef.getAccountholder().getControllingPersoneditAddressVo().getCountryCode());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersoneditAddressVo().getCountrySubentity())){
					address.setCountrySubentity(hidef.getAccountholder().getControllingPersoneditAddressVo().getCountrySubentity());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersoneditAddressVo().getDistrictName())){
					address.setDistrictName(hidef.getAccountholder().getControllingPersoneditAddressVo().getDistrictName());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersoneditAddressVo().getFloorIdentifier())){
					address.setFloorIdentifier(hidef.getAccountholder().getControllingPersoneditAddressVo().getFloorIdentifier());
					}
					
					address.setId(address.getId());
					if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersoneditAddressVo().getPob())){
					address.setPob(hidef.getAccountholder().getControllingPersoneditAddressVo().getPob());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersoneditAddressVo().getPostCode())){
					address.setPostCode(hidef.getAccountholder().getControllingPersoneditAddressVo().getPostCode());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersoneditAddressVo().getStreet())){
					address.setStreet(hidef.getAccountholder().getControllingPersoneditAddressVo().getStreet());
					}
					if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersoneditAddressVo().getSuitIdentifier())){
					address.setSuitIdentifier(hidef.getAccountholder().getControllingPersoneditAddressVo().getSuitIdentifier());
					}
				}
			}
			
		}
		
		String arrayToJson = mapper.writeValueAsString(addressView);
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		model.addAttribute("hidef", hidef);
        return "crsAccountHolder";
	}
	@RequestMapping(value = "/admin/crs/ctrlPersonDeleteAddress", method = RequestMethod.GET)
	@ResponseBody
	public String ctrlPersonDeleteAddress(@ModelAttribute("hidef")HidefVo hidef,@RequestParam(required = true) int id, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null && hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList() != null && hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList().size()>0) {
			
			addressList = hidef.getAccountholder().getControllingPersonVo().getControllingPersonAddressList();
			for(AddressVo address: addressList) {
				if(address.getId()==id) {
					addressView = address;
					List<AddressVo> copyList = new ArrayList<AddressVo>(addressList);
					copyList.remove(address);
					hidef.getAccountholder().getControllingPersonVo().setControllingPersonAddressList(copyList);
				}
			}
			
		}
		
		String arrayToJson = mapper.writeValueAsString(addressView);
		
        return arrayToJson;
	}
	
	@GetMapping(value = "/admin/crs/loadAccountHolderMain", consumes = "application/json")
	@ResponseBody
	public String loadAccountHolderMain(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		List<AccountHolderVo> accountHolderList = new ArrayList<AccountHolderVo>();
		String reportsJSON = mapper.writeValueAsString(accountHolderList);
		if (hidef.getAccountHolderList() != null && hidef.getAccountHolderList() != null
				&& !hidef.getAccountHolderList().isEmpty()) {
			reportsJSON = mapper.writeValueAsString(hidef.getAccountHolderList());
		}

		model.addAttribute("hidef", hidef);
		return reportsJSON;

	}
	@GetMapping(value = "/admin/crs/insertAcountHolderMain")
	@ResponseBody
	public AccountHolderVo insertAcountHolderMain(@ModelAttribute("hidef") HidefVo hidef, BindingResult result,
			ModelMap model, Map<String, Object> map) {

		AccountHolderVo accountHolderVO = new AccountHolderVo();
		Random rand = new Random();
		accountHolderVO = hidef.getAccountholder();
		accountHolderVO.setId(rand.nextInt(10000));
		accountHolderVO.setControllingPersonList(hidef.getControllingPersonList());
		if (hidef.getAccountholder() != null && hidef.getAccountHolderList() != null && hidef.getAccountHolderList().size() >0) {
			hidef.getAccountHolderList().add(accountHolderVO);
		} else {
			hidef.setAccountHolderList(new ArrayList<AccountHolderVo>());
			hidef.getAccountHolderList().add(accountHolderVO);
		}
		hidef.setAccountholder(new AccountHolderVo());
		model.addAttribute("hidef", hidef);

		return accountHolderVO;
	}
	
	@GetMapping(value = "/admin/crs/viewAcountHolderMain")
	@ResponseBody
	public String viewAcountHolderMain(@ModelAttribute("hidef") HidefVo hidef, @RequestParam int viewId, BindingResult result,
			ModelMap model, Map<String, Object> map) throws JsonParseException, JsonMappingException, IOException {
		AccountHolderVo viewAccountHolder = new AccountHolderVo();
		for (AccountHolderVo accountHolder : hidef.getAccountHolderList()) {
			if (accountHolder.getId() == viewId) {
				viewAccountHolder = accountHolder;
			}
		}

		hidef.setAccountholder(viewAccountHolder);
		model.addAttribute("hidef", hidef);
		return "success";
	}
	
	@GetMapping(value = "/admin/crs/editAcountHolderMain")
	@ResponseBody
	public String editAcountHolderMain(@ModelAttribute("hidef") HidefVo hidef, @RequestParam int editId, BindingResult result,
			ModelMap model, Map<String, Object> map) throws JsonParseException, JsonMappingException, IOException {
		AccountHolderVo editAccountHolder = new AccountHolderVo();
		for (AccountHolderVo accountHolder : hidef.getAccountHolderList()) {
			if (accountHolder.getId() == editId) {
				editAccountHolder = accountHolder;
			}
		}

		hidef.setAccountholder(editAccountHolder);
		model.addAttribute("hidef", hidef);
		return "success";
	}
	@GetMapping(value = "/admin/crs/viewAccountDone")
	@ResponseBody
	public String viewAccountDone(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			Map<String, Object> map) {
		hidef.setCurrentTab(CTSConstants.HIDEF_CTS_CRS_ACCOUNTHOLDER);
		hidef.setAccountholder(new AccountHolderVo());
		model.addAttribute("hidef", hidef);
		return "success";
	}
	@GetMapping(value = "/admin/crs/populateDeletedAccountHolder")
	@ResponseBody
	public String populateDeletedCBCReportsGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestParam int deleteId,
			BindingResult result, ModelMap model, Map<String, Object> map) {

		List<AccountHolderVo> newAccountHolderDeletedList = new ArrayList<AccountHolderVo>();
		if(hidef.getAccountHolderList() != null && hidef.getAccountHolderList().size()>0){
		for (AccountHolderVo accountsVO : hidef.getAccountHolderList()) {
			if (accountsVO.getId() != deleteId) {
				newAccountHolderDeletedList.add(accountsVO);
			}
		}
		}

		hidef.setAccountHolderList(newAccountHolderDeletedList);
		model.addAttribute("hidef", hidef);

		return "success";
	}
	
	@GetMapping(value = "/admin/crs/saveEditedData")
	@ResponseBody
	public AccountHolderVo populateEditedCBCReportsGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result,
			ModelMap model, Map<String, Object> map) {

		AccountHolderVo accountHolderVO = new AccountHolderVo();
		accountHolderVO = hidef.getAccountholder();
		int newAccountId = accountHolderVO.getId();

		List<AccountHolderVo> newCRSAccountEditedList = new ArrayList<AccountHolderVo>();
		for (AccountHolderVo accountVO : hidef.getAccountHolderList()) {
			if (accountVO.getId() != newAccountId) {
				newCRSAccountEditedList.add(accountVO);
			}
		}

		newCRSAccountEditedList.add(accountHolderVO);

		hidef.setAccountholder(new AccountHolderVo());
		model.addAttribute("hidef", hidef);

		return accountHolderVO;
	}
	
	@GetMapping(value = "/admin/crs/loadCtrlPersonMain", consumes = "application/json")
	@ResponseBody
	public String loadCtrlPersonMain(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		List<AccountHolderVo> accountHolderList = new ArrayList<AccountHolderVo>();
		String reportsJSON = mapper.writeValueAsString(accountHolderList);
		if (hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonList() != null 
				&& !hidef.getAccountholder().getControllingPersonList().isEmpty()) {
			reportsJSON = mapper.writeValueAsString(hidef.getAccountholder().getControllingPersonList());
		}

		model.addAttribute("hidef", hidef);
		return reportsJSON;

	}
	@GetMapping(value = "/admin/crs/insertCtrlPersonMain")
	@ResponseBody
	public ControllingPersonVo insertCtrlPersonMain(@ModelAttribute("hidef") HidefVo hidef, BindingResult result,
			ModelMap model, Map<String, Object> map) {

		ControllingPersonVo ctrlPerson = new ControllingPersonVo();
		Random rand = new Random();
		ctrlPerson = hidef.getAccountholder().getControllingPersonVo();
		ctrlPerson.setId(rand.nextInt(10000));
		if (hidef.getAccountholder().getControllingPersonList() != null && hidef.getAccountholder().getControllingPersonList().size() >0) {
			hidef.getAccountholder().getControllingPersonList().add(ctrlPerson);
		} else {
			hidef.getAccountholder().setControllingPersonList(new ArrayList<ControllingPersonVo>());
			hidef.getAccountholder().getControllingPersonList().add(ctrlPerson);
		}
		
		hidef.getAccountholder().setControllingPersonVo(new ControllingPersonVo());
		model.addAttribute("hidef", hidef);
		
		return ctrlPerson;
	}
	
	/*@GetMapping(value = "/admin/crs/editIndividualNameGrid")
	@ResponseBody
	public String editIndividualNameGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result,
			@RequestParam int id,
			ModelMap model, Map<String, Object> map) {

		List<NameTypeVo> nameTypeList = hidef.getAccountholder().getIndividualNameList();
		if(nameTypeList != null && nameTypeList.size() > 0){
			for(NameTypeVo nameType : nameTypeList){
				if(nameType.getId() == id){
					hidef.getAccountholder().setTitleList(nameType.getTitleList());
					hidef.getAccountholder().setMiddlenameList(nameType.getMiddlenameList());
					hidef.getAccountholder().setGenerateIdentifilerList(nameType.getGenerateIdentifilerList());
					hidef.getAccountholder().setSuffixList(nameType.getSuffixList());
				}
				
			}
		}
		model.addAttribute("hidef", hidef);

		return "crsAccountHolder";
	}*/

	
	
	@GetMapping(value ="/admin/crs/resetCtrlNameGrid")
	public String resetCtrlNameGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response,Map<String, Object> map) throws JsonProcessingException {
		NameTypeVo  nameVo = new NameTypeVo();
		hidef.getAccountholder().getControllingPersonVo().setCtrlPersonName(nameVo);
	/*	hidef.getAccountholder().setCtrlPersontitleList(new ArrayList<TitleVo>());
		hidef.getAccountholder().setCtrlPersonmiddlenameList(new ArrayList<MiddleNameVo>());
		hidef.getAccountholder().setCtrlPersongenerateIdentifilerList(new ArrayList<GenerationIdentifierVo>());
		hidef.getAccountholder().setCtrlPersonsuffixList(new ArrayList<SuffixVo>());*/
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		List<Cbcnametype> cbcnametype = ctccommonDropdownService.getAllNameType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		map.put("nameType", cbcnametype);
		model.addAttribute("hidef", hidef);
		return "crsAccountHolder";
	}
	
	
	
	
	
	@GetMapping(value ="/admin/crs/loadCtrlPersonNameTitleGrid",consumes="application/json")
	@ResponseBody
	public String loadCtrlPersonNameTitleGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<TitleVo> titleList = new ArrayList<TitleVo>();
		String titleJson = mapper.writeValueAsString(titleList);
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null &&
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName() != null && hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getTitleList() != null 
				&& hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getTitleList().size() >0) {
			titleJson = mapper.writeValueAsString(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getTitleList());
		}
		model.addAttribute("hidef", hidef);
		return titleJson;
	}
	
	
	@PostMapping(value ="/admin/crs/insertCtrlPersonNameTitleGrid",consumes="application/json")
	@ResponseBody
	public TitleVo insertCtrlPersonNameTitleGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String insertTitleGrid, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		TitleVo titleVo  = null;
		Random ran = new Random();
		try {
			List<TitleVo> titleList = new ArrayList<TitleVo>();
			titleVo = mapper.readValue(insertTitleGrid, TitleVo.class);
			titleVo.setId(ran.nextInt(10000));
			if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null &&
					hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName() != null && hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getTitleList() != null 
					&& hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getTitleList().size() >0) {
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getTitleList().add(titleVo);
			}else {
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().setTitleList(titleList);
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getTitleList().add(titleVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return titleVo;
	}
	
	@PostMapping(value ="/admin/crs/updateCtrlPersonNameTitleGrid",consumes="application/json")
	@ResponseBody
	public String updateCtrlPersonNameTitleGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String updateTitleGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null &&
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName() != null && hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getTitleList() != null 
				&& hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getTitleList().size() >0) {
			try{
			TitleVo updatedTitleVo = mapper.readValue(updateTitleGrid, TitleVo.class);
			for(TitleVo title :hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getTitleList()){
				if(title.getId()==id){
					title.setName(updatedTitleVo.getName());
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
	@PostMapping(value ="/admin/crs/deleteCtrlPersonNameTitleGrid",consumes="application/json")
	@ResponseBody
	public String deleteCtrlPersonNameTitleGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String deleteTitleGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(deleteTitleGrid != null && hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null &&
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName() != null && hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getTitleList() != null 
				&& hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getTitleList().size() >0){
			try{
			TitleVo deleteTitleVo = mapper.readValue(deleteTitleGrid, TitleVo.class);
			List<TitleVo> copyList = new ArrayList<TitleVo>(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getTitleList());
			for(TitleVo title:hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getTitleList()){
				if(id==title.getId()){
					copyList.remove(title);
					hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().setTitleList(copyList);
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
	
	@GetMapping(value ="/admin/crs/loadCtrlPersonMiddileNameGrid",consumes="application/json")
	@ResponseBody
	public String loadCtrlPersonNameGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<MiddleNameVo> middileNameList = new ArrayList<MiddleNameVo>();
		String titleJson = mapper.writeValueAsString(middileNameList);
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null &&
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName() != null && hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getMiddlenameList() != null 
				&& hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getMiddlenameList().size() >0) {
			titleJson = mapper.writeValueAsString(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getMiddlenameList());
		}
		model.addAttribute("hidef", hidef);
		return titleJson;
	}
	
	
	@PostMapping(value ="/admin/crs/insertCtrlPersonMiddileNameGrid",consumes="application/json")
	@ResponseBody
	public MiddleNameVo insertCtrlPersonMiddileNameGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String insertMiddleNameGrid, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		MiddleNameVo middleNameVo  = null;
		Random ran = new Random();
		try {
			List<MiddleNameVo> middleNameList = new ArrayList<MiddleNameVo>();
			middleNameVo = mapper.readValue(insertMiddleNameGrid, MiddleNameVo.class);
			middleNameVo.setId(ran.nextInt(10000));
			if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null &&
					hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName() != null && hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getMiddlenameList() != null 
					&& hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getMiddlenameList().size() >0) {
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getMiddlenameList().add(middleNameVo);
			}else {
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().setMiddlenameList(middleNameList);
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getMiddlenameList().add(middleNameVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return middleNameVo;
	}
	
	@PostMapping(value ="/admin/crs/updateCtrlPersonMiddileNameGrid",consumes="application/json")
	@ResponseBody
	public String updateCtrlPersonMiddileNameGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String updateMiddleNameGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null &&
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName() != null && hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getMiddlenameList() != null 
				&& hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getMiddlenameList().size() >0) {
			try{
			MiddleNameVo middlenameVo = mapper.readValue(updateMiddleNameGrid, MiddleNameVo.class);
			for(MiddleNameVo middlename :hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getMiddlenameList()){
				if(middlename.getId()==id){
					middlename.setMiddleName(middlenameVo.getMiddleName());
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
	@PostMapping(value ="/admin/crs/deleteCtrlPersonMiddileNameGrid",consumes="application/json")
	@ResponseBody
	public String deleteCtrlPersonMiddileNameGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String deleteMiddleNameGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(deleteMiddleNameGrid != null && hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null &&
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName() != null && hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getMiddlenameList() != null 
				&& hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getMiddlenameList().size() >0){
			try{
			MiddleNameVo deleteMiddleNameVo = mapper.readValue(deleteMiddleNameGrid, MiddleNameVo.class);
			List<MiddleNameVo> copyList = new ArrayList<MiddleNameVo>(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getMiddlenameList());
			for(MiddleNameVo middlename:hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getMiddlenameList()){
				if(id==middlename.getId()){
					copyList.remove(middlename);
					hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().setMiddlenameList(copyList);
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
	
	@GetMapping(value ="/admin/crs/loadCtrlPersonGenIdGrid",consumes="application/json")
	@ResponseBody
	public String loadCtrlPersonGenIdGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<GenerationIdentifierVo> genIdenList = new ArrayList<GenerationIdentifierVo>();
		String titleJson = mapper.writeValueAsString(genIdenList);
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null && 
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName() != null &&hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getGenerateIdentifilerList() != null 
				&& hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getGenerateIdentifilerList().size() >0) {
			titleJson = mapper.writeValueAsString(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getGenerateIdentifilerList());
		}
		model.addAttribute("hidef", hidef);
		return titleJson;
	}
	
	
	@PostMapping(value ="/admin/crs/insertCtrlPersonGenIdGrid",consumes="application/json")
	@ResponseBody
	public GenerationIdentifierVo insertCtrlPersonGenIdGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String insertGenIdenGrid, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		GenerationIdentifierVo genIdenVo  = null;
		Random ran = new Random();
		try {
			List<GenerationIdentifierVo> genIdenList = new ArrayList<GenerationIdentifierVo>();
			genIdenVo = mapper.readValue(insertGenIdenGrid, GenerationIdentifierVo.class);
			genIdenVo.setId(ran.nextInt(10000));
			if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null && 
					hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName() != null &&hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getGenerateIdentifilerList() != null 
					&& hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getGenerateIdentifilerList().size() >0) {
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getGenerateIdentifilerList().add(genIdenVo);
			}else {
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().setGenerateIdentifilerList(genIdenList);
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getGenerateIdentifilerList().add(genIdenVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return genIdenVo;
	}
	
	@PostMapping(value ="/admin/crs/updateCtrlPersonGenIdGrid",consumes="application/json")
	@ResponseBody
	public String updateCtrlPersonGenIdGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String updateGenIdenGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null && 
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName() != null &&hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getGenerateIdentifilerList() != null 
				&& hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getGenerateIdentifilerList().size() >0) {
			try{
				GenerationIdentifierVo middlenameVo = mapper.readValue(updateGenIdenGrid, GenerationIdentifierVo.class);
			for(GenerationIdentifierVo geniden :hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getGenerateIdentifilerList()){
				if(geniden.getId()==id){
					geniden.setGenerateIdentifier(middlenameVo.getGenerateIdentifier());
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
	@PostMapping(value ="/admin/crs/deleteCtrlPersonGenIdGrid",consumes="application/json")
	@ResponseBody
	public String deleteCtrlPersonGenIdGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String deleteGenIdenGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(deleteGenIdenGrid != null && hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null && 
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName() != null &&hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getGenerateIdentifilerList() != null 
				&& hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getGenerateIdentifilerList().size() >0){
			try{
			GenerationIdentifierVo deleteGenIdenVo = mapper.readValue(deleteGenIdenGrid, GenerationIdentifierVo.class);
			List<GenerationIdentifierVo> copyList = new ArrayList<GenerationIdentifierVo>(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getGenerateIdentifilerList());
			for(GenerationIdentifierVo geniden:hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getGenerateIdentifilerList()){
				if(id==geniden.getId()){
					copyList.remove(geniden);
					hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().setGenerateIdentifilerList(copyList);
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
	
	@GetMapping(value ="/admin/crs/loadCtrlPersonSuffixGrid",consumes="application/json")
	@ResponseBody
	public String loadCtrlPersonSuffixGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<SuffixVo> suffixList = new ArrayList<SuffixVo>();
		String titleJson = mapper.writeValueAsString(suffixList);
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null && 
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName() != null &&hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getSuffixList() != null 
				&& hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getSuffixList().size() >0) {
			titleJson = mapper.writeValueAsString(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getSuffixList());
		}
		model.addAttribute("hidef", hidef);
		return titleJson;
	}
	
	
	@PostMapping(value ="/admin/crs/insertCtrlPersonSuffixGrid",consumes="application/json")
	@ResponseBody
	public SuffixVo insertCtrlPersonSuffixGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestBody String insertSuffixGrid, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		SuffixVo suffixVo  = null;
		Random ran = new Random();
		try {
			List<SuffixVo> SuffixVo = new ArrayList<SuffixVo>();
			suffixVo = mapper.readValue(insertSuffixGrid, SuffixVo.class);
			suffixVo.setId(ran.nextInt(10000));
			if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null && 
					hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName() != null &&hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getSuffixList() != null 
					&& hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getSuffixList().size() >0) {
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getSuffixList().add(suffixVo);
			}else {
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().setSuffixList(SuffixVo);
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getSuffixList().add(suffixVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return suffixVo;
	}
	
	@PostMapping(value ="/admin/crs/updateCtrlPersonSuffixGrid",consumes="application/json")
	@ResponseBody
	public String updateCtrlPersonSuffixGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String updateGenIdenGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null && 
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName() != null &&hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getSuffixList() != null 
				&& hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getSuffixList().size() >0) {
			try{
				SuffixVo suffixVo = mapper.readValue(updateGenIdenGrid, SuffixVo.class);
			for(SuffixVo suffix :hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getSuffixList()){
				if(suffix.getId()==id){
					suffix.setSuffix(suffixVo.getSuffix());
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
	@PostMapping(value ="/admin/crs/deleteCtrlPersonSuffixGrid",consumes="application/json")
	@ResponseBody
	public String deleteCtrlPersonSuffixGrid(@ModelAttribute("hidef")HidefVo hidef, @RequestBody String deleteSuffixGrid,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if(deleteSuffixGrid != null && hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null && 
				hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName() != null &&hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getSuffixList() != null 
				&& hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getSuffixList().size() >0){
			try{
			SuffixVo deleteSuffixVo = mapper.readValue(deleteSuffixGrid, SuffixVo.class);
			List<SuffixVo> copyList = new ArrayList<SuffixVo>(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getSuffixList());
			for(SuffixVo suffix:hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getSuffixList()){
				if(id==suffix.getId()){
					copyList.remove(suffix);
					hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().setSuffixList(copyList);
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
	@GetMapping(value ="/admin/crs/ctrlPersonInsertNameGrid")
	@ResponseBody
	public NameTypeVo ctrlPersonInsertNameGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		List<NameTypeVo> NameTypeList = null;
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null && 
				hidef.getAccountholder().getControllingPersonVo().getNameTypeList() != null && hidef.getAccountholder().getControllingPersonVo().getNameTypeList().size()>0) {
			NameTypeList = hidef.getAccountholder().getControllingPersonVo().getNameTypeList();
			
		}else {
			NameTypeList = new ArrayList<NameTypeVo>();
			hidef.getAccountholder().getControllingPersonVo().setNameTypeList(NameTypeList);
		}
		
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getCtrlPersonName() != null){
			NameTypeVo nameTypeVo = new NameTypeVo();
			if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersonVo().getNameTypeList()) && hidef.getAccountholder().getControllingPersonVo().getNameTypeList().size()>0){
				nameTypeVo.setId(hidef.getAccountholder().getControllingPersonVo().getNameTypeList().size()+1);
				hidef.getAccountholder().getCtrlPersonName().setId(hidef.getAccountholder().getControllingPersonVo().getNameTypeList().size()+1);
				}else{
					nameTypeVo.setId(1);
					hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().setId(1);
				}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getFirstName())){
			nameTypeVo.setFirstName(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getFirstName());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getGeneralSuffix())){
			nameTypeVo.setGeneralSuffix(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getGeneralSuffix());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getLastName())){
				nameTypeVo.setLastName(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getLastName());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getName())){
				nameTypeVo.setName(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getName());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getNamePrefix())){
				nameTypeVo.setNamePrefix(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getNamePrefix());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getNameType())){
				nameTypeVo.setNameType(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getNameType());
			}
			if(!StringUtils.isEmpty(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getPrecedingTitle())){
				nameTypeVo.setPrecedingTitle(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getPrecedingTitle());
			}
			/*if(!StringUtils.isEmpty(hidef.getAccountholder().getCtrlPersonName().getPrecedingTitle())){
				nameTypeVo.set
			}*/
			nameTypeVo.setTitleList(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getTitleList());
			nameTypeVo.setMiddlenameList(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getMiddlenameList());
			nameTypeVo.setGenerateIdentifilerList(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getGenerateIdentifilerList());
			nameTypeVo.setSuffixList(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getSuffixList());
			NameTypeList.add(nameTypeVo);
		
		}
		
		hidef.getAccountholder().getControllingPersonVo().setNameTypeList(NameTypeList);
		String arrayToJson = mapper.writeValueAsString(NameTypeList);
		model.addAttribute("hidef", hidef);
        return hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName();
	}
	
	@GetMapping(value ="/admin/crs/editctrlPersonInsertNameGrid")
	public String editctrlPersonInsertNameGrid(@ModelAttribute("hidef")HidefVo hidef,Map<String, Object> map,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		NameTypeVo nameType = new NameTypeVo();
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null &&
				hidef.getAccountholder().getControllingPersonVo().getNameTypeList() != null &&
						hidef.getAccountholder().getControllingPersonVo().getNameTypeList().size() > 0){
			for(NameTypeVo nameList :hidef.getAccountholder().getControllingPersonVo().getNameTypeList()){
				if(nameList.getId() == id){
					nameType = nameList;
					/*hidef.getAccountholder().getControllingPersonVo().get.setCtrlPersonName(nameType);
					hidef.getAccountholder().setCtrlPersontitleList(nameType.getTitleList());
					hidef.getAccountholder().setCtrlPersonmiddlenameList(nameType.getMiddlenameList());
					hidef.getAccountholder().setCtrlPersongenerateIdentifilerList(nameType.getGenerateIdentifilerList());
					hidef.getAccountholder().setCtrlPersonsuffixList(nameType.getSuffixList());*/
					hidef.getAccountholder().getControllingPersonVo().setCtrlPersonName(nameType);
					break;
				}
			}
		}
		
		
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		List<Cbcnametype> cbcnametype = ctccommonDropdownService.getAllNameType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		map.put("nameType", cbcnametype);
		model.addAttribute("hidef", hidef);
        return "crsAccountHolder";
	}
	@GetMapping(value ="/admin/crs/viewctrlPersonInsertNameGrid")
	public String viewctrlPersonInsertNameGrid(@ModelAttribute("hidef")HidefVo hidef,Map<String, Object> map,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		NameTypeVo nameType = new NameTypeVo();
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null &&
				hidef.getAccountholder().getControllingPersonVo().getNameTypeList() != null && 
						hidef.getAccountholder().getControllingPersonVo().getNameTypeList().size() > 0){
			for(NameTypeVo nameList :hidef.getAccountholder().getControllingPersonVo().getNameTypeList()){
				if(nameList.getId() == id){
					nameType = nameList;
					hidef.getAccountholder().getControllingPersonVo().setCtrlPersonName(nameType);
					break;
				}
			}
		}
		
		
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		List<Cbcnametype> cbcnametype = ctccommonDropdownService.getAllNameType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		map.put("nameType", cbcnametype);
		model.addAttribute("hidef", hidef);
        return "crsAccountHolder";
	}
	
	
	@GetMapping(value ="/admin/crs/editSavectrlPersonInsertNameGrid")
	public String editSavectrlPersonInsertNameGrid(@ModelAttribute("hidef")HidefVo hidef,Map<String, Object> map,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo().getNameTypeList() != null){
			for(NameTypeVo nameList :hidef.getAccountholder().getControllingPersonVo().getNameTypeList()){
				if(hidef.getAccountholder().getCtrlPersonName().getId() == nameList.getId()){
					nameList.setFirstName(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getFirstName());
					nameList.setGeneralSuffix(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getGeneralSuffix());
					nameList.setGenerateIdentifilerList(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getGenerateIdentifilerList());
					nameList.setLastName(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getLastName());
					nameList.setMiddlenameList(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getMiddlenameList());
					nameList.setName(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getName());
					nameList.setNamePrefix(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getNamePrefix());
					nameList.setNameType(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getNameType());
					nameList.setPrecedingTitle(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getPrecedingTitle());
					nameList.setSuffixList(hidef.getAccountholder().getCtrlPersonsuffixList());
					nameList.setTitleList(hidef.getAccountholder().getControllingPersonVo().getCtrlPersonName().getTitleList());
					break;
				}
				
			}
		}
		model.addAttribute("hidef", hidef);
        return "crsAccountHolder";
	}
	
	@GetMapping(value ="/admin/crs/editIndividualNameGrid")
	public String editIndividualNameGrid(@ModelAttribute("hidef")HidefVo hidef,Map<String, Object> map,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		NameTypeVo nameType = new NameTypeVo();
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualNameList() != null &&
				hidef.getAccountholder().getIndividualNameList().size() > 0){
			for(NameTypeVo nameList :hidef.getAccountholder().getIndividualNameList()){
				if(nameList.getId() == id){
					nameType = nameList;
					hidef.getAccountholder().setIndividualName(nameType);
					hidef.getAccountholder().setTitleList(nameType.getTitleList());
					hidef.getAccountholder().setMiddlenameList(nameType.getMiddlenameList());
					hidef.getAccountholder().setGenerateIdentifilerList(nameType.getGenerateIdentifilerList());
					hidef.getAccountholder().setSuffixList(nameType.getSuffixList());
					break;
				}
			}
		}
		
		
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		List<Cbcnametype> cbcnametype = ctccommonDropdownService.getAllNameType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		map.put("nameType", cbcnametype);
		model.addAttribute("hidef", hidef);
        return "crsAccountHolder";
	}
	@GetMapping(value ="/admin/crs/viewIndividualNameGrid")
	public String viewIndividualNameGrid(@ModelAttribute("hidef")HidefVo hidef,Map<String, Object> map,
			@RequestParam(required = true) int id,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		NameTypeVo nameType = new NameTypeVo();
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualNameList() != null &&
				hidef.getAccountholder().getIndividualNameList().size() > 0){
			for(NameTypeVo nameList :hidef.getAccountholder().getIndividualNameList()){
				if(nameList.getId() == id){
					nameType = nameList;
					hidef.getAccountholder().setIndividualName(nameType);
					hidef.getAccountholder().setTitleList(nameType.getTitleList());
					hidef.getAccountholder().setMiddlenameList(nameType.getMiddlenameList());
					hidef.getAccountholder().setGenerateIdentifilerList(nameType.getGenerateIdentifilerList());
					hidef.getAccountholder().setSuffixList(nameType.getSuffixList());
					break;
				}
			}
		}
		
		
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		List<Cbcnametype> cbcnametype = ctccommonDropdownService.getAllNameType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		map.put("nameType", cbcnametype);
		model.addAttribute("hidef", hidef);
        return "crsAccountHolder";
	}
	
	@GetMapping(value ="/admin/crs/editSaveIndividualNameGrid")
	public String editSaveIndividualNameGrid(@ModelAttribute("hidef")HidefVo hidef,Map<String, Object> map,
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualNameList() != null){
			for(NameTypeVo nameList :hidef.getAccountholder().getIndividualNameList()){
				if(hidef.getAccountholder().getIndividualName().getId() == nameList.getId()){
					nameList.setFirstName(hidef.getAccountholder().getIndividualName().getFirstName());
					nameList.setGeneralSuffix(hidef.getAccountholder().getIndividualName().getGeneralSuffix());
					nameList.setGenerateIdentifilerList(hidef.getAccountholder().getGenerateIdentifilerList());
					nameList.setLastName(hidef.getAccountholder().getIndividualName().getLastName());
					nameList.setMiddlenameList(hidef.getAccountholder().getMiddlenameList());
					nameList.setName(hidef.getAccountholder().getIndividualName().getName());
					nameList.setNamePrefix(hidef.getAccountholder().getIndividualName().getNamePrefix());
					nameList.setNameType(hidef.getAccountholder().getIndividualName().getNameType());
					nameList.setPrecedingTitle(hidef.getAccountholder().getIndividualName().getPrecedingTitle());
					nameList.setSuffixList(hidef.getAccountholder().getSuffixList());
					nameList.setTitleList(hidef.getAccountholder().getTitleList());
					break;
				}
				
			}
		}
		model.addAttribute("hidef", hidef);
        return "crsAccountHolder";
	}
	@RequestMapping(value = "/admin/crs/deleteIndividualNameGrid", method = RequestMethod.GET)
	@ResponseBody
	public String deleteIndividualNameGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestParam(required = true) int id, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		NameTypeVo nameView = new NameTypeVo();
		List<NameTypeVo> nameList = null;
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getIndividualNameList() != null && hidef.getAccountholder().getIndividualNameList().size()>0) {
			
			nameList = hidef.getAccountholder().getIndividualNameList();
			for(NameTypeVo  nameType: nameList) {
				if(nameType.getId()==id) {
					nameView = nameType;
					List<NameTypeVo> copyList = new ArrayList<NameTypeVo>(nameList);
					copyList.remove(nameView);
					hidef.getAccountholder().setIndividualNameList(copyList);
				}
			}
			
		}
		
		String arrayToJson = mapper.writeValueAsString(nameView);
		
        return arrayToJson;
	}
	
	@RequestMapping(value = "/admin/crs/deleteCtrlNameGrid", method = RequestMethod.GET)
	@ResponseBody
	public String deleteCtrlNameGrid(@ModelAttribute("hidef")HidefVo hidef,@RequestParam(required = true) int id, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		NameTypeVo nameView = new NameTypeVo();
		List<NameTypeVo> nameList = null;
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getCtrlPersonNameList() != null && hidef.getAccountholder().getCtrlPersonNameList().size()>0) {
			
			nameList = hidef.getAccountholder().getCtrlPersonNameList();
			for(NameTypeVo  nameType: nameList) {
				if(nameType.getId()==id) {
					nameView = nameType;
					List<NameTypeVo> copyList = new ArrayList<NameTypeVo>(nameList);
					copyList.remove(nameView);
					hidef.getAccountholder().setCtrlPersonNameList(copyList);
				}
			}
			
		}
		
		String arrayToJson = mapper.writeValueAsString(nameView);
		
        return arrayToJson;
	}
	@GetMapping(value ="/admin/crs/loadctrlNameTypeMainGrid",consumes="application/json")
	@ResponseBody
	public String loadctrlNameTypeMainGrid(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		List<NameTypeVo> nameTypeList = new ArrayList<NameTypeVo>();
		String nameTypeJson = mapper.writeValueAsString(nameTypeList);
		if(hidef.getAccountholder() != null && hidef.getAccountholder().getControllingPersonVo() != null &&  hidef.getAccountholder().getControllingPersonVo().getNameTypeList() != null 
				&& hidef.getAccountholder().getControllingPersonVo().getNameTypeList().size() >0) {
			nameTypeJson = mapper.writeValueAsString(hidef.getAccountholder().getControllingPersonVo().getNameTypeList());
		}
		model.addAttribute("hidef", hidef);
		return nameTypeJson;
	}
	
	@GetMapping(value = "/admin/crs/viewControllingPersonMain")
	public String viewControllingPersonMain(@ModelAttribute("hidef") HidefVo hidef, @RequestParam int viewId, BindingResult result,
			ModelMap model, Map<String, Object> map) throws JsonParseException, JsonMappingException, IOException {
		ControllingPersonVo controllingPersonVo = new ControllingPersonVo();
		
		for (ControllingPersonVo ctrlPerson: hidef.getAccountholder().getControllingPersonList()) {
			if (ctrlPerson.getId() == viewId) {
				controllingPersonVo =ctrlPerson;
			}
		}

		hidef.getAccountholder().setControllingPersonVo(controllingPersonVo);;
		model.addAttribute("hidef", hidef);
		return "crsAccountHolder";
	}
	@GetMapping(value = "/admin/crs/editControllingPersonMain")
	public String editControllingPersonMain(@ModelAttribute("hidef") HidefVo hidef, @RequestParam int editId, BindingResult result,
			ModelMap model, Map<String, Object> map) throws JsonParseException, JsonMappingException, IOException {
		ControllingPersonVo controllingPersonVo = new ControllingPersonVo();
		
		for (ControllingPersonVo ctrlPerson: hidef.getAccountholder().getControllingPersonList()) {
			if (ctrlPerson.getId() == editId) {
				controllingPersonVo =ctrlPerson;
			}
		}

		hidef.getAccountholder().setControllingPersonVo(controllingPersonVo);;
		model.addAttribute("hidef", hidef);
		return "crsAccountHolder";
	}
	@GetMapping(value = "/admin/crs/deleteControllingPersonMain")
	public String deleteControllingPersonMain(@ModelAttribute("hidef") HidefVo hidef, @RequestParam int viewId, BindingResult result,
			ModelMap model, Map<String, Object> map) throws JsonParseException, JsonMappingException, IOException {
		ControllingPersonVo controllingPersonVo = new ControllingPersonVo();
		List<ControllingPersonVo> controllingPersonList = new ArrayList<ControllingPersonVo>();
		for (ControllingPersonVo ctrlPerson: hidef.getAccountholder().getControllingPersonList()) {
			if (ctrlPerson.getId()!= viewId) {
				controllingPersonList.add(ctrlPerson);
			}
		}

		hidef.getAccountholder().setControllingPersonList(controllingPersonList);
		model.addAttribute("hidef", hidef);
		return "crsAccountHolder";
	}
	
	@PostMapping(value = "/admin/crs/save")
	@ResponseBody
	public String save(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			Map<String, Object> map, HttpServletResponse response) throws IOException {
		
		/**
		 * Database Saving part created by Venki 
		 */
		ctcDataSaveService.savecrsData(hidef);		
		//FileCopyUtils.copy(metaDataContentInString, response.getWriter());
		return "success";

	}
	@GetMapping(value = "/admin/crs/viewControllingPersonDone")
	public String viewControllingPersonDone(@ModelAttribute("hidef") HidefVo hidef, BindingResult result,
			ModelMap model, Map<String, Object> map) {
		ControllingPersonVo controllingVO = new ControllingPersonVo();
		hidef.getAccountholder().setControllingPersonVo(controllingVO);
		model.addAttribute("hidef", hidef);
		return "crsAccountHolder";
	}
	@GetMapping(value = "/admin/crs/editsaveControllingPersonDone")
	public String saveEditedControllingPersonData(@ModelAttribute("hidef") HidefVo hidef, BindingResult result,
			ModelMap model, Map<String, Object> map) {

		ControllingPersonVo controllingVO = new ControllingPersonVo();
		controllingVO = hidef.getAccountholder().getControllingPersonVo();
		int newAccountId = controllingVO.getId();

		List<ControllingPersonVo> newCRSAccountEditedList = new ArrayList<ControllingPersonVo>();
		for (ControllingPersonVo accountVO : hidef.getAccountholder().getControllingPersonList()) {
			if (accountVO.getId() != newAccountId) {
				newCRSAccountEditedList.add(accountVO);
			}
		}

		newCRSAccountEditedList.add(controllingVO);
		hidef.getAccountholder().setControllingPersonList(newCRSAccountEditedList);
		ControllingPersonVo controlling = new ControllingPersonVo();
		hidef.getAccountholder().setControllingPersonVo(controlling);
		model.addAttribute("hidef", hidef);

		return "crsAccountHolder";
	}
	
	
	
	
	
	
	
	
	
	

}
