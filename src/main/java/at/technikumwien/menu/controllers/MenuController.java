package at.technikumwien.menu.controllers;

import at.technikumwien.menu.external.components.ExternalApiCaller;
import at.technikumwien.menu.forms.ImageResponse;
import at.technikumwien.menu.forms.MenuForm;
import at.technikumwien.menu.objects.Menu;
import at.technikumwien.menu.services.MenuService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @RateLimiter(name = "rateLimiterApi")
    public CompletableFuture<Integer> createMenu(@RequestBody MenuForm menuForm) {
        return CompletableFuture.supplyAsync(() -> {
            String tranlsation = externalAPICaller.getExternalTranslation();
            return menuService.createMenu(menuForm).getId();
        });
    }

    @GetMapping("/{id}")
    public Menu readMenu(@PathVariable Integer id) {
        return menuService.readMenu(id);
    }

    @GetMapping("/{id}/images")
    @CircuitBreaker(name = "CircuitBreakerService")
    @TimeLimiter(name = "imageTimeout")
    public CompletableFuture<ResponseEntity<ImageResponse>> getImagesForDishes(@PathVariable Integer id) {
        return CompletableFuture.supplyAsync(() -> externalAPICaller.getImages());
    }
}