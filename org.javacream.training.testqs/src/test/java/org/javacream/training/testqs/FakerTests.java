package org.javacream.training.testqs;

import org.junit.Test;

import com.github.javafaker.Faker;

public class FakerTests {

	@Test
	public void nameFaker() {
		Faker faker = new Faker();
		System.out.println(faker.chuckNorris().fact());
	}
}