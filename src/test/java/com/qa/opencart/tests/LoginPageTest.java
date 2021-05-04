package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.basetest.BaseTest;
import com.qa.opencart.utils.Constants;

public class LoginPageTest extends BaseTest 
{
	
	
	@Test(priority = 1)
	public void loginpageTitleTest()
	{
		String title = loginPage.getpageTitle();
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public  void loginpageURLTest() 
	{
		String url = loginPage.getpageUrl();
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_URL));

	}
	
	@Test
	public void LoginTest()
	{
		loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	
	
	
	
	

}
