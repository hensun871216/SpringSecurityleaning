package com.hensun.springTest.security;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

public class CustomJdbcDaoImp extends JdbcDaoImpl implements IChangePassword {

	public CustomJdbcDaoImp() {
	}

	public void changePassword(final String userName, final String password) {
		getJdbcTemplate().update("update users set password=? where username=?", new PreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, password);
				ps.setString(2, userName);
			}
		});
		
	}

}
