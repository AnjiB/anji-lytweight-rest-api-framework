package com.anji.framework.api.builder;


import java.util.Map;

import com.anji.framework.api.enums.ApiContentType;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Builder to get request details from test
 * 
 * @author boddupally.anji
 */
@Getter
@Setter
@Builder
public class RequestBuilder {
	
	private String username;
	
	private String password;

	private Object requestObject;
	
	private String pathUrl;
	
	@Default
	private boolean isAuthRequired = false;
	
	@Default
	private boolean isCachedClient = false;
	
	private Map<String, String> queryParameters;
	
	private Map<String, String> cookies;
	
	private Map<String, String> reqHeaders;
	
	@Default
	private ApiContentType contentType = ApiContentType.JSON;
	
	@Default
	private long waitTime = 10000;
	
	private boolean ignoreCerts;
	
}
