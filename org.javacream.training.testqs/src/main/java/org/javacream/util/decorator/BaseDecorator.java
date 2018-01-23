package org.javacream.util.decorator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class BaseDecorator implements InvocationHandler {
	private Object delegate;

	public void setDelegate(Object delegate) {
		this.delegate = delegate;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
		String methodName = method.getName();
		before(methodName, params);
		try {
			Object result = method.invoke(delegate, params);
			returning(methodName, params, result);
			return result;
		} catch (Throwable t) {
			if (t instanceof InvocationTargetException) {
				t = ((InvocationTargetException) t).getTargetException();
			}
			throwing(methodName, params, t);
			throw t;
		}
	}
	@SuppressWarnings("unchecked")
	public static <T> T decorate(T delegate, BaseDecorator handler) {
		handler.setDelegate(delegate);
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Class<?>[] interfaces = delegate.getClass().getInterfaces();
		return (T)Proxy.newProxyInstance(classLoader, interfaces, handler);
	}
	protected abstract void before(String methodName, Object[] params);
	protected abstract void returning(String methodName, Object[] params, Object result);
	protected abstract void throwing(String methodName, Object[] params, Throwable t);

}
