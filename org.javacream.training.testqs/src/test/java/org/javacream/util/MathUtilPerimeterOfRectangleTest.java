package org.javacream.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MathUtilPerimeterOfRectangleTest {

	public static final double HEIGHT = 10;
	public static final double WIDTH = 11;
	public static final double EXPECTED_PERIMETER = 42;
	private MathUtil mathUtil;
	
	@Before public void init() {
		mathUtil = new MathUtil();
	}
	
	@Test public void perimeterWithHeightTwoAndWidthTwentyOneIsFortytwo() {
		double result = mathUtil.perimeterOfRectangle(HEIGHT, WIDTH);
		Assert.assertEquals(EXPECTED_PERIMETER, result, 1e-9);
	}
	@Test(expected=IllegalArgumentException.class) public void perimeterWithNegativeHeightThrowsIllegalArgumentException() {
		mathUtil.perimeterOfRectangle(-1, WIDTH);
	}
	@Test(expected=IllegalArgumentException.class) public void perimeterWithNegativeWidthThrowsIllegalArgumentException() {
		mathUtil.perimeterOfRectangle(HEIGHT, -1);
	}
	
	@Test(expected=IllegalArgumentException.class) public void perimeterWithNegativeHeightAndNegativeWidthThrowsIllegalArgumentException() {
		mathUtil.perimeterOfRectangle(-1, -1);
	}
	
}
