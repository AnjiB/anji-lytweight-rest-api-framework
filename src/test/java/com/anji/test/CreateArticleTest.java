package com.anji.test;

import static com.anji.rest.api.constants.EndPoint.ARTICLES;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.anji.framework.api.builder.RequestBuilder;
import com.anji.framework.api.impl.ApiResponse;
import com.anji.framework.commons.config.ConfigLoader;
import com.anji.framework.commons.listeners.TestManagerListener;
import com.anji.rest.api.data.TestDataFactory;
import com.anji.rest.api.enus.Filter;
import com.anji.rest.api.pojo.ArticleRequestAndResponse;
import com.anji.rest.api.pojo.ArticlesResponse;
import com.anji.rest.api.pojo.Request;
import com.anji.rest.api.pojo.UserResponse;
import com.anji.rest.api.service.CreateArticleService;
import com.anji.rest.api.service.GetArticlesService;
import com.anji.rest.api.service.LoginService;
import com.anji.rest.api.service.RegisterService;
import com.google.common.collect.Maps;
@Listeners(TestManagerListener.class)
public class CreateArticleTest {
	
	private static int SC_CREATED = 201;
	private static int SC_OK = 200;
	private static int SC_UNAUTHORIZED = 401;
	private static int SC_UNPROCESSABLE_ENTITY = 422;

	private Request userRequest;

	private CreateArticleService<ArticleRequestAndResponse> creatArticle = new CreateArticleService<>(ArticleRequestAndResponse.class); 

	private GetArticlesService<ArticlesResponse> getArticleService = new GetArticlesService<>(ArticlesResponse.class);

	private LoginService<UserResponse> loginService = new LoginService<>(UserResponse.class);

	@BeforeMethod
	public void registerUser() throws Exception {
		userRequest = TestDataFactory.getValidUserRequest();
		RegisterService<UserResponse> registerService = new RegisterService<>(UserResponse.class);
		registerService.registerUser(userRequest);

	}

	@Test(dataProvider = "invalid-article-data-provider", dataProviderClass = InvalidTestDataProvider.class)
	public void createArticleWithEmptiesAndNulls(ArticleRequestAndResponse invalidArticleTestDataObject)
			throws Exception {

		ApiResponse<ArticleRequestAndResponse> response = creatArticle.createArticle(userRequest.getUser().getEmail(),
				invalidArticleTestDataObject);

		response.getResponse().articleAssertThat().errorAssert().errorMessageIs("Unexpected error");

		assertThat(response.getResponseCode()).isEqualTo(SC_UNPROCESSABLE_ENTITY);

	}

	/*
	 * Testing Create Article flow..
	 */
	@Test(description = "Testing if valid user can create the article or not")
	public void testCreateArticle() throws Exception {

		// Article count should be 0 before it is created
		Map<String, String> queryParam = Maps.newHashMap();
		queryParam.put(Filter.author.name(), userRequest.getUser().getUsername());

		ApiResponse<ArticlesResponse> articlesResponse = getArticleService.getArticles(queryParam);
		assertThat(articlesResponse.getResponseCode()).isEqualTo(SC_OK);
		articlesResponse.getResponse().assertThat().articleCountIs(0);
		
		ArticleRequestAndResponse requestObject = TestDataFactory.getValidArticle();

		ApiResponse<ArticleRequestAndResponse> response = creatArticle.createArticle(userRequest.getUser().getEmail(),
				requestObject);

		assertThat(response.getResponseCode()).isEqualTo(SC_CREATED);
		ArticleRequestAndResponse aResponse = response.getResponse();

		// verify created article content in response
		aResponse.articleAssertThat().thereIsNoError().articleBodyIs(requestObject.getArticle().getBody())
				.articleDescriptionIs(requestObject.getArticle().getDescription())
				.articleTitleIs(requestObject.getArticle().getTitle())
				.articleTagListIs(requestObject.getArticle().getTagList())
				.authorIs(userRequest.getUser().getUsername());

		// Article count should be 1 after it is created
		articlesResponse = getArticleService.getArticles(queryParam);
		assertThat(articlesResponse.getResponseCode()).isEqualTo(SC_OK);
		articlesResponse.getResponse().assertThat().articleCountIs(1);

	}

	/*
	 * Testing Create Article flow with special characters
	 */
	//@Test(description = "Testing if valid user can create the article or not with special characters")
	public void testCreateArticleWithSpecialCharacters() throws Exception {
		
		
		// Article count should be 0 before it is created
		Map<String, String> queryParam = Maps.newHashMap();
		queryParam.put(Filter.author.name(), userRequest.getUser().getUsername());

		ApiResponse<ArticlesResponse> articlesResponse = getArticleService.getArticles(queryParam);
		assertThat(articlesResponse.getResponseCode()).isEqualTo(SC_OK);
		articlesResponse.getResponse().assertThat().articleCountIs(0);

		ArticleRequestAndResponse requestObject = TestDataFactory.getValidArticle();

		ApiResponse<ArticleRequestAndResponse> response = creatArticle.createArticle(userRequest.getUser().getEmail(),
				requestObject);

		assertThat(response.getResponseCode()).isEqualTo(SC_CREATED);
		ArticleRequestAndResponse aResponse = response.getResponse();

		// verify created article content in response
		aResponse.articleAssertThat().thereIsNoError().articleBodyIs(requestObject.getArticle().getBody())
				.articleDescriptionIs(requestObject.getArticle().getDescription())
				.articleTitleIs(requestObject.getArticle().getTitle())
				.articleTagListIs(requestObject.getArticle().getTagList())
				.authorIs(userRequest.getUser().getUsername());

		
		// Article count should be 1 after it is created
		articlesResponse = getArticleService.getArticles(queryParam);
		assertThat(articlesResponse.getResponseCode()).isEqualTo(SC_OK);
		articlesResponse.getResponse().assertThat().articleCountIs(1);

	}

	/*
	 * Testing create article flow without authorization
	 */
	//@Test(description = "Testing if user can create the article without token or not")
	public void testArticleCannotBeCreatedWithoutAuthorization() throws Exception {

		ArticleRequestAndResponse requestObject = TestDataFactory.getValidArticle();
		ApiResponse<ArticleRequestAndResponse> response = creatArticle.createArticle(userRequest.getUser().getEmail(),
				requestObject, false);
		assertThat(response.getResponseCode()).isEqualTo(SC_UNAUTHORIZED);
		response.getResponse().articleAssertThat().thereIsAnError().errorAssert().errorMessageIs("Unauthorized");

	}

	/*
	 * This test is failing and it is a security issue. Token should be invalidated
	 * as soon as user logins each time.
	 */

	//@Test(description = "Testing if user can create the article with existing token.")
	public void testCreateArticleWithPreviousAuthToken() throws Exception {

		ArticleRequestAndResponse requestObject = TestDataFactory.getValidArticle();

		RequestBuilder builder = RequestBuilder.builder().username(userRequest.getUser().getEmail())
				.password(ConfigLoader.getDefaultPassword()).isAuthRequired(true).pathUrl(ARTICLES)
				.requestObject(requestObject).isCachedClient(true).build();
		ApiResponse<ArticleRequestAndResponse> response = creatArticle.createArticle(builder, requestObject);
				

		assertThat(response.getResponseCode()).isEqualTo(SC_CREATED);
		ArticleRequestAndResponse aResponse = response.getResponse();

		aResponse.articleAssertThat().thereIsNoError().articleBodyIs(requestObject.getArticle().getBody())
				.articleDescriptionIs(requestObject.getArticle().getDescription())
				.articleTitleIs(requestObject.getArticle().getTitle())
				.articleTagListIs(requestObject.getArticle().getTagList())
				.authorIs(userRequest.getUser().getUsername());

		// login again to get new token
		loginService.login(userRequest);

		// use same token
		requestObject = TestDataFactory.getValidArticle();
		builder = RequestBuilder.builder().username(userRequest.getUser().getEmail()).isAuthRequired(true)
				.pathUrl(ARTICLES).requestObject(requestObject).isCachedClient(true).build();
		response = creatArticle.createArticle(builder, requestObject);

		assertThat(response.getResponseCode()).isEqualTo(SC_UNAUTHORIZED);

	}

}
