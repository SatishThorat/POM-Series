package com.qa.opencart.basetest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.Registration;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest 
{
	DriverFactory df;
	public WebDriver driver;
	public Properties prop;
	
	public LoginPage loginPage;
	public AccountsPage accPage;
	public SearchResultPage searchResultPage;
		
	public ProductInfoPage productInfoPage;
	public Registration registration;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browsername)
	{
		df=new DriverFactory();
		prop = df.init_prop();
		prop.setProperty("browser", browsername);
		driver = df.init_driver(prop);
		loginPage= new LoginPage(driver);
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
