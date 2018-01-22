package org.javacream.training.books.warehouse.business;

import java.util.Properties;

import org.javacream.books.warehouse.business.PropertiesUtil;
import org.junit.Assert;
import org.junit.Test;

public class PropertiesUtilTest {

	@Test public void fileTestPropertiesIsRead() {
		String fileName = "/test.properties";
		Properties properties = PropertiesUtil.getProperties(fileName);
		Assert.assertNotNull(properties);
	}
	@Test(expected = IllegalArgumentException.class) public void unknownPropertiesFileThrowsException() {
		String unknownFileName = "/&%&%$.properties";
		PropertiesUtil.getProperties(unknownFileName);
	}
}
