/*
 * @(#)VavrBirthday.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2017
 * ===========================================================================
 * Created on 04.11.2017
 */

package io.github.rgra.live;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.MonthDay;

import de.rgra.functional.model.Employee;
import de.rgra.functional.model.EmployeeRegister;

import io.vavr.collection.List;

/**
 * @author rgra
 */
public class VavrBirthday {
    public static void main(String[] args) {
        List<Employee> persons = EmployeeRegister.getPersons(List.collector());

        persons.filter(p -> p.getBirthday().getMonth() == LocalDate.now().getMonth())
            .groupBy(e -> MonthDay.from(e.getBirthday()))
            .filter(v -> v._2().size() > 1)
            .forEach(VavrBirthday::sendMail);
    }

    private static void sendMail(MonthDay date, List<Employee> persons) {
        String emails = persons.map(Employee::getEmail).mkString(";");

        String subject = MessageFormat.format("Please organise a birthday breakfast on {0} ",
            date.atYear(2016).toString());
        System.out.println("-------------");
        System.out.println("To: " + emails);
        System.out.println(subject);
        // Body
        persons
            .map(Employee::getEmployeeNumber)
            .map(s -> "- Co-Worker #" + s)
            .forEach(System.out::println);

    }
}
