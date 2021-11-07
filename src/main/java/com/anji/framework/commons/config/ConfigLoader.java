package com.anji.framework.commons.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;


/**
 * Loads the configuration from appConfiguration.yaml file
 * 
 * @author anjiboddupally
 *
 */
public class ConfigLoader {
	
	private static Map<String, String> config = new HashMap<String, String>();


	/**
	 * Loads the configuration from appConfiguration.yaml file
	 */
	public static void loadCongifuration() {

		String autEnvironment = System.getProperty("autEnvironment");
		if (autEnvironment == null) {
			autEnvironment = "DEV";
		}
		if (autEnvironment.equalsIgnoreCase("STG") || autEnvironment.equalsIgnoreCase("STAGING")) {
			autEnvironment = "QA";
		} else if ((autEnvironment.equalsIgnoreCase("DEV")) || (autEnvironment.equalsIgnoreCase("DEVELOPMENT"))) {
			autEnvironment = "DEV";
		}

		// to be used further to know current running environment
		config.put("AUT_ENVIRONMENT", autEnvironment);

		loadAppConfiguration(autEnvironment);
	}

	/**
	 * Returns the map of key value pair of configuration in yaml file.
	 * 
	 * @return Map
	 */
	public static Map<String, String> getConfig() {
		return config;
	}

	@SuppressWarnings("unchecked")
	private static void loadAppConfiguration(String autEnvironment) {
		Yaml yaml = new Yaml();
		try(InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("appConfiguration.yaml")) {
			Map<String, Map<String, String>> YFile = (Map<String, Map<String, String>>) yaml.load(input);
			addToConfig(YFile.get(autEnvironment));
		} catch (YAMLException ye) {
			ye.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static void addToConfig(Map<String, String> m) {
		Iterator<String> iterator = m.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			config.put(key, m.get(key));
		}
	}
	
	public static String getBaseUrl() {
		return getConfig().get("BASE_URL");
	}
	
	public static String getDefaultPassword() {
		return getConfig().get("DEFAULT_PASSWORD");
	}
}
