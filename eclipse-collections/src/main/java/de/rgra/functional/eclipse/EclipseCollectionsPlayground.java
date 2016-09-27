/*
 * @(#)EclipseCollectionsPlayground.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on 25.09.2016
 */

package de.rgra.functional.eclipse;


import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.petkata.Person;
import org.eclipse.collections.petkata.PetDomainForKata;
import org.eclipse.collections.petkata.PetType;

/**
 * @author rgra
 */
public class EclipseCollectionsPlayground extends PetDomainForKata {

	public static void main(String[] args) throws Exception {
		EclipseCollectionsPlayground eclipseCollectionsPlayground = new EclipseCollectionsPlayground();
		eclipseCollectionsPlayground.setUp();
		eclipseCollectionsPlayground.test();
	}

	public void test() {
		boolean anyPeopleHaveCats = this.people
			.anySatisfyWith(Person::hasPet, PetType.CAT);

		int countPeopleWithCats = this.people
			.countWith(Person::hasPet, PetType.CAT);

		MutableList<Person> peopleWithCats = this.people
			.selectWith(Person::hasPet, PetType.CAT)
			.drop(10);

		MutableIntList numberOfPets = this.people
			.sortThisByInt(p -> p.getNumberOfPets())
			.collectInt(p -> p.getNumberOfPets());

		Person person = people
			.selectWith(Person::hasPet, PetType.CAT)
			.detect(p -> p.getFirstName().startsWith("R"));

		System.out.println(person);

	}
}
