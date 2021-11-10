package com.anji.framework.api.impl;

import static com.anji.framework.api.constants.ResponseStatusCode.SC_OK;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;

import com.anji.rest.api.pojo.Request;
import com.anji.rest.api.pojo.UserResponse;
import com.anji.rest.api.pojo.User;
import com.anji.rest.api.service.LoginService;

/**
 * 
 * Client to login to the system and stores the auth-key
 * 
 * @author anjiboddupally
 */

public class Client {
	
	private static final Logger LOGGER = Logger.getLogger(Client.class);
	
	private String username;
	
	private String password;
	
	private String authKey;
	
	public Client(String userString, String paString) {
		this.username = userString;
		this.password = paString;
	}
	
	public void login() throws Exception {
		// call login api
		// extract key from login response
	
		Request request = new Request();
		User user = new User();
		user.setEmail(username);
		user.setPassword(password);
		request.setUser(user);
		
		LoginService<UserResponse> loginService = new LoginService<>(UserResponse.class);
		ApiResponse<UserResponse> loginResponse = loginService.login(request);
		Assertions.assertThat(loginResponse.getResponseCode()).as("Login is not successful").isEqualTo(SC_OK);
		
		authKey = loginResponse.getResponse().getUser().getToken();
		LOGGER.info("Login is successful for the user: " + username);
	}
	
	public String getAuthKey() {
		return authKey;
	}

}
