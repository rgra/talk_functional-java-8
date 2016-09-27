/*
 * @(#)JoolPlayground.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on Sep 24, 2016
 */

package de.rgra.functional.jool;

import static java.util.Comparator.*;
import static org.jooq.lambda.tuple.Tuple.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import org.jooq.lambda.Agg;
import org.jooq.lambda.Seq;
import org.jooq.lambda.Unchecked;
import org.jooq.lambda.tuple.Tuple2;

/**
 * @author gransberger
 */
public class JoolPlayground {

	public static void main(String[] args) {
		Seq.of(tuple(1, 2, 3, 4))
			.mapToLong(t -> t.v1 * t.v2 * t.v3 * t.v4);

		Seq.of(Paths.get("D:\\test.csv"))
			.flatMap(file -> {
				try {
					return Files.lines(file, StandardCharsets.ISO_8859_1);
				}
				catch (IOException e) {
					throw new RuntimeException(e);
				}
			})
			.printOut();

		Seq.of(Paths.get("D:\\test.csv"))
			.flatMap(Unchecked.function(Files::lines))
			.printOut();

		System.out.println("Cross: " +
				Seq.of(tuple(1, 10), tuple(2, 20), tuple(3, 20))
					.crossJoin(Seq.of(1, 2))
					.format());

		System.out.println("Left: " +
				Seq.of(tuple(1, 10), tuple(2, 20), tuple(3, 20))
					.leftOuterJoin(Seq.of(1, 2), (x, y) -> Objects.equals(x.v1, y))
					.format());

		System.out.println("Right: " +
				Seq.of(tuple(1, 10), tuple(2, 20))
					.rightOuterJoin(Seq.of(1, 2, 3), (x, y) -> Objects.equals(x.v1, y))
					.format());

		System.out.println("Inner: " +
				Seq.of(tuple(1, 10), tuple(2, 20), tuple(3, 20))
					.innerJoin(Seq.of(1, 2), (x, y) -> Objects.equals(x.v1, y))
					.format());

		Seq.of(tuple("2016-09-27", new BigDecimal("-5")), tuple("2016-09-20", new BigDecimal("100")), tuple("2016-09-11", new BigDecimal("-20")))
			.window(comparing((Tuple2<String, BigDecimal> t) -> t.v1), Long.MIN_VALUE, 0)
			.map(w -> w.value().concat(w.sum(t -> t.v2).orElse(BigDecimal.ZERO)))
			.printOut();

		System.out.println(Seq.of("A", "AA", "BA")
			.collect(Agg.commonPrefix()));
		System.out.println(Seq.of("A", "AA", "BA")
			.collect(Agg.commonSuffix()));

	}

}
