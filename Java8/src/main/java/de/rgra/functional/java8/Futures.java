/*
 * @(#)Futures.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on 30.10.2016
 */

package de.rgra.functional.java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author rgra
 */
public class Futures {

	public static void main(String[] args) {
		CompletableFuture<List<String>> future = readList();

		future
			.thenApply(list -> "List read: " + list.size())
			.exceptionally(t -> "Failed: " + t.getMessage())
			.thenAccept(System.out::println);
	}

	private static CompletableFuture<List<String>> readList() {
		CompletableFuture<List<String>> future = new CompletableFuture<>();
		ExecutorService pool = Executors.newSingleThreadExecutor();

		pool.submit(() -> {
			try {
				future.complete(Files.readAllLines(Paths.get("D:", "test.csv")));
			}
			catch (IOException e) {
				future.completeExceptionally(e);
			}
		});
		pool.shutdown();

		return future;
	}

}
