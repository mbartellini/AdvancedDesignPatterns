package at.technikumwien.menu.controllers;

import at.technikumwien.menu.external.components.ExternalApiCaller;
import at.technikumwien.menu.forms.MenuForm;
import at.technikumwien.menu.objects.Menu;
import at.technikumwien.menu.services.MenuService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/menues")
public class MenuController {
	@Autowired
	private ExternalApiCaller externalAPICaller;

	@Autowired
	private MenuService menuService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@TimeLimiter(name = "translationTimeout")
	public CompletableFuture<Integer> createMenu(@RequestBody MenuForm menuForm) throws Exception {
		return CompletableFuture.supplyAsync(() -> menuService.createMenu(menuForm).getId());
	}

	@GetMapping("/{id}")
	public Menu readMenu(@PathVariable Integer id) {
		return menuService.readMenu(id);
	}

	@GetMapping("/{id}/images")
	@CircuitBreaker(name = "FetchExternalImages")
	@TimeLimiter(name = "imageTimeout")
	public CompletableFuture<String[]> getImagesForDishes(@PathVariable Integer id) {
		return CompletableFuture.supplyAsync(() -> externalAPICaller.getImages());
	}
}