package org.javacream.util.math;

import org.javacream.util.math.implementation.MathUtilImpl;
import org.javacream.util.test.Specification;
import org.javacream.util.test.SpecifiedBy;
import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

@Specification(title = "Mathematische Utilities")
public class MathUtilTest {

	private MathUtilImpl mathUtilImpl;
	private double height;
	private double width;

	@Before
	public void init() {
		height = 2.0;
		width = 5.0;
		mathUtilImpl = new MathUtilImpl();
	}

	@SpecifiedBy(section = "1.1")
	@Test
	public void areaOfHeight2AndWidth5Is10() {
		double expectedArea = 10.0;
		double area = mathUtilImpl.areaOfRectangle(height, width);
		Assert.assertEquals(expectedArea, area, 1e-9);
	}

	@SpecifiedBy(section = "2.1")
	@Test
	public void perimeterOfHeight2AndWidth5Is14() {
		double expectedPerimeter = 14.0;
		double perimeter = mathUtilImpl.perimeterOfRectangle(height, width);
		Assert.assertEquals(expectedPerimeter, perimeter, 1e-9);
	}

	@SpecifiedBy(section = "1.2")
	@Test(expected = IllegalArgumentException.class)
	public void negativeHeightIsInvalidForAreaCalculation() {
		mathUtilImpl.areaOfRectangle(-5, width);
	}

	@SpecifiedBy(section = "2.2")
	@Test(expected = IllegalArgumentException.class)
	public void negativeHeightIsInvalidForPerimeterCalculation() {
		mathUtilImpl.perimeterOfRectangle(-5, width);
	}

	@SpecifiedBy(section = "1.2")
	@Test(expected = IllegalArgumentException.class)
	public void negativeWidthIsInvalidForAreaCalculation() {
		mathUtilImpl.areaOfRectangle(height, -5);
	}

	@SpecifiedBy(section = "2.2")
	@Test(expected = IllegalArgumentException.class)
	public void negativeWidthIsInvalidForPerimeterCalculation() {
		mathUtilImpl.perimeterOfRectangle(height, -5);
	}
}
