package com.hensun.springTest.security;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DatabasePasswordSecureBean extends JdbcDaoSupport {
	@Autowired
	private PasswordEncoder passwordEncoder;


	public void secureDatabase() {
		getJdbcTemplate().query("select username, password from users",
				new RowCallbackHandler() {
					public void processRow(ResultSet rs) throws SQLException {
						String username = rs.getString(1);
						String password = rs.getString(2);
						String encodePassword = passwordEncoder.encode(password);
						System.out.println("11111=" + passwordEncoder.encode("123456"));
						System.out.println("22222=" + passwordEncoder.encode("123456"));
						getJdbcTemplate().update(
								"update users set password=? where username=?",
								new Object[] { encodePassword, username });
						logger.debug("update password for user:" + username
								+ " encodePassword:" + password + "oldPassword"+ password);
					}
				});
	}
}
