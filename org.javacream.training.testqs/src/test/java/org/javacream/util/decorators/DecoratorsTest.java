package org.javacream.util.decorators;

import java.util.ArrayList;
import java.util.List;

import org.javacream.util.decorator.TracingDecorator;
import org.junit.Assert;
import org.junit.Test;

public class DecoratorsTest {

	@Test public void testWithList() {
		List<String> names = new ArrayList<>();
		System.out.println(names.getClass().getName());
		names = TracingDecorator.decorate(names);
		System.out.println(names.getClass().getName());
		names.add("Hugo");
		names.add("Emil");
		names.add("Egon");
		Assert.assertTrue(names.size() == 3);
		
	}
}
