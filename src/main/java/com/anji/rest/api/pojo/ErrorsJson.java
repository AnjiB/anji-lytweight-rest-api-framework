package com.anji.rest.api.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Errors POJO
 * 
 * @author boddupally.anji
 */

public class ErrorsJson {

	@JsonProperty("body")
	private List<String> body;

	public List<String> getBody() {
		return body;
	}

	public void setBody(List<String> body) {
		this.body = body;
	}
}
