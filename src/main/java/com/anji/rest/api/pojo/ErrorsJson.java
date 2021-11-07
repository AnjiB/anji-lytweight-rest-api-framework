package com.anji.rest.api.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * Errors POJO
 * 
 * @author anjiboddupally
 */

@Setter
@Getter
public class ErrorsJson {

	@JsonProperty("body")
	private List<String> body;
}
