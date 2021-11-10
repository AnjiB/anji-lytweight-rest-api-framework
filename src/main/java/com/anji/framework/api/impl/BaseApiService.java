package com.anji.framework.api.impl;

import static com.anji.framework.api.utils.ApiUtil.getConfig;
import static io.restassured.RestAssured.given;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.anji.framework.api.builder.RequestBuilder;
import com.anji.framework.api.enums.ApiHeaders;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * Rest Assured Implementation of IAPIService.
 * 
 * @author anjiboddupally
 */


public abstract class BaseApiService<T> implements IApiService<T> {
	

	private RequestSpecBuilder requestSpecBuilder = new  RequestSpecBuilder();
	
	private String baseUri;

	private Class<T> klass;

	public BaseApiService(String basePath, Class<T> kClass) {
		this.baseUri = basePath;
		this.klass = kClass;
		requestSpecBuilder.setBaseUri(baseUri);
	}

	@Override
	public ApiResponse<T> get(RequestBuilder builder) throws Exception {
		return new ApiResponse<T>(given().spec(getSpecBuilder(builder)).get(), klass);
	}

	@Override
	public ApiResponse<T> post(RequestBuilder builder) throws Exception {
		return new ApiResponse<T>(given().spec(getSpecBuilder(builder)).post(), klass);
	}

	@Override
	public ApiResponse<T> patch(RequestBuilder builder) throws Exception {
		return new ApiResponse<T>(given().spec(getSpecBuilder(builder)).patch(), klass);
	}

	@Override
	public ApiResponse<T> put(RequestBuilder builder) throws Exception {
		return new ApiResponse<T>(given().spec(getSpecBuilder(builder)).put(), klass);
	}

	@Override
	public ApiResponse<T> delete(RequestBuilder builder) throws Exception {
		return new ApiResponse<T>(given().spec(getSpecBuilder(builder)).delete(), klass);
	}
	
	
	private RequestSpecification getSpecBuilder(RequestBuilder builder) throws Exception {
		
		if(StringUtils.isNotEmpty(builder.getPathUrl()))
			requestSpecBuilder.setBasePath(builder.getPathUrl());
		
		if(StringUtils.isNotEmpty(builder.getPathUrl()))
			requestSpecBuilder.setBasePath(builder.getPathUrl());
		
		if(builder.getQueryParameters() != null && !builder.getQueryParameters().isEmpty())
			requestSpecBuilder.addQueryParams(builder.getQueryParameters());
		
		if(builder.getReqHeaders() != null && !builder.getReqHeaders().isEmpty())
			requestSpecBuilder.addHeaders(builder.getReqHeaders());
		
		if(builder.getCookies() != null && !builder.getCookies().isEmpty())
			requestSpecBuilder.addCookies(builder.getCookies());
		
		if(!Objects.isNull(builder.getRequestObject()))
			requestSpecBuilder.setBody(builder.getRequestObject());
		if(builder.isAuthRequired()) {
			// login
			Client client = ClientService.getClient(builder.getUsername(), builder.getPassword(), builder.isCachedClient());
			requestSpecBuilder.addHeader(ApiHeaders.AUTH.getHeader(), "Token " + client.getAuthKey());
		}
		
		requestSpecBuilder.setConfig(getConfig(builder.getWaitTime()));
	
		return requestSpecBuilder.build();
	}

}
