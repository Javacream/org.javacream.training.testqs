package org.javacream.util;

import java.util.Properties;

public abstract class PropertiesUtil {

	public static Properties getProperties(String name) {
		Properties properties = new Properties();
		try {
			properties.load(PropertiesUtil.class.getResourceAsStream(name));
			return properties;
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}

	}
}
