package com.censof.myfi.hidefmyfi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.censof.myfi.hidefmyfi.CTSConstants;
import com.censof.myfi.hidefmyfi.entity.Cbcdocumenttypeindicator;
import com.censof.myfi.hidefmyfi.entity.Cbcpayldrescountry;
import com.censof.myfi.hidefmyfi.entity.Cbcpayldsumref;
import com.censof.myfi.hidefmyfi.entity.Cbcsummaryreference;
import com.censof.myfi.hidefmyfi.entity.Docrefid;
import com.censof.myfi.hidefmyfi.entity.Hicountry;
import com.censof.myfi.hidefmyfi.repository.CbcpayldrescountryRepository;
import com.censof.myfi.hidefmyfi.repository.CbcpayldsumrefRepository;
import com.censof.myfi.hidefmyfi.service.CtcDataSaveService;
import com.censof.myfi.hidefmyfi.service.CtccommonDropdownService;
import com.censof.myfi.hidefmyfi.service.PackageGenerationService;
import com.censof.myfi.hidefmyfi.vo.CbcAdditionalInfo;
import com.censof.myfi.hidefmyfi.vo.CommonDropdownGridBean;
import com.censof.myfi.hidefmyfi.vo.HidefVo;
import com.censof.myfi.hidefmyfi.vo.RecievingCountryVo;
import com.censof.myfi.hidefmyfi.vo.ResidentCountryVo;
import com.censof.myfi.hidefmyfi.vo.SummaryVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes("hidef")
public class CbcAdditionalInfoController {

	@Autowired
	private CtccommonDropdownService ctccommonDropdownService;

	@Autowired
	private PackageGenerationService metaDataGenerationService;
	
	@Autowired
	private CtcDataSaveService ctcDataSaveService;
	
	@Autowired
	private CbcpayldrescountryRepository cbcPayldResCountryRepository;
	
	@Autowired
	private CbcpayldsumrefRepository cbcPayldSummRefRepository;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	protected SimpleDateFormat sdfFileName = new SimpleDateFormat("yyyyMMdd'T'HHmmssSSS'Z'");

	@ModelAttribute("hidef")
	public HidefVo getmetadata() {
		return new HidefVo();
	}

	@GetMapping(value = "/admin/cbc/additionalInfo")
	public String getTabReportingEntity(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			Map<String, Object> map) {

		if (hidef.getCbcAddionalInfo() == null) {
			hidef.setCbcAddionalInfo(new CbcAdditionalInfo());
		}

		hidef.setCurrentTab(CTSConstants.HIDEF_CTS_CBC_ADDITIONAL_INFO);
		ObjectMapper mapper = new ObjectMapper();
		List<Cbcdocumenttypeindicator> cbcdocumenttypeindicator = ctccommonDropdownService
				.getAllDocumentTypeIndicator();
		List<Hicountry> hicountryList = ctccommonDropdownService.getAllCountries();
		List<Cbcsummaryreference> summaryList = ctccommonDropdownService.getAllCbcSummaryReference();

		List<CommonDropdownGridBean> gridBeans = new ArrayList<>();
		for (Hicountry residentCountry : hicountryList) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(residentCountry.getId());
			gridBean.setName(residentCountry.getCountryCode());
			gridBeans.add(gridBean);

		}
		List<CommonDropdownGridBean> nameTypegridBeans = new ArrayList<>();
		for (Cbcsummaryreference summary : summaryList) {
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(summary.getId());
			gridBean.setName(summary.getRefType());
			nameTypegridBeans.add(gridBean);

		}
		
		if(hidef.getCbcAddionalInfo() != null && hidef.getCbcAddionalInfo().getDocumentReferenceId() == null) {
		if(hidef.getDocRefId() != null){
			String nextDocRef = converToString(Integer.parseInt(hidef.getDocRefId())+1);
			hidef.getCbcAddionalInfo().setDocumentReferenceId(hidef.getDocRefIdStaticText()+"A"+nextDocRef);
			hidef.setDocRefId(String.valueOf(Integer.parseInt(hidef.getDocRefId())+1));
			}
		}
		List<CommonDropdownGridBean> fromProfileCountrygridBeans = new ArrayList<>();
		if(hidef.getUserprofile().getRecievingCountryList() != null && hidef.getUserprofile().getRecievingCountryList().size() > 0){
		for(RecievingCountryVo residentCountry:hidef.getUserprofile().getRecievingCountryList()) {
			Hicountry hicountry = ctccommonDropdownService.findCountryById(BigInteger.valueOf(residentCountry.getRecievingCountry()));
			CommonDropdownGridBean gridBean = new CommonDropdownGridBean();
			gridBean.setId(hicountry.getId());
			gridBean.setName(hicountry.getCountryCode());
			fromProfileCountrygridBeans.add(gridBean);			

		}
		}
		

		try {
			String arrayToJson = mapper.writeValueAsString(gridBeans);
			map.put("residentCountry", arrayToJson);
			map.put("summeryTypeList", mapper.writeValueAsString(nameTypegridBeans));
			map.put("userPropCountry", mapper.writeValueAsString(fromProfileCountrygridBeans));
		} catch (JsonProcessingException e) {
			e.printStackTrace();

		}
		map.put("documentTypeIndicator", cbcdocumenttypeindicator);
		model.addAttribute("hidef", hidef);
		return "cbcAddInfo";
	}

	@PostMapping(value = "/admin/cbc/additionalInfoPrevious")
	public String reportingEntityNext(@ModelAttribute("hidef") HidefVo hidef,
			@RequestParam(required = false, value = "next2") String next,
			@RequestParam(required = false, value = "previous3") String previous, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			return "cbcReportingEntity";
		}
		if (!StringUtils.isEmpty(previous)) {
			return "redirect:cbcReports";
		}

		if (!StringUtils.isEmpty(next)) {
			return "redirect:accountHolder";
		}
		model.addAttribute("hidef", hidef);
		return CTSConstants.tabSelected(hidef.getCurrentTab());
		// return "redirect:accountHolder";
	}

	@GetMapping(value = "/admin/cbc/addinfoloadResidentCountryGrid", consumes = "application/json")
	@ResponseBody
	public String loadResidentCountryGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		List<ResidentCountryVo> residentCountryList = new ArrayList<ResidentCountryVo>();
		String residentJson = mapper.writeValueAsString(residentCountryList);
		if (hidef.getCbcAddionalInfo() != null && hidef.getCbcAddionalInfo().getResidentCountryList() != null
				&& hidef.getCbcAddionalInfo().getResidentCountryList().size() > 0) {
			residentJson = mapper.writeValueAsString(hidef.getCbcAddionalInfo().getResidentCountryList());
		}

		model.addAttribute("hidef", hidef);
		return residentJson;
	}

	@PostMapping(value = "/admin/cbc/addinfoinsertResidentCountryGrid", consumes = "application/json")
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
			if (hidef.getCbcAddionalInfo() != null && hidef.getCbcAddionalInfo().getResidentCountryList() != null
					&& hidef.getCbcAddionalInfo().getResidentCountryList().size() > 0) {
				hidef.getCbcAddionalInfo().getResidentCountryList().add(residentCountryVo);
			} else {
				hidef.getCbcAddionalInfo().setResidentCountryList(residentCountryList);
				hidef.getCbcAddionalInfo().getResidentCountryList().add(residentCountryVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return residentCountryVo;
	}

	@PostMapping(value = "/admin/cbc/addinfoupdateResidentCountryGrid", consumes = "application/json")
	@ResponseBody
	public String updateResidentCountryGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestBody String updateResident,
			@RequestParam(required = true) int id, BindingResult result, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if (hidef.getCbcAddionalInfo() != null && hidef.getCbcAddionalInfo().getResidentCountryList() != null
				&& hidef.getCbcAddionalInfo().getResidentCountryList().size() > 0) {
			try {
				ResidentCountryVo residentCountryVo = mapper.readValue(updateResident, ResidentCountryVo.class);
				for (ResidentCountryVo residentvo : hidef.getCbcAddionalInfo().getResidentCountryList()) {
					if (residentvo.getId() == id) {
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

	@PostMapping(value = "/admin/cbc/addinfodeleteResidentCountryGrid", consumes = "application/json")
	@ResponseBody
	public String addinfodeleteResidentCountryGrid(@ModelAttribute("hidef") HidefVo hidef,
			@RequestBody String deleteResident, @RequestParam(required = true) int id, BindingResult result,
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if (deleteResident != null && hidef.getCbcAddionalInfo() != null
				&& hidef.getCbcAddionalInfo().getResidentCountryList() != null
				&& hidef.getCbcAddionalInfo().getResidentCountryList().size() > 0) {
			try {
				ResidentCountryVo residentCountryVo = mapper.readValue(deleteResident, ResidentCountryVo.class);
				List<ResidentCountryVo> copyList = new ArrayList<ResidentCountryVo>(
						hidef.getCbcAddionalInfo().getResidentCountryList());
				for (ResidentCountryVo residentCountry : hidef.getCbcAddionalInfo().getResidentCountryList()) {
					if (id == residentCountry.getId()) {
						copyList.remove(residentCountry);
						if(hidef.getCbcAddionalInfo().getId() != 0 && residentCountry.getId() != 0) {
							Optional<Cbcpayldrescountry> resCountry = cbcPayldResCountryRepository.findById(BigInteger.valueOf(residentCountry.getId()));
							if(resCountry.isPresent()) {
							cbcPayldResCountryRepository.deleteById(BigInteger.valueOf(residentCountry.getId()));
							}
						}
						hidef.getCbcAddionalInfo().setResidentCountryList(copyList);
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

	@GetMapping(value = "/admin/cbc/loadSummaryReferenceGrid", consumes = "application/json")
	@ResponseBody
	public String summaryloadResidentCountryGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result,
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		List<SummaryVo> summaryList = new ArrayList<SummaryVo>();
		String residentJson = mapper.writeValueAsString(summaryList);
		if (hidef.getCbcAddionalInfo() != null && hidef.getCbcAddionalInfo().getSummaryList() != null
				&& hidef.getCbcAddionalInfo().getSummaryList().size() > 0) {
			residentJson = mapper.writeValueAsString(hidef.getCbcAddionalInfo().getSummaryList());
		}

		model.addAttribute("hidef", hidef);
		return residentJson;
	}

	@PostMapping(value = "/admin/cbc/addSummaryReferenceGrid", consumes = "application/json")
	@ResponseBody
	public SummaryVo summaryinsertResidentCountryGrid(@ModelAttribute("hidef") HidefVo hidef,
			@RequestBody String insertResident, BindingResult result, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Random ran = new Random();
		SummaryVo summaryVo = null;
		try {
			List<SummaryVo> summaryList = new ArrayList<SummaryVo>();
			summaryVo = mapper.readValue(insertResident, SummaryVo.class);
			summaryVo.setId(ran.nextInt(10000));
			if (hidef.getCbcAddionalInfo() != null && hidef.getCbcAddionalInfo().getSummaryList() != null
					&& hidef.getCbcAddionalInfo().getSummaryList().size() > 0) {
				hidef.getCbcAddionalInfo().getSummaryList().add(summaryVo);
			} else {
				hidef.getCbcAddionalInfo().setSummaryList(summaryList);
				hidef.getCbcAddionalInfo().getSummaryList().add(summaryVo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hidef", hidef);
		return summaryVo;
	}

	@PostMapping(value = "/admin/cbc/updateSummaryReferenceGrid", consumes = "application/json")
	@ResponseBody
	public String updateSummaryReferenceGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestBody String updateResident,
			@RequestParam int id, BindingResult result, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if (hidef.getCbcAddionalInfo() != null && hidef.getCbcAddionalInfo().getSummaryList() != null
				&& hidef.getCbcAddionalInfo().getSummaryList().size() > 0) {
			try {
				SummaryVo summaryVo = mapper.readValue(updateResident, SummaryVo.class);
				for (SummaryVo summaryvo : hidef.getCbcAddionalInfo().getSummaryList()) {
					if (summaryvo.getId() == id) {
						summaryvo.setSummeryReference(summaryVo.getSummeryReference());
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

	@PostMapping(value = "/admin/cbc/deleteSummaryReferenceGrid", consumes = "application/json")
	@ResponseBody
	public String deleteSummaryReferenceGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestBody String deleteResident,
			@RequestParam int id, BindingResult result, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException {
		// ObjectMapper mapper = new ObjectMapper();
		if (deleteResident != null && hidef.getCbcAddionalInfo() != null
				&& hidef.getCbcAddionalInfo().getSummaryList() != null
				&& hidef.getCbcAddionalInfo().getSummaryList().size() > 0) {
			try {
				// SummaryVo summaryvo = mapper.readValue(deleteResident, SummaryVo.class);
				List<SummaryVo> copyList = new ArrayList<SummaryVo>();
				for (SummaryVo summary : hidef.getCbcAddionalInfo().getSummaryList()) {
					if (summary.getId() != id) {
						// copyList.remove(summaryvo);
						// break;
						copyList.add(summary);
					}else {
						if(hidef.getCbcAddionalInfo().getId() != 0 && summary.getId() != 0) {
							Optional<Cbcpayldsumref> resCountry = cbcPayldSummRefRepository.findById(BigInteger.valueOf(summary.getId()));
							if(resCountry.isPresent()) {
								cbcPayldSummRefRepository.deleteById(BigInteger.valueOf(summary.getId()));
							}
						}
					}
				}
				hidef.getCbcAddionalInfo().setSummaryList(copyList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("hidef", hidef);
		return "success";
	}

	@GetMapping(value = "/admin/cbc/loadCBCAddInfo", consumes = "application/json")
	@ResponseBody
	public String loadloadCBCReportsGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		List<CbcAdditionalInfo> cbcAddInfo = new ArrayList<CbcAdditionalInfo>();
		String addInfoJSON = mapper.writeValueAsString(cbcAddInfo);
		if (hidef.getCbcAddionalInfo() != null && hidef.getAddInfoList() != null && !hidef.getAddInfoList().isEmpty()) {
			addInfoJSON = mapper.writeValueAsString(hidef.getAddInfoList());
		}
		model.addAttribute("hidef", hidef);
		return addInfoJSON;
	}

	@GetMapping(value = "/admin/cbc/populateAddInfoGrid")
	@ResponseBody
	public CbcAdditionalInfo populateCBCAddInfoGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result,
			ModelMap model, Map<String, Object> map) {
		CbcAdditionalInfo addInfo;
		if (hidef.getCbcAddionalInfo() == null) {
			addInfo = new CbcAdditionalInfo();
		} else {
			addInfo = hidef.getCbcAddionalInfo();
		}
		Random rand = new Random();
		addInfo.setId(rand.nextInt(10000));
		if (hidef.getCbcAddionalInfo() != null && hidef.getAddInfoList() != null) {
			hidef.getAddInfoList().add(addInfo);
		} else {
			hidef.setAddInfoList(new ArrayList<CbcAdditionalInfo>());
			hidef.getAddInfoList().add(addInfo);
		}

		hidef.setCbcAddionalInfo(new CbcAdditionalInfo());
		model.addAttribute("hidef", hidef);
		return addInfo;
	}

	@GetMapping(value = "/admin/cbc/view/cbcAddInfo")
	@ResponseBody
	public String viewCBCAddInfo(@ModelAttribute("hidef") HidefVo hidef, @RequestParam int viewId, BindingResult result,
			ModelMap model, Map<String, Object> map) throws JsonParseException, JsonMappingException, IOException {

		CbcAdditionalInfo cbcAddInfo = new CbcAdditionalInfo();
		for (CbcAdditionalInfo addInfo : hidef.getAddInfoList()) {
			if (addInfo.getId() == viewId) {
				cbcAddInfo = addInfo;
			}
		}

		hidef.setCbcAddionalInfo(cbcAddInfo);
		model.addAttribute("hidef", hidef);
		return "success";
	}

	@GetMapping(value = "/admin/cbc/viewAddInfoDone")
	@ResponseBody
	public String doneClickedOnViewReports(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			Map<String, Object> map) {
		hidef.setCurrentTab(CTSConstants.HIDEF_CTS_CBC_ADDITIONAL_INFO);
		hidef.setCbcAddionalInfo(new CbcAdditionalInfo());
		model.addAttribute("hidef", hidef);
		return "success";
	}

	@GetMapping(value = "/admin/cbc/edit/cbcAddInfo")
	@ResponseBody
	public String editCBCAddInfo(@ModelAttribute("hidef") HidefVo hidef, @RequestParam int editId, BindingResult result,
			ModelMap model, Map<String, Object> map) throws JsonParseException, JsonMappingException, IOException {

		CbcAdditionalInfo cbcAddInfo = new CbcAdditionalInfo();
		for (CbcAdditionalInfo addInfo : hidef.getAddInfoList()) {
			if (addInfo.getId() == editId) {
				cbcAddInfo = addInfo;
			}
		}

		hidef.setCbcAddionalInfo(cbcAddInfo);
		model.addAttribute("hidef", hidef);
		return "success";
	}

	@GetMapping(value = "/admin/cbc/populateEditedCBCAddInfo")
	@ResponseBody
	public CbcAdditionalInfo populateEditedCBCReportsGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result,
			ModelMap model, Map<String, Object> map) {

		CbcAdditionalInfo cbcAddInfo = new CbcAdditionalInfo();
		cbcAddInfo = hidef.getCbcAddionalInfo();
		int newAddInfoId = cbcAddInfo.getId();

		List<CbcAdditionalInfo> newCBCAddInfoEditedList = new ArrayList<CbcAdditionalInfo>();
		for (CbcAdditionalInfo addInfo : hidef.getAddInfoList()) {
			if (addInfo.getId() != newAddInfoId) {
				newCBCAddInfoEditedList.add(addInfo);
			}
		}

		newCBCAddInfoEditedList.add(cbcAddInfo);

		hidef.setCbcAddionalInfo(new CbcAdditionalInfo());
		model.addAttribute("hidef", hidef);

		return cbcAddInfo;
	}

	@GetMapping(value = "/admin/cbc/populateDeletedCBCAddInfo")
	@ResponseBody
	public String populateDeletedCBCAddInfoGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestParam int deleteId,
			BindingResult result, ModelMap model, Map<String, Object> map) {

		List<CbcAdditionalInfo> newCBCAddInfoDeletedList = new ArrayList<CbcAdditionalInfo>();
		for (CbcAdditionalInfo addInfo : hidef.getAddInfoList()) {
			if (addInfo.getId() != deleteId) {
				newCBCAddInfoDeletedList.add(addInfo);
			}
		}

		hidef.setAddInfoList(newCBCAddInfoDeletedList);
		model.addAttribute("hidef", hidef);

		return "success";
	}

	@GetMapping(value = "/admin/cbc/generateMetaData", produces = MediaType.APPLICATION_XML_VALUE)
	public ModelAndView generateCBCMetaData(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			Map<String, Object> map, HttpServletResponse response) throws IOException {

		String metaDataContentInString = "";
		response.setContentType("application/xml");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String metaDataFileName = auth.getName()+"_"+hidef.getUserprofile().getCommunicationType()+"_Metadata.xml";
		response.setHeader("Content-Disposition", "attachment;filename=\""+metaDataFileName+"\"");
		metaDataContentInString = metaDataGenerationService.generateCBCXMLMetData(hidef);
		response.setContentLength(metaDataContentInString.length());
		ServletOutputStream outStream = response.getOutputStream();
		outStream.write(metaDataContentInString.getBytes());
		//outStream.println(metaDataContentInString);
		outStream.flush();
		outStream.close();
		response.flushBuffer();
		
		
		//ctcDataSaveService.saveCtcData(hidef);
		
		
		
		//FileCopyUtils.copy(metaDataContentInString, response.getWriter());
		return null;

	}
	
	@GetMapping(value = "/admin/cbc/generatePayload", produces = MediaType.APPLICATION_XML_VALUE)
	public ModelAndView generateCBCPayload(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			Map<String, Object> map, HttpServletResponse response) throws IOException {

		String metaDataContentInString = "";
		response.setContentType("application/xml");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String payloadFileName = auth.getName()+"_"+hidef.getUserprofile().getCommunicationType()+"_Payload.xml";
		response.setHeader("Content-Disposition", "attachment;filename=\""+payloadFileName+"\"");
		metaDataContentInString = metaDataGenerationService.generateCBCXMLPayload(hidef);
		response.setContentLength(metaDataContentInString.length());
		ServletOutputStream outStream = response.getOutputStream();
		outStream.write(metaDataContentInString.getBytes());
		//outStream.println(metaDataContentInString);
		outStream.flush();
		outStream.close();
		response.flushBuffer();
		//FileCopyUtils.copy(metaDataContentInString, response.getWriter());
		return null;

	}
	
	@GetMapping(value = "/admin/cbc/generatePackage", produces = MediaType.APPLICATION_XML_VALUE)
	public ModelAndView generateCBCPackage(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			Map<String, Object> map, HttpServletResponse response) throws Exception {
		//ctcDataSaveService.saveCtcData(hidef);		
		String packageFolderPath = "";
		response.setContentType("application/zip");
		String packageFileName = hidef.getUserprofile().getSendingcountry()+"_"+hidef.getUserprofile().getCommunicationType()+"_"+sdfFileName.format(new Date(System.currentTimeMillis()))+".zip";
		packageFolderPath = metaDataGenerationService.generateCBCPackage(hidef);
		
	     //Path sourcePath = sourceFile.toPath();
	     Path sourcePath = Paths.get(packageFolderPath);
	     response.setHeader("Content-Disposition", "attachment;filename=\""+sourcePath.getFileName()+"\"");
	     /*ZipOutputStream outZip = new ZipOutputStream(response.getOutputStream(),StandardCharsets.UTF_8);

	     outZip.putNextEntry(new ZipEntry(sourcePath.getFileName().toString()));
	     Files.copy(sourcePath, outZip);
	     outZip.closeEntry();
	     outZip.finish();
	     outZip.close();*/
	     
	     // download zip file code
	     File zipFile = new File(packageFolderPath);
	     InputStream ioStream = new FileInputStream(zipFile);
	     IOUtils.copy(ioStream, response.getOutputStream());
	     response.flushBuffer();
	     
	     ioStream.close();
		
		//FileCopyUtils.copy(metaDataContentInString, response.getWriter());
		return null;

	}
	
	@PostMapping(value = "/admin/cbc/save")
	@ResponseBody
	public String save(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			Map<String, Object> map, HttpServletResponse response) throws IOException {
		
		/**
		 * Database Saving part created by Venki 
		 */
		ctcDataSaveService.saveCtcData(hidef);		
		//FileCopyUtils.copy(metaDataContentInString, response.getWriter());
		return "success";

	}
	
	@GetMapping(value = "/admin/cbc/downloadTemplate", produces = MediaType.APPLICATION_XML_VALUE)
	public ModelAndView generateCBCDownloadTemplate(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			Map<String, Object> map, HttpServletResponse response) throws Exception {
		hidef = ctcDataSaveService.getUserProfileByMycbcId(hidef);
		metaDataGenerationService.writetoExcelFile(hidef,fetchProperties("downloadTemplateLoc"));
		//response.setContentType("Application/x-msexcel");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment;filename=\"CTCTemplate.xlsx\"");
		//File file = new File(fetchProperties("downloadTemplateLoc"));
		//metaDataContentInString = metaDataGenerationService.generateCBCPackage(hidef);
		response.setContentLength(getByte(fetchProperties("templateWorkPath")).length);
		
		//File file = new File(fetchProperties("downloadTemplateLoc"));
		ServletOutputStream outStream = response.getOutputStream();
		outStream.write(getByte(fetchProperties("templateWorkPath")));
		//outStream.println(metaDataContentInString);
		outStream.flush();
		outStream.close();
		response.flushBuffer();
		//FileCopyUtils.copy(metaDataContentInString, response.getWriter());
		
		File toDeleteFile = new File(fetchProperties("templateWorkPath"));
		toDeleteFile.delete();
		
		return null;

	}
	
	public String fetchProperties(String propertyName) {
		Properties properties = new Properties();
		String value = null;
		try {
			File file = ResourceUtils.getFile("classpath:application.properties");
			InputStream in = new FileInputStream(file);
			properties.load(in);
			value = properties.getProperty(propertyName);
		} catch (IOException e) {
		}
		return value;
	}
	
	 private byte[] getByte(String path) {
		    byte[] getBytes = {};
		    try {
		        File file = new File(path);
		        getBytes = new byte[(int) file.length()];
		        InputStream is = new FileInputStream(file);
		        is.read(getBytes);
		        is.close();
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    return getBytes;
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
			hidef.getCbcAddionalInfo().setDocumentReferenceId(docRefId);
			return hidef;
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
