package org.javacream.util.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class GenericDummy implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
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
					return returnType.getConstructor(method.getParameterTypes()).newInstance(params);
				} catch (Exception e2) {
					return null;
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T createDummyFor(Class<T> interfaceType) {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		GenericDummy gd = new GenericDummy();
		Class<?>[] types = { interfaceType };
		return (T) Proxy.newProxyInstance(cl, types, gd);
	}
}
