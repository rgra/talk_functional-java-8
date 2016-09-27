/*
 * @(#)List2Map.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on Sep 23, 2016
 */

package de.rgra.functional.jool;

import static java.util.stream.Collectors.*;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.List;

import org.jooq.lambda.Seq;

import de.rgra.functional.model.Employee;
import de.rgra.functional.model.EmployeeRegister;

/**
 * @author gransberger
 */
public class JoolTest {

	public static void main(String[] args) {
		List<Employee> persons = EmployeeRegister.getPersons(toList());

		Seq.seq(persons)
			.filter(p -> p.getBirthday().getMonth() == LocalDate.now().getMonth())
			// Tuple2<MonthDay,List<Employee>>
			.grouped(e -> MonthDay.from(e.getBirthday()), toList())
			.filter(t -> t.v2.size() > 1)
			.sorted(t -> t.v1)
			.forEach(t -> sendMail(t.v1, t.v2));
	}

	private static void sendMail(MonthDay date, List<Employee> persons) {
		String emails = Seq.seq(persons)
			.map(Employee::getEmail)
			.toString(";");

		String subject = MessageFormat.format("Please organise a birthday breakfast on {0} ",
			date.atYear(2016).toString());
		System.out.println("-------------");
		System.out.println("To: " + emails);
		System.out.println(subject);
		// Body
		Seq.seq(persons)
			.map(Employee::getEmployeeNumber)
			.map(s -> "- Co-Worker #" + s)
			.printOut();
	}

}
