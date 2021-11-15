package com.anji.framework.api.impl.restassured;

import static com.anji.framework.api.utils.ApiUtil.getConfig;
import static com.anji.framework.api.utils.TransformUtil.transform;
import static io.restassured.RestAssured.given;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.anji.framework.api.builder.RequestBuilder;
import com.anji.framework.api.enums.ApiHeaders;
import com.anji.framework.api.impl.GenericResponse;
import com.anji.framework.api.impl.IApiService;
import com.anji.framework.api.impl.client.Client;
import com.anji.framework.api.impl.client.ClientService;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * Rest Assured Implementation of IAPIService.
 * 
 * @author anjiboddupally
 */


public abstract class RestAssuredApiServiceImpl implements IApiService {
		
	private String baseUri;

	public RestAssuredApiServiceImpl(String basePath) {
		this.baseUri = basePath;
	}

	@Override
	public GenericResponse get(RequestBuilder builder) throws Exception {
		return transform(given().spec(getSpecBuilder(builder)).get());
	}

	@Override
	public GenericResponse post(RequestBuilder builder) throws Exception {
		return transform(given().spec(getSpecBuilder(builder)).post());
	}

	@Override
	public GenericResponse patch(RequestBuilder builder) throws Exception {
		return transform(given().spec(getSpecBuilder(builder)).patch());
	}

	@Override
	public GenericResponse put(RequestBuilder builder) throws Exception {
		return transform(given().spec(getSpecBuilder(builder)).put());
	}

	@Override
	public GenericResponse delete(RequestBuilder builder) throws Exception {
		return transform(given().spec(getSpecBuilder(builder)).delete());
	}
	
	
	private RequestSpecification getSpecBuilder(RequestBuilder builder) throws Exception {
		
		RequestSpecBuilder requestSpecBuilder = new  RequestSpecBuilder();
		
		requestSpecBuilder.setBaseUri(baseUri);
		
		if(builder.getContentType() != null) {
			requestSpecBuilder.setContentType(builder.getContentType().getContentType());
		}
		
		if(StringUtils.isNotEmpty(builder.getPathUrl()))
			requestSpecBuilder.setBasePath(builder.getPathUrl());
		
		if(builder.getQueryParameters() != null && !builder.getQueryParameters().isEmpty())
			requestSpecBuilder.addQueryParams(builder.getQueryParameters());
		
		if(builder.getReqHeaders() != null && !builder.getReqHeaders().isEmpty()) {
			requestSpecBuilder.addHeaders(builder.getReqHeaders());
		}

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
	
		RequestSpecification specification = requestSpecBuilder.build();
				
		return specification;
	}

}
