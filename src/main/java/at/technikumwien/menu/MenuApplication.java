package at.technikumwien.menu;

import at.technikumwien.menu.interfaces.CustomTranslator;
import at.technikumwien.menu.repositories.MenuRepository;
import at.technikumwien.menu.services.DeeplTranslator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MenuApplication {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(MenuApplication.class, args);
	}

}
