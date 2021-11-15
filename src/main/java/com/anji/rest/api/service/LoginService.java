package com.anji.rest.api.service;

import static com.anji.rest.api.constants.EndPoint.LOGIN;

import com.anji.framework.api.builder.RequestBuilder;
import com.anji.framework.api.impl.ApiResponseImpl;
import com.anji.framework.api.impl.restassured.RestAssuredApiServiceImpl;
import com.anji.framework.commons.config.ConfigLoader;
import com.anji.rest.api.pojo.Request;

/**
 * 
 * Service for login api
 * 
 * @author anjiboddupally
 */

public class LoginService<UserResponse> extends RestAssuredApiServiceImpl {
	
	
	private Class<UserResponse> klass;
	
	public LoginService(Class<UserResponse> kClass) {
		super(ConfigLoader.getBaseUrl());
		this.klass = kClass;
	}

	public ApiResponseImpl<UserResponse> login(Request request) throws Exception {
		
		RequestBuilder builder = RequestBuilder.builder().pathUrl(LOGIN)
				.requestObject(request).build();
		
		return new ApiResponseImpl<UserResponse>(post(builder), klass);

	}
}
