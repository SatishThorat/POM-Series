package com.qa.opencart.tests;

import java.security.PublicKey;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.basetest.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtils;

public class RegistrationPageTest extends BaseTest
{
	@BeforeClass
	public void setupRegister() {
		registration = loginPage.navigateToRegister();
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		Object regData[][] = ExcelUtils.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	public  String getRandomNumber()
	{
		Random randomnumber = new Random();
		String email = "testautomation" +randomnumber.nextInt(1000)+"@gmail.com";
		return email;

	}
	
	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstName, String lastName, 
									String telephone, 
									String password, String subscribe)
	{
		
		Assert.assertTrue (registration.AccountRegistration(firstName, lastName,getRandomNumber(), telephone, password, subscribe));
	}
	
	
}
