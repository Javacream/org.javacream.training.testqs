package org.javacream.util.test;

import java.lang.reflect.Method;

public class BlockingDecorator extends BaseDecorator {

	@Override
	public void before(Method method, Object[] args) {
		synchronized(this){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void afterReturning(Method method, Object[] args, Object result) {
	}

	@Override
	public void afterThrowing(Method method, Object[] args, Throwable t) {
	}

}
