package com.anji.rest.api.data;

import org.apache.commons.lang3.RandomStringUtils;

import com.anji.framework.commons.config.ConfigLoader;
import com.anji.rest.api.pojo.Article;
import com.anji.rest.api.pojo.ArticleRequestAndResponse;
import com.anji.rest.api.pojo.Request;
import com.anji.rest.api.pojo.User;

/**
 * 
 * Test Data Factory which gives us different test data objects to be used in tests for various scenarios
 * 
 * @author boddupally.anji
 */
public class TestDataFactory {

	public static User getValidUser() {
		return new User.Builder().withUsername("testuser" + RandomStringUtils.randomAlphanumeric(5).toLowerCase())
				.withPassword(ConfigLoader.getDefaultPassword()).withEmail(RandomStringUtils.randomAlphanumeric(5).toLowerCase() + "@example.com").build();
	}

	public static Request getValidUserRequest() {
		Request req = new Request();
		User user = getValidUser();
		req.setUser(user);
		return req;
	}
	
	public static ArticleRequestAndResponse getValidArticle() {
		ArticleRequestAndResponse ar = new ArticleRequestAndResponse();	
		Article article = new Article.Builder()
				.withArticleTitle(RandomStringUtils.randomAlphanumeric(50))
				.withArticleBody(RandomStringUtils.randomAlphanumeric(50))
				.withArticleDescription(RandomStringUtils.randomAlphanumeric(50))
				.withArticleTags(RandomStringUtils.randomAlphanumeric(5))
				.build();
		ar.setArticle(article);
		return ar;
	}
	
	public static ArticleRequestAndResponse getArticleObjectWithEmptyValues() {
		ArticleRequestAndResponse ar = new ArticleRequestAndResponse();	
		Article article = new Article.Builder()
				.withArticleTitle("")
				.withArticleBody("")
				.withArticleDescription("")
				.withArticleTags("")
				.build();
		ar.setArticle(article);
		return ar;
	}
	
	public static ArticleRequestAndResponse getValidArticleWithSpecialCharacters() {
		ArticleRequestAndResponse ar = new ArticleRequestAndResponse();	
		Article article = new Article.Builder()
				.withArticleTitle(RandomStringUtils.randomAlphanumeric(50))
				.withArticleBody(RandomStringUtils.randomAlphanumeric(50))
				.withArticleDescription(RandomStringUtils.randomAlphanumeric(50))
				.withArticleTags("<script>alert();</script>", "!@#$%\\0[]&*()+_()")
				.build();
		
		ar.setArticle(article);
		return ar;
	}
}
