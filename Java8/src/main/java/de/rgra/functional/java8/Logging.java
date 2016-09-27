/*
 * @(#)Logging.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on 25.09.2016
 */

package de.rgra.functional.java8;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author rgra
 */
public class Logging {

	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		if (logger.isTraceEnabled()) {
			logger.trace("Long-running operation: {}", expensiveOperation());
		}
		logger.trace("Long-running operation: {}", () -> expensiveOperation());

		logger.info(() -> "Hello world");
	}

	private static String expensiveOperation() {
		try {
			TimeUnit.SECONDS.sleep(10L);
		}
		catch (InterruptedException e) {
			logger.error("Error: " + e.getMessage(), e);
		}
		return "Done";
	}

}
