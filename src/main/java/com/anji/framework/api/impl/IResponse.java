package com.anji.framework.api.impl;

import java.util.Map;

/**
 * 
 * A generic response contract
 * 
 * @author anjiboddupally
 */

public interface IResponse<T> {

	long getExecTime();

	int getResponseCode();

	String getResponseAsString();

	Map<String, String> getResponseHeaders();

	Map<String, String> getResponseCookies();

	T getResponse() throws Exception;

	String getResponseContentType();
}
