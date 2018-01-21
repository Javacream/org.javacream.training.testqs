package org.javacream.util.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.apache.commons.lang.ClassUtils;


public class PerformanceInvocationHandler implements InvocationHandler{
	private Object delegate;

	public void setDelegate(Object delegate) {
		this.delegate = delegate;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		long start = System.currentTimeMillis();
		String methodName = method.getName();
		try{
			Object result = method.invoke(delegate, args);
			return result;
		}
		catch(InvocationTargetException ite){
			Throwable t = ite.getTargetException();
			throw t;
		}
		finally{
			System.out.println("calling method " + methodName +", took " + (System.currentTimeMillis() - start));
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T decorate(T delegate){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		List interfaceList = ClassUtils.getAllInterfaces(delegate.getClass());
		Class[] interfaces = new Class<?>[interfaceList.size()];
		interfaceList.toArray(interfaces);
		PerformanceInvocationHandler performaceDecorator = new PerformanceInvocationHandler();
		performaceDecorator.setDelegate(delegate);
		Object decorator = Proxy.newProxyInstance(classLoader, interfaces, performaceDecorator);
		return (T)decorator;
	}

}
