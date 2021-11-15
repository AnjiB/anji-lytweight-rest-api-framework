package com.anji.framework.api.impl.client;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * A service that gives Client object. You can decide if you want to cache the client so 
 * that you don't need login to the system each time.
 * 
 * @author anjiboddupally
 */

public class ClientService {

private static final Logger LOGGER = Logger.getLogger(ClientService.class);
	
	private static ClientServiceCache cache = ClientServiceCache.getInstance();
	
	public static Client getClient(String username, String password, boolean isAddToCache) throws Exception {

		LOGGER.info("Getting client for the user: " + username);
		
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
			LOGGER.info("Not getting user from cache");
			if(username != null)
				cache.remove(username);
			client = new Client(username, password);
			LOGGER.info("Login as " + username);
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


