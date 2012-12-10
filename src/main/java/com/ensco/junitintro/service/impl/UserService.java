/**
 * 
 */
package com.ensco.junitintro.service.impl;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ensco.junitintro.dao.IUserDao;
import com.ensco.junitintro.exceptions.DuplicateDataException;
import com.ensco.junitintro.exceptions.InvalidParameterException;
import com.ensco.junitintro.exceptions.MissingRequiredParameterException;
import com.ensco.junitintro.service.IUserService;

/**
 * @author shriramsharma
 * 
 */
@Service
@Qualifier("userService")
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ensco.junitintro.service.IUserService#addUser(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@ExceptionHandler({ MissingRequiredParameterException.class,
			InvalidParameterException.class, DuplicateDataException.class })
	public boolean addUser(String firstName, String lastName, String email) {

		/* Required check */
		if (firstName == null || firstName.length() <= 0)
			throw new MissingRequiredParameterException(
					"First Name is required.");
		if (lastName == null || lastName.length() <= 0)
			throw new MissingRequiredParameterException(
					"Last Name is required.");
		if (email == null || email.length() <= 0)
			throw new MissingRequiredParameterException("Email is required.");

		/* Validity check */
		if (firstName.matches(".*\\d\\.*"))
			throw new InvalidParameterException(
					"First name should contain only characters.");
		if (lastName.matches(".*\\d\\.*"))
			throw new InvalidParameterException(
					"Last name should contain only characters.");
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(email);
		if (!m.matches())
			throw new InvalidParameterException("Invalid email address");

		/* Check if user already exists */
		int checkUserExists = userDao.getUserByEmail(email).size();
		if (checkUserExists > 0)
			throw new DuplicateDataException(email + " already exists");

		return userDao.addUser(firstName, lastName, email);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ensco.junitintro.service.IUserService#getUser(java.lang.String)
	 */
	public Collection<Map<String, Object>> getUser(String searchString) {

		if (searchString == null)
			throw new MissingRequiredParameterException(
					"searchString cannot be null");

		return userDao.getUser(searchString);
	}

	public Collection<Map<String, Object>> getAllUsers() {
		return userDao.getAllUser();
	}

}
