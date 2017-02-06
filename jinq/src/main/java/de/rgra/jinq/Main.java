package de.rgra.jinq;

import static test.generated.Tables.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.jinq.jooq.JinqJooqContext;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import test.generated.Test;
import test.generated.tables.records.AuthorRecord;

public class Main {
	public static void main(String[] args) {
		String userName = "root";
		String password = "";
		String url = "jdbc:mysql://localhost:3306/test";

		// Connection is the only JDBC resource that we need
		// PreparedStatement and ResultSet are handled by jOOQ, internally
		try (Connection conn = DriverManager.getConnection(url, userName, password)) {

			// Use jOOQ to talk to the JDBC connection
			DSLContext context = DSL.using(conn, SQLDialect.MYSQL);

			// Use Jinq to talk to the jOOQ connection
			JinqJooqContext jinq = JinqJooqContext.using(context, Test.TEST);

			List<AuthorRecord> results = jinq.from(AUTHOR)
				.where(c -> c.getAge() > 30)
				.selectAll()
				.toList();

			results.stream()
				.map(a -> a.getFirstName() + " " + a.getLastName())
				.forEach(System.out::println);
		}

		// For the sake of this tutorial, let's keep exception handling simple
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
