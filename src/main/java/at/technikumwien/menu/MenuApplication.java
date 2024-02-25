package at.technikumwien.menu;

import at.technikumwien.interfaces.CustomTranslator;
import at.technikumwien.services.DeeplTranslator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MenuApplication {

	private static final CustomTranslator translator = DeeplTranslator.getInstance();

	public static void main(String[] args) {
		SpringApplication.run(MenuApplication.class, args);
	}

}
