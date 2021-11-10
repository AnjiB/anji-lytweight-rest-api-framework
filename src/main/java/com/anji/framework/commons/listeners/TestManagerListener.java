package com.anji.framework.commons.listeners;

import org.apache.log4j.Logger;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.anji.framework.commons.config.ConfigLoader;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;


/**
 *
 * Implementation of TestNG's ITestListner and IExecutionListener to perform activities pre and post test runs 
 * 
 * @author anjiboddupally
 */
public class TestManagerListener implements ITestListener, IExecutionListener {

	private Logger getLogger(String className) {
		Logger logger = Logger.getLogger(className);
		return logger;

	}

	@Override
	public void onTestStart(ITestResult result) {
		Logger logger = getLogger(result.getTestClass().getName());
		logger.info(result.getMethod().getMethodName() + " started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Logger logger = getLogger(result.getTestClass().getName());
		logger.info(result.getMethod().getMethodName() + " success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Logger logger = getLogger(result.getTestClass().getName());
		logger.info(result.getMethod().getMethodName() + " failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		Logger logger = getLogger(result.getTestClass().getName());
		logger.info(result.getMethod().getMethodName() + " skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {

		Logger logger = getLogger(context.getSuite().getName());
		logger.info("Starting Test Suite Execution");
	}

	@Override
	public void onFinish(ITestContext context) {
		Logger logger = getLogger(context.getSuite().getName());
		logger.info("Finishing Test Suite Execution");
	}

	@Override
	public void onExecutionStart() {
		String enableLogs = System.getProperty("enableLogs");
		if(Boolean.parseBoolean(enableLogs))
			RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
		ConfigLoader.loadCongifuration();
	}

	@Override
	public void onExecutionFinish() {
		// TODO Auto-generated method stub
		
	}
}
