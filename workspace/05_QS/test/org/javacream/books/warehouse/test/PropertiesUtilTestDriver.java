package org.javacream.books.warehouse.test;

import java.util.Properties;

import org.javacream.books.warehouse.PropertiesUtil;

public class PropertiesUtilTestDriver implements PropertiesUtil {
	@Override
	public Properties getProperties(String name) {
		final String TEST_ISBN1 = "1-123-12345-1-TEST";
		final String TEST_ISBN1_STOCK = "10";
		final String TEST_ISBN2 = "2-123-12345-1-TEST";
		final String TEST_ISBN2_STOCK = "0";
		final String TEST_ISBN3 = "3-123-12345-1-TEST";
		final String TEST_ISBN3_STOCK = "20";
		final String TEST_ISBN4 = "4-123-12345-1-TEST";
		final String TEST_ISBN4_STOCK = "40";

		Properties props = new Properties();
		props.setProperty(TEST_ISBN1, TEST_ISBN1_STOCK);
		props.setProperty(TEST_ISBN2, TEST_ISBN2_STOCK);
		props.setProperty(TEST_ISBN3, TEST_ISBN3_STOCK);
		props.setProperty(TEST_ISBN4, TEST_ISBN4_STOCK);
		
		return props;
	}

}
