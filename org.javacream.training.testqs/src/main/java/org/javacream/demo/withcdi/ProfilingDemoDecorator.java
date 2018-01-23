package org.javacream.demo.withcdi;

public class ProfilingDemoDecorator implements Demo {
	private Demo demo;

	public void setDemo(Demo demo) {
		this.demo = demo;
	}

	@Override
	public void doSomething() {
		long start = System.currentTimeMillis();
		demo.doSomething();
		System.out.println("calling doSomething took " + (System.currentTimeMillis() - start) + " msec");
	}

}
