



import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.List;
import java.util.Map;

import de.rgra.functional.model.Employee;
import de.rgra.functional.model.EmployeeRegister;

/**
 * @author gransberger
 */
public class List2Map {

	public static void main(String[] args) {
		List<Employee> persons = EmployeeRegister.getPersons(toList());

		Map<MonthDay, List<Employee>> byDate = persons.stream()
			.filter(p -> p.getBirthday().getMonth() == LocalDate.now().getMonth())
			.collect(groupingBy(e -> MonthDay.from(e.getBirthday())));

		byDate.entrySet().stream()
			.filter(e -> e.getValue().size() > 1)
			.sorted(comparing(Map.Entry::getKey))
			.forEach(e -> sendMail(e.getKey(), e.getValue()));
	}

	// TODO methode als Template?
	private static void sendMail(MonthDay date, List<Employee> persons) {
		String emails = persons.stream().map(Employee::getEmail)
			.collect(joining(";"));
		String subject = MessageFormat.format(
			"Please organise a birthday breakfast on {0} ",
			date.atYear(2016).toString());
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
