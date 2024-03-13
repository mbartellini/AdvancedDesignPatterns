package at.technikumwien.menu.controllers;

import at.technikumwien.menu.external.components.ExternalApiCaller;
import at.technikumwien.menu.forms.ImageResponse;
import at.technikumwien.menu.forms.MenuForm;
import at.technikumwien.menu.objects.Menu;
import at.technikumwien.menu.services.MenuService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @CircuitBreaker(name = "CircuitBreakerService")
    public ResponseEntity<ImageResponse> getImagesForDishes(@PathVariable Integer id) {
        return externalAPICaller.getImages();
    }
}