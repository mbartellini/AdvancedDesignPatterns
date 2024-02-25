package at.technikumwien.menu;

import at.technikumwien.interfaces.CustomTranslator;
import at.technikumwien.services.DeeplTranslator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class MenuApplicationTests {
	@Value("${authKey}")
	private String authKey;

	private CustomTranslator translator = DeeplTranslator.getInstance(authKey);

	@Test
	void contextLoads() {
	}

	@Test
	void testTranslation() {
		assertDoesNotThrow(() -> translator.translate("Hello, World!", null, "DE"));
	}

}
