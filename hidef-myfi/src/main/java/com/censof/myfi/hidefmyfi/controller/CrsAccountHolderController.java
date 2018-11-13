package com.censof.myfi.hidefmyfi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.censof.myfi.hidefmyfi.CTSConstants;
import com.censof.myfi.hidefmyfi.entity.Hicountry;
import com.censof.myfi.hidefmyfi.service.CtcDataSaveService;
import com.censof.myfi.hidefmyfi.service.CtccommonDropdownService;
import com.censof.myfi.hidefmyfi.vo.HidefVo;

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
		List<Hicountry> country = ctccommonDropdownService.getAllCountries();
		map.put("countryList", country);
		model.addAttribute("hidef", hidef);
		hidef.setCurrentTab(CTSConstants.HIDEF_CTS_CRS_ACCOUNTHOLDER);
		return "crsAccountHolder";
	}
	@PostMapping(value ="/admin/crs/accountHolderPrevious")
	public String accountHolderPrevious(@ModelAttribute("hidef")HidefVo hidef, BindingResult result,
			 @RequestParam(required = false, value = "next1") String next,
			 @RequestParam(required = false, value = "previous1") String previous,
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

}