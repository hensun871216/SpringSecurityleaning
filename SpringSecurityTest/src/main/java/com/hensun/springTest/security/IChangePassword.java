package com.hensun.springTest.security;

import org.springframework.security.access.prepost.PreAuthorize;


public interface IChangePassword {
	@PreAuthorize("#userName==principal.username or hasRole('ROLE_ADMIN')") //change to use the aop 
	void changePassword(String userName, String password); 

}
