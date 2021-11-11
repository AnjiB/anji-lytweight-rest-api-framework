package com.anji.framework.api.enums;

/**
 * 
 * Content-Type enum
 * 
 * @author anjiboddupally
 */
public enum ApiContentType {

	JSON("application/json"),
	XML("application/xml");
	
	private String contentType;
	
	ApiContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getContentType() {
		return this.contentType;
	}
}
