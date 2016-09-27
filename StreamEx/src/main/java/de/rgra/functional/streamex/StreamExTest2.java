/*
 * @(#)List2Map.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on Sep 23, 2016
 */

package de.rgra.functional.streamex;

import static java.time.MonthDay.*;
import static java.util.stream.Collectors.*;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.List;

import javax.util.streamex.StreamEx;

import de.rgra.functional.model.Employee;
import de.rgra.functional.model.EmployeeRegister;

/**
 * @author gransberger
 */
public class StreamExTest2 {

	public static void main(String[] args) {
		List<Employee> persons = EmployeeRegister.getPersons(toList());

		StreamEx.of(persons).filter(p -> p.getBirthday().getMonth() == LocalDate.now().getMonth())
			.sortedBy(e -> from(e.getBirthday()))
			.groupRuns((e, n) -> from(e.getBirthday()).equals(from(n.getBirthday())))
			.filter(l -> l.size() > 1)
			.forEach(StreamExTest2::sendMail);
	}


	private static void sendMail(List<Employee> persons) {
		MonthDay date = from(persons.get(0).getBirthday());
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
