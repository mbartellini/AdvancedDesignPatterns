package at.technikumwien.menu.external.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Component
public class ExternalApiCaller {
    private final RestTemplate restTemplate;

    @Autowired
    public ExternalApiCaller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String callApi() {
        return restTemplate.getForObject("/api/google_translate", String.class);
    }

    public String[] getImages() {
        return restTemplate.getForObject("/api/google_images", String[].class);
    }
}
