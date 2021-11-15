package com.anji.framework.api.impl;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A generic response object to be used by tests for assertions
 * 
 * @author anjiboddupally
 * 
 * @param <T>
 */
public class ApiResponseImpl<T> implements IResponse<T> {

	private GenericResponse response;

	private Class<T> klass;

	public ApiResponseImpl(GenericResponse response, Class<T> kClass) {
		this.response = response;
		this.klass = kClass;
	}

	public long getExecTime() {
		return response.getResponseTime();
	}

	public int getResponseCode() {
		return response.getResponseStatusCode();
	}

	public String getResponseAsString() {
		return response.getResposeBody();
	}

	public Map<String, String> getResponseHeaders() {
		return response.getResponseHeaders();
	}

	public Map<String, String> getResponseCookies() {
		return response.getResponseCookies();
	}

	@SuppressWarnings("unchecked")
	public T getResponse() throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		if(klass != null) {
			return objectMapper.readValue(getResponseAsString(), klass);
		}
		return (T) getResponseAsString();
	}

	public String getResponseContentType() {
		return response.getResponseContentType();
	}
}
