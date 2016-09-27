/*
 * @(#)SpliteratorsPlayground.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on 25.09.2016
 */

package de.rgra.functional.spliterators;

import java.util.stream.Stream;

import org.paumard.spliterators.MoreSpliterators;

/**
 * @author rgra
 */
public class SpliteratorsPlayground {

	public static void main(String[] args) {
		MoreSpliterators.interrupt(Stream.of("A", "B", "C", "D"), s -> s.equals("C"))
			.forEach(System.out::println);

		MoreSpliterators.gate(Stream.of("A", "B", "C", "D"), s -> s.equals("C"))
			.forEach(System.out::println);
	}

}
