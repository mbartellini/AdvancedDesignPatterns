package at.technikumwien.menu;

import at.technikumwien.menu.interfaces.CustomTranslator;
import at.technikumwien.menu.services.DeeplTranslator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MenuApplication {

	public static void main(String[] args) {
		SpringApplication.run(MenuApplication.class, args);
	}

}
