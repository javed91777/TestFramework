package com.framework.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.KlovReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.framework.utilities.ReadExcelSheet;


public class TestBase {
	
	public static WebDriver driver;
	public static ReadExcelSheet data; 
	public static Logger log = Logger.getLogger("TestBase");
	public static ExtentReports extent;
	public static ExtentTest test;
	public String buildName;
	
	@BeforeClass
	@Parameters({"ReportName","ProjectName","buildName","browserName","environment"})
	public void GeneratingReportLogs(String ReportName, String ProjectName, String buildName, 
			String browserName, String environment) throws IOException{
		try{
			log.info("Configuration of reporting logs ..........");
			this.buildName = buildName;
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/CustomReports/Report.html");
			// report or build name
			htmlReporter.config().setReportName(buildName);
			// chart location - top, bottom
			htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
			// theme - standard, dark
			htmlReporter.config().setTheme(Theme.STANDARD);
			// set timeStamp format
			htmlReporter.config().setTimeStampFormat("dd/mm/yyyy hh:mm:ss a");
			extent = new ExtentReports();
			//extent.attachReporter(config_klov_report("localhost",27017,ProjectName,ReportName,"http://localhost"));
			extent.attachReporter(htmlReporter);
			config_Log4j();	
			
			
			//Browser Initialization
			BrowserType.browserName(browserName);
		    extent.setSystemInfo("BrowserName", browserName);
		    
		    //EnvironMent initialization
		    EnvironmentType.environmentMode(environment);
		    extent.setSystemInfo("Environment", environment);
		    extent.setSystemInfo("Build", buildName);
		
			//TestData Configuration  
		    data = new ReadExcelSheet(System.getProperty("user.dir")+"/src/main/resources/TestData.xlsx");

	
		}
		catch(Throwable t) 
	    {	
			  log.info(t.getLocalizedMessage());
		      Error e1 = new Error(t.getMessage()); 
		      log.error("Configuration failed........" +t.getLocalizedMessage());
		      log.error(t.getMessage());
		      e1.setStackTrace(t.getStackTrace()); 
		      throw e1;
	   }	
	
	}
	
	
	public void config_Log4j() {
		try {
			//Configuring properties of Log4j
			Properties prop = new Properties();
			FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/Log4j.properties");
			prop.load(file);
			PropertyConfigurator.configure(prop);
			log.info("Configuration of reporting logs successfully..........");
        } 
		
		catch (FileNotFoundException Ex) {
            log.info("File not found: " + Ex.getMessage());
        } 
		catch (IOException Ex) {
            log.info("Exception occurred: " + Ex.getMessage());
        }
	}
	
	
	public KlovReporter config_klov_report(String host, int port, String projectName,
			String ReportName, String klov_url) {
		try {
			KlovReporter klovReporter = new KlovReporter();
			klovReporter.initMongoDbConnection(host, port);
			klovReporter.setProjectName(projectName);
			klovReporter.setReportName(ReportName);
			klovReporter.setKlovUrl(klov_url);
			return klovReporter;
		}
		catch (Exception Ex) {
            log.info("Exception occurred: " + Ex.getMessage());
            return null;
        }
		
	
	}
	
	@AfterMethod
	 public void getResult(ITestResult result) throws Exception
	    {
	        if (result.getStatus() == ITestResult.FAILURE)
	        {
	        	test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test Case FAILED", ExtentColor.RED));
	            String screenShotPath = CommonMethod.takeScreenShot(driver);
	            test.fail(result.getThrowable());
	            test.fail("Snapshot below: " + test.addScreenCaptureFromPath(screenShotPath));
	        }
	        else if(result.getStatus() == ITestResult.SUCCESS)
	        {
	            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
	        }
	        else
	        {
	            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
	            test.skip(result.getThrowable());
	        }
	        extent.flush();
	    }
	 
	@AfterClass
	public void tearDown(){
		
		driver.close();
		
	}

	
	
	
	
	
	
	
	
	
	
}
