package com.anji.framework.api.impl;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class ClientFactory {

private static final Logger LOGGER = Logger.getLogger(ClientFactory.class);
	
	private static ClientServiceCache cache = ClientServiceCache.getInstance();
	
	public static Client getClient(String username, String password, boolean isAddToCache) throws Exception {

		Client client = null;

		if (isAddToCache) {
			client = cache.getClient(username);
			if (client == null) {
				client = new Client(username, password);
				client.login();
				cache.add(username, client);
				LOGGER.info("Cached the client for the user: " + username);
			} else {
				LOGGER.info("Obtained the client from the cache for the user: " + username);
			}
			
			
		} else {
			if(username != null)
				cache.remove(username);
			client = new Client(username, password);
			client.login();
		}
		return client;
	}
	
	
	private static class ClientServiceCache {

		Cache<String, Client> cliCache;

		private ClientServiceCache() {
			cliCache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).maximumSize(10).build();
		}

		public void add(final String username, final Client client) {
			Preconditions.checkNotNull(username);
			cliCache.put(username, client);
		}

		public Client getClient(final String username) {
			return cliCache.getIfPresent(username);
		}

		public void remove(final String username) {
			cliCache.invalidate(username);
		}

		public static ClientServiceCache getInstance() {
			return new ClientServiceCache();
		}
	}
}


