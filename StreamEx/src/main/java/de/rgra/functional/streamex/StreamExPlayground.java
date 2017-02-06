/*
 * @(#)Main.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on Sep 24, 2016
 */

package de.rgra.functional.streamex;

import static java.util.stream.Collectors.*;

import java.util.stream.Stream;

import de.rgra.functional.model.Employee;
import de.rgra.functional.model.EmployeeRegister;

import one.util.streamex.StreamEx;

/**
 * @author gransberger
 */
public class StreamExPlayground {

	public static void main(String[] args) {
		println(StreamEx.of(EmployeeRegister.getPersons(toList())));
		printEMail(StreamEx.of(EmployeeRegister.getPersons(toList())));

		StreamEx.of(1, 2, 1, 1, 2)
			.groupRuns((x, y) -> x.equals(y))
			.forEach(System.out::println);
	}

	private static void printEMail(Iterable<Employee> iterable) {
		for (Employee element : iterable) {
			System.out.println(element.getEmail());
		}
	}

	private static <T> void println(Stream<T> stream) {
		stream.forEach(System.out::println);
	}

}
