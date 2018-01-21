package org.javacream.books.warehouse.business;

import java.util.Properties;

import org.javacream.books.warehouse.business.SimplePropertiesUtil;
import org.junit.Assert;
import org.junit.Test;

public class PropertiesUtilUnitTest {

	@Test
	public void testBooksProperties(){
		SimplePropertiesUtil simplePropertiesUtil = new SimplePropertiesUtil();
		Properties props = simplePropertiesUtil.getProperties("books-store.properties");
		Assert.assertNotNull(props);
	}
}
