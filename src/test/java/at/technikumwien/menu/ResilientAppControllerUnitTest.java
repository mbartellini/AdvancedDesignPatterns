package at.technikumwien.menu;

import at.technikumwien.menu.external.components.ExternalApiCaller;
import at.technikumwien.menu.forms.ImageResponse;
import at.technikumwien.menu.forms.MenuForm;
import at.technikumwien.menu.objects.Menu;
import at.technikumwien.menu.utilities.DummyGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.stream.IntStream;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResilientAppControllerUnitTest {
    @RegisterExtension
    static WireMockExtension EXTERNAL_SERVICE = WireMockExtension.newInstance()
            .options(WireMockConfiguration.wireMockConfig().port(9090))
            .build();

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCircuitBreaker() {
//        EXTERNAL_SERVICE.stubFor(WireMock.get(WireMock.urlEqualTo(ExternalApiCaller.EXTERNAL_QUERY)).willReturn(okJson("{\"images\": [\"image1.jpg\", \"image2.jpg\"]}")));
        EXTERNAL_SERVICE.stubFor(WireMock.get(ExternalApiCaller.EXTERNAL_IMAGES_QUERY).willReturn(serverError()));

        IntStream.rangeClosed(1, 5)
                .forEach(i -> {
                    ResponseEntity<ImageResponse> response = restTemplate.getForEntity("/menues/0/images", ImageResponse.class);
                    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
                });

        IntStream.rangeClosed(1, 5)
                .forEach(i -> {
                    ResponseEntity response = restTemplate.getForEntity("/menues/0/images", String[].class);
                    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
                });

        EXTERNAL_SERVICE.verify(5, getRequestedFor(urlEqualTo(ExternalApiCaller.EXTERNAL_IMAGES_QUERY)));
    }

    final DummyGenerator dummyGenerator = new DummyGenerator();
    @Test
    public void testTimeLimiter() throws JsonProcessingException {
        // Prepare extern translation fetching
        EXTERNAL_SERVICE.stubFor(WireMock.post(ExternalApiCaller.EXTERNAL_TRANSLATION_QUERY).willReturn(ok("Translated")));

        //Create Request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var dishForms = dummyGenerator.generateDishForm(3);
        MenuForm menuForm = new MenuForm(dishForms, "Eng", "Ger");
        HttpEntity<MenuForm> request = new HttpEntity<>(menuForm, headers);
        ResponseEntity<Menu> response = restTemplate.postForEntity("/menues", request, Menu.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.REQUEST_TIMEOUT);
        EXTERNAL_SERVICE.verify(1, postRequestedFor(urlEqualTo(ExternalApiCaller.EXTERNAL_TRANSLATION_QUERY)));
    }

    @Test
    public void testRateLimiter() {
        // Prepare extern translation fetching
        EXTERNAL_SERVICE.stubFor(WireMock.post(ExternalApiCaller.EXTERNAL_TRANSLATION_QUERY).willReturn(ok("Translated")));

        //Create Request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var dishForms = dummyGenerator.generateDishForm(3);
        MenuForm menuForm = new MenuForm(dishForms, "Eng", "Ger");
        HttpEntity<MenuForm> request = new HttpEntity<>(menuForm, headers);


        restTemplate.postForEntity("/menues", request, Menu.class);
        ResponseEntity<Menu> response = restTemplate.postForEntity("/menues", request, Menu.class);

        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.TOO_MANY_REQUESTS));
    }
}
