package org.javacream.calculator.business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

//TEST_INZEST!
public class CircleCalculatorTest {

	private CircleCalculatorImpl impl;
	private static final double RADIUS = 4.2;
	private static final double ILLEGAL_RADIUS = -4.2;
	@Before public void init(){
		impl = new CircleCalculatorImpl();
	}
	@Test public void testArea(){
		double expectedArea = RADIUS * RADIUS * Math.PI;
		double area = impl.area(RADIUS);
		Assert.assertEquals(expectedArea, area, 1e-9);
	}
	@Test public void testPerimeter(){
		double expectedPerimeter = 2 * RADIUS * Math.PI;
		double perimeter = impl.perimeter(RADIUS);
		Assert.assertEquals(expectedPerimeter, perimeter, 1e-9);
		
	}

	@Test(expected=IllegalArgumentException.class) public void testAreaIllegal(){
		impl.area(ILLEGAL_RADIUS);
	}
	@Test(expected=IllegalArgumentException.class) public void testPerimeterIllegal(){
		impl.area(ILLEGAL_RADIUS);
	}

}
