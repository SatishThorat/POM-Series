package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.basetest.BaseTest;

public class ProductInfoPageTest extends BaseTest
{
	@BeforeClass
	public void productInfoSetup()
	{
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@DataProvider 
	public Object[][] searchData()
	{
		return new Object[][] {{"Macbook"},{"iMac"}};
	}
	@Test(dataProvider = "searchData")
	public void productCountTest(String productname)
	{
		searchResultPage= accPage.doSearch(productname);
		Assert.assertTrue(searchResultPage.getProductResultsCount()==1);
	}
	
	@Test
	public void productInfoHederTest()
	{
		searchResultPage = accPage.doSearch("imac");
		productInfoPage = searchResultPage.selectProductFromResults("imac");
		Assert.assertEquals(productInfoPage.getProductHeaderText(),"imac");
	}
	
	@Test
	public void productImagesTest()
	{
		searchResultPage = accPage.doSearch("Macbook");
		productInfoPage = searchResultPage.selectProductFromResults("Macbook Pro");
		//Assert.assertTrue(productInfoPage.getProductImagesCount() == 4);
	}
	

}