package org.javacream.util.math.implementation;

import org.javacream.util.math.api.MathUtil;

public class MathUtilImpl implements MathUtil {

	@Override
	public double areaOfRectangle(double height, double width) {
		check(height, width);
		return height * width;
	}

	private void check(double height, double width) {
		if (height < 0 || width < 0) {
			throw new IllegalArgumentException("not valid: height=" + height + ", width=" + width);
		}
	}

	@Override
	public double perimeterOfRectangle(double height, double width) {
		check(height, width);
		return 2*(height + width);
	}

}
