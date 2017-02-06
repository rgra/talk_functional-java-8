/*
 * @(#)List2Map.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on Sep 23, 2016
 */

package de.rgra.functional.streamex;

import static java.util.stream.Collectors.*;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.List;
import java.util.Map;

import de.rgra.functional.model.Employee;
import de.rgra.functional.model.EmployeeRegister;

import one.util.streamex.EntryStream;
import one.util.streamex.StreamEx;

/**
 * @author gransberger
 */
public class StreamExTest {

	public static void main(String[] args) {
		List<Employee> persons = EmployeeRegister.getPersons(toList());

		Map<MonthDay, List<Employee>> byDate = StreamEx.of(persons)
			.filter(p -> p.getBirthday().getMonth() == LocalDate.now().getMonth())
			.groupingBy(e -> MonthDay.from(e.getBirthday()));

		EntryStream.of(byDate)
			.filterValues(l -> l.size() > 1)
			.sortedBy(Map.Entry::getKey)
			.forKeyValue(StreamExTest::sendMail);
	}

	private static void sendMail(MonthDay date, List<Employee> persons) {
		String emails = StreamEx.of(persons)
			.map(Employee::getEmail)
			.joining(";");
		String subject = MessageFormat.format("Please organise a birthday breakfast on {0} ",
			date.atYear(2016).toString());
		System.out.println("-------------");
		System.out.println("To: " + emails);
		System.out.println(subject);
		// Body
		StreamEx.of(persons)
			.map(Employee::getEmployeeNumber)
			.map(s -> "- Co-Worker #" + s)
			.forEach(System.out::println);

	}

}
