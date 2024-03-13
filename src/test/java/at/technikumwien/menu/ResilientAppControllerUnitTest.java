package at.technikumwien.menu;

import at.technikumwien.menu.external.components.ExternalApiCaller;
import at.technikumwien.menu.forms.ImageResponse;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
        EXTERNAL_SERVICE.stubFor(WireMock.get(ExternalApiCaller.EXTERNAL_QUERY).willReturn(serverError()));

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

        EXTERNAL_SERVICE.verify(5, getRequestedFor(urlEqualTo(ExternalApiCaller.EXTERNAL_QUERY)));
    }
}
