/**
 * 
 */
package com.ensco.junitintro.service;

import java.util.Collection;
import java.util.Map;

/**
 * @author shriramsharma
 * 
 */
public interface IUserService {

	public boolean addUser(String firstName, String lastName, String email);

	public Collection<Map<String, Object>> getUser(String searchString);

	public Collection<Map<String, Object>> getAllUsers();

}
