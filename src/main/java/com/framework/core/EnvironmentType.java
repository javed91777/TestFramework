package com.framework.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentType extends TestBase {

	 //Selecting Environment
	public static void environmentMode(String environment) throws IOException{
		log.info("Choosing Environment Mode");
		Properties config= new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/Environment.properties");
		config.load(file);
		log.info("Choosing Environment Mode successfully as......... "+environment);

	try{

		if(environment.equalsIgnoreCase("qa"))
		{
			System.out.println(config.getProperty("QA"));
			driver.get(config.getProperty("QA"));
			log.info("Navigated to URL : "+config.getProperty("QA"));
		}
		else if(environment.equalsIgnoreCase("Staging")){
			driver.get(config.getProperty("Staging"));
			log.info("Navigated to URL : "+config.getProperty("Staging"));

		}
		else if(environment.equalsIgnoreCase("Production")){
			driver.get(config.getProperty("Production"));
			log.info("Navigated to URL : "+config.getProperty("Production"));
		}
		else if(environment.equalsIgnoreCase("Dev")){
			driver.get(config.getProperty("Dev"));
			log.info("Navigated to URL : "+config.getProperty("Dev"));
		}
		else{
			throw new RuntimeException("Invalid Environment has configured");
		}
		  log.info("Choosing Environment Mode successfully as......... "+environment);

		}   
	catch(Throwable t) 
	    {	
			  log.info(t.getLocalizedMessage());
		      Error e1 = new Error(t.getMessage()); 
		      log.error("Navigating URL failed.........." +t.getLocalizedMessage());
		      log.error(t.getMessage());
		      e1.setStackTrace(t.getStackTrace()); 
		      throw e1;
	   }	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
