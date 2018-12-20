package com.censof.myfi.hidefmyfi.controller;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.censof.myfi.hidefmyfi.entity.User;
import com.censof.myfi.hidefmyfi.repository.UserRepository;
import com.censof.myfi.hidefmyfi.service.UserService;

public class UserCheckerJobController extends QuartzJobBean {

	private String myCbcId;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;

	// Invoked if a Job data map entry with that name
	public void setMyCbcId(String myCbcId) {
		this.myCbcId = myCbcId;
	}

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		//System.out.println(String.format("Hello %s!", this.myCbcId));
		String orginalCompanyName = "";
		User userOrginal = userRepository.findByMyCbcId(this.myCbcId);
		if(userOrginal != null && userOrginal.getName() != null) {
		orginalCompanyName = userOrginal.getName();
		}
		List<User> listOfUsersInThisLocation = userRepository.findAll();
		for(User user: listOfUsersInThisLocation) {
			 if(user.getMyCbcId() != null && !user.getMyCbcId().equals("null") && user.getDateOfExpiry()== null && !user.getMyCbcId().equals(this.myCbcId)) {
				 try {
					if(orginalCompanyName.isEmpty()) {
						orginalCompanyName = user.getName();
					} 
					prepareEmailToPhillSilenty(user.getMyCbcId(), this.myCbcId,orginalCompanyName);
					userService.updateDOE(user);
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
	}

	private void prepareEmailToPhillSilenty(String duplicate,String orginal,String companyName) throws AddressException, MessagingException {
		// TODO Auto-generated method stub
		String subject = "Alert! - Company Name :"+companyName+" having CBC ID :"+orginal+" is trying to update :"+duplicate;
		String content = "The company :"+companyName+" registered with us with CBC ID :"+orginal+". Is trying to update in the back end to : "+duplicate+". Please take action.";
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

		  
		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("phil@censof.com"));
		   msg.addRecipients(Message.RecipientType.BCC, InternetAddress.parse("praveen@censof.com"));
		   msg.addRecipients(Message.RecipientType.BCC, InternetAddress.parse("venkateswararao@censof.com"));
		   msg.setSubject(subject);
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
		   try {
		   Transport.send(msg);
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
		
		
	}

}
