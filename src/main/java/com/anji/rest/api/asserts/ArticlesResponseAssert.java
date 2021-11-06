package com.anji.rest.api.asserts;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.AbstractAssert;

import com.anji.rest.api.pojo.ArticlesResponse;

/**
 * Article Response Assertions
 * 
 * @author boddupally.anji
 *
 */
public class ArticlesResponseAssert extends AbstractAssert<ArticlesResponseAssert, ArticlesResponse> {

	public ArticlesResponseAssert(ArticlesResponse actual) {
		super(actual, ArticlesResponseAssert.class);
	}

	public ArticlesResponseAssert articleCountIs(int artCount) {
		assertThat(actual.getArticlesCount())
				.as(format("Expected article count %s is not matching with actual count %s", artCount,
						actual.getArticlesCount()))
				.isEqualTo(artCount);
		return this;
	}
	
	public ArticlesResponseAssert articlesCountIs(int artCount) {
		
		assertThat(actual.getArticles().size())
				.as(format("Expected article count %s is not matching with actual count %s", artCount,
						actual.getArticles().size()))
				.isEqualTo(artCount);

		return this;
	}

	public ArticlesResponseAssert assertArticleCountIsNotZero() {
		
		assertThat(actual.getArticles().size()).isGreaterThan(0);
		return this;
	}

	public ArticlesResponseAssert assertArticleCountIsZero() {
		
		assertThat(actual.getArticles().size()).isEqualTo(0);

		return this;
	}
	
	public ArticlesResponseAssert thereIsAnError() {
		assertThat(actual.getErrors()).as("There is no error in the response").isNotNull();
		return this;
	}

	public ArticlesResponseAssert thereIsNoError() {
		assertThat(actual.getErrors()).as("There is an error in the response").isNull();
		return this;
	}
	
	public ErrorResponseAssert errorAssert() {
		return new ErrorResponseAssert(actual.getErrors());
	}

}
