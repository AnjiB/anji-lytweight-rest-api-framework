package com.anji.framework.api.utils;


import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.anji.framework.api.builder.RequestBuilder;
import com.anji.framework.api.enums.ApiHeaders;
import com.anji.framework.api.impl.Client;
import com.anji.framework.api.impl.ClientFactory;

import io.restassured.builder.RequestSpecBuilder;

/**
 * 
 * Util that handles the conversion of various strings to pojos and vice-versa
 * 
 * @author boddupally.anji
 *
 */
public final class ConvertionUtil {
	
	public static RequestSpecBuilder transform(RequestBuilder builder) throws Exception {
		
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		if(StringUtils.isNotEmpty(builder.getBaseUrl()))
			requestSpecBuilder.setBaseUri(builder.getBaseUrl());
		
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
			Client client = ClientFactory.getClient(builder.getUsername(), builder.getPassword(), builder.isCachedClient());
			requestSpecBuilder.addHeader(ApiHeaders.AUTH.getHeader(), "Token " + client.getAuthKey());
		}
			
		requestSpecBuilder.setContentType(builder.getContentType().getContentType());
		
		return requestSpecBuilder;
	}
}
