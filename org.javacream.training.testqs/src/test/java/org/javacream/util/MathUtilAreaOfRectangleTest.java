package org.javacream.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MathUtilAreaOfRectangleTest {

	public static final double HEIGHT = 2;
	public static final double WIDTH = 21;
	public static final double EXPECTED_AREA = 42;
	private MathUtil mathUtil;
	
	@Before public void init() {
		mathUtil = new MathUtil();
	}
	
	@Test public void areaWithHeightTwoAndWidthTwentyOneIsFortytwo() {
		double result = mathUtil.areaOfRectangle(HEIGHT, WIDTH);
		Assert.assertEquals(EXPECTED_AREA, result, 1e-9);
	}
	@Test(expected=IllegalArgumentException.class) public void areaWithNegativeHeightThrowsIllegalArgumentException() {
		mathUtil.areaOfRectangle(-1, WIDTH);
	}
	@Test(expected=IllegalArgumentException.class) public void areaWithNegativeWidthThrowsIllegalArgumentException() {
		mathUtil.areaOfRectangle(HEIGHT, -1);
	}
	
	@Test(expected=IllegalArgumentException.class) public void areaWithNegativeHeightAndNegativeWidthThrowsIllegalArgumentException() {
		mathUtil.areaOfRectangle(-1, -1);
	}
	
}
