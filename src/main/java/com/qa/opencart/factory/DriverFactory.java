package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Satish Thorat
 * this method return the driver
 */
public class DriverFactory 
{
		WebDriver driver;
		Properties prop;
		private  OptionsManager optionsManager;
		
		public static String highlight = null;
		
		public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
		
	
		
		public WebDriver init_driver (Properties prop)
		{
			highlight = prop.getProperty("highlight");
			optionsManager =new OptionsManager(prop);
			String browsername = prop.getProperty("browser").trim();
			System.out.println("browsername is:"+browsername);
			
			if(browsername.equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				//driver= new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
			
			else if (browsername.equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
				//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			}
			
			else if (browsername.equalsIgnoreCase("edge"))
			{
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}
			
			else {
				System.out.println("please provide correct browsername:" + browsername);
			}
			
			getDriver().manage().window().maximize();
			getDriver().manage().deleteAllCookies();
			getDriver().get(prop.getProperty("url").trim());
			
			return getDriver(); 
			
		}
		
		public static synchronized WebDriver getDriver()
		{
			return tlDriver.get();
		}
		
		public Properties init_prop()
		{
			FileInputStream io =null;
			prop = new Properties();
			String env = System.getProperty("env");
			System.out.println("running environment----->" +env);
			
			if (env==null)
			{
				try {
					io=new FileInputStream("src/test/resources/config/config.properties");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else
			{
				try {
					switch (env) 
					{
					case "qa":
						io=new FileInputStream("./src/test/resources/config/qa.config.properties");
						break;

					case "stage":
						io=new FileInputStream("./src/test/resources/config/stage.config.properties");
						break;

					case "dev":
						io=new FileInputStream("./src/test/resources/config/dev.config.properties");
						break;

					default:
						break;
					}
				} 
				
				catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}
			}
			
			try {
				
				prop.load(io);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return prop;
		}
		
		/**
		 * TAKE SCREEN SHOTS
		 * @return 
		 */
		
		public String getScreenshot()
		{
			File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
			File destination = new File(path);
			
			try {
				FileUtils.copyFile(src, destination);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return path;
			
		}

}
