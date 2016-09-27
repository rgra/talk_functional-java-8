/*
 * @(#)EmployeeRegister.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on Sep 23, 2016
 */

package de.rgra.functional.model;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Collector;

/**
 * @author gransberger
 */
public class EmployeeRegister {

	public static <T> T getPersons(Collector<Employee, ?, T> collector) {
		Random random = new Random();
		return random.ints(1, 28)
			.limit(1_000)
			.mapToObj(i -> LocalDate.of(1983, random.nextInt(11) + 1, i))
			.map(Employee::new)
			.collect(collector);
	}
}
