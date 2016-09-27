
/*
 * @(#)RandomDateCreator.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on Sep 23, 2016
 */
package de.rgra.functional.spliterators;
import static java.util.stream.Collectors.*;
import static org.paumard.spliterators.MoreSpliterators.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author gransberger
 */
public final class DateCreator {

	private DateCreator() {
		// only static methods
		throw new AssertionError();
	}

	public static void main(String[] args) {
		allPossibleMonthDays().forEach(System.out::println);
		randomDates().forEach(System.out::println);
	}

	public static List<MonthDay> allPossibleMonthDays() {
		return zip(
			repeat(IntStream.rangeClosed(1, 12).boxed(), 31),
			cycle(IntStream.rangeClosed(1, 31).boxed()),
			DateCreator::secureDate)
				.distinct()
				.collect(toList());
	}

	public static List<LocalDate> randomDates() {
		Random random = new Random();
		int numberElements = 100;

		return zip(
			random.ints(2014, 2017).boxed(),
			random.ints(1, 366).boxed(),
			LocalDate::ofYearDay)
				.distinct()
				.limit(numberElements)
				.collect(toList());
	}

	private static MonthDay secureDate(Integer month, Integer dayOfMonth) {
		try {
			return MonthDay.of(month, dayOfMonth);
		}
		catch (DateTimeException ignore) {
			// ignore exception, supply dummy value instead
			return MonthDay.of(1, 1);
		}
	}
}
