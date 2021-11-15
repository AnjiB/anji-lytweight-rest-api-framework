package com.anji.rest.api.service;

import static com.anji.rest.api.constants.EndPoint.ARTICLES;

import java.util.Map;

import com.anji.framework.api.builder.RequestBuilder;
import com.anji.framework.api.impl.ApiResponseImpl;
import com.anji.framework.api.impl.restassured.RestAssuredApiServiceImpl;
import com.anji.framework.commons.config.ConfigLoader;

/**
 * 
 * Service for get articles api
 * 
 * @author anjiboddupally
 */
public class GetArticlesService<ArticlesResponse> extends RestAssuredApiServiceImpl {

	private Class<ArticlesResponse> klass;
	
	public GetArticlesService(Class<ArticlesResponse> kClass) {
		super(ConfigLoader.getBaseUrl());
		klass = kClass;
	}

	public ApiResponseImpl<ArticlesResponse> getArticles(Map<String, String> queryParam) throws Exception {

		RequestBuilder builder = RequestBuilder.builder().queryParameters(queryParam).pathUrl(ARTICLES).build();
		return new ApiResponseImpl<ArticlesResponse>(get(builder), klass);
	}
}
