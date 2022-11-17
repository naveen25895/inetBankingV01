package com.inetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.XLUtilis;

public class TC_LoginDDT_002 extends BaseClass{

	@Test(dataProvider = "LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException
	{	
		driver.get(baseUrl);
		logger.info("URL is Opened"); 
		
		LoginPage lp= new LoginPage(driver);
		lp.setUserName(user);
		logger.info("user name is provided");
		
		lp.setPassword(pwd);
		logger.info("password provided");

		lp.clickSubmit();
		
		if(isAlertPresent() == true)
		{
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login failed");
			Thread.sleep(3000);
		}
		else
		{
			Assert.assertTrue(true);
			logger.info("Test passed");
			lp.clicklogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();//close logout alert
			driver.switchTo().defaultContent();
		}
	}
	
	
	public boolean isAlertPresent()//user defined method to check allert is present or not
	{
		try
		{
			driver.switchTo().alert();
			return true;	
		}catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		String path = System.getProperty("user.dir")+"/src/test/java/com/inetBanking/testData/LoginData.xlsx";
		
		int rownum = XLUtilis.getRowCount(path, "Sheet1");
		int colcount = XLUtilis.getCellCount(path, "Sheet1", 1);
		
		String logindata[][] = new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++) 
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j] = XLUtilis.getCellData(path, "Sheet1", i, j);
//				System.out.println(logindata[i-1][j]);
			}
		}
		return logindata;
	}
}
