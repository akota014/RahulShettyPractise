package com.testing.BaseTest;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.testing.DriverFactory.DriveFactory;

public class BaseTest {
	
	public WebDriver driver;
	@Parameters({"browser", "execution"})
	@BeforeMethod
	public void setup(String browser, String execution) throws MalformedURLException {
		driver = DriveFactory.initDriver(browser, execution);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		try {
		driver.manage().window().setSize(new Dimension(1920, 1080));
		}
		catch(Exception e) {
			System.out.println("continue with exception");
		}
		
		driver.get("https://rahulshettyacademy.com/loginpagePractise/");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	public String getScreenShot(String testcase, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir")+"reports/"+testcase+".png");
		FileUtils.copyFile(source, destination);
		return destination.getPath();
	}
}
