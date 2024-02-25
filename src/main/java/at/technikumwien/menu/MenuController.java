package at.technikumwien.menu;

import at.technikumwien.menu.forms.MenuForm;
import at.technikumwien.menu.objects.Menu;
import at.technikumwien.menu.services.MenuService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menues")
public class MenuController {

	//
	// This could also be solved with dependency injection
	private final MenuService menuService = MenuService.getInstance();

	@GetMapping()
	public String readMenues() {
		return "Greetings from Spring Boot!";
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public int createMenu(@RequestBody MenuForm menuForm) {
		return menuService.createMenu(menuForm).getId();
	}

	@GetMapping("/{id}")
	public Menu readMenu(@PathVariable Integer id) {
		return menuService.readMenu(id);
	}

}