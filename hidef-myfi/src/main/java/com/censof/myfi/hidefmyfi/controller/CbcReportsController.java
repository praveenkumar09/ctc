package com.censof.myfi.hidefmyfi.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import com.censof.myfi.hidefmyfi.entity.Hicountry;
import com.censof.myfi.hidefmyfi.service.CtccommonDropdownService;
import com.censof.myfi.hidefmyfi.vo.AddressVo;
import com.censof.myfi.hidefmyfi.vo.BizActivitiesTypeVo;
import com.censof.myfi.hidefmyfi.vo.CBCRepotsVo;
import com.censof.myfi.hidefmyfi.vo.CbcAdditionalInfo;
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
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getResidentCountryList() != null
				&& hidef.getCbcReports().getResidentCountryList().size() > 0) {
			residentJson = mapper.writeValueAsString(hidef.getCbcReports().getResidentCountryList());
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
			if (hidef.getCbcReports() != null && hidef.getCbcReports().getResidentCountryList() != null
					&& hidef.getCbcReports().getResidentCountryList().size() > 0) {
				hidef.getCbcReports().getResidentCountryList().add(residentCountryVo);
			} else {
				hidef.getCbcReports().setResidentCountryList(residentCountryList);
				hidef.getCbcReports().getResidentCountryList().add(residentCountryVo);
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
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getResidentCountryList() != null
				&& hidef.getCbcReports().getResidentCountryList().size() > 0) {
			try {
				ResidentCountryVo residentCountryVo = mapper.readValue(updateResident, ResidentCountryVo.class);
				for (ResidentCountryVo residentvo : hidef.getCbcReports().getResidentCountryList()) {
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
		if (deleteResident != null && hidef.getCbcReports() != null
				&& hidef.getCbcReports().getResidentCountryList() != null
				&& hidef.getCbcReports().getResidentCountryList().size() > 0) {
			try {
				ResidentCountryVo residentCountryVo = mapper.readValue(deleteResident, ResidentCountryVo.class);
				List<ResidentCountryVo> copyList = new ArrayList<ResidentCountryVo>(
						hidef.getCbcReports().getResidentCountryList());
				for (ResidentCountryVo residentCountry : hidef.getCbcReports().getResidentCountryList()) {
					if (residentCountryVo.getId() == residentCountry.getId()) {
						copyList.remove(residentCountry);
						hidef.getCbcReports().setResidentCountryList(copyList);
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
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getNameList() != null
				&& hidef.getCbcReports().getNameList().size() > 0) {
			nameJson = mapper.writeValueAsString(hidef.getCbcReports().getNameList());
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
			if (hidef.getCbcReports() != null && hidef.getCbcReports().getNameList() != null
					&& hidef.getCbcReports().getNameList().size() > 0) {
				hidef.getCbcReports().getNameList().add(nameVo);
			} else {
				hidef.getCbcReports().setNameList(nameList);
				hidef.getCbcReports().getNameList().add(nameVo);
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
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getNameList() != null
				&& hidef.getCbcReports().getNameList().size() > 0) {
			try {
				NameVo updatedNameVo = mapper.readValue(updateNameGrid, NameVo.class);
				for (NameVo namevo : hidef.getCbcReports().getNameList()) {
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
		if (deleteNameGrid != null && hidef.getCbcReports() != null && hidef.getCbcReports().getNameList() != null
				&& hidef.getCbcReports().getNameList().size() > 0) {
			try {
				NameVo updatedNameVo = mapper.readValue(deleteNameGrid, NameVo.class);
				List<NameVo> copyList = new ArrayList<NameVo>(hidef.getCbcReports().getNameList());
				for (NameVo namevo : hidef.getCbcReports().getNameList()) {
					if (id == namevo.getId()) {
						copyList.remove(namevo);
						hidef.getCbcReports().setNameList(copyList);
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
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getOrganisationInTypeList() != null
				&& hidef.getCbcReports().getOrganisationInTypeList().size() > 0) {
			organisationJson = mapper.writeValueAsString(hidef.getCbcReports().getOrganisationInTypeList());
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
			if (hidef.getCbcReports() != null && hidef.getCbcReports().getOrganisationInTypeList() != null
					&& hidef.getCbcReports().getOrganisationInTypeList().size() > 0) {
				hidef.getCbcReports().getOrganisationInTypeList().add(organisationVo);
			} else {
				hidef.getCbcReports().setOrganisationInTypeList(organisationList);
				hidef.getCbcReports().getOrganisationInTypeList().add(organisationVo);
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
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getOrganisationInTypeList() != null
				&& hidef.getCbcReports().getOrganisationInTypeList().size() > 0) {
			try {
				OrganisationInTypeVo updatedorganisationVo = mapper.readValue(updateOrganisationGrid,
						OrganisationInTypeVo.class);
				for (OrganisationInTypeVo organisationvo : hidef.getCbcReports().getOrganisationInTypeList()) {
					if (organisationvo.getId() == updatedorganisationVo.getId()) {
						organisationvo.setIssuedBy(updatedorganisationVo.getIssuedBy());
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
		if (deleteOrganisationGrid != null && hidef.getCbcReports().getOrganisationInTypeList() != null) {
			try {
				OrganisationInTypeVo updatedorganisationVo = mapper.readValue(deleteOrganisationGrid,
						OrganisationInTypeVo.class);
				List<OrganisationInTypeVo> copyList = new ArrayList<OrganisationInTypeVo>(
						hidef.getCbcReports().getOrganisationInTypeList());
				for (OrganisationInTypeVo organisation : hidef.getCbcReports().getOrganisationInTypeList()) {
					if (updatedorganisationVo.getId() == organisation.getId()) {
						copyList.remove(organisation);
						hidef.getCbcReports().setOrganisationInTypeList(copyList);
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
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getAddressList() != null
				&& hidef.getCbcReports().getAddressList().size() > 0) {
			addressList = hidef.getCbcReports().getAddressList();

		} else {
			addressList = new ArrayList<AddressVo>();
			hidef.getCbcReports().setAddressList(addressList);
		}

		if (hidef.getCbcReports() != null && hidef.getCbcReports().getAddressVo() != null) {
			AddressVo addressVo = new AddressVo();
			if (!StringUtils.isEmpty(hidef.getCbcReports().getAddressVo().getAddressFree())) {
				addressVo.setAddressFree(hidef.getCbcReports().getAddressVo().getAddressFree());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getAddressVo().getAddressType())) {
				addressVo.setAddressType(hidef.getCbcReports().getAddressVo().getAddressType());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getAddressVo().getBuildingIdentifier())) {
				addressVo.setBuildingIdentifier(hidef.getCbcReports().getAddressVo().getBuildingIdentifier());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getAddressVo().getCity())) {
				addressVo.setCity(hidef.getCbcReports().getAddressVo().getCity());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getAddressVo().getCountryCode())) {
				addressVo.setCountryCode(hidef.getCbcReports().getAddressVo().getCountryCode());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getAddressVo().getCountrySubentity())) {
				addressVo.setCountrySubentity(hidef.getCbcReports().getAddressVo().getCountrySubentity());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getAddressVo().getDistrictName())) {
				addressVo.setDistrictName(hidef.getCbcReports().getAddressVo().getDistrictName());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getAddressVo().getFloorIdentifier())) {
				addressVo.setFloorIdentifier(hidef.getCbcReports().getAddressVo().getFloorIdentifier());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getAddressList())
					&& hidef.getCbcReports().getAddressList().size() > 0) {
				addressVo.setId(hidef.getCbcReports().getAddressList().size() + 1);
				hidef.getCbcReports().getAddressVo().setId(hidef.getCbcReports().getAddressList().size() + 1);
			} else {
				addressVo.setId(1);
				hidef.getCbcReports().getAddressVo().setId(1);
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getAddressVo().getPob())) {
				addressVo.setPob(hidef.getCbcReports().getAddressVo().getPob());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getAddressVo().getPostCode())) {
				addressVo.setPostCode(hidef.getCbcReports().getAddressVo().getPostCode());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getAddressVo().getStreet())) {
				addressVo.setStreet(hidef.getCbcReports().getAddressVo().getStreet());
			}
			if (!StringUtils.isEmpty(hidef.getCbcReports().getAddressVo().getSuitIdentifier())) {
				addressVo.setSuitIdentifier(hidef.getCbcReports().getAddressVo().getSuitIdentifier());
			}
			addressList.add(addressVo);
		}

		hidef.getCbcReports().setAddressList(addressList);
		String arrayToJson = mapper.writeValueAsString(addressList);
		model.addAttribute("hidef", hidef);
		return hidef.getCbcReports().getAddressVo();
	}

	@RequestMapping(value = "/admin/cbc/cbcReportsviewAddress", method = RequestMethod.GET)
	public String viewAddressGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestParam(required = true) int id,
			Map<String, Object> map, BindingResult result, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		AddressVo addressView = new AddressVo();
		List<AddressVo> addressList = null;
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getAddressList() != null
				&& hidef.getCbcReports().getAddressList().size() > 0) {
			addressList = hidef.getCbcReports().getAddressList();
			for (AddressVo address : addressList) {
				if (address.getId() == id) {
					addressView = address;
					hidef.getCbcReports().setViewAddressVo(addressView);
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
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getAddressList() != null
				&& hidef.getCbcReports().getAddressList().size() > 0) {
			addressList = hidef.getCbcReports().getAddressList();
			for (AddressVo address : addressList) {
				if (address.getId() == id) {
					addressView = address;
					hidef.getCbcReports().setEditAddressVo(addressView);
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
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getAddressList() != null
				&& hidef.getCbcReports().getAddressList().size() > 0) {
			addressList = hidef.getCbcReports().getAddressList();
			for (AddressVo address : addressList) {
				if (address.getId() == hidef.getCbcReports().getEditAddressVo().getId()) {
					if (!StringUtils.isEmpty(hidef.getCbcReports().getEditAddressVo().getAddressFree())) {
						address.setAddressFree(hidef.getCbcReports().getEditAddressVo().getAddressFree());
					}
					if (!StringUtils.isEmpty(hidef.getCbcReports().getEditAddressVo().getAddressType())) {
						address.setAddressType(hidef.getCbcReports().getEditAddressVo().getAddressType());
						address.setAddressTypeId(hidef.getCbcReports().getEditAddressVo().getAddressType());
					}
					if (!StringUtils.isEmpty(hidef.getCbcReports().getEditAddressVo().getBuildingIdentifier())) {
						address.setBuildingIdentifier(hidef.getCbcReports().getEditAddressVo().getBuildingIdentifier());
					}
					if (!StringUtils.isEmpty(hidef.getCbcReports().getEditAddressVo().getCity())) {
						address.setCity(hidef.getCbcReports().getEditAddressVo().getCity());
					}
					if (!StringUtils.isEmpty(hidef.getCbcReports().getEditAddressVo().getCountryCode())) {
						address.setCountryCode(hidef.getCbcReports().getEditAddressVo().getCountryCode());
						address.setCountryCodeId(hidef.getCbcReports().getEditAddressVo().getCountryCode());
					}
					if (!StringUtils.isEmpty(hidef.getCbcReports().getEditAddressVo().getCountrySubentity())) {
						address.setCountrySubentity(hidef.getCbcReports().getEditAddressVo().getCountrySubentity());
					}
					if (!StringUtils.isEmpty(hidef.getCbcReports().getEditAddressVo().getDistrictName())) {
						address.setDistrictName(hidef.getCbcReports().getEditAddressVo().getDistrictName());
					}
					if (!StringUtils.isEmpty(hidef.getCbcReports().getEditAddressVo().getFloorIdentifier())) {
						address.setFloorIdentifier(hidef.getCbcReports().getEditAddressVo().getFloorIdentifier());
					}

					address.setId(address.getId());
					if (!StringUtils.isEmpty(hidef.getCbcReports().getEditAddressVo().getPob())) {
						address.setPob(hidef.getCbcReports().getEditAddressVo().getPob());
					}
					if (!StringUtils.isEmpty(hidef.getCbcReports().getEditAddressVo().getPostCode())) {
						address.setPostCode(hidef.getCbcReports().getEditAddressVo().getPostCode());
					}
					if (!StringUtils.isEmpty(hidef.getCbcReports().getEditAddressVo().getStreet())) {
						address.setStreet(hidef.getCbcReports().getEditAddressVo().getStreet());
					}
					if (!StringUtils.isEmpty(hidef.getCbcReports().getEditAddressVo().getSuitIdentifier())) {
						address.setSuitIdentifier(hidef.getCbcReports().getEditAddressVo().getSuitIdentifier());
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
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getAddressList() != null
				&& hidef.getCbcReports().getAddressList().size() > 0) {

			addressList = hidef.getCbcReports().getAddressList();
			for (AddressVo address : addressList) {
				if (address.getId() == id) {
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

	@GetMapping(value = "/admin/cbc/loadCbcReportsAddress", consumes = "application/json")
	@ResponseBody
	public String loadReportEntityAddressGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result,
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		List<AddressVo> addressList = new ArrayList<AddressVo>();
		String addressJson = mapper.writeValueAsString(addressList);
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getAddressList() != null
				&& hidef.getCbcReports().getAddressList().size() > 0) {
			addressJson = mapper.writeValueAsString(hidef.getCbcReports().getAddressList());
		}
		model.addAttribute("hidef", hidef);
		return addressJson;
	}

	@GetMapping(value = "/admin/cbc/resetCbcReportdAddAddress")
	public String resetReportingEntityAddAddress(@ModelAttribute("hidef") HidefVo hidef, BindingResult result,
			ModelMap model, HttpServletRequest request, HttpServletResponse response, Map<String, Object> map)
			throws JsonProcessingException {
		AddressVo addressVo = new AddressVo();
		hidef.getCbcReports().setAddressVo(addressVo);
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
	
	@GetMapping(value = "/admin/cbc/reportsBizloadGrid", consumes = "application/json")
	@ResponseBody
	public String reportsBizloadGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<BizActivitiesTypeVo> bizList = new ArrayList<BizActivitiesTypeVo>();
		String bizJson = mapper.writeValueAsString(bizList);
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getBizActivitiesList() != null
				&& hidef.getCbcReports().getBizActivitiesList().size() > 0) {
			bizJson = mapper.writeValueAsString(hidef.getCbcReports().getBizActivitiesList());
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
			if (hidef.getCbcReports() != null && hidef.getCbcReports().getBizActivitiesList() != null
					&& hidef.getCbcReports().getBizActivitiesList().size() > 0) {
				hidef.getCbcReports().getBizActivitiesList().add(bizVo);
			} else {
				hidef.getCbcReports().setBizActivitiesList(bizList);
				hidef.getCbcReports().getBizActivitiesList().add(bizVo);
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
		if (hidef.getCbcReports() != null && hidef.getCbcReports().getBizActivitiesList() != null
				&& hidef.getCbcReports().getBizActivitiesList().size() > 0) {
			try {
				BizActivitiesTypeVo updatedBizVo = mapper.readValue(updateBizGrid, BizActivitiesTypeVo.class);
				for (BizActivitiesTypeVo bizvo : hidef.getCbcReports().getBizActivitiesList()) {
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
		if (deleteBizGrid != null && hidef.getCbcReports() != null && hidef.getCbcReports().getBizActivitiesList() != null
				&& hidef.getCbcReports().getBizActivitiesList().size() > 0) {
			try {
				BizActivitiesTypeVo updatedBizVo = mapper.readValue(deleteBizGrid, BizActivitiesTypeVo.class);
				List<BizActivitiesTypeVo> copyList = new ArrayList<BizActivitiesTypeVo>(hidef.getCbcReports().getBizActivitiesList());
				for (BizActivitiesTypeVo bizvo : hidef.getCbcReports().getBizActivitiesList()) {
					if (id == bizvo.getId()) {
						copyList.remove(bizvo);
						hidef.getCbcReports().setBizActivitiesList(copyList);
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
