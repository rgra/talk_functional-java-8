/*
 * @(#)StreamExFactory.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on Sep 24, 2016
 */

package de.rgra.functional.streamex;

import static java.util.Arrays.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.util.streamex.StreamEx;

/**
 * @author gransberger
 */
public class StreamExFactory {

	public static void main(String[] args) {
		// ofLines(Path)
		// ofValues/Keys(Map)

		StreamEx.cartesianProduct(asList(
			asList(1, 2),
			asList(-3, -4),
			asList(50, 60)))
			.forEach(System.out::println);

		StreamEx.split("Firstname-Name-Titel", "-").forEach(System.out::println);

		StreamEx.zip(asList("Rabea", "vJUG"), asList("Gransb.", "24"), (f, l) -> f + " " + l)
			.forEach(System.out::println);

		StreamEx.ofTree(Paths.get("D:\\"),
			listDirs())
			.limit(20).forEach(System.out::println);

	}

	private static Function<Path, Stream<Path>> listDirs() {
		Function<Path, Stream<Path>> mapper = dir -> {
			if (Files.isDirectory(dir) && !dir.toString().contains("RECYCLE.BIN")) {
				try {
					return Files.list(dir);
				}
				catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			else {
				return Stream.empty();
			}
		};
		return mapper;
	}

}
