package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import com.sun.org.apache.bcel.internal.generic.RETURN;

public class Registration 
{
	ElementUtil elementUtil;
	private By firstname = By.name("firstname");
	private By lastname = By.name("lastname");
	private By emailId = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpass = By.name("confirm");
	private By subscribeyes = By.xpath("//label[@class='radio-inline'] [position()=1]/input");
	private By subscribeno = By.xpath("//label[@class='radio-inline'] [position()=2]/input");
	private By agree = By.name("agree");
	private By continuebutton = By.xpath("//input[@type='submit']");
	private By succesmsg = By.cssSelector("div#content h1");
	
	private By logoutlink = By.linkText("Logout");
	private By register = By.linkText("Register");
	
	public Registration(WebDriver driver)
	{
		elementUtil=new ElementUtil(driver);
	}
	
	
	public boolean AccountRegistration(String firstname, String lastname, String emailId, String telephone, String password, String subscribe) 
	{
		elementUtil.doSendKeys(this.firstname, firstname);
		elementUtil.doSendKeys(this.lastname, lastname);
		elementUtil.doSendKeys(this.emailId, emailId);
		elementUtil.doSendKeys(this.telephone, telephone);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.confirmpass, password);
		
		if (subscribeyes.equals("yes"))
		{
			elementUtil.DoClick(subscribeyes);
		}
		else 
		{
			elementUtil.DoClick(subscribeno);
		}
		
		elementUtil.DoClick(agree);
		elementUtil.DoClick(continuebutton);
		
		
		String msg = elementUtil.waitForElementVisible(succesmsg, 5).getText();
		System.out.println("account creation:" +msg);
		if(msg.contains(Constants.REGISTER_SUCCESS_MSG))
		{
			elementUtil.DoClick(logoutlink);
			elementUtil.DoClick(register);
			return true;
		}
		
		return false;
	}
	
	 

}
