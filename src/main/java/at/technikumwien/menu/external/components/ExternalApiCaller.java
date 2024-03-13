package at.technikumwien.menu.external.components;

import at.technikumwien.menu.forms.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class ExternalApiCaller {

    private final RestTemplate restTemplate;


    @Autowired
    public ExternalApiCaller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static final String EXTERNAL_QUERY = "/images/biryani/";
    public static final String EXTERNAL_SERVICE_URL = "http://localhost:9090" + EXTERNAL_QUERY;

    public ResponseEntity<ImageResponse> getImages() {

        ResponseEntity<ImageResponse> response = restTemplate.getForEntity(EXTERNAL_SERVICE_URL, ImageResponse.class);


        return response;
    }
}

