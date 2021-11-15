package com.anji.framework.api.impl.apachehttp;

import static com.anji.framework.api.enums.ApiContentType.JSON;
import static com.anji.framework.api.enums.ApiContentType.XML;
import static com.anji.framework.api.utils.TransformUtil.convertPojoToJson;
import static com.anji.framework.api.utils.TransformUtil.convertPojoToXml;
import static com.anji.framework.api.utils.TransformUtil.transform;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.log4j.Logger;

import com.anji.framework.api.builder.RequestBuilder;
import com.anji.framework.api.enums.ApiHeaders;
import com.anji.framework.api.impl.GenericResponse;
import com.anji.framework.api.impl.IApiService;
import com.anji.framework.api.impl.client.Client;
import com.anji.framework.api.impl.client.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Maps;



/**
 * 
 * Apache HttpClient Implementation of IAPIService.
 * 
 * @author anjiboddupally
 */

public class ApacheHttpImpl implements IApiService {

	private HttpClientContext clientContext;

	private CloseableHttpClient client;

	private static final Logger LOGGER = Logger.getLogger(ApacheHttpImpl.class);

	private String baseUri;

	public ApacheHttpImpl(String bString) {
		baseUri = bString;
	}

	@Override
	public GenericResponse get(RequestBuilder builder) throws Exception {
		StopWatch sw = new StopWatch();
		sw.start();
		client = getClient(builder);
		CloseableHttpResponse response = getOrDelete(builder, new HttpGet());
		sw.stop();

		return transform(response, clientContext, (int) sw.getTime() / 1000);
	}

	@Override
	public GenericResponse post(RequestBuilder builder) throws Exception {
		StopWatch sw = new StopWatch();
		sw.start();
		client = getClient(builder);
		CloseableHttpResponse response = postOrPatchOrPut(builder, new HttpPost());
		sw.stop();

		return transform(response, clientContext, (int) sw.getTime() / 1000);
	}

	@Override
	public GenericResponse patch(RequestBuilder builder) throws Exception {
		StopWatch sw = new StopWatch();
		sw.start();
		client = getClient(builder);
		CloseableHttpResponse response = postOrPatchOrPut(builder, new HttpPatch());
		sw.stop();

		return transform(response, clientContext, (int) sw.getTime() / 1000);
	}

	@Override
	public GenericResponse put(RequestBuilder builder) throws Exception {
		StopWatch sw = new StopWatch();
		sw.start();
		client = getClient(builder);
		CloseableHttpResponse response = postOrPatchOrPut(builder, new HttpPut());
		sw.stop();

		return transform(response, clientContext, (int) sw.getTime() / 1000);
	}

	@Override
	public GenericResponse delete(RequestBuilder builder) throws Exception {
		StopWatch sw = new StopWatch();
		sw.start();
		client = getClient(builder);
		CloseableHttpResponse response = getOrDelete(builder, new HttpDelete());

		return transform(response, clientContext, (int) sw.getTime() / 1000);
	}

	private CloseableHttpResponse postOrPatchOrPut(RequestBuilder builder,
			HttpEntityEnclosingRequestBase postOrPatchOrPut) throws Exception {

		CloseableHttpResponse response = null;

		// set body

		String body = getBodyAsString(builder);
		LOGGER.info("Request Body: " + body);
		HttpEntity entity = new ByteArrayEntity(body.getBytes(UTF_8));
		postOrPatchOrPut.setEntity(entity);
		postOrPatchOrPut.setHeader(ApiHeaders.CONTENT_TYPE.getHeader(), builder.getContentType().getContentType());

		try {
			response = executeRequest(builder, postOrPatchOrPut);
			return response;
		} finally {
			postOrPatchOrPut.releaseConnection();
			if (response != null)
				response.close();
		}
	}

	private CloseableHttpResponse getOrDelete(RequestBuilder builder, HttpRequestBase getOrDelete) throws Exception {

		CloseableHttpResponse response = null;

		try {
			response = executeRequest(builder, getOrDelete);
			return response;

		} finally {
			getOrDelete.releaseConnection();
			if (response != null)
				response.close();
		}

	}

	private String getBodyAsString(RequestBuilder reqBuilder) throws JsonProcessingException {
		String body = "";

		if (Objects.nonNull(reqBuilder.getRequestObject())) {
			if (reqBuilder.getContentType().equals(JSON)) {
				body = convertPojoToJson(reqBuilder.getRequestObject());
			} else if (reqBuilder.getContentType().equals(XML)) {
				body = convertPojoToXml(reqBuilder.getRequestObject());
			} else {
				throw new IllegalArgumentException("Unsupported content type");
			}
		}

		return body;
	}

	private CloseableHttpResponse executeRequest(RequestBuilder reqBuilder, HttpRequestBase baseRequest)
			throws Exception {

		URI uri = new URI(getFullURL.apply(reqBuilder).toString());
		LOGGER.info("URL is:\t" + uri.toString());
		baseRequest.setURI(uri);

		clientContext = HttpClientContext.create();

		if (reqBuilder.getCookies() != null && !reqBuilder.getCookies().isEmpty()) {
			BasicCookieStore cookieStore = new BasicCookieStore();
			for (Map.Entry<String, String> entry : reqBuilder.getCookies().entrySet()) {
				BasicClientCookie cookie = new BasicClientCookie(entry.getKey(), entry.getValue());
				cookie.setPath("/");
				cookieStore.addCookie(cookie);
			}
			clientContext.setCookieStore(cookieStore);
		}

		Client apiClient = null;

		Map<String, String> reqHeaders = Maps.newHashMap();
		if (reqBuilder.isAuthRequired()) {
			try {
				apiClient = ClientService.getClient(reqBuilder.getUsername(), reqBuilder.getPassword(),
						reqBuilder.isCachedClient());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqHeaders.put(ApiHeaders.AUTH.getHeader(), "Token " + apiClient.getAuthKey());
		} else
			LOGGER.info("Auth token is not provided");

		// String authentication = getAuthHeader(superUsername, superPassword);
		// reqHeaders.put(ApiHeaders.AUTH, authentication);

		reqHeaders.put(ApiHeaders.CONTENT_TYPE.getHeader(), reqBuilder.getContentType().getContentType());
		if (reqBuilder.getReqHeaders() != null && !reqBuilder.getReqHeaders().isEmpty())
			reqHeaders.putAll(reqBuilder.getReqHeaders());
		for (Map.Entry<String, String> entry : reqHeaders.entrySet()) {
			baseRequest.setHeader(entry.getKey(), entry.getValue());
		}

		return client.execute(baseRequest, clientContext);

	}

	private Function<RequestBuilder, URL> getFullURL = builder -> {

		StringBuilder sb = new StringBuilder(baseUri);
		sb.append("/").append(builder.getPathUrl());

		if (builder.getQueryParameters() != null && !builder.getQueryParameters().isEmpty()) {
			sb.append("?");
			List<NameValuePair> pair = new ArrayList<NameValuePair>();
			builder.getQueryParameters().forEach((key, value) -> {
				pair.add(new BasicNameValuePair(key, value));
			});
			sb.append(URLEncodedUtils.format(pair, UTF_8));
		}

		try {
			return new URL(sb.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;
	};

	private CloseableHttpClient getClient(RequestBuilder requestBuilder) throws Exception {
		int timeout = Long.valueOf(requestBuilder.getWaitTime() / 1000).intValue();
		LOGGER.info("Wait time : " + timeout);
		HttpClientBuilder builder = HttpClientBuilder.create();

		PoolingHttpClientConnectionManager connMgr = null;

		if (requestBuilder.isIgnoreCerts()) {

			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
					return true;
				}
			}).build();
			builder.setSSLContext(sslContext);

			HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
			SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register("http", PlainConnectionSocketFactory.getSocketFactory())
					.register("https", sslSocketFactory).build();

			connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		} else {
			connMgr = new PoolingHttpClientConnectionManager();
			connMgr.setValidateAfterInactivity(5000);
		}

		builder.setConnectionManager(connMgr);

		RequestConfig config = RequestConfig.custom().setSocketTimeout(timeout).setConnectionRequestTimeout(timeout)
				.setConnectTimeout(timeout).build();

		builder.setDefaultRequestConfig(config);

		return builder.build();
	}

}
