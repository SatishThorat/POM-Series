package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class LoginPage
{
	private WebDriver driver;
	private ElementUtil elementUtil;
	// By locators
	private By Username = By.id("input-email");
	private By password = By.name("password");
	private By loginbutton = By.xpath("//input[@type='submit']");
	private By searchExist = By.xpath("//input[@type='text']");
	
	private By registerlink = By.linkText("Register");
	
	// contructor
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
	}

	// public actions (methods)
	public String getpageTitle()
	{
		return driver.getTitle();
	}
	
	public String getpageUrl ()
	{
		return driver.getCurrentUrl();
	}
	
	public boolean isSearchExist()
	{
		return driver.findElement(searchExist).isDisplayed();
	}
	
	
	public AccountsPage doLogin(String un, String pwd)
	{
	  elementUtil.doSendKeys(Username, un);
	  elementUtil.doSendKeys(password, pwd);
	  elementUtil.DoClick(loginbutton);
	  
	  return new AccountsPage(driver);
	}
	
	public Registration navigateToRegister() 
	{
		elementUtil.DoClick(registerlink);
		return new Registration(driver);
	}
	
}