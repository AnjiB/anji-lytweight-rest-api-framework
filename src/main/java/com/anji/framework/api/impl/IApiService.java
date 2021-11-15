package com.anji.framework.api.impl;

import com.anji.framework.api.builder.RequestBuilder;

/**
 * API Contract
 * 
 * @author anjiboddupally
 */

public interface IApiService {

	GenericResponse get(RequestBuilder builder) throws Exception;
	
	
	GenericResponse post(RequestBuilder builder) throws Exception;
	
	
	GenericResponse patch(RequestBuilder builder) throws Exception;
	
	
	GenericResponse put(RequestBuilder builder) throws Exception;
	
	
	GenericResponse delete(RequestBuilder builder) throws Exception;
}
