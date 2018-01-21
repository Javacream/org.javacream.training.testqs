package org.javacream.util.test;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.lang.SerializationUtils;

/**
 * Decorates an object with network semantics:
 * <ul>
 * <li>Makes a deep copy of all parameters using serialization. Therefor parameters are passes call-by-value.</li>
 * <li>Makes a deep copy of results or exception objects using serialization. Therefor results and exceptions are passes call-by-value.</li>
 * <li>A configurable delay is used to simulate network latency.</li>
 * </ul>
 * 
 * usage:
 * <code>
 * interfaceType decoratedInterface = NetworkSimulatorDecorator.decorate(interfaceObject, delay)  
 * </code>
 * 
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto rainersawitzki@javacream.org
 *
 */
public class NetworkSimulatorInvocationHandler implements InvocationHandler{

	private Object delegate;
	private long delay;
	private boolean timeout;

	public void setDelegate(Object delegate) {
		this.delegate = delegate;
	}
	public void setDelay(long delay) {
		this.delay = delay;
	}
	public void setTimeout(boolean timeout) {
		this.timeout = timeout;
	}


	
	@Override
	public Object invoke(Object proxy, Method method, Object[] params)
			throws Throwable {
		Object[] clone = (Object[]) SerializationUtils.clone(params);
		if (delay > 0) {
			Thread.sleep(delay);
		}
		if (timeout){
			throw new RuntimeException("timeout after " + delay);
		}
		try {
			Object result = method.invoke(delegate, clone);
			return SerializationUtils.clone((Serializable) result);
		} catch (Throwable t) {
			Throwable reThrowable;
			if (t instanceof InvocationTargetException) {
				InvocationTargetException ite = (InvocationTargetException) t;
				reThrowable = ite.getTargetException();

			} else {
				reThrowable = t;
			}
			throw (Throwable) SerializationUtils.clone(reThrowable);
		}

	}

	@SuppressWarnings("unchecked")
	public static <T> T decorate(T toDecorate, long delay) {
		ClassLoader classLoader = NetworkSimulatorInvocationHandler.class
				.getClassLoader();
		Class<?>[] interfaces = toDecorate.getClass().getInterfaces();
		NetworkSimulatorInvocationHandler decorator = new NetworkSimulatorInvocationHandler();
		decorator.setDelay(delay);
		decorator.setDelegate(toDecorate);
		return (T) Proxy.newProxyInstance(classLoader, interfaces, decorator);

	}

	
	@SuppressWarnings("unchecked")
	public static <T> T decorate(T toDecorate, long delay, boolean timeout) {
		ClassLoader classLoader = NetworkSimulatorInvocationHandler.class
				.getClassLoader();
		Class<?>[] interfaces = toDecorate.getClass().getInterfaces();
		NetworkSimulatorInvocationHandler decorator = new NetworkSimulatorInvocationHandler();
		decorator.setDelay(delay);
		decorator.setTimeout(timeout);
		decorator.setDelegate(toDecorate);
		return (T) Proxy.newProxyInstance(classLoader, interfaces, decorator);

	}

}
