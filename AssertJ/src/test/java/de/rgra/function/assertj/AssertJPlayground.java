package de.rgra.function.assertj;

/*
 * @(#)AssertJPlayground.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on 25.09.2016
 */
import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

/**
 * @author rgra
 */
public class AssertJPlayground {

	@Test
	public void assertJTest() {
		String frodo = "Rabea";
		assertThat(frodo).startsWith("Fro")
			.endsWith("do")
			.isEqualToIgnoringCase("frodo");
	}
}
