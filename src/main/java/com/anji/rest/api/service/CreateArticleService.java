package com.anji.rest.api.service;

import static com.anji.rest.api.constants.EndPoint.ARTICLES;

import com.anji.framework.api.builder.RequestBuilder;
import com.anji.framework.api.impl.ApiResponse;
import com.anji.framework.api.impl.BaseApiService;
import com.anji.framework.commons.config.ConfigLoader;
import com.anji.rest.api.pojo.User;

/**
 * 
 * Service for create article api
 * 
 * @author boddupally.anji
 */
public class CreateArticleService<ArticleRequestAndResponse> extends BaseApiService<ArticleRequestAndResponse> {
	
	
	public CreateArticleService(Class<ArticleRequestAndResponse> kClass) {
		super(kClass);
	}

	public ApiResponse<ArticleRequestAndResponse> createArticle(String username, String password, ArticleRequestAndResponse ar, boolean authRequired) throws Exception {
		
		RequestBuilder builder = RequestBuilder.builder()
				.username(username)
				.password(password)
				.isAuthRequired(authRequired)
				.pathUrl(ARTICLES)
				.requestObject(ar).build();
		
		return createArticle(builder, ar);
	}
	
	
	public ApiResponse<ArticleRequestAndResponse> createArticle(RequestBuilder builder, ArticleRequestAndResponse ar) throws Exception {
		
		return post(builder);
	}
	
	
	public ApiResponse<ArticleRequestAndResponse> createArticle(String username, ArticleRequestAndResponse createArticleRequest) throws Exception {
		return createArticle(username, ConfigLoader.getDefaultPassword(), createArticleRequest, true);
	}
	
	public ApiResponse<ArticleRequestAndResponse> createArticle(String username, ArticleRequestAndResponse createArticleRequest, boolean authRequired) throws Exception {
		return createArticle(username, ConfigLoader.getDefaultPassword(), createArticleRequest, authRequired);
	}
	
	public ApiResponse<ArticleRequestAndResponse> createArticle(User user, ArticleRequestAndResponse createArticleRequest) throws Exception {
		return createArticle(user.getEmail(), user.getPassword(), createArticleRequest, true);
	}
}
