package org.javacream.demo.withcdi;

public class TracingDemoImpl extends DemoImpl {

	@Override
	public void doSomething() {
		System.out.println("Entering doSomething");
		super.doSomething();
		System.out.println("Exiting doSomething");
	}

}
