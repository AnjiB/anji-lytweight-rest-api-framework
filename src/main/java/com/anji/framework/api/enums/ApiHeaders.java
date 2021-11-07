package com.anji.framework.api.enums;

/**
 * 
 * Request Headers enum
 * 
 * @author anjiboddupally
 */

public enum ApiHeaders {

	CONTENT_TYPE("Content-Type"),
	ACCEPT("Accept"),
	AUTH("Authorization"),
	SET_COOKIE("Set-Cookie"),
	USER_AGENT("User-Agent"),
	JWT_TOKEN("jwtauthorization");
	
	private String header;
	
	private ApiHeaders(String header) {
		this.header = header;
	}
	
	public String getHeader() {
		return this.header;
	}
}
