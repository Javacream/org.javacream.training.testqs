package org.javacream.calculator.business;

import org.javacream.calculator.CircleCalculator;

public class CircleCalculatorImpl implements CircleCalculator {

	@Override
	public double area(double radius) {
		checkRadius(radius);
		return radius * radius * Math.PI;
		
	}

	@Override
	public double perimeter(double radius) {
		checkRadius(radius);
		return 2 * radius * Math.PI;
	}

	private void checkRadius(double radius){
		if (radius < 0){
			throw new IllegalArgumentException("illegal radius");
		}
	}
}
