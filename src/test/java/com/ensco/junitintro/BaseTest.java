package com.ensco.junitintro;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ensco.junitintro.dao.IUserDao;
import com.ensco.junitintro.dao.impl.UserDao;
import com.ensco.junitintro.exceptions.DuplicateDataException;
import com.ensco.junitintro.exceptions.InvalidParameterException;
import com.ensco.junitintro.exceptions.MissingRequiredParameterException;
import com.ensco.junitintro.service.IUserService;
import com.ensco.junitintro.service.impl.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/junitintro_context_test.xml" })
public class BaseTest extends TestCase {

	private IUserDao userDao;
	private IUserService userService;

	@Before
	public void setUp() {

		userDao = new UserDao();
		userDao = createMock(IUserDao.class);
		userService = new UserService();
		userService.setUserDao(userDao);

	}

	@Test(expected = MissingRequiredParameterException.class)
	public void testAddUser1() {

		String firstName = null;
		String lastName = null;
		String email = null;
		userService.addUser(firstName, lastName, email);
	}

	@Test(expected = MissingRequiredParameterException.class)
	public void testAddUser2() {

		String firstName = "Test User First Name";
		String lastName = null;
		String email = null;
		userService.addUser(firstName, lastName, email);

	}

	@Test(expected = MissingRequiredParameterException.class)
	public void testAddUser3() {

		String firstName = "Test User First Name";
		String lastName = "Test User Last Name";
		String email = null;
		userService.addUser(firstName, lastName, email);

	}

	@Test(expected = MissingRequiredParameterException.class)
	public void testAddUser4() {

		String firstName = "";
		String lastName = null;
		String email = null;
		userService.addUser(firstName, lastName, email);
	}

	@Test(expected = MissingRequiredParameterException.class)
	public void testAddUser5() {

		String firstName = "Test User First Name";
		String lastName = "";
		String email = null;
		userService.addUser(firstName, lastName, email);

	}

	@Test(expected = MissingRequiredParameterException.class)
	public void testAddUser6() {

		String firstName = "Test User First Name";
		String lastName = "Test User Last Name";
		String email = "";
		userService.addUser(firstName, lastName, email);

	}

	@Test(expected = InvalidParameterException.class)
	public void testAddUser7() {

		String firstName = "Test User First Name 123";
		String lastName = "Test User Last Name";
		String email = "testUser@testDomain.com";
		userService.addUser(firstName, lastName, email);
	}

	@Test(expected = InvalidParameterException.class)
	public void testAddUser8() {

		String firstName = "Test User First Name";
		String lastName = "Test User Last Name 123";
		String email = "testUser@testDomain.com";
		userService.addUser(firstName, lastName, email);

	}

	@Test(expected = InvalidParameterException.class)
	public void testAddUser9() {

		String firstName = "Test User First Name";
		String lastName = "Test User Last Name";
		String email = "123TestEmail.com";
		userService.addUser(firstName, lastName, email);
	}

	@Test(expected = DuplicateDataException.class)
	public void testAddUser10() {

		String firstName = "Test User First Name";
		String lastName = "Test User Last Name";
		String email = "testUser@testDomain.com";
		List<Map<String, Object>> user = new ArrayList<Map<String, Object>>();

		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("firstName", "Test User First Name");
		userMap.put("lastName", "Test User Last Name");
		userMap.put("email", "testUser@testDomain.com");

		user.add(userMap);

		expect(userDao.getUserByEmail(isA(String.class))).andReturn(user);

		replay(userDao);

		userService.addUser(firstName, lastName, email);

	}

	@Test(expected = MissingRequiredParameterException.class)
	public void testgetUser1() {

		String searchString = null;
		userService.getUser(searchString);

	}

}