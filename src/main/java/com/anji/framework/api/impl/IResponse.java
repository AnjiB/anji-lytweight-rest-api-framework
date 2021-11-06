package com.anji.framework.api.impl;

import java.util.Map;

public interface IResponse<T> {

	long getExecTime();

	int getResponseCode();

	String getResponseAsString();

	Map<String, String> getResponseHeaders();

	Map<String, String> getResponseCookies();

	T getResponse();

	String getResponseContentType();
}
