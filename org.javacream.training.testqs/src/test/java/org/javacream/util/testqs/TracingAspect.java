package org.javacream.util.testqs;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TracingAspect implements InvocationHandler {
	private Object delegate;


	public void setDelegate(Object delegate) {
		this.delegate = delegate;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		System.out.println("entering " + methodName);
		try {
			Object result = method.invoke(delegate, args);
			System.out.println("returning from " + methodName);
			return result;
		} catch (Throwable e) {
			if (e instanceof InvocationTargetException) {
				e = ((InvocationTargetException)e).getTargetException();
			}
			System.out.println("throwing from " + methodName);
			throw e;
		}
	}
	
	public static <T> T aspect(T delegate) {
		ClassLoader classLoader = TracingAspect.class.getClassLoader();
		TracingAspect tracingAspect = new TracingAspect();
		tracingAspect.setDelegate(delegate);
		return (T) Proxy.newProxyInstance(classLoader, delegate.getClass().getInterfaces(), tracingAspect);
	}
}
