package org.javacream.demo.withcdi;

public class TracingDemoDecorator implements Demo{

	private Demo demo;

	public void setDemo(Demo demo) {
		this.demo = demo;
	}

	@Override
	public void doSomething() {
		System.out.println("Entering doSomething");
		demo.doSomething();
		System.out.println("Exiting doSomething");
	}
}
