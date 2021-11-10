package com.anji.rest.api.service;

import static com.anji.rest.api.constants.EndPoint.REGISTER;

import com.anji.framework.api.builder.RequestBuilder;
import com.anji.framework.api.impl.ApiResponse;
import com.anji.framework.api.impl.BaseApiService;
import com.anji.framework.commons.config.ConfigLoader;
import com.anji.rest.api.pojo.Request;


/**
 * 
 * Service for register api
 * 
 * @author anjiboddupally
 */
public class RegisterService<UserResponse> extends BaseApiService<UserResponse> {

	public RegisterService(Class<UserResponse> kClass) {
		super(ConfigLoader.getBaseUrl(), kClass);
	}

	public UserResponse registerUser(Request request) throws Exception {
		
		RequestBuilder builder = RequestBuilder.builder().pathUrl(REGISTER)
				.requestObject(request).build();
		
		ApiResponse<UserResponse> response =  post(builder);
		
		return response.getResponse();
	}
}
