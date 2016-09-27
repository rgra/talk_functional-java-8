package de.rgra.functional.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
class AssertionsDemo {

	@Test
	void standardAssertions() {
		assertEquals(2, 2);
		assertEquals(4, 4, "The optional assertion message is now the last parameter.");
		assertTrue(2 == 2, () -> "Assertion messages can be lazily evaluated -- "
				+ "to avoid constructing complex messages unnecessarily.");
	}

	@Test
	void groupedAssertions() {
		String firstname = "Rabea";
		String lastname = "Gransberger";
		// In a grouped assertion all assertions are executed, and any
		// failures will be reported together.
		assertAll("names",
			() -> assertEquals("John", firstname),
			() -> assertEquals("User", lastname));
	}

	@Test
	void exceptionTesting() {
		Throwable exception = expectThrows(IOException.class, () -> {
			new URL("http://www.vjug26.com").openStream().read();
		});
		assertEquals("www.vjug26.com", exception.getMessage());
	}

}
