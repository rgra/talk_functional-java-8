/*
 * @(#)Person.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on Sep 23, 2016
 */

package de.rgra.functional.model;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gransberger
 */
public class Employee {

	private static final AtomicInteger personCnt = new AtomicInteger(0);

	private final int employeeNumber;

	private final String email;

	private final LocalDate birthday;


	public Employee(LocalDate birthday) {
		this.employeeNumber = personCnt.incrementAndGet();
		this.email = "person" + employeeNumber + "@email.org";
		this.birthday = birthday;
	}


	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public String getEmail() {
		return email;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	@Override
	public String toString() {
		return "Employee [email=" + email + ", birthday=" + birthday + ", employeeNumber=" + employeeNumber + "]";
	}

}
