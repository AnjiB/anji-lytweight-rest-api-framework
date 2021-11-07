package com.anji.rest.api.asserts;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.AbstractAssert;

import com.anji.rest.api.pojo.ArticleRequestAndResponse;

/**
 * Article Response Assertions
 * 
 * @author anjiboddupally
 *
 */
public class ArticleResponseAssert extends AbstractAssert<ArticleResponseAssert, ArticleRequestAndResponse> {

	private String errorInfoMessage = "Expected is: %s but actual is %s";

	public ArticleResponseAssert(ArticleRequestAndResponse actual) {
		super(actual, ArticleResponseAssert.class);
	}

	public ArticleResponseAssert articleDescriptionIs(String expDescription) {
		assertThat(actual.getArticle().getDescription())
				.as(format(errorInfoMessage, expDescription, actual.getArticle().getDescription()))
				.isEqualTo(expDescription);
		return this;
	}

	public ArticleResponseAssert articleTitleIs(String expTitle) {
		assertThat(actual.getArticle().getTitle())
				.as(format(errorInfoMessage, expTitle, actual.getArticle().getTitle())).isEqualTo(expTitle);
		return this;
	}

	public ArticleResponseAssert articleBodyIs(String expBody) {
		assertThat(actual.getArticle().getBody())
				.as(format(errorInfoMessage, expBody, actual.getArticle().getBody())).isEqualTo(expBody);
		return this;
	}

	public ArticleResponseAssert authorIs(String author) {
		assertThat(actual.getArticle().getAuthor().getUsername())
				.as(format(errorInfoMessage, author, actual.getArticle().getAuthor().getUsername())).isEqualTo(author);
		return this;
	}

	public ArticleResponseAssert articleTagListIs(List<String> expTagList) {
		assertThat(actual.getArticle().getTagList())
				.as(format(errorInfoMessage, expTagList, actual.getArticle().getDescription()))
				.containsExactlyElementsOf(expTagList);
		return this;
	}
	
	public ArticleResponseAssert thereIsAnError() {
		assertThat(actual.getErrors()).as("There is no error in the response").isNotNull();
		return this;
	}

	public ArticleResponseAssert thereIsNoError() {
		assertThat(actual.getErrors()).as("There is an error in the response").isNull();
		return this;
	}
	
	public ErrorResponseAssert errorAssert() {
		return new ErrorResponseAssert(actual.getErrors());
	}

}
