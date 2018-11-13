package com.censof.myfi.hidefmyfi.validation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.censof.myfi.hidefmyfi.service.UserService;
import com.censof.myfi.hidefmyfi.vo.HidefVo;
import com.censof.myfi.hidefmyfi.vo.UserVo;


@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserVo.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	UserVo user = (UserVo) o;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "myCBCId", "NotEmpty");
        
        if(user.getMyCBCId().length() < 8 || user.getMyCBCId().length() > 8){
        	errors.rejectValue("myCBCId", "Size.user.myCBCId");
        }
        
        if(!user.getMyCBCId().startsWith("2")){
        	errors.rejectValue("myCBCId", "Start.user.myCBCId");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.user.username");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.user.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.user.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.user.passwordConfirm");
        }
    }
    
    public void validateEmail(Object o, Errors errors){
    	UserVo user = (UserVo) o;
    	if(user == null){
    		errors.rejectValue("email", "Email.user.notRegistered");
    	}
    	
    }
    
    public void validateResetPassword(Object o, Errors errors){
    	UserVo user = (UserVo) o;
    	
    	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.user.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.user.passwordConfirm");
        }
    }
}