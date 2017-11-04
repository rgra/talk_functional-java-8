package de.rgra.functional.java8;
/*
 * @(#)List2Map.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on Sep 23, 2016
 */

import static java.util.stream.Collectors.*;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.List;

import de.rgra.functional.model.Employee;
import de.rgra.functional.model.EmployeeRegister;

/**
 * @author gransberger
 */
public class List2Map {

	// 1. Get list of employees
	// 2. Find all birthdays this month
	// 3. Group by birthday
	// 4. Send E-Mail to each group and ask them to organize breakfast together
	public static void main(String[] args) {
		List<Employee> persons = EmployeeRegister.getPersons(toList());
		Month currentMonth = LocalDate.now().getMonth();



	}

	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	// Map<MonthDay, List<Employee>> map = persons.stream()
	// .filter(p -> p.getBirthday().getMonth() == currentMonth)
	// .collect(groupingBy(p -> MonthDay.from(p.getBirthday())));
	//
	// map.entrySet().stream()
	// .filter(e -> e.getValue().size() > 1)
	// .sorted(Comparator.comparing(Map.Entry::getKey))
	// .forEach(e -> sendMail(e.getKey(), e.getValue()));
	//
	//
	private static void sendMail(MonthDay date, List<Employee> persons) {
		String emails = persons.stream().map(Employee::getEmail)
			.collect(joining(";"));
		String subject = MessageFormat.format("Please organise a birthday breakfast on {0} ", date.atYear(2016).toString());
		System.out.println("-------------");
		System.out.println("To: " + emails);
		System.out.println(subject);
		// Body
		persons.stream()
			.map(Employee::getEmployeeNumber)
			.map(s -> "- Co-Worker #" + s)
			.forEach(System.out::println);

	}

}
