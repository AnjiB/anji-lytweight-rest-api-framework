package com.anji.framework.commons.listeners;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

/**
 * 
 * Generates the summary of test run on console.
 * 
 * @author anji.boddupally
 *
 */
public class SummaryReportListener implements IReporter {

	private static final Logger LOGGER = Logger.getLogger(SummaryReportListener.class);

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

		for (ISuite suite : suites) {
			String suiteName = suite.getName();
			Map<String, ISuiteResult> suiteResults = suite.getResults();
			for (ISuiteResult sr : suiteResults.values()) {
				ITestContext tc = sr.getTestContext();

				LOGGER.info("\n\n******* Summary of the Report *********");
				LOGGER.info(
						"Passed tests for suite '" + suiteName + "' is:" + tc.getPassedTests().getAllResults().size());
				LOGGER.info(
						"Failed tests for suite '" + suiteName + "' is:" + tc.getFailedTests().getAllResults().size());
				LOGGER.info("Skipped tests for suite '" + suiteName + "' is:"
						+ tc.getSkippedTests().getAllResults().size());
			}
		}
	}
}
