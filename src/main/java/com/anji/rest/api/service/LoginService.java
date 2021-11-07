package com.anji.rest.api.service;

import static com.anji.rest.api.constants.EndPoint.LOGIN;

import com.anji.framework.api.builder.RequestBuilder;
import com.anji.framework.api.impl.ApiResponse;
import com.anji.framework.api.impl.BaseApiService;
import com.anji.rest.api.pojo.Request;

/**
 * 
 * Service for login api
 * 
 * @author anjiboddupally
 */

public class LoginService<UserResponse> extends BaseApiService<UserResponse> {
	
	public LoginService(Class<UserResponse> kClass) {
		super(kClass);
	}

	public ApiResponse<UserResponse> login(Request request) throws Exception {
		
		RequestBuilder builder = RequestBuilder.builder().pathUrl(LOGIN)
				.requestObject(request).build();
		
		return post(builder);

	}
}
