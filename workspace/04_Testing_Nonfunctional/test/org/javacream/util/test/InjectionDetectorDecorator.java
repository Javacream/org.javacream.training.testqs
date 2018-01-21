package org.javacream.util.test;

import java.lang.reflect.Method;

public class InjectionDetectorDecorator extends BaseDecorator {

	@Override
	public void before(Method method, Object[] args) {
		if (args != null) {
			for (Object arg : args) {
				if (arg instanceof String) {
					checkString(arg.toString());
				}
			}
		}
	}

	private void checkString(String string) {
		if (string.contains("'") || string.contains("<")) {
			throw new IllegalArgumentException("detected possible injection!");
		}
	}

	@Override
	public void afterReturning(Method method, Object[] args, Object result) {
		if (result instanceof String){
			checkString(result.toString());
		}
	}

	@Override
	public void afterThrowing(Method method, Object[] args, Throwable t) {

	}

}
