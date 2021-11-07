package com.anji.rest.api.asserts;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.AbstractAssert;

import com.anji.rest.api.pojo.ErrorsJson;

/**
 * Error Response Assertions
 * 
 * @author anjiboddupally
 *
 */
public class ErrorResponseAssert extends AbstractAssert<ErrorResponseAssert, ErrorsJson> {

	private String errorInfoMessage = "Expected is: %s but actual is %s";

	public ErrorResponseAssert(ErrorsJson actual) {
		super(actual, ErrorResponseAssert.class);
	}

	public ErrorResponseAssert errorMessageIs(String expMessage) {
		assertThat(actual.getBody().get(0)).as(format(errorInfoMessage, expMessage, actual.getBody().get(0)))
				.isEqualTo(expMessage);
		return this;
	}
}
