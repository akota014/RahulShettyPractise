package com.testing.tests;


import org.testng.annotations.Test;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;


import com.testing.BaseTest.BaseTest;
import com.testing.Utils.ExcelReader;
import com.testing.pages.LoginPage;

public class LoginTest extends BaseTest{
	
	 @Test
	    public void testLogin() {
	        System.out.println("Running Test");
	    }

	@Test(dataProvider = "loginData")
	public void loginWithUsernamePassword(String username, String password,
			String userType, String dropdownValue, String expected, String msg) {
		LoginPage lp = new LoginPage(driver);
		lp.enterUsernamePassword(username, password);
		if(!(userType==""||userType==null)) {
			lp.chooseAdminUser(userType);
		}
		if(!(dropdownValue==""||dropdownValue==null))lp.chooseRole(dropdownValue);
		
		lp.termsAndAgreement();
		lp.clickSigin();
		
		 if(expected.equalsIgnoreCase("success")) {
			 	lp.waitForNext();
	            Assert.assertTrue(driver.getCurrentUrl().contains("shop"));
	        }
	        else {
	            Assert.assertTrue(driver.getPageSource().contains("Incorrect"));
	            Assert.assertEquals(lp.verifyMsg(), msg);
	        }
	}
	
	
	
	@DataProvider(name="loginData")
	public Object[][] getdata() throws IOException{
		return ExcelReader.excelDataReader(1);
	}
	
	
}
