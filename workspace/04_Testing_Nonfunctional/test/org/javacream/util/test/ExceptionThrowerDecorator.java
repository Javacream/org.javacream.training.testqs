package org.javacream.util.test;

import java.lang.reflect.Method;

public class ExceptionThrowerDecorator extends BaseDecorator {

	@Override
	public void before(Method method, Object[] args) {
		throw new RuntimeException("From ExceptionThrowerDecorator");
	}

	@Override
	public void afterReturning(Method method, Object[] args, Object result) {
	}

	@Override
	public void afterThrowing(Method method, Object[] args, Throwable t) {
	}

}
