package com.testing.BaseTest;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.testing.Utils.ExtentReporterNg;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listener extends BaseTest implements ITestListener {
	ExtentReports extent = ExtentReporterNg.getExtentReporters();
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	
	 public  void onTestStart(ITestResult result) {
		 System.out.println("Start flushed");
		 String test = result.getMethod().getMethodName();
		 Object[] para =result.getParameters();
		 
		 String testname = test +"-"+Arrays.toString(para);
		  ExtentTest exTest = extent.createTest(testname);
		  extentTest.set(exTest);
		  }

		  /**
		   * Invoked each time a test succeeds.
		   *
		   * @param result <code>ITestResult</code> containing information about the run test
		   * @see ITestResult#SUCCESS
		   */
		  public void onTestSuccess(ITestResult result) {
		    // not implemented
			  System.out.println("Passed flushed");
			  extentTest.get().log(Status.PASS,"Passed");
		  }

		  /**
		   * Invoked each time a test fails.
		   *
		   * @param result <code>ITestResult</code> containing information about the run test
		   * @see ITestResult#FAILURE
		   */
		  public void onTestFailure(ITestResult result) {
		    // not implemented
			  System.out.println("Failed flushed");
			  extentTest.get().log(Status.FAIL,"Failed");
			  WebDriver driver = null;
			  Field declaredField = null;
			try {
				declaredField = result.getInstance().getClass().getSuperclass().getDeclaredField("driver");
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  declaredField.setAccessible(true);
			  try {
				 driver = (WebDriver) declaredField.get(result.getInstance());
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			  
			  if(driver!=null) {
				  try {
					String screenshotPath = getScreenShot(result.getMethod().getMethodName(), driver);
					extentTest.get().addScreenCaptureFromPath(screenshotPath);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			  else {
				  System.out.println("driver is null");
			  }
			  
		  }

		  /**
		   * Invoked each time a test is skipped.
		   *
		   * @param result <code>ITestResult</code> containing information about the run test
		   * @see ITestResult#SKIP
		   */
		  public void onTestSkipped(ITestResult result) {
		    // not implemented
		  }

		  /**
		   * Invoked each time a method fails but has been annotated with successPercentage and this failure
		   * still keeps it within the success percentage requested.
		   *
		   * @param result <code>ITestResult</code> containing information about the run test
		   * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
		   */
		  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		    // not implemented
		  }

		  /**
		   * Invoked each time a test fails due to a timeout.
		   *
		   * @param result <code>ITestResult</code> containing information about the run test
		   */
		  public void onTestFailedWithTimeout(ITestResult result) {
		    onTestFailure(result);
		  }

		  /**
		   * Invoked before running all the test methods belonging to the classes inside the &lt;test&gt;
		   * tag and calling all their Configuration methods.
		   *
		   * @param context The test context
		   */
		  public void onStart(ITestContext context) {
		    // not implemented
		  }

		  /**
		   * Invoked after all the test methods belonging to the classes inside the &lt;test&gt; tag have
		   * run and all their Configuration methods have been called.
		   *
		   * @param context The test context
		   */
		  public void onFinish(ITestContext context) {
		    // not implemented
			  System.out.println("Finish flushed");
			  extent.flush();
		  }

}
