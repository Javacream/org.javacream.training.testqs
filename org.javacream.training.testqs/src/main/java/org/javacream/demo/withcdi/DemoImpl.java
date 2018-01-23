package org.javacream.demo.withcdi;

public class DemoImpl implements Demo {

	/* (non-Javadoc)
	 * @see org.javacream.demo.withcdi.Demo#doSomething()
	 */
	@Override
	public void doSomething() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
