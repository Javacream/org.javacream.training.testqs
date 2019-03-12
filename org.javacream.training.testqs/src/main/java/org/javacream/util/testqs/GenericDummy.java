package org.javacream.util.testqs;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class GenericDummy implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Class<?> returnType = method.getReturnType();
		if (returnType == String.class) {
			return "Hello";
		} else if (returnType == Integer.class || returnType == int.class) {
			return 42;
		} else if (returnType == Double.class || returnType == double.class) {
			return 47.11;
		} else if (returnType == Long.class || returnType == long.class) {
			return 42l;
		} else if (returnType == Boolean.class || returnType == boolean.class) {
			return true;
		} else {
			try {
				return returnType.newInstance();
			} catch (Exception e) {
				try {
					return returnType.getConstructor(method.getParameterTypes()).newInstance(args);
				} catch (Exception e2) {
					return null;
				}
			}
		}
	}
}
