package org.javacream.util.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.apache.commons.lang.ClassUtils;


public class TracingInvocationHandler implements InvocationHandler{
	private Object delegate;

	public void setDelegate(Object delegate) {
		this.delegate = delegate;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		String methodName = method.getName();
		try{
			System.out.println("INVOCATIONHANDLER: Entering " + methodName);
			Object result = method.invoke(delegate, args);
			System.out.println("INVOCATIONHANDLER: returning from " + methodName +", result=" + result);
			return result;
		}
		catch(InvocationTargetException ite){
			Throwable t = ite.getTargetException();
			System.out.println("INVOCATIONHANDLER: throwing from " + methodName +", throwable=" + t);
			throw t;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T decorate(T delegate){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		List interfaceList = ClassUtils.getAllInterfaces(delegate.getClass());
		Class[] interfaces = new Class<?>[interfaceList.size()];
		interfaceList.toArray(interfaces);
		TracingInvocationHandler tracingDecorator = new TracingInvocationHandler();
		tracingDecorator.setDelegate(delegate);
		Object decorator = Proxy.newProxyInstance(classLoader, interfaces, tracingDecorator);
		return (T)decorator;
	}

}
