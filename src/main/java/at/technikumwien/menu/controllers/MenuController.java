package at.technikumwien.menu.controllers;

import at.technikumwien.menu.external.components.ExternalApiCaller;
import at.technikumwien.menu.forms.MenuForm;
import at.technikumwien.menu.objects.Menu;
import at.technikumwien.menu.services.MenuService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menues")
public class MenuController {
	@Autowired
	private ExternalApiCaller externalAPICaller;

	@Autowired
	private MenuService menuService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public int createMenu(@RequestBody MenuForm menuForm) throws Exception {
		return menuService.createMenu(menuForm).getId();
	}

	@GetMapping("/{id}")
	public Menu readMenu(@PathVariable Integer id) {
		return menuService.readMenu(id);
	}

	@GetMapping("/{id}/images")
	@CircuitBreaker(name = "FetchExternalImages")
	public String[] getImagesForDishes(@PathVariable Integer id) {
		return externalAPICaller.getImages();
	}
}