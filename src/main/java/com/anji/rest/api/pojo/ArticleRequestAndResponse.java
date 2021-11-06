package com.anji.rest.api.pojo;

import com.anji.rest.api.asserts.ArticleResponseAssert;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Article Request/Response Pojo
 * 
 * @author boddupally.anji
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleRequestAndResponse {

	@JsonProperty("article")
	private Article article;
	
	@JsonProperty("errors")
	private ErrorsJson errors;

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
	public ErrorsJson getErrors() {
		return errors;
	}

	public void setErrors(ErrorsJson errors) {
		this.errors = errors;
	}

	public ArticleResponseAssert articleAssertThat() {
		return new ArticleResponseAssert(this);
	}
}
