/*
 * @(#)DynamicTestFactory.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on 25.09.2016
 */

package de.rgra.functional.junit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.*;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

/**
 * @author rgra
 */
@RunWith(JUnitPlatform.class)
public class DynamicTestFactory {

	@Test
	public void testFibonacci1() {
		testIsOdd(fibonacci(1));
	}

	@Test
	public void testFibonacci2() {
		testIsOdd(fibonacci(2));
	}

	@Test
	public void testFibonacci3() {
		testIsOdd(fibonacci(3));
	}

	@TestFactory
	@DisplayName("all Fibonacci numbers are odd")
	Stream<DynamicTest> testFactory() {
		return IntStream.rangeClosed(1, 20)
			.mapToObj(n -> dynamicTest("Fibonacci(" + n + ")", () -> testIsOdd(fibonacci(n))));
	}

	public int fibonacci(int n) {
		if (n <= 2)
			return 1;
		return fibonacci(n - 1) + fibonacci(n - 2);
	}

	private void testIsOdd(int i) {
		assertTrue(i % 2 == 1, "Should be odd, but was: " + i);
	}
}
