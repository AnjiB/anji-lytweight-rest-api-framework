package com.anji.rest.api.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * Response POJO
 * 
 * @author anjiboddupally
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {

	@JsonProperty("user")
	private User user;
}
