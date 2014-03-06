package com.hensun.springTest.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.memory.InMemoryDaoImpl;

public class ImMemoryChangePasswordDaoImpl extends InMemoryDaoImpl implements
		IChangePassword {

	public ImMemoryChangePasswordDaoImpl() {
		super();
	}

	public void changePassword(String userName, String newPassword) {
		// getUserDetails
		User userDetails = (User) loadUserByUsername(userName);
		User userDetailsNew = new User(userDetails.getUsername(),
				newPassword, userDetails.isEnabled(),
				userDetails.isAccountNonExpired(),
				userDetails.isCredentialsNonExpired(),
				userDetails.isAccountNonLocked(), userDetails.getAuthorities());
		getUserMap().addUser(userDetailsNew);
	}

}
