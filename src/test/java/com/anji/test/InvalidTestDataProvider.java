package com.anji.test;

import org.testng.annotations.DataProvider;

import com.anji.rest.api.data.TestDataFactory;

/**
 * 
 * Data provider class that caters to the tests for various scenarios.
 * 
 * @author anjiboddupally
 */

public class InvalidTestDataProvider {

	@DataProvider(name = "invalid-article-data-provider")
	public Object[][] invalidProductID() {
		return new Object[][] { 
				{ TestDataFactory.getArticleObjectWithEmptyValues() }};
	}
}
