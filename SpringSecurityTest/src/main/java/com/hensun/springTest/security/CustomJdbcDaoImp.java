package com.hensun.springTest.security;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

public class CustomJdbcDaoImp extends JdbcDaoImpl implements IChangePassword {
	@Autowired
	private PasswordEncoder passwordEncoder;

	public CustomJdbcDaoImp() {
	}

	public void changePassword(final String userName, final String password) {
		final String encodePassword = passwordEncoder.encode(password);
		getJdbcTemplate().update("update users set password=? where username=?", new PreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, encodePassword);
				ps.setString(2, userName);
			}
		});
		
	}

}
