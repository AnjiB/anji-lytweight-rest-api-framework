package com.anji.rest.api.data;

import java.util.Arrays;
import java.util.Collections;

import com.anji.framework.commons.config.ConfigLoader;
import com.anji.rest.api.pojo.Article;
import com.anji.rest.api.pojo.ArticleRequestAndResponse;
import com.anji.rest.api.pojo.Request;
import com.anji.rest.api.pojo.User;
import com.github.javafaker.Faker;


/**
 * 
 * Test Data Factory which gives us different test data objects to be used in tests for various scenarios
 * 
 * @author anjiboddupally
 */
public class TestDataFactory {
	
	private static Faker faker = new Faker();

	public static User getValidUser() {
		
		return User.builder().username("testuser" + faker.name().firstName())
				.password(ConfigLoader.getDefaultPassword()).email(faker.internet().safeEmailAddress()).build();
	}

	public static Request getValidUserRequest() {
		Request req = new Request();
		User user = getValidUser();
		req.setUser(user);
		return req;
	}
	
	public static ArticleRequestAndResponse getValidArticle() {
		ArticleRequestAndResponse ar = new ArticleRequestAndResponse();	
		Article article = Article.builder()
				.title(faker.lorem().fixedString(20))
				.body(faker.lorem().paragraph())
				.description(faker.lorem().sentence())
				.tag(faker.lorem().fixedString(5))
				.tag(faker.lorem().fixedString(5))
				.build();
		ar.setArticle(article);
		return ar;
	}
	
	public static ArticleRequestAndResponse getArticleObjectWithEmptyValues() {
		ArticleRequestAndResponse ar = new ArticleRequestAndResponse();	
		Article article = Article.builder()
				.title("")
				.body("")
				.description("")
				.tagList(Collections.emptyList())
				.build();
		ar.setArticle(article);
		return ar;
	}
	
	public static ArticleRequestAndResponse getValidArticleWithSpecialCharacters() {
		ArticleRequestAndResponse ar = new ArticleRequestAndResponse();	
		Article article = Article.builder()
				.title(faker.lorem().fixedString(20))
				.body(faker.lorem().paragraph())
				.description(faker.lorem().sentence())
				.tagList(Arrays.asList("<script>alert();</script>", "!@#$%\\0[]&*()+_()"))
				.build();
		
		ar.setArticle(article);
		return ar;
	}
}
