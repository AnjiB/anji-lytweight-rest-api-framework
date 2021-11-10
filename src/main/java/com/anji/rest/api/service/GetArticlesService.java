package com.anji.rest.api.service;

import static com.anji.rest.api.constants.EndPoint.ARTICLES;

import java.util.Map;

import com.anji.framework.api.builder.RequestBuilder;
import com.anji.framework.api.impl.ApiResponse;
import com.anji.framework.api.impl.BaseApiService;
import com.anji.framework.commons.config.ConfigLoader;

/**
 * 
 * Service for get articles api
 * 
 * @author anjiboddupally
 */
public class GetArticlesService<ArticlesResponse> extends BaseApiService<ArticlesResponse> {

	public GetArticlesService(Class<ArticlesResponse> kClass) {
		super(ConfigLoader.getBaseUrl(), kClass);

	}

	public ApiResponse<ArticlesResponse> getArticles(Map<String, String> queryParam) throws Exception {

		RequestBuilder builder = RequestBuilder.builder().queryParameters(queryParam).pathUrl(ARTICLES).build();
		return get(builder);
	}
}
