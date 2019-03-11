package org.javacream.util;

import org.javacream.util.testqs.SpecifiedBy;

public class MathUtil {
	
	@SpecifiedBy(document="Flipchart...", version="1.0")
	public double areaOfRectangle(double height, double width) {
		validateHeightAndWith(height, width);
		return height * width;
	}

	@SpecifiedBy(document="Flipchart...", version="1.0")
	public double perimeterOfRectangle(double height, double width) {
		validateHeightAndWith(height, width);
		return 2*(height + width);
	}

	private void validateHeightAndWith(double height, double width) {
		if (height < 0 || width < 0) {
			throw new IllegalArgumentException("height=" + height + ", width=" + width);
		}

	}
}
