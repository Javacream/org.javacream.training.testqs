package org.javacream.demo.withcdi;

public class ProfilingDemoImpl extends DemoImpl {

	@Override
	public void doSomething() {
		long start = System.currentTimeMillis();
		super.doSomething();
		System.out.println("calling doSomething took " + (System.currentTimeMillis() - start) + " msec");
	}

}
