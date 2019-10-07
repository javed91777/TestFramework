package com.framework.utilities;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.testng.annotations.Test;
import org.xml.sax.SAXException;
public class SendReports

{
	 static List<String> filesListInDir = new ArrayList<String>();
	
		 	File filename=null;
		 	ZIPFile Zip = new ZIPFile();
		 	//ZIPFile Zip1 = new ZIPFile();
	

		
		public void zipAutomationReports() throws InterruptedException, ParserConfigurationException, IOException, SAXException{
			 filename = new File(System.getProperty("user.dir")+"/CustomReports");
			 Zip.zipDirectory(filename, "AutomationReports.zip");
			 System.out.println("Zipping of AutomationReports successfully");
			 Thread.sleep(3000);

		}

		@Test
		public void sendmail() throws ParserConfigurationException, SAXException, IOException
	
		{
			
			PropertyUtils prop = new PropertyUtils(System.getProperty("user.dir")+"\\src\\main\\resources\\email.properties");
			
			String username= prop.getValue("username");
			String password=prop.getValue("password");
			String to=prop.getValue("to");
			String cc=prop.getValue("bcc");
			String bcc=prop.getValue("cc");
			String Subject=prop.getValue("subject");
			
				  String[] toArray= to.split(",");
				  String[] ccArray={cc};
	              String[] bccArray={bcc};
	              SMTPUtility.sendMail(username,
	            		  password,
	              		            "smtp.gmail.com",
	              		            "465",
	              		          toArray,   
	              		          ccArray,
	              		          bccArray,
	              		            Subject,                		            
	              		            "PFA",System.getProperty("user.dir")+"/AutomationReports.zip","AutomationReports.zip");
	              System.out.println("Report has been sent through mail Successfully");
				}

		      public void Sharereports() {
		    	  try{
		    		  zipAutomationReports();
		  			  sendmail();
		 
		    	  }
		    	  catch(Throwable t) 
		  	    {	
		  		      Error e1 = new Error(t.getMessage()); 
		  		      e1.setStackTrace(t.getStackTrace()); 
		  		      throw e1;
		  	   }	
		    	  
		      }
      
      
      
     
			}
