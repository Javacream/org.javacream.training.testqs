package org.javacream.util.decorator;

public class TracingDecorator extends BaseDecorator {
	public static <T> T decorate(T delegate) {
		return BaseDecorator.decorate(delegate, new TracingDecorator());
	}

	@Override
	protected void before(String methodName, Object[] params) {
		System.out.println("entering " + methodName);
	}

	@Override
	protected void returning(String methodName, Object[] params, Object result) {
		System.out.println("returning from " + methodName);
	}

	@Override
	protected void throwing(String methodName, Object[] params, Throwable t) {
		System.out.println("throwing from " + methodName);
	}

}
