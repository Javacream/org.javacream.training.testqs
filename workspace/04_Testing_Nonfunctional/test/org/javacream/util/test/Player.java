package org.javacream.util.test;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Dynamic proxy that replays a serialized invocation list. The retrieved
 * invocation will be removed, so calls must be executed in exactly the same
 * order as during recording.
 * 
 * 
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto rainersawitzki@javacream.org
 * 
 */
public class Player implements InvocationHandler {

	private LinkedList<Invocation> invocations;

	@SuppressWarnings("unchecked")
	public Player(final String filename) {
		try(ObjectInputStream objectInputStream = new ObjectInputStream(
				new FileInputStream(filename));) {
			invocations = (LinkedList<Invocation>) objectInputStream
					.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] params)
			throws Throwable {
		Invocation invocation = invocations.removeFirst();
		if (invocation.compareInvocationParams(Invocation
				.create(method, params))) {
			Throwable throwable = invocation.getThrowable();
			if (throwable != null) {
				throw throwable;
			} else {
				return invocation.getResult();
			}
		} else {
			throw new IllegalArgumentException(
					"Cannot find Invocation: method=" + method + ", params="
							+ toString(params));
		}

	}

	private String toString(Object[] params) {
		if (params == null) {
			return "no params";
		} else {
			return Arrays.asList(params).toString();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T createPlayer(Class toImplement, String filename) {
		ClassLoader classLoader = Player.class.getClassLoader();
		Class[] interfaces = { toImplement };
		Player decorator = new Player(filename);
		return (T) Proxy.newProxyInstance(classLoader, interfaces, decorator);

	}

}
