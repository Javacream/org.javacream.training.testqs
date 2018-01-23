package org.javacream.demo.withcdi;

import org.javacream.Context;

public class DemoActor {
	
	{
		Demo demo = Context.demo();
		demo.doSomething();
	}
	public static void main(String[] args) {
		new DemoActor();
	}
}
