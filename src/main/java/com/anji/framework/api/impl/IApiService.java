package com.anji.framework.api.impl;

import com.anji.framework.api.builder.RequestBuilder;

/**
 * API Contract
 * 
 * @author anjiboddupally
 */

public interface IApiService<T> {

	ApiResponse<T> get(RequestBuilder builder) throws Exception;
	
	
	ApiResponse<T> post(RequestBuilder builder) throws Exception;
	
	
	ApiResponse<T> patch(RequestBuilder builder) throws Exception;
	
	
	ApiResponse<T> put(RequestBuilder builder) throws Exception;
	
	
	ApiResponse<T> delete(RequestBuilder builder) throws Exception;
}
