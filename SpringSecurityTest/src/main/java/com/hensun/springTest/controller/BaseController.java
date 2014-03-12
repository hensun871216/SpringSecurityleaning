package com.hensun.springTest.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;


/**
 * The base Spring MVC controller. Used to provide common functionality to all controllers.
 * 
 * @author Mularien
 */
public class BaseController {
	
	Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	@ModelAttribute("showLoginLink")
	public boolean getShowLoginLink() {
		for(GrantedAuthority authority : getAuthentication().getAuthorities()) {
			if(authority.equals("ROLE_USER"))
				return false;
		}
		return true;
	}
}
