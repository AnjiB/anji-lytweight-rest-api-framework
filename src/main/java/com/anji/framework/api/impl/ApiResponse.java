package com.anji.framework.api.impl;

import java.util.Map;
import java.util.stream.Collectors;

import io.restassured.http.Header;
import io.restassured.response.Response;

/**
 * A generic response object to be used by tests for assertions
 * 
 * @author anjiboddupally
 * 
 * @param <T>
 */
public class ApiResponse<T> implements IResponse<T> {

	private Response response;

	private Class<T> klassClass;

	public ApiResponse(Response response, Class<T> kClass) {
		this.response = response;
		this.klassClass = kClass;
	}

	public long getExecTime() {
		return response.getTime();
	}

	public int getResponseCode() {
		return response.getStatusCode();
	}

	public String getResponseAsString() {
		return response.asString();
	}

	public Map<String, String> getResponseHeaders() {
		return response.getHeaders().asList().stream().collect(Collectors.toMap(Header::getName, Header::getName));
	}

	public Map<String, String> getResponseCookies() {
		return response.getCookies();
	}

	public T getResponse() {
		return response.as(klassClass);
	}

	public String getResponseContentType() {
		return response.getContentType();
	}
}
