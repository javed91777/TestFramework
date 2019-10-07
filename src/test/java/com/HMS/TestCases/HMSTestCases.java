package com.HMS.TestCases;

import java.io.IOException;
import org.testng.annotations.Test;
import com.HMS.PageObjects.HMSLoginPage;
import com.framework.core.TestBase;
public class HMSTestCases extends TestBase {
	
	HMSLoginPage loginpage = new HMSLoginPage();
	
	
	@Test(priority=1)
	public void HMSLogin() throws IOException{
		    test = extent.createTest("TC-001: HMS Login");
		    loginpage.generateDataForHMSLogin();
			loginpage.fillupDataForHMSLOgin(driver);
			loginpage.clickOnLoginButton(driver);
			loginpage.verifyLoginFunctionality(driver);
				
		 	}
	
	@Test(priority=2)
	public void HMSLogout() throws IOException{
		    test = extent.createTest("Tc-002: HMS Logout");
		    loginpage.clickOnLogoutButton();
		 	}
	
	
	
	
	
	
	
	
	
	
	

}
