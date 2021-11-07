package com.anji.rest.api.pojo;

import com.anji.rest.api.asserts.ArticleResponseAssert;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * Article Request/Response Pojo
 * 
 * @author anjiboddupally
 * 
 * TODO : split it in future
 */

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleRequestAndResponse {

	@JsonProperty("article")
	private Article article;
	
	@JsonProperty("errors")
	private ErrorsJson errors;

	public ArticleResponseAssert articleAssertThat() {
		return new ArticleResponseAssert(this);
	}
}
