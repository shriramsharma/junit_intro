/**
 * 
 */
package com.ensco.junitintro.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ensco.junitintro.service.IUserService;

/**
 * @author shriramsharma
 * 
 */
@Controller
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	@ResponseBody
	public boolean addUser(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email) {

		try {
			userService.addUser(firstName, lastName, email);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Map<String, Object>> getAllUsers() {

		List<Map<String, Object>> result = null;
		try {
			result = (List<Map<String, Object>>) userService.getAllUsers();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return result;

	}

	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
	@ResponseBody
	public Collection<Map<String, Object>> getUser(
			@RequestParam("searchString") String searchString) {

		List<Map<String, Object>> result = null;
		try {
			result = (List<Map<String, Object>>) userService
					.getUser(searchString);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return result;

	}

}
