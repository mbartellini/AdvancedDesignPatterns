package at.technikumwien.menu.external.components;

import at.technikumwien.menu.forms.ImageResponse;
import at.technikumwien.menu.forms.MenuForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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

    public static final String EXTERNAL_URL = "http://localhost:9090";
    public static final String EXTERNAL_IMAGES_QUERY = "/images/biryani/";
    public static final String EXTERNAL_IMAGES_URL = EXTERNAL_URL + EXTERNAL_IMAGES_QUERY;
    public static final String EXTERNAL_TRANSLATION_QUERY = "/get/translation";
    public static final String EXTERNAL_TRANSLATION_URL = EXTERNAL_URL + EXTERNAL_TRANSLATION_QUERY;;

    public String getExternalTranslation() {
        HttpEntity<?> request = HttpEntity.EMPTY;
        ResponseEntity<String> result = restTemplate.postForEntity(EXTERNAL_TRANSLATION_URL, request, String.class);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignore) {
        }
        return result.getBody();
    }

    public ResponseEntity<ImageResponse> getImages() {

        ResponseEntity<ImageResponse> response = restTemplate.getForEntity(EXTERNAL_IMAGES_URL, ImageResponse.class);


        return response;
    }
}

