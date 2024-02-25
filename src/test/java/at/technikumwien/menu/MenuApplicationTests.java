package at.technikumwien.menu;

import at.technikumwien.interfaces.CustomTranslator;
import at.technikumwien.services.DeeplTranslator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class MenuApplicationTests {

	private final CustomTranslator translator = DeeplTranslator.getInstance();

	@Test
	void contextLoads() {
	}

	@Test
	void testTranslation() {
		assertDoesNotThrow(() -> translator.translate("Hello, World!", null, "DE"));
	}

}
