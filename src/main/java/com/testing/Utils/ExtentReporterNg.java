package com.testing.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNg {
		
	private static ExtentReports extent;
	public static ExtentReports getExtentReporters() {
		
		if(extent==null) {
			String path = System.getProperty("user.dir")+"//reports//index.html";
			ExtentSparkReporter extenthtml = new ExtentSparkReporter(path);
			extenthtml.config().setDocumentTitle("RahulShettyLogin");
			extenthtml.config().setReportName("Document");
			
			extent  = new ExtentReports();
			extent.attachReporter(extenthtml);
		}
		return extent;
				
	}
}
