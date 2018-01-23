package org.javacream.demo;

public class DemoActor {
	
	{
		DemoImpl impl = new DemoImpl();
		impl.doSomething();
	}
	public static void main(String[] args) {
		new DemoActor();
	}
}
