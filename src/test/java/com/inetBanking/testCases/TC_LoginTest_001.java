package com.inetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass{

	@Test
	public void logintest() throws IOException
	{	

		driver.get(baseUrl);
		logger.info("URL is Opened");
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(userName);
		logger.info("Entered UserName");

		lp.setPassword(password);
		logger.info("Entered Password");

		lp.clickSubmit();
		
		if(driver.getTitle().equalsIgnoreCase("Guru99 Bank Manager HomePage")) 
		{
			Assert.assertTrue(true);
			logger.info("Login Test Passed");

		}else
		{	
			captureScreen(driver, "logintest");
			Assert.assertTrue(false);
			logger.info("Login Test Failed");

		}
			
			
	}
}
