package com.qa.opencart.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.basetest.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Error;

public class AccountsPageTest extends BaseTest
{
	WebDriver driver;
	@BeforeClass
	public void accPageSetup()
	{
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test
	public void accPageTitleTest()
	{
		String title = accPage.getAccPageTitle();
		System.out.println("Page Title is :" + title);
		Assert.assertEquals(title, Constants.ACCOUNTS_PAGE_TITLE, Error.ACC_PAGE_TITLE_ERROR);
	}
	
	@Test
	public void accPageHeaderTest()
	{
		String header = accPage.getAccPageHeader();
		System.out.println(header);
		Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER, Error.ACC_PAGE_HEADER_ERROR);
	}
	
	@Test
	public void logoutLinkTest()
	{
		boolean flag = accPage.isLogoutExist();
		System.out.println(flag);
		Assert.assertTrue(accPage.isLogoutExist());
	}
	
	@Test
	public void getPageURLTest()
	{
		String pageURL = accPage.getPageUrl();
		System.out.println(pageURL);
	}
}
