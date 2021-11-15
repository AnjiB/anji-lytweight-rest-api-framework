package com.anji.framework.api.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import com.anji.framework.api.impl.GenericResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.base.Preconditions;

import io.restassured.http.Header;
import io.restassured.response.Response;

/**
 * 
 * Transformation util
 * 
 * @author anjiboddupally
 */


public class TransformUtil {
	
	private TransformUtil() {};
	
	public static GenericResponse transform(Response response) {
		
		Map<String, String> headersMap = response.getHeaders().asList().stream().collect(Collectors.toMap(Header::getName, Header::getName));
		
		return GenericResponse.builder()
				.responseContentType(response.getContentType())
				.responseCookies(response.getCookies())
				.responseStatusCode(response.getStatusCode())
				.responseHeaders(headersMap)
				.responseTime(response.getTime())
				.resposeBody(response.getBody().asString())
				.build();
		
	}
	
	
	public static GenericResponse transform(CloseableHttpResponse response, HttpClientContext context, int time) {
		
		Preconditions.checkNotNull(response); 
		String responseString = null;
		try {
			responseString = response.getEntity() == null ? "" : EntityUtils.toString(response.getEntity());
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int responseCode = response.getStatusLine().getStatusCode();
		org.apache.http.Header[] headers = response.getAllHeaders();
		Map<String, String> headMap = null;
		if (ArrayUtils.isNotEmpty(headers)) {
			headMap = Arrays.stream(headers).collect(Collectors.toMap(org.apache.http.Header::getName, 
					org.apache.http.Header::getValue));
		}
		
		return GenericResponse.builder()
				.responseContentType(ContentType.get(response.getEntity()).getMimeType())
				.responseCookies(context.getCookieStore().getCookies().stream()
						.collect(Collectors.toMap(Cookie::getName, Cookie::getValue)))
				.responseStatusCode(responseCode)
				.responseHeaders(headMap)
				.responseTime(time)
				.resposeBody(responseString)
				.build();
		
	}
	
	
	public static String convertPojoToJson(Object object) throws JsonProcessingException {
		Preconditions.checkNotNull(object);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
	
	public static String convertPojoToXml(Object object) throws JsonProcessingException {
		XmlMapper xmlMapper = new XmlMapper();
		return xmlMapper.writeValueAsString(object);
	}

}
