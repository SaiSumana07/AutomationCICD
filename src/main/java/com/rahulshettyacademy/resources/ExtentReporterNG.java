package com.rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReport() {
		//ExtentReports and extentSparkReporter
		String path = System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setReportName("Web Automation Report");
		report.config().setDocumentTitle("Test Results");
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Tester", "Sai Sumana");
		extent.setSystemInfo("Environment", "UAT");
		
		return extent;
		
	}

}
