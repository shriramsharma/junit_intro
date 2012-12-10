/**
 * 
 */
package com.ensco.junitintro.dao.impl;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ensco.junitintro.dao.IUserDao;

/**
 * @author shriramsharma
 * 
 */
@Repository
@Qualifier("userDao")
public class UserDao implements IUserDao {

	@Autowired
	private BasicDataSource datasource;

	private static String addUserQuery = "INSERT INTO USERS (FIRST_NAME, LAST_NAME, EMAIL) VALUES(?,?,?)";

	private static String getUserQuery = "SELECT FIRST_NAME, LAST_NAME, EMAIL FROM USERS WHERE FIRST_NAME LIKE ? OR LAST_NAME LIKE ? OR EMAIL LIKE ?";

	private static String getAllUsersQuery = "SELECT FIRST_NAME, LAST_NAME, EMAIL FROM USERS";

	private static String getUserByEmailQuery = "SELECT FIRST_NAME, LAST_NAME, EMAIL FROM USERS WHERE EMAIL = ?";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ensco.junitintro.dao.IUserDao#addUser(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public boolean addUser(String firstName, String lastName, String email) {
		JdbcTemplate insert = new JdbcTemplate(datasource);
		int check = insert.update(addUserQuery, new Object[] { firstName,
				lastName, email });
		if (check <= 0)
			return false;
		else
			return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ensco.junitintro.dao.IUserDao#getUser(java.lang.String)
	 */
	public Collection<Map<String, Object>> getUser(String searchString) {
		JdbcTemplate select = new JdbcTemplate(datasource);
		return select.queryForList(getUserQuery, new Object[] {
				"%" + searchString + "%", "%" + searchString + "%",
				"%" + searchString + "%" });
	}

	public Collection<Map<String, Object>> getAllUser() {
		JdbcTemplate selectAll = new JdbcTemplate(datasource);
		return selectAll.queryForList(getAllUsersQuery);
	}

	public Collection<Map<String, Object>> getUserByEmail(String email) {
		JdbcTemplate select = new JdbcTemplate(datasource);
		return select.queryForList(getUserByEmailQuery, new Object[] { email });
	}

}
