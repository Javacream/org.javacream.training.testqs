package org.javacream.util.test;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;


/**
 * Test decorator that records all method calls in a Invocation-List. The list
 * will be written to a specified file at shutdown using serialization.
 * 
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto rainersawitzki@javacream.org
 * 
 */
public class RecordingDecorator implements InvocationHandler {

	private Object delegate;
	private LinkedList<Invocation> invocations;

	public RecordingDecorator(final String filename) {
		Runnable writer = new Runnable() {

			@Override
			public void run() {
				try {
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(
							new FileOutputStream(filename));
					objectOutputStream.writeObject(invocations);
					objectOutputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e.getMessage());
				}
			}
		};
		Runtime.getRuntime().addShutdownHook(new Thread(writer));
		invocations = new LinkedList<Invocation>();
	}

	public void setDelegate(Object delegate) {
		this.delegate = delegate;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] params)
			throws Throwable {
		Invocation invocation = Invocation.create(method, params);
		try {
			Object result = method.invoke(delegate, params);
			invocation.setResult(result);
			return result;
		} catch (Throwable t) {
			invocation.setThrowable(t);
			if (t instanceof InvocationTargetException){
				t = ((InvocationTargetException)t).getTargetException();
			}
			throw t;
		} finally {
			invocations.add(invocation);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T decorate(T toDecorate, String filename) {
		ClassLoader classLoader = RecordingDecorator.class.getClassLoader();
		Class[] interfaces = toDecorate.getClass().getInterfaces();
		RecordingDecorator decorator = new RecordingDecorator(filename);
		decorator.setDelegate(toDecorate);
		return (T) Proxy.newProxyInstance(classLoader, interfaces, decorator);

	}

}
