package com.anji.framework.api.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;

/**
 * Api Util
 *
 * @author anjiboddupally
 */

public class ApiUtil {

	private static final String CONNECTION_TIMEOUT = "http.connection.timeout";

	private static final String SOCKET_TIMEOUT = "http.socket.timeout";

	private static final Logger LOGGER = Logger.getLogger(ApiUtil.class);

	private ApiUtil() {
	};

	public static RestAssuredConfig getConfig(long timeout) {
		RestAssuredConfig restAssuredConfig = new RestAssuredConfig();
		restAssuredConfig.httpClient(HttpClientConfig.httpClientConfig().setParam(CONNECTION_TIMEOUT, timeout)
				.setParam(SOCKET_TIMEOUT, timeout));
		return restAssuredConfig;
	}

	public static void poll(Callable<Boolean> callable, Duration pollTime, Duration waitTime) {

		Instant currentTime = Instant.now();

		Instant endTime = currentTime.plus(waitTime);

		while (true) {

			try {
				if (callable.call().booleanValue()) {
					LOGGER.info("Condition satisfied... breaking the loop");
					break;
				}

				// check if waitTime is crossed the currentClockTime
				if (currentTime.isAfter(endTime)) {
					LOGGER.info("Wait time is exceeded... breaking the loop");
					break;
				}

				// wait for pollTime
				Thread.sleep(pollTime.toMillis());

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

	}
	
	

}
