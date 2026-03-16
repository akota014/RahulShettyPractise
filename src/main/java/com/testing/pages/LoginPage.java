package com.testing.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testing.Utils.BrowserUtils;

public class LoginPage {

	WebDriver driver;
	
	By username = By.id("username");
	By password = By.id("password");
	By loginBtn = By.id("signInBtn");
	By termsAndAgreement = By.id("terms");
	By Admin = By.xpath("(//input[@value='admin'])");
	By User = By.xpath("(//input[@value='user'])");
	By popupOkay = By.id("okayBtn");
	By popupCancel =By.id("cancelBtn");
	By errMsg = By.cssSelector(".alert.alert-danger.col-md-12");
	
	By dropdown = By.cssSelector("select[class='form-control']");
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void enterUsernamePassword(String name, String pwd) {
		driver.findElement(username).sendKeys(name);
		driver.findElement(password).sendKeys(pwd);
	}
	
	public void chooseAdminUser(String str) {
		if(str.equalsIgnoreCase("admin")) {
			userAdmin(Admin);
		}
		else if(str.equalsIgnoreCase("user")) {
			userAdmin(User);
		}
	}
	
	public void chooseRole(String value) {
		WebElement dropD= driver.findElement(dropdown);
		Select sel = new Select(dropD);
		sel.selectByValue(value);
	}
	
	public String verifydropdown() {
		return driver.findElement(dropdown).getText();
	}
	public void termsAndAgreement() {
		driver.findElement(termsAndAgreement).click();
	}
	
	public void clickSigin() {
		driver.findElement(loginBtn).click();
	}

	public void waitForNext() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1[class='my-4']"))).isDisplayed();
	}
	
	public void userAdmin(By checkBox) {
		driver.findElement(checkBox).click();
		if(checkBox==User) {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
			wait.until(ExpectedConditions.elementToBeClickable(popupOkay));
			driver.findElement(popupOkay).click();
		}
	}
	
	public String verifyMsg() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(errMsg)).getText();
		
	}
}
