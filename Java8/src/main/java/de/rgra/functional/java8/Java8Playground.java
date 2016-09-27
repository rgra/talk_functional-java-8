/*
 * @(#)Java8Playground.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on Sep 26, 2016
 */

package de.rgra.functional.java8;

import java.util.Arrays;
import java.util.List;

/**
 * @author gransberger
 */
public class Java8Playground {
	public static void main(String[] args) {
		List<String> values = Arrays.asList("A", "B", "C");
		calculateResults(values);
	}

	static void calculateResults(List<String> values) {
		values.removeIf(s -> s.length() < 2);
	}

}
