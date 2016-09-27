/*
 * @(#)Composition.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on Sep 26, 2016
 */

package de.rgra.functional.java8;

import java.util.function.BiFunction;

/**
 * @author gransberger
 */
public class Composition {

	public static void main(String[] args) {
		BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
		BiFunction<Integer, Integer, Integer> addAndMultBy5 = add.andThen(x -> x * 5);
		Integer result = addAndMultBy5.apply(2, 5);
		System.out.println(result);
	}

}
