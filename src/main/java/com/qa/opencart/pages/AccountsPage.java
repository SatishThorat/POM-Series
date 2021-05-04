package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class AccountsPage
{
	public WebDriver driver;
	private ElementUtil elementUtil;
	
	
	private By header = By.cssSelector("div#logo a");
	private By logoutButton = By.linkText("Logout");
	private By searchField = By.name("search");
	private By searchButton = By.cssSelector("div#search button");

	public AccountsPage(WebDriver driver)
	{
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getAccPageTitle()
	{
	return  driver.getTitle();
	}
	
	public String getPageUrl()
	{
		return driver.getCurrentUrl();
	}
	
	public  String getAccPageHeader()
	{
		return elementUtil.doGetText(header);
		
	}
	
	public boolean isLogoutExist()
	{
		return driver.findElement(logoutButton).isDisplayed();
	}
	
	// Search Field - text box
	
	public SearchResultPage doSearch(String productname)
	{
		System.out.println("searching the product:" +productname);
		elementUtil.doSendKeys(searchField, productname);
		elementUtil.DoClick(searchButton);
		return new SearchResultPage(driver);
	}
	
	
}
