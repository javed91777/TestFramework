package com.HMS.PageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.framework.core.CommonMethod;
import com.framework.core.TestBase;

public class HMSLoginPage extends TestBase {
	

	public By username =By.xpath("//input[@name='username']");
	public By password =By.xpath("//input[@name='password']");
	public By login=By.xpath("//input[@name='submit']");
	public By logout = By.xpath("//a[@href='logout.php']");

	
	
	public String username1;
	public String password1;
	

	public void generateDataForHMSLogin(){

		 username1=data.getCellData("Login", "Username", 2);
		 password1=data.getCellData("Login", "Password", 2);
	
	}
	
	
	public void fillupDataForHMSLOgin(WebDriver driver) throws IOException{
		
		CommonMethod.clear(driver, username,"Clearing username filed");
		CommonMethod.sendKeys(driver, username, username1,"passing data to the username filed");
		
		CommonMethod.clear(driver, password,"Clearing password filed");
		CommonMethod.sendKeys(driver, password, password1,"Passing data to password filed");
		
		
	}
	
	public void clickOnLoginButton(WebDriver driver) throws IOException{
		
		CommonMethod.click(driver, login,"Clicking on login button");

	}
	
	public void verifyLoginFunctionality(WebDriver driver) throws IOException {
		
		CommonMethod.comparedWithExpectedText(driver, By.xpath("//strong[contains(.,'About Application: ')]"),"About Application:");

	}
	
	
	
	public void clickOnLogoutButton() throws IOException {
		
		CommonMethod.click(driver, logout, "Clicking on Logout Button");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
