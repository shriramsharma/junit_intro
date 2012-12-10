/**
 * 
 */
package com.ensco.junitintro.dao;

import java.util.Collection;
import java.util.Map;

/**
 * @author shriramsharma
 * 
 */
public interface IUserDao {

	public boolean addUser(String firstName, String lastName, String email);

	public Collection<Map<String, Object>> getUser(String searchString);

	public Collection<Map<String, Object>> getAllUser();

	public Collection<Map<String, Object>> getUserByEmail(String email);

}
