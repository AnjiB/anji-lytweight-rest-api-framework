package com.anji.framework.api.enums;

/**
 * 
 * Content-Type enum
 * 
 * @author anjiboddupally
 */
public enum ApiContentType {

	JSON("application/json;charset=utf-8"),
	XML("application/xml");
	
	private String contentType;
	
	ApiContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getContentType() {
		return this.contentType;
	}
}
