package org.javacream.util.test;

import java.lang.reflect.Method;


public class TracingDecorator extends BaseDecorator{

	@Override
	public void before(Method method, Object[] args) {
		System.out.println("TRACINGDECORATOR: Entering " + method.getName());
	}

	@Override
	public void afterReturning(Method method, Object[] args, Object result) {
		System.out.println("TRACINGDECORATOR: returning from " + method.getName() +", result=" + result);
	}

	@Override
	public void afterThrowing(Method method, Object[] args, Throwable t) {
		System.out.println("TRACINGDECORATOR: throwing from " + method.getName() +", throwable=" + t);
	}

}
