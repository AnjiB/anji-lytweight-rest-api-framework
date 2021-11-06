package com.anji.framework.api.impl;

import static io.restassured.RestAssured.given;
import static com.anji.framework.api.utils.ConvertionUtil.transform;

import com.anji.framework.api.builder.RequestBuilder;


import io.restassured.builder.RequestSpecBuilder;

public abstract class BaseApiService<T> implements IApiService<T> {

	private Class<T> klass;

	public BaseApiService(Class<T> kClass) {
		this.klass = kClass;
	}

	@Override
	public ApiResponse<T> get(RequestBuilder builder) throws Exception {
		return new ApiResponse<T>(given().spec(getSpecBuilder(builder).build()).get(), klass);
	}

	@Override
	public ApiResponse<T> post(RequestBuilder builder) throws Exception {
		return new ApiResponse<T>(given().spec(getSpecBuilder(builder).build()).post(), klass);
	}

	@Override
	public ApiResponse<T> patch(RequestBuilder builder) throws Exception {
		return new ApiResponse<T>(given().spec(getSpecBuilder(builder).build()).patch(), klass);
	}

	@Override
	public ApiResponse<T> put(RequestBuilder builder) throws Exception {
		return new ApiResponse<T>(given().spec(getSpecBuilder(builder).build()).put(), klass);
	}

	@Override
	public ApiResponse<T> delete(RequestBuilder builder) throws Exception {
		return new ApiResponse<T>(given().spec(getSpecBuilder(builder).build()).delete(), klass);
	}

	private RequestSpecBuilder getSpecBuilder(RequestBuilder builder) throws Exception {
		return transform(builder);
	}

}
