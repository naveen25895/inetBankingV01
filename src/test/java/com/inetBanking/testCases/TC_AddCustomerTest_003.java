package com.inetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.AddCusttomerPage;
import com.inetBanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass {
	
	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		driver.get(baseUrl);
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(userName);
		lp.setPassword(password);
		lp.clickSubmit();
		
		AddCusttomerPage addcust = new AddCusttomerPage(driver);
		
		addcust.clickAddNewCustomer();
		
		logger.info("Providing Customer Details ...");
		
		addcust.custname(randomstring());
		addcust.custgender("M");
		addcust.custdob("25", "08", "1995");
		Thread.sleep(3000);
		addcust.custaddress("India");
		addcust.custstate("Karnataka");
		addcust.custcity("Bengaluru");
		addcust.custpinnpo(560091);
		
		String phno = randomNum();
		addcust.custtelephoneno(phno);
		
		String email = randomstring()+"@gmail.com";
		addcust.custemailid(email);
		
		addcust.custpassword("gfhjkl");
		addcust.custsubmit();
		
		Thread.sleep(3000);
		
		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("Test Case3 is passed");
		}
		else
		{
			captureScreen(driver, "addNewCustomer");
			Assert.assertTrue(false);
			logger.info("Test Case3 failed");
		}
	}
	

	
}
