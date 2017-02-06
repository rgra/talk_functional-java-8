package de.rgra.functional.junit;

import static java.time.Duration.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

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
		Throwable exception = assertThrows(FileNotFoundException.class, () -> {
			new URL("http://127.0.0.1").openStream().read();
		});
		assertEquals("http://127.0.0.1", exception.getMessage());
	}

	@Test
	void timeoutExceeded() {
		assertTimeout(ofMillis(10), () -> {
			TimeUnit.MILLISECONDS.sleep(100);
		});
	}

}
