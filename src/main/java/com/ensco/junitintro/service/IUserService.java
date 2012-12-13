/**
 * 
 */
package com.ensco.junitintro.service;

import java.util.Collection;
import java.util.Map;

import com.ensco.junitintro.dao.IUserDao;

/**
 * @author shriramsharma
 * 
 */
public interface IUserService {

	public boolean addUser(String firstName, String lastName, String email);

	public Collection<Map<String, Object>> getUser(String searchString);

	public Collection<Map<String, Object>> getAllUsers();

	public void setUserDao(IUserDao userDao);

}
