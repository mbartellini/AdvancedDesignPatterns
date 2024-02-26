package at.technikumwien.menu;

import at.technikumwien.menu.interfaces.CustomTranslator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class MenuApplicationTests {

	@Autowired
	private CustomTranslator translator;

	@Test
	void contextLoads() {
	}

	@Test
	void testTranslation() {
		assertDoesNotThrow(() -> translator.translate("Hello, World!", null, "DE"));
	}

}
