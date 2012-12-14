/**
 * 
 */
package com.ensco.junitintro.integrationtest.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author sharma.shriram
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/junitintro_context_test.xml" })
public class SeleniumFirefoxTest {

	@Autowired
	@Qualifier("firefoxDriver")
	private WebDriver driver;

	@Autowired
	private URI siteUri;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHomePage() {
		driver.get(siteUri.toString());
		// Page title test.
		assertEquals("Title match: ", "Introduction to JUnit.",
				driver.getTitle());
		// Proceed link presence test
		assertTrue("Proceed link: ", driver
				.findElements(By.linkText("Proceed")).size() > 0);
		// Proceed to add user page.
		driver.findElement(By.linkText("Proceed")).click();
		assertTrue("From home page to add user check: ", driver.getPageSource()
				.contains("Contacts"));
	}

	@Test
	public void testAddUserFormAndLayout() {
		driver.get(siteUri.toString());

		// Proceed to add user page.
		driver.findElement(By.linkText("Proceed")).click();
		assertTrue("At add user check: ",
				driver.getPageSource().contains("Contacts"));

		// Check if contacts form exists.
		assertTrue("Form check: ", driver.findElements(By.id("addUserForm"))
				.size() > 0);

		// Check if Add button exists.
		assertTrue("Add button check: ",
				driver.findElements(By.id("submitAddUserForm")).size() > 0);

		// Check if all required form elements and their labels exists.
		assertTrue("First Name label: ",
				driver.getPageSource().contains("First Name"));
		assertTrue("Last Name label: ",
				driver.getPageSource().contains("Last Name"));
		assertTrue("Email label: ", driver.getPageSource().contains("Email"));
		assertTrue("First Name textbox: ",
				driver.findElements(By.id("firstName")).size() > 0);
		assertTrue("Last Name textbox: ", driver
				.findElements(By.id("lastName")).size() > 0);
		assertTrue("Email textbox: ", driver.findElements(By.id("email"))
				.size() > 0);

		// Check if Search textbox and label exists.
		assertTrue("Search label: ", driver.getPageSource().contains("Search"));
		assertTrue("Search textbox: ",
				driver.findElements(By.id("searchString")).size() > 0);

		// Check if table exists.
		assertTrue("Contacts table: ",
				driver.findElements(By.id("contactsTable")).size() > 0);
		// Check table headings.
		assertTrue("First Name heading: ",
				driver.getPageSource().contains("<th>First Name</th>"));
		assertTrue("Last Name heading: ",
				driver.getPageSource().contains("<th>Last Name</th>"));
		assertTrue("Email heading: ",
				driver.getPageSource().contains("<th>Email</th>"));
	}

	@Test
	public void testAddUserFuntionality() throws InterruptedException {
		driver.get(siteUri.toString());

		// Proceed to add user page.
		driver.findElement(By.linkText("Proceed")).click();
		assertTrue("At add user check: ",
				driver.getPageSource().contains("Contacts"));

		// Submit empty form.
		driver.findElement(By.id("addUserForm")).submit();
		// Should show an error message.
		Alert alertBox = driver.switchTo().alert();
		assertEquals("Show error alert", "Error Occured".toLowerCase(),
				alertBox.getText().toLowerCase());
		alertBox.accept();

		// Fill form.
		driver.findElement(By.id("firstName")).sendKeys("John");
		driver.findElement(By.id("lastName")).sendKeys("Anderson");
		int randomNum = (int) ((Math.random() + 5) * 99);
		driver.findElement(By.id("email")).sendKeys(
				"John.Anderson" + randomNum + "@yahoo.com");
		driver.findElement(By.id("addUserForm")).submit();

		// No other way but to put to sleep.
		Thread.sleep(1000);

		// Check if added successfully.
		assertTrue(
				"John has been added",
				driver.getPageSource().contains(
						"<td>John.Anderson" + randomNum + "@yahoo.com</td>"));

	}

	@Test
	public void testSearchFuntionality() throws InterruptedException {
		driver.get(siteUri.toString());

		// Proceed to add user page.
		driver.findElement(By.linkText("Proceed")).click();
		assertTrue("At add user check: ",
				driver.getPageSource().contains("Contacts"));

		// Fill form.
		int randomNum = (int) ((Math.random() + 5) * 99);
		driver.findElement(By.id("firstName")).sendKeys("Jim");
		driver.findElement(By.id("lastName")).sendKeys("Hendricks");
		driver.findElement(By.id("email")).sendKeys(
				"Jim.Hendricks" + randomNum + "@yahoo.com");
		driver.findElement(By.id("addUserForm")).submit();

		// No other way but to put to sleep.
		Thread.sleep(1000);

		// Check if added successfully.
		assertTrue(
				"Jim has been added",
				driver.getPageSource().contains(
						"<td>Jim.Hendricks" + randomNum + "@yahoo.com</td>"));

		// Do a search
		driver.findElement(By.id("searchString")).sendKeys(
				"Jim.Hendricks" + randomNum);

		// Check search result
		assertTrue(
				"Jim found",
				driver.getPageSource().contains(
						"<td>Jim.Hendricks" + randomNum + "@yahoo.com</td>"));

		// Double Check search result
		assertFalse("Searching for John",
				driver.getPageSource().contains("<td>John</td>"));

	}
}
