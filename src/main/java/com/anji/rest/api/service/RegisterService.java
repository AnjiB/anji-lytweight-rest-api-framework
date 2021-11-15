package com.anji.rest.api.service;

import static com.anji.rest.api.constants.EndPoint.REGISTER;

import com.anji.framework.api.builder.RequestBuilder;
import com.anji.framework.api.impl.ApiResponseImpl;
import com.anji.framework.api.impl.restassured.RestAssuredApiServiceImpl;
import com.anji.framework.commons.config.ConfigLoader;
import com.anji.rest.api.pojo.Request;


/**
 * 
 * Service for register api
 * 
 * @author anjiboddupally
 */
public class RegisterService<UserResponse> extends RestAssuredApiServiceImpl {
	
	private Class<UserResponse> klass;

	public RegisterService(Class<UserResponse> kClass) {
		super(ConfigLoader.getBaseUrl());
		this.klass = kClass;
	}

	public UserResponse registerUser(Request request) throws Exception {
		
		RequestBuilder builder = RequestBuilder.builder().pathUrl(REGISTER)
				.requestObject(request).build();
		
		ApiResponseImpl<UserResponse> response =  new ApiResponseImpl<UserResponse>(post(builder), klass);
		
		return response.getResponse();
	}
}
