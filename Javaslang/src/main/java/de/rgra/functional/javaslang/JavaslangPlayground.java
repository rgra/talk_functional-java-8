/*
 * @(#)JavaslangPlayground.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on Sep 26, 2016
 */

package de.rgra.functional.javaslang;

import static javaslang.API.*;
import static javaslang.Predicates.*;

import javaslang.Function1;
import javaslang.Function2;
import javaslang.control.Option;

/**
 * @author gransberger
 */
public class JavaslangPlayground {

	public static void main(String[] args) {

		Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
		Function1<Integer, Integer> add2 = sum.curried().apply(2);

		System.out.println(add2.apply(4));

		Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;
		Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);

		try {
			System.out.println(divide.apply(5, 0));
		}
		catch (Throwable e) {
			e.printStackTrace();
		}

		System.out.println(safeDivide.apply(5, 0));

		int i = 5;
		String s = Match(i).of(Case(is(1), "one"), Case(is(2), "two"), Case($(), "?"));
		System.out.println(s);

		Match("-h").of(Case(isIn("-h", "--help"), o -> run(JavaslangPlayground::displayHelp)), Case(isIn("-v", "--version"), o -> run(JavaslangPlayground::displayVersion)), Case($(), o -> run(() -> {
			throw new IllegalArgumentException("Test");
		})));
	}

	static void displayHelp() {
		System.out.println("Help");
	}

	static void displayVersion() {
		System.out.println("Version");
	}

}
