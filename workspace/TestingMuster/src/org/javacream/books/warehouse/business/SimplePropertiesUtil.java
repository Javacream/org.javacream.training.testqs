package org.javacream.books.warehouse.business;

import java.io.IOException;
import java.util.Properties;

import org.javacream.books.warehouse.PropertiesUtil;

public class SimplePropertiesUtil implements PropertiesUtil {

	/* (non-Javadoc)
	 * @see org.javacream.books.warehouse.business.withinterfaces.PropertiesUtil#getProperties(java.lang.String)
	 */
	@Override
	public Properties getProperties(String name) {
		Properties properties = new Properties();
		try {
			properties.load(SimplePropertiesUtil.class.getResourceAsStream(name));
			return properties;
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}

	}
}
