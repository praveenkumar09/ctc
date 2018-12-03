package com.censof.myfi.hidefmyfi.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.censof.myfi.hidefmyfi.CTSConstants;
import com.censof.myfi.hidefmyfi.entity.CbcCurrency;
import com.censof.myfi.hidefmyfi.entity.Cbcaddresstype;
import com.censof.myfi.hidefmyfi.entity.Cbcbizactivitiesreference;
import com.censof.myfi.hidefmyfi.entity.Cbcdocumenttypeindicator;
import com.censof.myfi.hidefmyfi.entity.Cbcnametype;
import com.censof.myfi.hidefmyfi.entity.Cbcpayldaddress;
import com.censof.myfi.hidefmyfi.entity.Cbcpayldin;
import com.censof.myfi.hidefmyfi.entity.Cbcpayldname;
import com.censof.myfi.hidefmyfi.entity.Cbcpayldrescountry;
import com.censof.myfi.hidefmyfi.entity.Hicountry;
import com.censof.myfi.hidefmyfi.repository.CbcpayldaddressRepository;
import com.censof.myfi.hidefmyfi.repository.CbcpayldbizactivRepository;
import com.censof.myfi.hidefmyfi.repository.CbcpayldinRepository;
import com.censof.myfi.hidefmyfi.repository.CbcpayldnameRepository;
import com.censof.myfi.hidefmyfi.repository.CbcpayldrescountryRepository;
import com.censof.myfi.hidefmyfi.service.CtccommonDropdownService;
import com.censof.myfi.hidefmyfi.vo.AddressVo;
import com.censof.myfi.hidefmyfi.vo.BizActivitiesTypeVo;
import com.censof.myfi.hidefmyfi.vo.CBCRepotsVo;
import com.censof.myfi.hidefmyfi.vo.CbcAdditionalInfo;
import com.censof.myfi.hidefmyfi.vo.CbcConstituentEntityVO;
import com.censof.myfi.hidefmyfi.vo.CommonDropdownGridBean;
import com.censof.myfi.hidefmyfi.vo.HidefVo;
import com.censof.myfi.hidefmyfi.vo.NameVo;
import com.censof.myfi.hidefmyfi.vo.OrganisationInTypeVo;
import com.censof.myfi.hidefmyfi.vo.ResidentCountryVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes("hidef")
public class CbcReportsController {

	@Autowired
	private CtccommonDropdownService ctccommonDropdownService;
	
	@Autowired
	private CbcpayldbizactivRepository cbcpayldbizactivRepository;
	
	@Autowired
	private CbcpayldrescountryRepository cbcPayldResCountryRepository;
	
	@Autowired
	private CbcpayldnameRepository cbcpayldnameRepository;
	
	@Autowired
	private CbcpayldinRepository cbcpayldinRepository;
	
	@Autowired
	private CbcpayldaddressRepository cbcpayldaddressRepository;
	

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@ModelAttribute("hidef")
	public HidefVo getmetadata() {
		return new HidefVo();
	}

	@GetMapping(value = "/admin/cbc/cbcReports")
	public String getTabReportingEntity(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			Map<String, Object> map) {
		
		if(hidef.getCbcReports() == null) {
			hidef.setCbcReports(new CBCRepotsVo());
		}
		
		if(hidef.getCbcReports().getConstituentEntity() == null) {
			hidef.getCbcReports().setConstituentEntity(new CbcConstituentEntityVO());
		}
		
		
		
		ObjectMapper mapper = new ObjectMapper();
		hidef.setCurrentTab(CTSConstants.HIDEF_CTS_CBC_REPORTS);
		List<Hicountry> country = ctccommonDropdownService.getAllCountries();
		List<Cbcnametype> cbcnametype = ctccommonDropdownService.getAllNameType();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		List<Cbcdocumenttypeindicator> cbcdocumenttypeindicator = ctccommonDropdownService.getAllDocumentTypeIndicator();
		
		List<CbcCurrency> currencyRef = ctccommonDropdownService.getAllCurrencyreference();
		List<CommonDropdownGridBean> gridBeans = new ArrayList<>();
		List<Cbcbizactivitiesreference> bizActivitiesList = ctccommonDropdownService.getAllCbcbizactivitiesreference();
		for (Hicountry residentCountry : country) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(residentCountry.getId());
			gridBean.setName(residentCountry.getCountryCode());
			gridBeans.add(gridBean);

		}
		List<CommonDropdownGridBean> nameTypegridBeans = new ArrayList<>();
		for (Cbcnametype nameType : cbcnametype) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(new BigInteger(nameType.getId()));
			gridBean.setName(nameType.getNameType());
			nameTypegridBeans.add(gridBean);

		}
		List<CommonDropdownGridBean> BizgridBeans = new ArrayList<>();
		for (Cbcbizactivitiesreference bizActivities : bizActivitiesList) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(bizActivities.getId());
			gridBean.setName(bizActivities.getBizType());
			BizgridBeans.add(gridBean);

		}

		try {
			String arrayToJson = mapper.writeValueAsString(gridBeans);
			String nameGridJson = mapper.writeValueAsString(nameTypegridBeans);
			String bizGridJson = mapper.writeValueAsString(BizgridBeans);
			map.put("documentTypeIndicator", cbcdocumenttypeindicator);
			map.put("residentCountry", arrayToJson);
			map.put("nameTypeList", nameGridJson);
			map.put("bizTypeList", bizGridJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();

		}
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getDocumentRefId() == null
				) {
			if (hidef.getDocRefId() != null) {
				String nextDocRef = converToString(Integer.parseInt(hidef.getDocRefId()) + 1);
				hidef.getCbcReports().setDocumentRefId(hidef.getDocRefIdStaticText() + "R" + nextDocRef);
				hidef.setDocRefId(String.valueOf(Integer.parseInt(hidef.getDocRefId()) + 1));
			}
		}
		map.put("countryList", country);
		map.put("currencyList", currencyRef);
		model.addAttribute("hidef", hidef);
		return "cbcReports";
	}

	@PostMapping(value = "/admin/cbc/cbcReportsNext")
	public String reportingEntityNext(@ModelAttribute("hidef") HidefVo hidef,
			@RequestParam(required = false, value = "next3") String next,@RequestParam(required = true, value = "newForm") int newFormId,
			@RequestParam(required = false, value = "previous2") String previous, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			return "cbcReports";
		}
		if (!StringUtils.isEmpty(previous)) {
			return "redirect:reportingEntity";
		}

		if (!StringUtils.isEmpty(next)) {
			if(newFormId == 1) {
				hidef.setCbcAddionalInfo(new CbcAdditionalInfo());
			}
			return "redirect:additionalInfo";
		}
		model.addAttribute("hidef", hidef);
		return CTSConstants.tabSelected(hidef.getCurrentTab());
		// return "redirect:accountHolder";
	}

	@GetMapping(value = "/admin/cbc/reportsloadResidentCountryGrid", consumes = "application/json")
	@ResponseBody
	public String loadResidentCountryGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		List<ResidentCountryVo> residentCountryList = new ArrayList<ResidentCountryVo>();
		String residentJson = mapper.writeValueAsString(residentCountryList);
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntity().getResidentCountryList() != null
				&& hidef.getCbcReports().getConstituentEntity().getResidentCountryList().size() > 0) {
			residentJson = mapper.writeValueAsString(hidef.getCbcReports().getConstituentEntity().getResidentCountryList());
		}

		model.addAttribute("hidef", hidef);
		return residentJson;
	}

	@PostMapping(value = "/admin/cbc/reportsinsertResidentCountryGrid", consumes = "application/json")
	@ResponseBody
	public ResidentCountryVo insertResidentCountryGrid(@ModelAttribute("hidef") HidefVo hidef,
			@RequestBody String insertResident, BindingResult result, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Random ran = new Random();
		ResidentCountryVo residentCountryVo = null;
		try {
			List<ResidentCountryVo> residentCountryList = new ArrayList<ResidentCountryVo>();
			residentCountryVo = mapper.readValue(insertResident, ResidentCountryVo.class);
			residentCountryVo.setId(ran.nextInt(10000));
			if (hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntity().getResidentCountryList() != null
					&& hidef.getCbcReports().getConstituentEntity().getResidentCountryList().size() > 0) {
				hidef.getCbcReports().getConstituentEntity().getResidentCountryList().add(residentCountryVo);
			} else {
				hidef.getCbcReports().getConstituentEntity().setResidentCountryList(residentCountryList);
				hidef.getCbcReports().getConstituentEntity().getResidentCountryList().add(residentCountryVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return residentCountryVo;
	}

	@PostMapping(value = "/admin/cbc/reportsupdateResidentCountryGrid", consumes = "application/json")
	@ResponseBody
	public String updateResidentCountryGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestBody String updateResident,
			@RequestParam(required = true) int id, BindingResult result, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if (hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity().getResidentCountryList() != null
				&& hidef.getCbcReports().getConstituentEntity().getResidentCountryList().size() > 0) {
			try {
				ResidentCountryVo residentCountryVo = mapper.readValue(updateResident, ResidentCountryVo.class);
				for (ResidentCountryVo residentvo : hidef.getCbcReports().getConstituentEntity().getResidentCountryList()) {
					if (residentvo.getId() == residentCountryVo.getId()) {
						residentvo.setResidentCountryCode(residentCountryVo.getResidentCountryCode());
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

		}
		model.addAttribute("hidef", hidef);
		return "success";
	}

	@PostMapping(value = "/admin/cbc/reportsdeleteResidentCountryGrid", consumes = "application/json")
	@ResponseBody
	public String deleteResidentCountryGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestBody String deleteResident,
			@RequestParam(required = true) int id, BindingResult result, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if (deleteResident != null && hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null
				&& hidef.getCbcReports().getConstituentEntity().getResidentCountryList() != null
				&& hidef.getCbcReports().getConstituentEntity().getResidentCountryList().size() > 0) {
			try {
				ResidentCountryVo residentCountryVo = mapper.readValue(deleteResident, ResidentCountryVo.class);
				List<ResidentCountryVo> copyList = new ArrayList<ResidentCountryVo>(
						hidef.getCbcReports().getConstituentEntity().getResidentCountryList());
				for (ResidentCountryVo residentCountry : hidef.getCbcReports().getConstituentEntity().getResidentCountryList()) {
					if (residentCountryVo.getId() == residentCountry.getId()) {
						copyList.remove(residentCountry);
						if(residentCountry.getId() != 0) {
							Optional<Cbcpayldrescountry> resCountry = cbcPayldResCountryRepository.findById(BigInteger.valueOf(residentCountry.getId()));
							if(resCountry.isPresent()) {
							cbcPayldResCountryRepository.deleteById(BigInteger.valueOf(residentCountry.getId()));
							}
						}
						hidef.getCbcReports().getConstituentEntity().setResidentCountryList(copyList);
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("hidef", hidef);
		return "success";
	}

	@GetMapping(value = "/admin/cbc/reportsloadNameGrid", consumes = "application/json")
	@ResponseBody
	public String loadNameGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<NameVo> nameList = new ArrayList<NameVo>();
		String nameJson = mapper.writeValueAsString(nameList);
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntity().getNameList() != null
				&& hidef.getCbcReports().getConstituentEntity().getNameList().size() > 0) {
			nameJson = mapper.writeValueAsString(hidef.getCbcReports().getConstituentEntity().getNameList());
		}
		model.addAttribute("hidef", hidef);
		return nameJson;
	}

	@PostMapping(value = "/admin/cbc/reportsinsertNameGrid", consumes = "application/json")
	@ResponseBody
	public NameVo insertNameGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestBody String insertNameGrid,
			BindingResult result, ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Random ran = new Random();
		NameVo nameVo = null;
		try {
			List<NameVo> nameList = new ArrayList<NameVo>();
			nameVo = mapper.readValue(insertNameGrid, NameVo.class);
			nameVo.setId(ran.nextInt(10000));
			if (hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntity().getNameList() != null
					&& hidef.getCbcReports().getConstituentEntity().getNameList().size() > 0) {
				hidef.getCbcReports().getConstituentEntity().getNameList().add(nameVo);
			} else {
				hidef.getCbcReports().getConstituentEntity().setNameList(nameList);
				hidef.getCbcReports().getConstituentEntity().getNameList().add(nameVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return nameVo;
	}

	@PostMapping(value = "/admin/cbc/reportsupdateNameGrid", consumes = "application/json")
	@ResponseBody
	public String updateNameGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestBody String updateNameGrid,
			@RequestParam(required = true) int id, BindingResult result, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntity().getNameList() != null
				&& hidef.getCbcReports().getConstituentEntity().getNameList().size() > 0) {
			try {
				NameVo updatedNameVo = mapper.readValue(updateNameGrid, NameVo.class);
				for (NameVo namevo : hidef.getCbcReports().getConstituentEntity().getNameList()) {
					if (namevo.getId() == id) {
						namevo.setLastName(updatedNameVo.getLastName());
						namevo.setFirstName(updatedNameVo.getFirstName());
						namevo.setNameType(namevo.getNameType());
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

		}

		model.addAttribute("hidef", hidef);
		return "success";
	}

	@PostMapping(value = "/admin/cbc/reportsdeleteNameGrid", consumes = "application/json")
	@ResponseBody
	public String deleteNameGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestBody String deleteNameGrid,
			@RequestParam(required = true) int id, BindingResult result, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if (deleteNameGrid != null && hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntity().getNameList() != null
				&& hidef.getCbcReports().getConstituentEntity().getNameList().size() > 0) {
			try {
				NameVo updatedNameVo = mapper.readValue(deleteNameGrid, NameVo.class);
				List<NameVo> copyList = new ArrayList<NameVo>(hidef.getCbcReports().getConstituentEntity().getNameList());
				for (NameVo namevo : hidef.getCbcReports().getConstituentEntity().getNameList()) {
					if (id == namevo.getId()) {
						copyList.remove(namevo);
						if(hidef.getCbcReports().getConstituentEntity().getConsId() != 0 && namevo.getId() != 0) {
							Optional<Cbcpayldname> nameOb = cbcpayldnameRepository.findById(BigInteger.valueOf(namevo.getId()));
							if(nameOb.isPresent()) {
							cbcpayldnameRepository.deleteById(BigInteger.valueOf(namevo.getId()));
							}
						}
						hidef.getCbcReports().getConstituentEntity().setNameList(copyList);
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

		}
		model.addAttribute("hidef", hidef);
		return "success";
	}

	@GetMapping(value = "/admin/cbc/reportsloadOrganisationGrid", consumes = "application/json")
	@ResponseBody
	public String loadOrganisationGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<OrganisationInTypeVo> organisationList = new ArrayList<OrganisationInTypeVo>();
		String organisationJson = mapper.writeValueAsString(organisationList);
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntity().getOrganisationInTypeList() != null
				&& hidef.getCbcReports().getConstituentEntity().getOrganisationInTypeList().size() > 0) {
			organisationJson = mapper.writeValueAsString(hidef.getCbcReports().getConstituentEntity().getOrganisationInTypeList());
		}
		model.addAttribute("hidef", hidef);
		return organisationJson;
	}

	@PostMapping(value = "/admin/cbc/reportsinsertOrganisationGrid", consumes = "application/json")
	@ResponseBody
	public OrganisationInTypeVo insertOrganisationGrid(@ModelAttribute("hidef") HidefVo hidef,
			@RequestBody String insertOrganisationGrid, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Random ran = new Random();
		OrganisationInTypeVo organisationVo = null;
		try {
			List<OrganisationInTypeVo> organisationList = new ArrayList<OrganisationInTypeVo>();
			organisationVo = mapper.readValue(insertOrganisationGrid, OrganisationInTypeVo.class);
			organisationVo.setId(ran.nextInt(10000));
			if (hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntity().getOrganisationInTypeList() != null
					&& hidef.getCbcReports().getConstituentEntity().getOrganisationInTypeList().size() > 0) {
				hidef.getCbcReports().getConstituentEntity().getOrganisationInTypeList().add(organisationVo);
			} else {
				hidef.getCbcReports().getConstituentEntity().setOrganisationInTypeList(organisationList);
				hidef.getCbcReports().getConstituentEntity().getOrganisationInTypeList().add(organisationVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return organisationVo;
	}

	@PostMapping(value = "/admin/cbc/reportsupdateOrganisationGrid", consumes = "application/json")
	@ResponseBody
	public String updateOrganisationGrid(@ModelAttribute("hidef") HidefVo hidef,
			@RequestBody String updateOrganisationGrid, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntity().getOrganisationInTypeList() != null
				&& hidef.getCbcReports().getConstituentEntity().getOrganisationInTypeList().size() > 0) {
			try {
				OrganisationInTypeVo updatedorganisationVo = mapper.readValue(updateOrganisationGrid,
						OrganisationInTypeVo.class);
				for (OrganisationInTypeVo organisationvo : hidef.getCbcReports().getConstituentEntity().getOrganisationInTypeList()) {
					if (organisationvo.getId() == updatedorganisationVo.getId()) {
						organisationvo.setIssuedBy(updatedorganisationVo.getIssuedBy());
						organisationvo.setIn(updatedorganisationVo.getIn());
						organisationvo.setInType(updatedorganisationVo.getInType());
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

		}
		model.addAttribute("hidef", hidef);
		return "success";
	}

	@PostMapping(value = "/admin/cbc/reportsdeleteOrganisationGrid", consumes = "application/json")
	@ResponseBody
	public String deleteOrganisationGrid(@ModelAttribute("hidef") HidefVo hidef,
			@RequestBody String deleteOrganisationGrid, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if (deleteOrganisationGrid != null && hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntity().getOrganisationInTypeList() != null) {
			try {
				OrganisationInTypeVo updatedorganisationVo = mapper.readValue(deleteOrganisationGrid,
						OrganisationInTypeVo.class);
				List<OrganisationInTypeVo> copyList = new ArrayList<OrganisationInTypeVo>(
						hidef.getCbcReports().getConstituentEntity().getOrganisationInTypeList());
				for (OrganisationInTypeVo organisation : hidef.getCbcReports().getConstituentEntity().getOrganisationInTypeList()) {
					if (updatedorganisationVo.getId() == organisation.getId()) {
						copyList.remove(organisation);
						if(organisation.getId() != 0) {
							Optional<Cbcpayldin> org = cbcpayldinRepository.findById(BigInteger.valueOf(organisation.getId()));
							if(org.isPresent()) {
							cbcpayldinRepository.deleteById(BigInteger.valueOf(organisation.getId()));
							}
						}
						hidef.getCbcReports().getConstituentEntity().setOrganisationInTypeList(copyList);
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("hidef", hidef);
		return "success";
	}

	/*
	 * @GetMapping(value
	 * ="/admin/cbc/loadCbcReportsAddress",consumes="application/json")
	 * 
	 * @ResponseBody public String
	 * loadCbcReportsAddressGrid(@ModelAttribute("hidef")HidefVo hidef,
	 * BindingResult result, ModelMap model,HttpServletRequest request,
	 * HttpServletResponse response) throws JsonProcessingException { ObjectMapper
	 * mapper = new ObjectMapper();
	 * 
	 * List<AddressVo> addressList = new ArrayList<AddressVo>(); String addressJson
	 * = mapper.writeValueAsString(addressList); if(hidef.getReportingEntity() !=
	 * null && hidef.getReportingEntity().getAddressList() != null &&
	 * hidef.getReportingEntity().getAddressList().size() >0) { addressJson =
	 * mapper.writeValueAsString(hidef.getReportingEntity().getAddressList()); }
	 * model.addAttribute("hidef", hidef); return addressJson; }
	 */

	@GetMapping(value = "/admin/cbc/cbcReporstinsertAddress")
	@ResponseBody
	public AddressVo addAddressGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		List<AddressVo> addressList = null;
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntity().getAddressList() != null
				&& hidef.getCbcReports().getConstituentEntity().getAddressList().size() > 0) {
			addressList = hidef.getCbcReports().getConstituentEntity().getAddressList();

		} else {
			addressList = new ArrayList<AddressVo>();
			hidef.getCbcReports().getConstituentEntity().setAddressList(addressList);
		}

		if (hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntity().getAddressVo() != null) {
			AddressVo addressVo = new AddressVo();
			if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getAddressVo().getAddressFree())) {
				addressVo.setAddressFree(hidef.getCbcReports().getConstituentEntity().getAddressVo().getAddressFree());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getAddressVo().getAddressType())) {
				addressVo.setAddressType(hidef.getCbcReports().getConstituentEntity().getAddressVo().getAddressType());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getAddressVo().getBuildingIdentifier())) {
				addressVo.setBuildingIdentifier(hidef.getCbcReports().getConstituentEntity().getAddressVo().getBuildingIdentifier());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getAddressVo().getCity())) {
				addressVo.setCity(hidef.getCbcReports().getConstituentEntity().getAddressVo().getCity());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getAddressVo().getCountryCode())) {
				addressVo.setCountryCode(hidef.getCbcReports().getConstituentEntity().getAddressVo().getCountryCode());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getAddressVo().getCountrySubentity())) {
				addressVo.setCountrySubentity(hidef.getCbcReports().getConstituentEntity().getAddressVo().getCountrySubentity());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getAddressVo().getDistrictName())) {
				addressVo.setDistrictName(hidef.getCbcReports().getConstituentEntity().getAddressVo().getDistrictName());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getAddressVo().getFloorIdentifier())) {
				addressVo.setFloorIdentifier(hidef.getCbcReports().getConstituentEntity().getAddressVo().getFloorIdentifier());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getAddressList())
					&& hidef.getCbcReports().getConstituentEntity().getAddressList().size() > 0) {
				addressVo.setId(hidef.getCbcReports().getConstituentEntity().getAddressList().size() + 1);
				hidef.getCbcReports().getConstituentEntity().getAddressVo().setId(hidef.getCbcReports().getConstituentEntity().getAddressList().size() + 1);
			} else {
				addressVo.setId(1);
				hidef.getCbcReports().getConstituentEntity().getAddressVo().setId(1);
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getAddressVo().getPob())) {
				addressVo.setPob(hidef.getCbcReports().getConstituentEntity().getAddressVo().getPob());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getAddressVo().getPostCode())) {
				addressVo.setPostCode(hidef.getCbcReports().getConstituentEntity().getAddressVo().getPostCode());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getAddressVo().getStreet())) {
				addressVo.setStreet(hidef.getCbcReports().getConstituentEntity().getAddressVo().getStreet());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getAddressVo().getSuitIdentifier())) {
				addressVo.setSuitIdentifier(hidef.getCbcReports().getConstituentEntity().getAddressVo().getSuitIdentifier());
			}
			addressList.add(addressVo);
		}

		hidef.getCbcReports().getConstituentEntity().setAddressList(addressList);
		String arrayToJson = mapper.writeValueAsString(addressList);
		model.addAttribute("hidef", hidef);
		return hidef.getCbcReports().getConstituentEntity().getAddressVo();
	}

	@RequestMapping(value = "/admin/cbc/cbcReportsviewAddress", method = RequestMethod.GET)
	public String viewAddressGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestParam(required = true) int id,
			Map<String, Object> map, BindingResult result, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntity().getAddressList() != null
				&& hidef.getCbcReports().getConstituentEntity().getAddressList().size() > 0) {
			addressList = hidef.getCbcReports().getConstituentEntity().getAddressList();
			for (AddressVo address : addressList) {
				if (address.getId() == id) {
					addressView = address;
					hidef.getCbcReports().getConstituentEntity().setViewAddressVo(addressView);
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
		return "cbcReports";
	}

	@RequestMapping(value = "/admin/cbc/cbcReportseditAddress", method = RequestMethod.GET)
	public String editAddressGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestParam(required = true) int id,
			BindingResult result, ModelMap model, HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> map) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntity().getAddressList() != null
				&& hidef.getCbcReports().getConstituentEntity().getAddressList().size() > 0) {
			addressList = hidef.getCbcReports().getConstituentEntity().getAddressList();
			for (AddressVo address : addressList) {
				if (address.getId() == id) {
					addressView = address;
					hidef.getCbcReports().getConstituentEntity().setEditAddressVo(addressView);
				}
			}

		}

		String arrayToJson = mapper.writeValueAsString(addressView);
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		model.addAttribute("hidef", hidef);
		return "cbcReports";
	}

	@RequestMapping(value = "/admin/cbc/cbcReportseditSaveAddress", method = RequestMethod.GET)
	public String saveEditAddressGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntity().getAddressList() != null
				&& hidef.getCbcReports().getConstituentEntity().getAddressList().size() > 0) {
			addressList = hidef.getCbcReports().getConstituentEntity().getAddressList();
			for (AddressVo address : addressList) {
				if (address.getId() == hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getId()) {
					if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getAddressFree())) {
						address.setAddressFree(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getAddressFree());
					}
					if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getAddressType())) {
						address.setAddressType(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getAddressType());
						address.setAddressTypeId(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getAddressType());
					}
					if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getBuildingIdentifier())) {
						address.setBuildingIdentifier(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getBuildingIdentifier());
					}
					if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getCity())) {
						address.setCity(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getCity());
					}
					if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getCountryCode())) {
						address.setCountryCode(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getCountryCode());
						address.setCountryCodeId(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getCountryCode());
					}
					if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getCountrySubentity())) {
						address.setCountrySubentity(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getCountrySubentity());
					}
					if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getDistrictName())) {
						address.setDistrictName(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getDistrictName());
					}
					if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getFloorIdentifier())) {
						address.setFloorIdentifier(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getFloorIdentifier());
					}

					address.setId(address.getId());
					if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getPob())) {
						address.setPob(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getPob());
					}
					if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getPostCode())) {
						address.setPostCode(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getPostCode());
					}
					if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getStreet())) {
						address.setStreet(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getStreet());
					}
					if (!StringUtils.isEmpty(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getSuitIdentifier())) {
						address.setSuitIdentifier(hidef.getCbcReports().getConstituentEntity().getEditAddressVo().getSuitIdentifier());
					}
				}
			}

		}

		String arrayToJson = mapper.writeValueAsString(addressView);
		model.addAttribute("hidef", hidef);
		return "cbcReports";
	}

	@RequestMapping(value = "/admin/cbc/cbcReportsdeleteAddress", method = RequestMethod.GET)
	@ResponseBody
	public String deleteAddressGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestParam(required = true) int id,
			BindingResult result, ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntity().getAddressList() != null
				&& hidef.getCbcReports().getConstituentEntity().getAddressList().size() > 0) {

			addressList = hidef.getCbcReports().getConstituentEntity().getAddressList();
			for (AddressVo address : addressList) {
				if (address.getId() == id) {
					addressView = address;
					List<AddressVo> copyList = new ArrayList<AddressVo>(addressList);
					copyList.remove(address);
					if(address.getId() != 0) {
						Optional<Cbcpayldaddress> addressOb = cbcpayldaddressRepository.findById(BigInteger.valueOf(address.getId()));
						if(addressOb.isPresent()) {
						cbcpayldaddressRepository.deleteById(BigInteger.valueOf(address.getId()));
						}
					}
					hidef.getCbcReports().getConstituentEntity().setAddressList(copyList);
				}
			}

		}

		String arrayToJson = mapper.writeValueAsString(addressView);

		return arrayToJson;
	}

	@GetMapping(value = "/admin/cbc/loadCbcReportsAddress", consumes = "application/json")
	@ResponseBody
	public String loadReportEntityAddressGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result,
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		List<AddressVo> addressList = new ArrayList<AddressVo>();
		String addressJson = mapper.writeValueAsString(addressList);
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntity().getAddressList() != null
				&& hidef.getCbcReports().getConstituentEntity().getAddressList().size() > 0) {
			addressJson = mapper.writeValueAsString(hidef.getCbcReports().getConstituentEntity().getAddressList());
		}
		model.addAttribute("hidef", hidef);
		return addressJson;
	}

	@GetMapping(value = "/admin/cbc/resetCbcReportdAddAddress")
	public String resetReportingEntityAddAddress(@ModelAttribute("hidef") HidefVo hidef, BindingResult result,
			ModelMap model, HttpServletRequest request, HttpServletResponse response, Map<String, Object> map)
			throws JsonProcessingException {
		AddressVo addressVo = new AddressVo();
		hidef.getCbcReports().getConstituentEntity().setAddressVo(addressVo);
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcaddresstype> cbcaddresstype = ctccommonDropdownService.getAllAddressType();
		map.put("tinlist", hicountryList);
		map.put("addressType", cbcaddresstype);
		model.addAttribute("hidef", hidef);
		return "cbcReports";
	}

	@GetMapping(value = "/admin/cbc/viewReportsDone")
	@ResponseBody
	public String doneClickedOnViewReports(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			Map<String, Object> map) {
		hidef.setCurrentTab(CTSConstants.HIDEF_CTS_CBC_REPORTS);
		hidef.setCbcReports(new CBCRepotsVo());
		model.addAttribute("hidef", hidef);
		return "success";
	}

	@GetMapping(value = "/admin/cbc/view/cbcReports")
	@ResponseBody
	public String viewCBCReports(@ModelAttribute("hidef") HidefVo hidef, @RequestParam int viewId, BindingResult result,
			ModelMap model, Map<String, Object> map) throws JsonParseException, JsonMappingException, IOException {
		CBCRepotsVo viewReport = new CBCRepotsVo();
		for (CBCRepotsVo reports : hidef.getListCBCReports()) {
			if (reports.getId() == viewId) {
				viewReport = reports;
			}
		}

		logger.info("viewReport =====> {}", viewReport);
		hidef.setCbcReports(viewReport);
		model.addAttribute("hidef", hidef);
		return "success";
	}

	@GetMapping(value = "/admin/cbc/edit/cbcReports")
	@ResponseBody
	public String editCBCReports(@ModelAttribute("hidef") HidefVo hidef, @RequestParam int editId, BindingResult result,
			ModelMap model, Map<String, Object> map) throws JsonParseException, JsonMappingException, IOException {
		CBCRepotsVo editReport = new CBCRepotsVo();
		for (CBCRepotsVo reports : hidef.getListCBCReports()) {
			if (reports.getId() == editId) {
				editReport = reports;
			}
		}

		logger.info("viewReport =====> {}", editReport);
		hidef.setCbcReports(editReport);
		model.addAttribute("hidef", hidef);
		return "success";
	}

	@GetMapping(value = "/admin/cbc/cbcReports/populateCBCReports")
	@ResponseBody
	public CBCRepotsVo populateCBCReportsGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result,
			ModelMap model, Map<String, Object> map) {

		CBCRepotsVo reportVO = new CBCRepotsVo();
		Random rand = new Random();
		reportVO = hidef.getCbcReports();
		reportVO.setId(rand.nextInt(10000));
		if (hidef.getCbcReports() != null && hidef.getListCBCReports() != null) {
			hidef.getListCBCReports().add(reportVO);
		} else {
			hidef.setListCBCReports(new ArrayList<CBCRepotsVo>());
			hidef.getListCBCReports().add(reportVO);
		}
		hidef.setCbcReports(new CBCRepotsVo());
		model.addAttribute("hidef", hidef);

		return reportVO;
	}
	
	
	@GetMapping(value = "/admin/cbc/cbcReports/populateCBCConsitituentEntityGrid")
	@ResponseBody
	public CbcConstituentEntityVO populateCBCConsitituentEntityGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result,
			ModelMap model, Map<String, Object> map) {

		CbcConstituentEntityVO reportVO = new CbcConstituentEntityVO();
		Random rand = new Random();
		reportVO = hidef.getCbcReports().getConstituentEntity();
		reportVO.setConsId(rand.nextInt(10000));
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntityList() != null) {
			hidef.getCbcReports().getConstituentEntityList().add(reportVO);
		} else if(hidef.getCbcReports() != null) {
			hidef.getCbcReports().setConstituentEntityList(new ArrayList<CbcConstituentEntityVO>());
			hidef.getCbcReports().getConstituentEntityList().add(reportVO);
		}else if(hidef.getCbcReports() == null) {
			hidef.setCbcReports(new CBCRepotsVo());
		}
		hidef.getCbcReports().setConstituentEntity(new CbcConstituentEntityVO());
		model.addAttribute("hidef", hidef);

		return reportVO;
	}
	
	
	@GetMapping(value = "/admin/cbc/cbcReports/validateConstiituentEntityGrids")
	@ResponseBody
	public String validateConstiituentEntityGrids(@ModelAttribute("hidef") HidefVo hidef, BindingResult result,
			ModelMap model, Map<String, Object> map) {
		String errorFlag = "false";
		
		CbcConstituentEntityVO constituentEntityVo = hidef.getCbcReports().getConstituentEntity();
		
		if(constituentEntityVo.getBizActivitiesList() == null || constituentEntityVo.getBizActivitiesList().isEmpty()) {
			errorFlag = "true";
		}
		
		if(constituentEntityVo.getNameList() == null || constituentEntityVo.getNameList().isEmpty()) {
			errorFlag = "true";
		}
		
		if(constituentEntityVo.getOrganisationInTypeList() == null || constituentEntityVo.getOrganisationInTypeList().isEmpty()) {
			errorFlag = "true";
		}
		
		return errorFlag; 
	}
	
	@GetMapping(value = "/admin/cbc/cbcReports/populateEditedConsitituentEntityGrid")
	@ResponseBody
	public CbcConstituentEntityVO populateEditedConsitituentEntityGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result,
			ModelMap model, Map<String, Object> map) {

		CbcConstituentEntityVO reportVO = new CbcConstituentEntityVO();
		reportVO = hidef.getCbcReports().getConstituentEntity();
		int newReportId = reportVO.getConsId();

		List<CbcConstituentEntityVO> newCBCReportsEditedList = new ArrayList<CbcConstituentEntityVO>();
		for (CbcConstituentEntityVO reportsVO : hidef.getCbcReports().getConstituentEntityList()) {
			if (reportsVO.getConsId() != newReportId) {
				newCBCReportsEditedList.add(reportsVO);
			}
		}

		newCBCReportsEditedList.add(reportVO);

		hidef.getCbcReports().setConstituentEntity(new CbcConstituentEntityVO());
		model.addAttribute("hidef", hidef);

		return reportVO;
	}
	
	@GetMapping(value = "/admin/cbc/cbcReports/populateEditedCBCReports")
	@ResponseBody
	public CBCRepotsVo populateEditedCBCReportsGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result,
			ModelMap model, Map<String, Object> map) {

		CBCRepotsVo reportVO = new CBCRepotsVo();
		reportVO = hidef.getCbcReports();
		int newReportId = reportVO.getId();

		List<CBCRepotsVo> newCBCReportsEditedList = new ArrayList<CBCRepotsVo>();
		for (CBCRepotsVo reportsVO : hidef.getListCBCReports()) {
			if (reportsVO.getId() != newReportId) {
				newCBCReportsEditedList.add(reportsVO);
			}
		}

		newCBCReportsEditedList.add(reportVO);

		hidef.setCbcReports(new CBCRepotsVo());
		model.addAttribute("hidef", hidef);

		return reportVO;
	}

	@GetMapping(value = "/admin/cbc/cbcReports/populateDeletedCBCReports")
	@ResponseBody
	public String populateDeletedCBCReportsGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestParam int deleteId,
			BindingResult result, ModelMap model, Map<String, Object> map) {

		List<CBCRepotsVo> newCBCReportsDeletedList = new ArrayList<CBCRepotsVo>();
		for (CBCRepotsVo reportsVO : hidef.getListCBCReports()) {
			if (reportsVO.getId() != deleteId) {
				newCBCReportsDeletedList.add(reportsVO);
			}
		}

		hidef.setListCBCReports(newCBCReportsDeletedList);
		model.addAttribute("hidef", hidef);

		return "success";
	}

	@GetMapping(value = "/admin/cbc/loadCBCReports", consumes = "application/json")
	@ResponseBody
	public String loadloadCBCReportsGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		List<CBCRepotsVo> cbcReports = new ArrayList<CBCRepotsVo>();
		String reportsJSON = mapper.writeValueAsString(cbcReports);
		if (hidef.getCbcReports() != null && hidef.getListCBCReports() != null
				&& !hidef.getListCBCReports().isEmpty()) {
			reportsJSON = mapper.writeValueAsString(hidef.getListCBCReports());
		}

		model.addAttribute("hidef", hidef);
		return reportsJSON;

	}
	
	//loadCbcConstituentEntityGrid
	@GetMapping(value = "/admin/cbc/loadCbcConstituentEntityGrid", consumes = "application/json")
	@ResponseBody
	public String loadCbcConstituentEntityGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();		
		
		List<CbcConstituentEntityVO> cbcReports = new ArrayList<CbcConstituentEntityVO>();
		String reportsJSON = mapper.writeValueAsString(cbcReports);
		
		if(hidef != null && hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntityList() != null && !hidef.getCbcReports().getConstituentEntityList().isEmpty()) {
			reportsJSON = mapper.writeValueAsString(hidef.getCbcReports().getConstituentEntityList());
			
		}

		model.addAttribute("hidef", hidef);
		return reportsJSON;

	}
	
	@GetMapping(value = "/admin/cbc/reportsBizloadGrid", consumes = "application/json")
	@ResponseBody
	public String reportsBizloadGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<BizActivitiesTypeVo> bizList = new ArrayList<BizActivitiesTypeVo>();
		String bizJson = mapper.writeValueAsString(bizList);
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntity().getBizActivitiesList() != null
				&& hidef.getCbcReports().getConstituentEntity().getBizActivitiesList().size() > 0) {
			bizJson = mapper.writeValueAsString(hidef.getCbcReports().getConstituentEntity().getBizActivitiesList());
		}
		model.addAttribute("hidef", hidef);
		return bizJson;
	}
	@PostMapping(value = "/admin/cbc/reportsinsertBizGrid", consumes = "application/json")
	@ResponseBody
	public BizActivitiesTypeVo reportsinsertBizGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestBody String insertBizGrid,
			BindingResult result, ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Random ran = new Random();
		BizActivitiesTypeVo bizVo = null;
		try {
			List<BizActivitiesTypeVo> bizList = new ArrayList<BizActivitiesTypeVo>();
			bizVo = mapper.readValue(insertBizGrid, BizActivitiesTypeVo.class);
			bizVo.setId(ran.nextInt(10000));
			if (hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntity().getBizActivitiesList() != null
					&& hidef.getCbcReports().getConstituentEntity().getBizActivitiesList().size() > 0) {
				hidef.getCbcReports().getConstituentEntity().getBizActivitiesList().add(bizVo);
			} else {
				hidef.getCbcReports().getConstituentEntity().setBizActivitiesList(bizList);
				hidef.getCbcReports().getConstituentEntity().getBizActivitiesList().add(bizVo);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return bizVo;
	}
	
	@PostMapping(value = "/admin/cbc/reportsupdateBizGrid", consumes = "application/json")
	@ResponseBody
	public String reportsupdateBizGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestBody String updateBizGrid,
			BindingResult result, ModelMap model, HttpServletRequest request,
			@RequestParam(required = true) int id,
			HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntity().getBizActivitiesList() != null
				&& hidef.getCbcReports().getConstituentEntity().getBizActivitiesList().size() > 0) {
			try {
				BizActivitiesTypeVo updatedBizVo = mapper.readValue(updateBizGrid, BizActivitiesTypeVo.class);
				for (BizActivitiesTypeVo bizvo : hidef.getCbcReports().getConstituentEntity().getBizActivitiesList()) {
					if (bizvo.getId() == id) {
						bizvo.setId(updatedBizVo.getId());
						bizvo.setBizType(updatedBizVo.getBizType());
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

		}

		model.addAttribute("hidef", hidef);
		return "success";
	}

	@PostMapping(value = "/admin/cbc/reportsdeleteBizGrid", consumes = "application/json")
	@ResponseBody
	public String reportsdeleteBizGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestBody String deleteBizGrid,
			@RequestParam(required = true) int id, BindingResult result, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if (deleteBizGrid != null && hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntity() != null && hidef.getCbcReports().getConstituentEntity().getBizActivitiesList() != null
				&& hidef.getCbcReports().getConstituentEntity().getBizActivitiesList().size() > 0) {
			try {
				BizActivitiesTypeVo updatedBizVo = mapper.readValue(deleteBizGrid, BizActivitiesTypeVo.class);
				List<BizActivitiesTypeVo> copyList = new ArrayList<BizActivitiesTypeVo>(hidef.getCbcReports().getConstituentEntity().getBizActivitiesList());
				for (BizActivitiesTypeVo bizvo : hidef.getCbcReports().getConstituentEntity().getBizActivitiesList()) {
					if (id == bizvo.getId()) {
						copyList.remove(bizvo);
						if(hidef.getCbcReports().getConstituentEntity().getConsId() != 0 && bizvo.getId() != 0) {
							cbcpayldbizactivRepository.deleteById(BigInteger.valueOf(bizvo.getId()));
						}
						hidef.getCbcReports().getConstituentEntity().setBizActivitiesList(copyList);
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

		}
		model.addAttribute("hidef", hidef);
		return "success";
	}
	
	@GetMapping(value="/admin/cbc/viewConsEntity")
	public String viewConstEntityBasedOnId(@ModelAttribute("hidef")HidefVo hidef,@RequestParam int viewId,BindingResult result,ModelMap model,
			Map<String,Object> map) {
		
		
		
		if(hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntityList() != null && !hidef.getCbcReports().getConstituentEntityList().isEmpty()) {
		for(CbcConstituentEntityVO constEntity : hidef.getCbcReports().getConstituentEntityList()) {			
			if(constEntity.getConsId() == viewId) {
				hidef.getCbcReports().setConstituentEntity(new CbcConstituentEntityVO());
				hidef.getCbcReports().setConstituentEntity(constEntity);
			}			
		}			
		}
		
		ObjectMapper mapper = new ObjectMapper();
		List<Hicountry> country = ctccommonDropdownService.getAllCountries();
		List<Cbcnametype> cbcnametype = ctccommonDropdownService.getAllNameType();
		List<CommonDropdownGridBean> gridBeans = new ArrayList<>();
		List<Cbcbizactivitiesreference> bizActivitiesList = ctccommonDropdownService.getAllCbcbizactivitiesreference();
		for (Hicountry residentCountry : country) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(residentCountry.getId());
			gridBean.setName(residentCountry.getCountryCode());
			gridBeans.add(gridBean);

		}
		List<CommonDropdownGridBean> nameTypegridBeans = new ArrayList<>();
		for (Cbcnametype nameType : cbcnametype) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(new BigInteger(nameType.getId()));
			gridBean.setName(nameType.getNameType());
			nameTypegridBeans.add(gridBean);

		}
		List<CommonDropdownGridBean> BizgridBeans = new ArrayList<>();
		for (Cbcbizactivitiesreference bizActivities : bizActivitiesList) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(bizActivities.getId());
			gridBean.setName(bizActivities.getBizType());
			BizgridBeans.add(gridBean);

		}

		try {
			String arrayToJson = mapper.writeValueAsString(gridBeans);
			String nameGridJson = mapper.writeValueAsString(nameTypegridBeans);
			String bizGridJson = mapper.writeValueAsString(BizgridBeans);
			map.put("countryList", country);
			map.put("residentCountry", arrayToJson);
			map.put("nameTypeList", nameGridJson);
			map.put("bizTypeList", bizGridJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();

		}
		
		model.addAttribute("hidef", hidef);
		
		return "cbcReports";
	}
	
	@GetMapping(value="/admin/cbc/deleteConsEntity")
	public String deleteConsEntity(@ModelAttribute("hidef")HidefVo hidef,@RequestParam int viewId,BindingResult result,ModelMap model,
			Map<String,Object> map) {
		
		
		List<CbcConstituentEntityVO> constituentEntityList = new ArrayList<CbcConstituentEntityVO>();
		if(hidef.getCbcReports() != null && hidef.getCbcReports().getConstituentEntityList() != null && !hidef.getCbcReports().getConstituentEntityList().isEmpty()) {
		for(CbcConstituentEntityVO constEntity : hidef.getCbcReports().getConstituentEntityList()) {			
			if(constEntity.getConsId() != viewId) {
				constituentEntityList.add(constEntity);
			}			
		}
		
		hidef.getCbcReports().setConstituentEntityList(constituentEntityList);
		
		}
		
		ObjectMapper mapper = new ObjectMapper();
		List<Hicountry> country = ctccommonDropdownService.getAllCountries();
		List<Cbcnametype> cbcnametype = ctccommonDropdownService.getAllNameType();
		List<CommonDropdownGridBean> gridBeans = new ArrayList<>();
		List<Cbcbizactivitiesreference> bizActivitiesList = ctccommonDropdownService.getAllCbcbizactivitiesreference();
		for (Hicountry residentCountry : country) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(residentCountry.getId());
			gridBean.setName(residentCountry.getCountryCode());
			gridBeans.add(gridBean);

		}
		List<CommonDropdownGridBean> nameTypegridBeans = new ArrayList<>();
		for (Cbcnametype nameType : cbcnametype) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(new BigInteger(nameType.getId()));
			gridBean.setName(nameType.getNameType());
			nameTypegridBeans.add(gridBean);

		}
		List<CommonDropdownGridBean> BizgridBeans = new ArrayList<>();
		for (Cbcbizactivitiesreference bizActivities : bizActivitiesList) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(bizActivities.getId());
			gridBean.setName(bizActivities.getBizType());
			BizgridBeans.add(gridBean);

		}

		try {
			String arrayToJson = mapper.writeValueAsString(gridBeans);
			String nameGridJson = mapper.writeValueAsString(nameTypegridBeans);
			String bizGridJson = mapper.writeValueAsString(BizgridBeans);
			map.put("countryList", country);
			map.put("residentCountry", arrayToJson);
			map.put("nameTypeList", nameGridJson);
			map.put("bizTypeList", bizGridJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();

		}
		
		model.addAttribute("hidef", hidef);
		
		return "cbcReports";
	}
	
	
	
	
	
	@GetMapping(value = "/admin/cbc/reloadConstEntityAlone")
	public String getReportsTabForConsEntity(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			Map<String, Object> map) {
		hidef.getCbcReports().setConstituentEntity(new CbcConstituentEntityVO());
		
		ObjectMapper mapper = new ObjectMapper();
		List<Hicountry> country = ctccommonDropdownService.getAllCountries();
		List<Cbcnametype> cbcnametype = ctccommonDropdownService.getAllNameType();
		List<CommonDropdownGridBean> gridBeans = new ArrayList<>();
		List<Cbcbizactivitiesreference> bizActivitiesList = ctccommonDropdownService.getAllCbcbizactivitiesreference();
		for (Hicountry residentCountry : country) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(residentCountry.getId());
			gridBean.setName(residentCountry.getCountryCode());
			gridBeans.add(gridBean);

		}
		List<CommonDropdownGridBean> nameTypegridBeans = new ArrayList<>();
		for (Cbcnametype nameType : cbcnametype) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(new BigInteger(nameType.getId()));
			gridBean.setName(nameType.getNameType());
			nameTypegridBeans.add(gridBean);

		}
		List<CommonDropdownGridBean> BizgridBeans = new ArrayList<>();
		for (Cbcbizactivitiesreference bizActivities : bizActivitiesList) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(bizActivities.getId());
			gridBean.setName(bizActivities.getBizType());
			BizgridBeans.add(gridBean);

		}

		try {
			String arrayToJson = mapper.writeValueAsString(gridBeans);
			String nameGridJson = mapper.writeValueAsString(nameTypegridBeans);
			String bizGridJson = mapper.writeValueAsString(BizgridBeans);
			map.put("countryList", country);
			map.put("residentCountry", arrayToJson);
			map.put("nameTypeList", nameGridJson);
			map.put("bizTypeList", bizGridJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();

		}
		
		model.addAttribute("hidef", hidef);
		return "cbcReports";
	}
	
	@GetMapping(value = "/admin/cbc/viewConstituentEntityDone")
	public String viewConstituentEntityDone(@ModelAttribute("hidef") HidefVo hidef, BindingResult result,
			ModelMap model, Map<String, Object> map) {
		CbcConstituentEntityVO reportVO = new CbcConstituentEntityVO();
		hidef.getCbcReports().setConstituentEntity(reportVO);
		
		ObjectMapper mapper = new ObjectMapper();
		List<Hicountry> country = ctccommonDropdownService.getAllCountries();
		List<Cbcnametype> cbcnametype = ctccommonDropdownService.getAllNameType();
		List<CommonDropdownGridBean> gridBeans = new ArrayList<>();
		List<Cbcbizactivitiesreference> bizActivitiesList = ctccommonDropdownService.getAllCbcbizactivitiesreference();
		for (Hicountry residentCountry : country) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(residentCountry.getId());
			gridBean.setName(residentCountry.getCountryCode());
			gridBeans.add(gridBean);

		}
		List<CommonDropdownGridBean> nameTypegridBeans = new ArrayList<>();
		for (Cbcnametype nameType : cbcnametype) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(new BigInteger(nameType.getId()));
			gridBean.setName(nameType.getNameType());
			nameTypegridBeans.add(gridBean);

		}
		List<CommonDropdownGridBean> BizgridBeans = new ArrayList<>();
		for (Cbcbizactivitiesreference bizActivities : bizActivitiesList) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(bizActivities.getId());
			gridBean.setName(bizActivities.getBizType());
			BizgridBeans.add(gridBean);

		}

		try {
			String arrayToJson = mapper.writeValueAsString(gridBeans);
			String nameGridJson = mapper.writeValueAsString(nameTypegridBeans);
			String bizGridJson = mapper.writeValueAsString(BizgridBeans);
			map.put("countryList", country);
			map.put("residentCountry", arrayToJson);
			map.put("nameTypeList", nameGridJson);
			map.put("bizTypeList", bizGridJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();

		}
		model.addAttribute("hidef", hidef);
		return "cbcReports";
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

}
