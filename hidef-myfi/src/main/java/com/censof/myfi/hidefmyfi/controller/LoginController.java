package com.censof.myfi.hidefmyfi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.censof.myfi.hidefmyfi.entity.Messagetype;
import com.censof.myfi.hidefmyfi.service.CtcDataSaveService;
import com.censof.myfi.hidefmyfi.service.CtccommonDropdownService;
import com.censof.myfi.hidefmyfi.service.SecurityService;
import com.censof.myfi.hidefmyfi.service.UserService;
import com.censof.myfi.hidefmyfi.validation.UserValidator;
import com.censof.myfi.hidefmyfi.vo.CBCSummaryGridVo;
import com.censof.myfi.hidefmyfi.vo.CRSSummaryGridVo;
import com.censof.myfi.hidefmyfi.vo.HidefVo;
import com.censof.myfi.hidefmyfi.vo.UserVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes("hidef")
public class LoginController {
	
	@Autowired
	private CtcDataSaveService ctcDataSaveService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired UserValidator userValidator;
	
	@Autowired
	private CtccommonDropdownService ctccommonDropdownService;
	
	@ModelAttribute("hidef")
    public HidefVo getmetadata () {
        return new HidefVo(); 
    }

	/*@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(Model model,@ModelAttribute("hidef")HidefVo hidef,String error,String logout) {
		

	    if (error != null)
	        model.addAttribute("error", "Your username and password is invalid.");
	
	    if (logout != null)
	        model.addAttribute("message", "You have been logged out successfully.");	
		
		hidef.setUser(new UserVo());
		model.addAttribute("hidef",new HidefVo());
		return "login";
	}*/


	@RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model,@ModelAttribute("user")UserVo user,@ModelAttribute("hidef") HidefVo hidef,Map<String, Object> map) {
		if(hidef.getCheckEmail() != "") {
			model.addAttribute("checkEmail",hidef.getCheckEmail());
		}
		hidef.setCheckEmail("");
		
		List<Messagetype>  messageTypes = ctccommonDropdownService.findAllMessageTypes();
		map.put("messageType", messageTypes);
		
        model.addAttribute("user", new UserVo());
        return "registration";
    }

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registerNewUser(ModelMap model,@ModelAttribute("user")UserVo userVo,BindingResult bindingResult,@ModelAttribute("hidef") HidefVo hidef,Map<String, Object> map) {
	 	String token = UUID.randomUUID().toString();	
	 	userVo.setToken(token);
	    userValidator.validate(userVo, bindingResult);
	    List<Messagetype>  messageTypes = ctccommonDropdownService.findAllMessageTypes();
		
	    hidef.setCheckEmail("Please check your email address <b>"+userVo.getEmail()+"</b> for activation link and click on the link provided for activation. Thanks!");
	
	    if (bindingResult.hasErrors()) {
	    	map.put("messageType", messageTypes);
	        return "registration";
	    }
	
	    userService.saveUser(userVo);
	    try {
	    	String url = fetchProperties("emailPath");
	    	String content = url+token;
			sendmail(userVo.getEmail(), content);
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    //securityService.autologin(userVo.getEmail(),userVo.getPasswordConfirm());
	    map.put("messageType", messageTypes);
	    model.addAttribute("checkEmail",hidef.getCheckEmail());	
	    return "registrationEmail";
	}
	
	@RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
	public String regitrationConfirm(@RequestParam String token, HttpServletRequest request) {
		
		UserVo user = userService.findByToken(token);
		if(user != null) {
			user.setStatus(1);
			userService.updateUser(user);
		}
	   
	    return "registrationConfirm";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		
		System.getProperty("java.classpath");
		
	    if (error != null)
	        model.addAttribute("error", "Your username and password is invalid.");
	
	    if (logout != null)
	        model.addAttribute("message", "You have been logged out successfully.");
	
	    return "login";
	}
	
	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public String home(ModelMap model,@ModelAttribute("hidef")HidefVo hidef,Map<String, Object> map) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		hidef.setMycbcId(auth.getName());
		UserVo uservo = userService.findByMyCbcIdAndStatus(auth.getName(),1);
		if(uservo.getMessageType() != null && uservo.getMessageType().equals("CRS")) {
			if(hidef.isUserProfileSaved()) {
			map.put("toProfilePage", "cbctrue");
			}else {
				map.put("toProfilePage", hidef.isUserProfileSaved());
			}
		}else {
			map.put("toProfilePage", hidef.isUserProfileSaved());
		}
		map.put("messageType", uservo.getMessageType());
		model.addAttribute("hidef",hidef);	
		return "home";
	}
	
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public String forgotPassword(ModelMap model,@ModelAttribute("user")UserVo userVo,@ModelAttribute("hidef")HidefVo hidef) {
		model.addAttribute("user",new UserVo());
		return "forgotPassword";
	}
	
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public String forgotPasswordPost(@ModelAttribute("user") UserVo userForm, BindingResult bindingResult,Model model) {
		UserVo user = userService.findUserByEmail(userForm.getEmail());
		
		userValidator.validateEmail(user, bindingResult);
			
	    if (bindingResult.hasErrors()) {
	        return "forgotPassword";
	    }
        
	    String emailContent = fetchProperties("resetEmailPath");
    	String content = emailContent+user.getToken();
    	try {
			sendmail(user.getEmail(), content);
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}

        return "redirect:/login";
    }
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetPassword(@RequestParam String token, Model model,@ModelAttribute("user") UserVo userForm,HttpServletRequest request) {
		
		UserVo user = userService.findByToken(token);
		user.setPassword("");
		model.addAttribute("user", user);
		
	    return "resetPassword";
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public String resetPasswordPost(@ModelAttribute("user") UserVo userForm, BindingResult bindingResult,Model model) {
		UserVo user = userService.findByToken(userForm.getToken());
		user.setPasswordConfirm(userForm.getPasswordConfirm());
		user.setPassword(userForm.getPassword());
		
		userValidator.validateResetPassword(user, bindingResult);

		if(bindingResult.hasErrors()){
			return "resetPassword";
		}
		
		userService.saveUser(user);
		System.out.println(userForm.getUsername() + ":" + userForm.getPassword());
		
        return "redirect:/login";
    }

	
	@GetMapping(value = "/admin/cbc/summary", consumes = "application/json")
	@ResponseBody
	public List<CBCSummaryGridVo> loadSummaryGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		List<CBCSummaryGridVo> cbcReports = new ArrayList<CBCSummaryGridVo>();
		String reportsJSON = mapper.writeValueAsString(cbcReports);
		hidef = ctcDataSaveService.getAllDatabyCBCId(hidef);//todo need to change login cbcid
		if(hidef.getCbcsummary() != null && hidef.getCbcsummary().size()>0){
		cbcReports = hidef.getCbcsummary();
		//reportsJSON = mapper.writeValueAsString(cbcReports);
		}
		model.addAttribute("hidef", hidef);
		return cbcReports;

	}
	
	@GetMapping(value = "/admin/cbc/viewSummaryGrid")
	@ResponseBody
	public String viewCBCSummaryGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestParam String id, BindingResult result,
			ModelMap model, Map<String, Object> map) throws JsonParseException, JsonMappingException, IOException {
		HidefVo hidefVo = new HidefVo();
		hidefVo.setMycbcId(hidef.getMycbcId());
		hidef = ctcDataSaveService.viewAllDatabyCBCId(hidefVo, id);
		hidef.setIsSummaryView("true");
		model.addAttribute("hidef", hidef);
		return "success";
	}
	
	@GetMapping(value = "/admin/cbc/editSummaryGrid")
	@ResponseBody
	public String editCBCSummaryGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestParam String id, BindingResult result,
			ModelMap model, Map<String, Object> map) throws JsonParseException, JsonMappingException, IOException {
		HidefVo hidefVo = new HidefVo();
		hidefVo.setMycbcId(hidef.getMycbcId());
		hidef = ctcDataSaveService.viewAllDatabyCBCId(hidefVo, id);
		hidef.setIsSummaryView("false");
		model.addAttribute("hidef", hidef);
		return "success";
	}
	
	@GetMapping(value = "/admin/cbc/deleteSummaryGrid")
	@ResponseBody
	public String deleteSummaryGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestParam String id, BindingResult result,
			ModelMap model, Map<String, Object> map) throws JsonParseException, JsonMappingException, IOException {
		if(hidef.getCbcsummary() != null && hidef.getCbcsummary().size() > 0){
			List<CBCSummaryGridVo> copyList = new ArrayList<CBCSummaryGridVo>(hidef.getCbcsummary());
			for(CBCSummaryGridVo summary :hidef.getCbcsummary() ){
				if(summary.getHrdId() != null && id != null){
					if(Integer.parseInt(id) == summary.getHrdId().intValue()){
						copyList.remove(summary);
						hidef.setCbcsummary(copyList);
						hidef = ctcDataSaveService.deleteSummaryGrid(hidef,new BigInteger(id));
						break;
					}
				}
			}
		}
		
		model.addAttribute("hidef", hidef);
		return "success";
	}
	
	private void sendmail(String username, String content) throws AddressException, MessagingException, IOException {
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("hidefcensof@gmail.com", "hidef@123");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("hidefcensof@gmail.com", false));

		  
		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username));
		   msg.setSubject("Email Notification");
		   msg.setContent(content, "text/html");
		   msg.setSentDate(new Date());

		 /*  MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent("Tutorials point email", "text/html");

		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   MimeBodyPart attachPart = new MimeBodyPart();

		   attachPart.attachFile("/var/tmp/image19.png");
		   multipart.addBodyPart(attachPart);
		   msg.setContent(multipart);*/
		   Transport.send(msg);   
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
	
	@GetMapping(value = "/admin/crs/summary", consumes = "application/json")
	@ResponseBody
	public List<CRSSummaryGridVo> loadcrsSummaryGrid(@ModelAttribute("hidef") HidefVo hidef, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		List<CRSSummaryGridVo> crsReports = new ArrayList<CRSSummaryGridVo>();
		String reportsJSON = mapper.writeValueAsString(crsReports);
		hidef = ctcDataSaveService.getAllDatabyCRSId(hidef);//todo need to change login cbcid
		if(hidef.getCrssummary() != null && hidef.getCrssummary().size()>0){
		crsReports = hidef.getCrssummary();
		//reportsJSON = mapper.writeValueAsString(cbcReports);
		}
		model.addAttribute("hidef", hidef);
		return crsReports;

	}
	@GetMapping(value = "/admin/crs/viewSummaryGrid")
	@ResponseBody
	public String viewCRSSummaryGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestParam String id, BindingResult result,
			ModelMap model, Map<String, Object> map) throws JsonParseException, JsonMappingException, IOException {
		hidef = ctcDataSaveService.viewAllDatabyCRSId(hidef, id);
		hidef.setIsSummaryView("true");
		model.addAttribute("hidef", hidef);
		return "success";
	}
	@GetMapping(value = "/admin/crs/editSummaryGrid")
	@ResponseBody
	public String editCRSSummaryGrid(@ModelAttribute("hidef") HidefVo hidef, @RequestParam String id, BindingResult result,
			ModelMap model, Map<String, Object> map) throws JsonParseException, JsonMappingException, IOException {
		HidefVo hidefVo = new HidefVo();
		hidefVo.setMycbcId(hidef.getMycbcId());
		hidef = ctcDataSaveService.viewAllDatabyCRSId(hidefVo, id);
		hidef.setIsSummaryView("false");
		model.addAttribute("hidef", hidef);
		return "success";
	}
	
	


}
