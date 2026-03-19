package com.testing.DriverFactory;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriveFactory {

	
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public static WebDriver initDriver(String browser,String execution) throws MalformedURLException {
		WebDriver driverInstance = null;
		
		
		if(execution.equalsIgnoreCase("grid")){
			if(browser.equalsIgnoreCase("chrome"))
			{
				ChromeOptions options =new ChromeOptions();
				options.addArguments("--headless=new");
	//			WebDriverManager.chromedriver().setup();
			driverInstance = new RemoteWebDriver(new URL("http://host.docker.internal:4444/wd/hub"), options);
			driver.set(driverInstance);
			}
			
			else if(browser.equalsIgnoreCase("edge")) {
				EdgeOptions options = new EdgeOptions();
	//			WebDriverManager.edgedriver().setup();
				options.addArguments("--headless=new");
				driverInstance = new RemoteWebDriver(new URL("http://host.docker.internal:4444/wd/hub"), options);
				driver.set(driverInstance);
			}
			
			else if(browser.equalsIgnoreCase("firefox")) {
				FirefoxOptions options = new FirefoxOptions();
	//			WebDriverManager.firefoxdriver().setup();
				options.addArguments("--headless=new");
				driverInstance = new RemoteWebDriver(new URL("http://host.docker.internal:4444/wd/hub"), options);
				driver.set(driverInstance);
			}
		}
		else if(execution.equalsIgnoreCase("local")) {
			if(browser.equalsIgnoreCase("chrome"))
			{
				ChromeOptions options =new ChromeOptions();
				options.addArguments("--headless=new");
				WebDriverManager.chromedriver().setup();
				driverInstance = new ChromeDriver(options);
				driver.set(driverInstance);
				
			}
			else if(browser.equalsIgnoreCase("edge"))
			{
				EdgeOptions options = new EdgeOptions();
							WebDriverManager.edgedriver().setup();
							options.addArguments("--headless=new");
				driverInstance = new EdgeDriver(options);
				driver.set(driverInstance);
			}
		}
		
		
		
		return driver.get();
	}
	public static WebDriver getDriver() {
		return driver.get();
	}
	
}
