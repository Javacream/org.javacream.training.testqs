package org.javacream.util.testqs;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TracingAspectTest {

	@Test public void testList() {
		List<String> names = new ArrayList<>();
		names = TracingAspect.aspect(names);
		names.add("Hugo");
		names.add("Emil");
		names.add("Franz");
		System.out.println(names.size());
		System.out.println(names);
		
	}
}
