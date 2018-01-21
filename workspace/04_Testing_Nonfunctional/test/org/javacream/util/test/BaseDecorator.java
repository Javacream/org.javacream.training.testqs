package org.javacream.util.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.apache.commons.lang.ClassUtils;


public abstract class BaseDecorator implements InvocationHandler{
	private Object delegate;

	public void setDelegate(Object delegate) {
		this.delegate = delegate;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		before(method, args);
		try{
			Object result = method.invoke(delegate, args);
			afterReturning(method, args, result);
			return result;
		}
		catch(InvocationTargetException ite){
			Throwable t = ite.getTargetException();
			afterThrowing(method, args, t);
			throw t;
		}
	}

	public abstract void before(Method method, Object[] args) ;
	public abstract void afterReturning(Method method, Object[] args, Object result) ;
	public abstract void afterThrowing(Method method, Object[] args, Throwable t) ;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T decorate(T delegate, Class<? extends BaseDecorator> handlerClass){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		List interfaceList = ClassUtils.getAllInterfaces(delegate.getClass());
		Class[] interfaces = new Class<?>[interfaceList.size()];
		interfaceList.toArray(interfaces);
		BaseDecorator baseDecorator;
		try {
			baseDecorator = handlerClass.newInstance();
			baseDecorator.setDelegate(delegate);
			Object decorator = Proxy.newProxyInstance(classLoader, interfaces, baseDecorator);
			return (T)decorator;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

}
