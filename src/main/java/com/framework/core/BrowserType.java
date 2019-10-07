package com.framework.core;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class BrowserType extends TestBase {
	
	

	 public static void browserName(String browserName){
			
	   log.info("Choosing BrowserType");
	  
	   try{
		   if(browserName.equalsIgnoreCase("Firefox")){
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/DriverFiles/geckodriver.exe");
				driver = new FirefoxDriver();
				
			}
			else if(browserName.equalsIgnoreCase("chrome")){
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/DriverFiles/chromedriver.exe");
				driver = new ChromeDriver();
				
			}
			else if(browserName.equalsIgnoreCase("ie")){
			
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/DriverFiles/DriverFiles/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			else if(browserName.equalsIgnoreCase("opera")){
				
				System.setProperty("webdriver.opera.driver", System.getProperty("user.dir")+"/DriverFiles/DriverFiles/OperaDriver.exe");
				driver = new InternetExplorerDriver();
			}
		   
		   log.info("Choosing BrowserType successfully as........"+browserName);
			  driver.manage().window().maximize();

	   }  
		 catch(Throwable t) 
		    {	
				  log.info(t.getLocalizedMessage());
			      Error e1 = new Error(t.getMessage()); 
			      log.error("Choosing BrowserType type failed.....     " +t.getLocalizedMessage());
			      log.error(t.getMessage());
			      e1.setStackTrace(t.getStackTrace()); 
			      throw e1;
		   }	
		
		}
	
	
	
	
	

	}
