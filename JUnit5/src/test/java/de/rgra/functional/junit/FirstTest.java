package de.rgra.functional.junit;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@MyFastTag
class FirstTest {

	@Test
	@DisplayName("My 1st JUnit 5 test! ?")
	void myFirstTest(TestInfo testInfo) {
		assertEquals("My 1st JUnit 5 test! ?", testInfo.getDisplayName(), () -> "TestInfo is injected correctly");
	}

}