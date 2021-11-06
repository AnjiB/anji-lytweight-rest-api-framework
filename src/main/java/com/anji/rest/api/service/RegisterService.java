package com.anji.rest.api.service;

import static com.anji.rest.api.constants.EndPoint.REGISTER;
import static org.assertj.core.api.Assertions.assertThat;

import org.apache.http.HttpStatus;

import com.anji.framework.api.builder.RequestBuilder;
import com.anji.framework.api.impl.ApiResponse;
import com.anji.framework.api.impl.BaseApiService;
import com.anji.rest.api.pojo.Request;


/**
 * 
 * Service for register api
 * 
 * @author boddupally.anji
 */
public class RegisterService<UserResponse> extends BaseApiService<UserResponse> {

	public RegisterService(Class<UserResponse> kClass) {
		super(kClass);
	}

	public UserResponse registerUser(Request request) throws Exception {
		
		RequestBuilder builder = RequestBuilder.builder().pathUrl(REGISTER)
				.requestObject(request).build();
		
		ApiResponse<UserResponse> response =  post(builder);
		
		assertThat(response.getResponseCode()).isEqualTo(HttpStatus.SC_CREATED);

		return response.getResponse();
	}
}
