package org.javacream.util.test;

import java.lang.reflect.Method;


public class HeapProfilingDecorator extends BaseDecorator{

	private Runtime runtime;
	private long startUsedMemory;

	{
		runtime = Runtime.getRuntime();
	}
	@Override
	public void before(Method method, Object[] args) {
		System.gc();
		startUsedMemory = runtime.totalMemory() - runtime.freeMemory(); 
	}

	@Override
	public void afterReturning(Method method, Object[] args, Object result) {
		System.gc();
		long endUsedMemory = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("Calling " + method.getName() + " used memory " + (endUsedMemory - startUsedMemory));
	}

	@Override
	public void afterThrowing(Method method, Object[] args, Throwable t) {
		
	}

}
