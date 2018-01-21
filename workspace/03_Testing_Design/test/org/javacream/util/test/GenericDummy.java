package org.javacream.util.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class GenericDummy implements InvocationHandler {

	public static final String DEFAULT_STRING = "Hugo";
	public static final double DEFAULT_DOUBLE = 4.2;
	public static final long DEFAULT_LONG = 42;
	public static final boolean DEFAULT_BOOLEAN = false;
	public static final char DEFAULT_CHAR = 'a';
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Class<?> returnType = method.getReturnType();
		if (returnType == Void.class) {
			return null;
		}
		if (returnType == String.class) {
			return DEFAULT_STRING;
		}
		if (returnType == Boolean.class || returnType == boolean.class) {
			return DEFAULT_BOOLEAN;
		}
		if (returnType == Double.class || returnType == double.class) {
			return DEFAULT_DOUBLE;
		}
		if (returnType == Float.class || returnType == float.class) {
			return (float)DEFAULT_DOUBLE;
		}
		if (returnType == Long.class || returnType == long.class) {
			return DEFAULT_LONG;
		}
		if (returnType == Integer.class || returnType == int.class) {
			return (int)DEFAULT_LONG;
		}
		if (returnType == Short.class || returnType == short.class) {
			return (short) DEFAULT_LONG;
		}
		if (returnType == Byte.class || returnType == byte.class) {
			return (byte) DEFAULT_LONG;
		}
		if (returnType == Character.class || returnType == char.class) {
			return DEFAULT_CHAR;
		}

		try {
			Object result = returnType.newInstance();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static <T> T createDummy(Class<T> interfaceType){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Class<?>[] interfaces = new Class<?>[]{interfaceType};
		GenericDummy dummy = new GenericDummy();
		Object decorator = Proxy.newProxyInstance(classLoader, interfaces, dummy);
		return interfaceType.cast(decorator);
	}

}
