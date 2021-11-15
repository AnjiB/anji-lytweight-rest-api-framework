package com.anji.framework.api.impl;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

/**
 * @author anjiboddupally
 */


@Data
@Builder
public class GenericResponse {
	
	private int responseStatusCode;

	private long responseTime;
	
	private Map<String, String> responseHeaders;
	
	private Map<String, String> responseCookies;
	
	private String resposeBody;
	
	private String responseContentType;
}
