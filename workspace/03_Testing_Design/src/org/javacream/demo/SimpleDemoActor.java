package org.javacream.demo;

import org.javacream.util.test.GenericDummy;

public class SimpleDemoActor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Egal egal = GenericDummy.createDummy(Egal.class);
		System.out.println(egal.berechneIrgendwas());
		System.out.println(egal.machIrgendwas());
	}

}
