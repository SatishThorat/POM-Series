package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil 
{
	private WebDriver driver;
	private JavaScriptUtils jsUtils;
	public ElementUtil(WebDriver driver)
	{
		this.driver=driver;
		jsUtils=new JavaScriptUtils(driver);
	}
	
	public  WebElement getElement (By locator) // for single web element
	{
		WebElement element = driver.findElement(locator);
		
		if (Boolean.parseBoolean(DriverFactory.highlight))
		{
			jsUtils.flash(element);
		}
		
		return element;
	}
	
	public List<WebElement> getElements(By locator) // for multiple web elements
	{
		return driver.findElements(locator);
	}
	
	public  void doSendKeys(By locator, String value) 
	{
		getElement(locator).sendKeys(value);
	}
	
	public  void DoClick(By locator) 
	{
		getElement(locator).click();
	}
	
	public  String  doGetText(By locator)
	{
		return getElement(locator).getText();
	}
	
	
	
	public  List<String> getElementTextList(By loacator) // for the FOR loop
	{
		List<String> eletextList=new ArrayList<String>();
		List<WebElement> elements= getElements(loacator);
		for(WebElement e: elements)
		{
			eletextList.add(e.getText());
		}
		return eletextList;
	}

 /******************************** DropDown Utils ************************************************/
	public void doSelectDropDownByIndex(By locator, int index)  // for Index 
	{
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	public void doSelectDropDownByVisibleText(By locator, String visibletext) // for Visible Text
	{
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibletext);
	}
	
	public void doSelectDropDownByValue(By locator, String Value) // for Value in the attribute value
	{
		Select select = new Select(getElement(locator));
		select.selectByValue(Value);
	}
	
	public void doSelectDropDownValue(By locator, String value) // IQ - Select drop down without using index/ visible text and value 
	                                                            // include get element generic method
	{
		Select select =new Select(getElement(locator));
		List <WebElement> optionList = select.getOptions();
		System.out.println(optionList.size());
		
		for(WebElement e: optionList)
		{
			String text = e.getText();
			if (text.equals(value))
			{
				e.click();
				break;
			}
		}
	}
	
	public void doSelectDropDownWithoutSelect(By locator, String value) // select drop down without using the Select class
	{
		
		List<WebElement> list = driver.findElements(locator);
		
		for(WebElement e : list)
		{
			if(e.getText().equals(value))
			{
				e.click();
				break;
			}
		}
		
	}
	
	/***************************************************** WAIT UTILS  **********************************************************/
	public Alert waitForAlertPresent(int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void getAlertText(int timeouts) 
	{
		waitForAlertPresent(timeouts).getText();
	}
	
	public void acceptAlert(int timeout) 
	{
		waitForAlertPresent(timeout).accept();
	}
	
	public void dismissAlert(int timeout) 
	{
		waitForAlertPresent(timeout).dismiss();
	}

	
	public  WebElement waitForElementPresent(By locator, int timeouts)
	{
		 WebDriverWait wait = new WebDriverWait(driver, timeouts);
		 return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public  WebElement waitForElementVisible(By locator, int timeouts)
	{
		 WebDriverWait wait = new WebDriverWait(driver, timeouts);
		 return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	
}
