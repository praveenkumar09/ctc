package com.censof.myfi.hidefmyfi.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.censof.myfi.hidefmyfi.CTSConstants;
import com.censof.myfi.hidefmyfi.entity.Cbcbinaryencodingschemecd;
import com.censof.myfi.hidefmyfi.entity.Cbcfileformatcd;
import com.censof.myfi.hidefmyfi.entity.Crsmessagetypeindic;
import com.censof.myfi.hidefmyfi.entity.Hicountry;
import com.censof.myfi.hidefmyfi.service.CtcDataSaveService;
import com.censof.myfi.hidefmyfi.service.CtccommonDropdownService;
import com.censof.myfi.hidefmyfi.service.PackageGenerationService;
import com.censof.myfi.hidefmyfi.vo.CrsMetadataVo;
import com.censof.myfi.hidefmyfi.vo.HidefVo;
import com.censof.myfi.hidefmyfi.vo.UserProfileVo;

@Controller
@SessionAttributes("hidef")
public class CrsMetaDataController {
	
	@Autowired
	private CtccommonDropdownService ctccommonDropdownService;
	
	@Autowired
	private CtcDataSaveService ctcDataSaveService;
	
	@Autowired
	private PackageGenerationService packageGenerationService;
	
	@ModelAttribute("hidef")
    public HidefVo getmetadata () {
        return new HidefVo(); 
    }
	
	@RequestMapping(value ="/admin/crs/main", method = RequestMethod.GET)
	public String getTabMetaData(@ModelAttribute("hidef")HidefVo hidef, 
		      BindingResult result, ModelMap model,Map<String, Object> map) {
		
		hidef = ctcDataSaveService.getUserProfileByMycbcId(hidef);
		
		if(hidef != null && hidef.getCrsmetadata() != null) {
			hidef = setUserProfileInfoForMetaData(hidef);
		}else {
			hidef.setCrsmetadata(new CrsMetadataVo());
			hidef = setUserProfileInfoForMetaData(hidef);
		}
		
		
		List<Hicountry> country = ctccommonDropdownService.getAllCountries();
		List<Crsmessagetypeindic> crsMessageTypeIndic = ctccommonDropdownService.findAllCrsMessageTypeIndic();
		List<Cbcbinaryencodingschemecd> binaryencoding = ctccommonDropdownService.getAllBinaryEncodingSchemesType();
		List<Cbcfileformatcd> fileformatcd = ctccommonDropdownService.getAllFileFormatCdType();
		map.put("countryList", country);
		map.put("messageTypeIndic", crsMessageTypeIndic);
		map.put("binaryencodingList", binaryencoding);
		map.put("fileformatcodeList", fileformatcd);
		model.addAttribute("hidef", hidef);
		hidef.setCurrentTab(CTSConstants.HIDEF_CTS_CRS_METADATA);
		return "crsmetadata";
	}

	@PostMapping(value ="/admin/crs/metadataNext")
	public String MetaDataNext(@ModelAttribute("hidef")HidefVo hidef, BindingResult result,
			 @RequestParam(required = false, value = "next1") String next,
		     ModelMap model,RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "crsmetadata";
		}
		
		if (!StringUtils.isEmpty(next)) {
	            return "redirect:reportingFi";
	    }
		model.addAttribute("hidef", hidef);
		return CTSConstants.tabSelected(hidef.getCurrentTab());
	}
	
	
	@GetMapping(value = "/admin/crs/generateMetaData", produces = MediaType.APPLICATION_XML_VALUE)
	public ModelAndView generateCRSMetaData(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			Map<String, Object> map, HttpServletResponse response) throws IOException {

		String metaDataContentInString = "";
		response.setContentType("application/xml");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String metaDataFileName = auth.getName()+"_"+hidef.getUserprofile().getCommunicationType()+"_Metadata.xml";
		response.setHeader("Content-Disposition", "attachment;filename=\""+metaDataFileName+"\"");
		metaDataContentInString = packageGenerationService.generateCRSXMLMetadata(hidef);
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
	
	@GetMapping(value = "/admin/crs/generateCRSPayload", produces = MediaType.APPLICATION_XML_VALUE)
	public ModelAndView generateCRSPayload(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			Map<String, Object> map, HttpServletResponse response) throws IOException {

		String payloadContentInString = "";
		response.setContentType("application/xml");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String payloadFileName = auth.getName()+"_"+hidef.getUserprofile().getCommunicationType()+"_Payload.xml";
		response.setHeader("Content-Disposition", "attachment;filename=\""+payloadFileName+"\"");
		payloadContentInString = packageGenerationService.generateCRSXMLPayload(hidef);
		response.setContentLength(payloadContentInString.length());
		ServletOutputStream outStream = response.getOutputStream();
		outStream.write(payloadContentInString.getBytes());
		outStream.flush();
		outStream.close();
		response.flushBuffer();
		
		
		//ctcDataSaveService.saveCtcData(hidef);
		
		
		
		//FileCopyUtils.copy(metaDataContentInString, response.getWriter());
		return null;

	}
	
	
	private HidefVo setUserProfileInfoForMetaData(HidefVo hidef) {
		// TODO Auto-generated method stub
		if(hidef.getUserprofile() != null) {
			
			if(hidef.getUserprofile().getSendingcountry() != null && !hidef.getUserprofile().getSendingcountry().isEmpty()) {
				hidef.getCrsmetadata().setSendingCountry(hidef.getUserprofile().getSendingcountry());
			}
			
			if(hidef.getUserprofile().getReceivingcountry() != null && !hidef.getUserprofile().getReceivingcountry().isEmpty()) {
				hidef.getCrsmetadata().setReceivingCountry(hidef.getUserprofile().getReceivingcountry());
			}
			
			if(hidef.getUserprofile().getFileformatCode() != null && !hidef.getUserprofile().getFileformatCode().isEmpty()) {
				hidef.getCrsmetadata().setFileFormatCode(hidef.getUserprofile().getFileformatCode());
			}
			
			if(hidef.getUserprofile().getMsgType() != null && !hidef.getUserprofile().getMsgType().isEmpty()) {
				hidef.getCrsmetadata().setMessageType(hidef.getUserprofile().getMsgType());
			}
			
			if(hidef.getUserprofile().getCommunicationType() != null && !hidef.getUserprofile().getCommunicationType().isEmpty()) {
				hidef.getCrsmetadata().setCommunicationType(hidef.getUserprofile().getCommunicationType());
			}
			
			if(hidef.getUserprofile().getBinaryEncoding() != null && !hidef.getUserprofile().getBinaryEncoding().isEmpty()) {
				hidef.getCrsmetadata().setBinaryEncoding(hidef.getUserprofile().getBinaryEncoding());
			}
			
			if(hidef.getUserprofile().getSendContactEmailAddress() != null && !hidef.getUserprofile().getSendContactEmailAddress().isEmpty()) {
				hidef.getCrsmetadata().setSenderContactEmail(hidef.getUserprofile().getSendContactEmailAddress());
			}
			
			if(hidef.getUserprofile().getRecievingCountryList() != null && !hidef.getUserprofile().getRecievingCountryList().isEmpty()) {
				hidef.getCrsmetadata().setReceivingCountry(ctccommonDropdownService.findCountryById(BigInteger.valueOf(hidef.getUserprofile().getRecievingCountryList().get(0).getRecievingCountry())).getCountryCode());
			}
			
		}else {
			hidef.setUserprofile(new UserProfileVo());
		}
		return hidef;
	}
	
	
	
	

}
