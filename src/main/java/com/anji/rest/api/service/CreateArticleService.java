package com.anji.rest.api.service;

import static com.anji.rest.api.constants.EndPoint.ARTICLES;

import com.anji.framework.api.builder.RequestBuilder;
import com.anji.framework.api.impl.ApiResponseImpl;
import com.anji.framework.api.impl.restassured.RestAssuredApiServiceImpl;
import com.anji.framework.commons.config.ConfigLoader;
import com.anji.rest.api.pojo.User;

/**
 * 
 * Service for create article api
 * 
 * @author anjiboddupally
 */
public class CreateArticleService<ArticleRequestAndResponse> extends RestAssuredApiServiceImpl {
	
	private Class<ArticleRequestAndResponse> klass;
	
	public CreateArticleService(Class<ArticleRequestAndResponse> kClass) {
		super(ConfigLoader.getBaseUrl());
		this.klass = kClass;
	}

	public ApiResponseImpl<ArticleRequestAndResponse> createArticle(String username, String password, ArticleRequestAndResponse ar, boolean authRequired) throws Exception {
		
		RequestBuilder builder = RequestBuilder.builder()
				.username(username)
				.password(password)
				.isAuthRequired(authRequired)
				.pathUrl(ARTICLES)
				.requestObject(ar).build();
		
		return createArticle(builder, ar);
	}
	
	
	public ApiResponseImpl<ArticleRequestAndResponse> createArticle(RequestBuilder builder, ArticleRequestAndResponse ar) throws Exception {
		return new ApiResponseImpl<ArticleRequestAndResponse>(post(builder), klass);
	}
	
	
	public ApiResponseImpl<ArticleRequestAndResponse> createArticle(String username, ArticleRequestAndResponse createArticleRequest) throws Exception {
		return createArticle(username, ConfigLoader.getDefaultPassword(), createArticleRequest, true);
	}
	
	public ApiResponseImpl<ArticleRequestAndResponse> createArticle(String username, ArticleRequestAndResponse createArticleRequest, boolean authRequired) throws Exception {
		return createArticle(username, ConfigLoader.getDefaultPassword(), createArticleRequest, authRequired);
	}
	
	public ApiResponseImpl<ArticleRequestAndResponse> createArticle(User user, ArticleRequestAndResponse createArticleRequest) throws Exception {
		return createArticle(user.getEmail(), user.getPassword(), createArticleRequest, true);
	}
}
