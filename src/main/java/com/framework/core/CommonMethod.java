package com.framework.core;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.aventstack.extentreports.Status;


public class CommonMethod extends TestBase {
	
	public static String error_message = "Due to Element is not visible or disabled on the webPage."
			+ " Please check Element Locator once";
	
	
	/**
	 * Return WebElement
	 * @param driver
	 * @param elementBy
	 * @return
	 */
	public static WebElement findElement(WebDriver driver,By elementBy){
		return driver.findElement(elementBy);	
	}
	
	public void goBack() {
		driver.navigate().back();
		log.info("Naviagte to back");
	}

	public void goForward() {
		driver.navigate().forward();
		log.info("Navigate to forward");
	}

	public void refresh() {
		driver.navigate().refresh();
		log.info("Refreshing browser");
	}

		/** 
	 * Clear Method
	 * @param driver
	 * @param elementBy
	 * @throws IOException 
	 */
	public static void clear(WebDriver driver,By elementBy,String StepDescription) throws IOException{
		
		if(CommonMethod.isElementPresent(driver, elementBy)){
			findElement(driver, elementBy).clear();
			 log.info(StepDescription+" "+elementBy);
			 test.log(Status.PASS, StepDescription);
		}
	
		else{
			log.error(StepDescription+" Failed" +error_message+" "+elementBy);
			 throw new RuntimeException(StepDescription + " Failed "+
					 error_message +elementBy);
		}
	}
	
	/**
	 * SendKeys Method
	 * @param driver
	 * @param elementBy
	 * @throws IOException 
	 */
	public static void click(WebDriver driver,By elementBy,String StepDescription) throws IOException{
			if(CommonMethod.isElementPresent(driver, elementBy)){
				findElement(driver, elementBy).click();
				log.info(StepDescription+" "+elementBy);
				test.log(Status.PASS, StepDescription);

			}
		
			else{
				log.error(StepDescription+" Failed " +error_message+" "+elementBy);
				throw new RuntimeException(StepDescription + " Failed "+error_message +elementBy);
			}
	}
	
	/**
	 * SendKeys method
	 * @param driver
	 * @param elementBy
	 * @param data
	 * @throws IOException 
	 */
	public static void sendKeys(WebDriver driver,By elementBy,String data,String StepDescription) throws IOException{
		if(CommonMethod.isElementPresent(driver, elementBy)){
			findElement(driver, elementBy).sendKeys(data);
			log.info(StepDescription+" "+elementBy);
			test.log(Status.PASS, StepDescription);
		}
		else{
			
			log.error(StepDescription+" Failed " +error_message+" "+elementBy);
			 throw new RuntimeException(StepDescription + " Failed "+
					 error_message +elementBy);
		}

	}
	
	/**
	 * GetText Method
	 * @param driver
	 * @param elementBy
	 * @return
	 * @throws IOException 
	 */
	public static String getText(WebDriver driver,By elementBy, String StepDescription) throws IOException{
		
		String out = null;

		if(CommonMethod.isElementPresent(driver, elementBy)){
			out =findElement(driver, elementBy).getText();
			log.info(StepDescription+" "+elementBy);
			test.log(Status.PASS, StepDescription);
		}
		else{
			
			log.error(StepDescription+" Failed " +error_message+" "+elementBy);
			 throw new RuntimeException(StepDescription + " Failed "+
					 error_message +elementBy);
		}
		return out;
		
	}
	
	/**
	 * Select drop down
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * @throws IOException 
	 * 
	 */
	public static void selectDropdownByText(WebDriver driver, By elementBy, String value,String StepDescription) throws IOException{
		
		if(CommonMethod.isElementPresent(driver, elementBy)){
			new Select (findElement(driver,elementBy)).selectByVisibleText(value); 
			log.info(StepDescription+" "+elementBy);
			test.log(Status.PASS, StepDescription);

		}
		else{
			
			log.error(StepDescription+" Failed " +error_message+" "+elementBy);
			 throw new RuntimeException(StepDescription + " Failed "+
					 error_message +elementBy);

		}
			
	}
	/**
	 * selectDropdownByIndex
	 * @param driver
	 * @param elementBy
	 * @param index
	 * @param StepDescription
	 * @throws IOException
	 */
	public static void selectDropdownByIndex(WebDriver driver, By elementBy, int index,String StepDescription) throws IOException{
			
			if(CommonMethod.isElementPresent(driver, elementBy)){
				new Select (findElement(driver,elementBy)).selectByIndex(index); 
				log.info(StepDescription+" "+elementBy);
				test.log(Status.PASS, StepDescription);
	
			}
			else{
				log.error(StepDescription+" Failed" +error_message+" "+elementBy);
				 throw new RuntimeException(StepDescription + " Failed "+
						 error_message +elementBy);
			}
				
		}

	/**
	 * selectDropdownByValue
	 * @param driver
	 * @param elementBy
	 * @param value
	 * @param StepDescription
	 * @throws IOException
	 */
	public static void selectDropdownByValue(WebDriver driver, By elementBy, String value,String StepDescription) throws IOException{
		
		if(CommonMethod.isElementPresent(driver, elementBy)){
			new Select (findElement(driver,elementBy)).selectByValue(value); 
			log.info(StepDescription+" "+elementBy);
			test.log(Status.PASS, StepDescription);
	
		}
		else{
			log.error(StepDescription+" Failed" +error_message+" "+elementBy);
			 throw new RuntimeException(StepDescription + " Failed "+
					 error_message +elementBy);
		}
			
	}

	/**
	 * getAllDropDownValues
	 * @param locator
	 * @return
	 */
	public List<String> getAllDropDownValues(WebElement locator) {
		Select select = new Select(locator);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		
		for (WebElement element : elementList) {
			log.info(element.getText());
			valueList.add(element.getText());
		}
		return valueList;
	}
	
	/**
	 * getSelectedValue
	 * @param element
	 * @return
	 */
	public String getSelectedValue(WebElement element) {
		String value = new Select(element).getFirstSelectedOption().getText();
		log.info("WebELement : " + element + " Value : "+ value);
		return value;
	}

	
	
	public Set<String> getWindowHandlens() {
		log.info("");
		return driver.getWindowHandles();
	}
	
	/**
	 * SwitchToWindow
	 * @param index
	 */
	public void SwitchToWindow(int index) {

		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());

		if (index < 0 || index > windowsId.size()){
			throw new IllegalArgumentException("Invalid Index : " + index);
		}
		driver.switchTo().window(windowsId.get(index));
		log.info(index);
	}
	
	
	/**
	 * Handling New Tab from Parent
	 * @param driver
	 */
	public static void handleNewTab(WebDriver driver)
	{
		Set<String> allWindowHandles = driver.getWindowHandles();
		Iterator<String> iter=allWindowHandles.iterator();
		int size=allWindowHandles.size();
		String child = null;
		for(int i=0;i<size;i++){
			child=iter.next();
		}
		
		driver.switchTo().window(child);
	}
	
	/**
	 * Handling Parent tab
	 * @param driver
	 */
	public static void handleParentTab(WebDriver driver){
		
		Set<String> allWindowHandles = driver.getWindowHandles();
		String parent = (String) allWindowHandles.toArray()[0];
		driver.switchTo().window(parent);

	}
	/**
	 * Handling childParent tab
	 * @param driver
	 */
	public static void handleChildParentTab(WebDriver driver){
		
		Set<String> allWindowHandles = driver.getWindowHandles();
		String window1 = (String) allWindowHandles.toArray()[1];
		driver.switchTo().window(window1);

	}
	
	/**
	 * For getting get attribute value from field
	 * @param driver
	 * @param objectLocater
	 * @return
	 * @throws IOException
	 */
	public static String getValue(WebDriver driver,By elementBy,String StepDescription) throws IOException{
		String out =null;
		if(CommonMethod.isElementPresent(driver, elementBy)){
			out=findElement(driver, elementBy).getAttribute("value");
			log.info(StepDescription+" "+elementBy);
			test.log(Status.PASS, StepDescription);
		}
		else{
			
			log.error(StepDescription+" Failed " +error_message+" "+elementBy);
			 throw new RuntimeException(StepDescription + " Failed "+
					 error_message +elementBy);
		}
		return out;
	}

	/**
	 * Explicit wait for particular object locator
	 * @param driver
	 * @param timeOutInSeconds
	 * @param objectLocater
	 * @throws IOException
	 */
	public static void explicitWait(WebDriver driver,int timeOutInSeconds,By elementBy) throws IOException{
		WebDriverWait wait=new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(elementBy));
		}
	
	/**
	 * Handling alerts and popups
	 * @param WebDriver
	 * @return
	 */
	public static void acceptAlert(final WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();

			
		} catch (Exception e) {
			
		}
	}
	/**
	 * Handling cancel alert
	 * @param driver
	 * @return
	 */
	public static String cancelAlert(final WebDriver driver) {
		String message = null;
		try {
			Alert alert = driver.switchTo().alert();

			message = alert.getText();
			alert.dismiss();
			
		} catch (Exception e) {
		  message = null;
		}

		return message;
	}
	
	
	public Alert getAlert(final WebDriver driver) {
		return driver.switchTo().alert();
	}
	
	/**
	 * isAlertPresent
	 * @param driver
	 * @return
	 */
	public boolean isAlertPresent(final WebDriver driver) {
		try {
			driver.switchTo().alert();
			log.info("true");
			return true;
		} catch (NoAlertPresentException e) {
			// Ignore
			log.info("false");
			return false;
		}
	}
    /**
     * AcceptAlertIfPresent
     * @param driver
     */
	public void AcceptAlertIfPresent(final WebDriver driver) {
		if (!isAlertPresent(driver))
			return;
		acceptAlert(driver);
		log.info("Accepting Alert");
	}

	/**
	 *DismissAlertIfPresent 
	 * @param driver
	 */
	public void DismissAlertIfPresent(final WebDriver driver) {

		if (!isAlertPresent(driver))
			return;
		cancelAlert(driver);
		log.info("Dismiss Alert");
	}
	
	/**
	 * AcceptPrompt
	 * @param driver
	 * @param text
	 */
	public void AcceptPrompt(final WebDriver driver, String text) {
		
		if (!isAlertPresent(driver))
			return;
		
		Alert alert = getAlert(driver);
		alert.sendKeys(text);
		alert.accept();
		log.info(text);
	}
	
	/**
	 * Compact way to verify if an element is on the page 
	 * @param driver
	 * @throws IOException 
	 */
	public static boolean isElementPresent(final WebDriver driver, By elementBy) throws IOException  {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		  
			if(findElements(driver,elementBy).size()>0){
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				return true;
				
			}
			else{
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				return false;
			}	   		
	}
	
	/**
	 * List Web elements 
	 * @param driver
	 * @param object
	 * @return
	 * @throws IOException 
	 */
	public static List<WebElement> findElements(final WebDriver driver,By elementBy) throws IOException{
		  
			return driver.findElements(elementBy);

 			}
		
	/**
	 * Upload file
	 * @param driver
	 * @param value
	 * @throws IOException 
	 * 
	 */
	public static void uploadFile(WebDriver driver, By elementBy, String path,String StepDescription ) throws IOException{
		
		if(CommonMethod.isElementPresent(driver, elementBy)){
			findElement(driver,elementBy).sendKeys(path);
			log.info(StepDescription+" "+elementBy);
			test.log(Status.PASS, StepDescription);

		}
		else{
			log.error(StepDescription+" Failed " +error_message+" "+elementBy);
			 throw new RuntimeException(StepDescription + " Failed "+
					 error_message +elementBy);
		}
	}
	/**
	 * Handle Mouse Hover
	 * @param driver
	 * @param elementBy
	 */
	public static void handleMouseHover(WebDriver driver, By elementBy){
		
		Actions action = new Actions(driver);
		action.moveToElement(findElement(driver, elementBy)).build().perform();
		
	
	}
	
	/**
	 * Handle Drag And Drop
	 * @param driver
	 * @param sourceElement
	 * @param DestinationElement
	 */
   public static void handleDragAndDrop(WebDriver driver, By sourceElement,By DestinationElement ){
		
		Actions action = new Actions(driver);
		action.dragAndDrop(findElement(driver, sourceElement), findElement(driver, DestinationElement));
	}
  
   /**
    * Handle Frames
    * @param driver
    * @param elementBy
    */
   public static void handleFrames(WebDriver driver, By elementBy){
	   driver.switchTo().frame(findElement(driver, elementBy));
	      
   }
/***
 * Checking check box
 * @param driver
 * @param elementBy
 * @param StepDescription
 * @throws IOException
 */
   public static void checkCheckBox(WebDriver driver, By elementBy,String StepDescription ) throws IOException{
	   
	   //Check if the object is enabled, if yes click the same
   if (findElement(driver, elementBy).isDisplayed() && findElement(driver, elementBy).isEnabled()){
       //Check state of check box
       boolean isChecked = findElement(driver, elementBy).isSelected();

       //Check if Not Checked
       if(isChecked == false){
    	   findElement(driver, elementBy).click();
    	   log.info(StepDescription+" "+elementBy);
		   test.log(Status.PASS, StepDescription);
       }
   }
   else{
		
	   log.error(StepDescription+" Failed" +error_message+" "+elementBy);
		 throw new RuntimeException(StepDescription + " Failed"+
				 error_message +elementBy);
	}
   
  
   }
   /**
    * Uncheck Selected check box
    * @param driver
    * @param elementBy
    * @param StepDescription
    * @throws IOException
    */
    public static void unCheckCheckBox(WebDriver driver, By elementBy,String StepDescription ) throws IOException{
	   
	   //Check if the object is enabled, if yes click the same
       if (findElement(driver, elementBy).isDisplayed() && findElement(driver, elementBy).isEnabled()){
           //Check state of check box
           boolean isChecked = findElement(driver, elementBy).isSelected();

           //Check if Not Checked
           if(isChecked == true){
        	   findElement(driver, elementBy).click();
        	   log.info(StepDescription+" "+elementBy);
   			   test.log(Status.PASS, StepDescription);
           }  
       }
       else{
    	   log.error(StepDescription+" Failed " +error_message+" "+elementBy);
			 throw new RuntimeException(StepDescription + " Failed "+
					 error_message +elementBy);
		}
	      
   }
   /**
    * Taking ScreenShot
    * @param driver
    * @return
    * @throws IOException
    */
	public static  String  takeScreenShot(WebDriver driver) throws IOException{
		
		Date d = new Date();
		Timestamp t = new Timestamp(d.getTime());
		String timeStamp = t.toString();
		timeStamp = timeStamp.replace(' ', '_');
		timeStamp = timeStamp.replace(':', '_'); 
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"/Screenshots/"+timeStamp+".png"));
	    String path=System.getProperty("user.dir")+"/Screenshots/"+timeStamp+".png";
	    
		return path;		
		
	}
	
	 /**
	  * compared actual Text with the expected text
	  * @param driver
	  * @param actual
	  * @param expected
	  * @throws IOException
	  */
	public static void comparedWithExpectedText(WebDriver driver,By actual,String expectedValue) throws IOException{
			{
			
			if(findElement(driver, actual).getText().equals(expectedValue)){
				Assert.assertEquals(findElement(driver, actual).getText(), expectedValue);
			    test.log(Status.PASS, "Actual value"+"....."+findElement(driver, actual).getText()+ ""
			    		+ "..... is  matching with expected value...."+expectedValue+".......");
			}
			else{
			    test.log(Status.FAIL, "Actual value ...."+findElement(driver, actual).getText()+ ""
			    		+ "..... is not matching with expected value...."+expectedValue+".......");
	            throw(new RuntimeException("Actual value ...."+findElement(driver, actual).getText()+ ""
	            		+ "....is not matching with expected value...."+expectedValue+"......."));

			}
		}
		
	}
	
    /**
     * compared With Expected Element
     * @param driver
     * @param expected
     * @throws IOException
     */
	public static void comparedWithExpectedElement(WebDriver driver,By expected) throws IOException{
		try{
			
			if(findElements(driver, expected).size()>0){
				Assert.assertTrue(findElement(driver, expected).isDisplayed());
			    test.log(Status.PASS, "Expected value present in the webpage");
			}
			else{
			    test.log(Status.FAIL, "Expected value not present in the webpage");
	            throw(new RuntimeException("Expected value not present in the webpage"));

			}
		}
		catch(Throwable t){
			 log.info(t.getLocalizedMessage());
		      Error e1 = new Error(t.getMessage()); 
		      log.error("Expected value not present in the webpage"+t.getLocalizedMessage());
		      log.error(t.getMessage());
		      e1.setStackTrace(t.getStackTrace()); 
		      throw e1;
			
		}
}

	/**
	 * verifyElementPresent
	 * @param element
	 * @return
	 */
	public static synchronized boolean verifyElementPresent( WebElement element) {
		boolean isDispalyed = false;
		try {
			 isDispalyed= element.isDisplayed();
			 log.info(element.getText()+" is dispalyed");
		}
		catch(Exception ex) {
			log.error("Element not found " + ex);
		}
		
		return isDispalyed;
	}
	
	/**
	 * verifyElementNotPresent
	 * @param element
	 * @return
	 */
	public static synchronized boolean verifyElementNotPresent( WebElement element) {
		boolean isDispalyed = false;
		try {
			 element.isDisplayed();
			 log.info(element.getText()+" is dispalyed");
		}
		catch(Exception ex) {
			log.error("Element not found " + ex);
			isDispalyed = true;
		}
		
		return isDispalyed;
	}
	
	
	/**
	 * verifyTextEquals
	 * @param element
	 * @param expectedText
	 * @return
	 */
	public static synchronized boolean verifyTextEquals( WebElement element,String expectedText) {
		boolean flag = false;
		try {
			String actualText=element.getText();
			if(actualText.equals(expectedText)) {
				log.info("actualText is :"+actualText+" expected text is: "+expectedText);
				return flag=true;
			}
			else {
				log.error("actualText is :"+actualText+" expected text is: "+expectedText);
				return flag;
			}
		}
		catch(Exception ex) {
			log.error("actualText is :"+element.getText()+" expected text is: "+expectedText);
			log.info("text not matching" + ex);
			return flag;
		}
	}
	
}
 
	
	
	
	
	


