package com.censof.myfi.hidefmyfi.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

public class TaskImplementingLogoutHandler implements LogoutHandler {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
		// TODO Auto-generated method stub
		logger.info("In here");
		    if(request != null) {
		    	request.getSession().invalidate();
		    }
		
	}

}
