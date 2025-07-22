package dev.cyanide.muc_mcp_server.client;


import dev.cyanide.muc_mcp_server.types.Station;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class MVGClient {

    private final RestTemplate restTemplate;
    private static final String BASE_URL = "https://www.mvg.de/.rest/zdm/stations";

    public MVGClient() {
        this.restTemplate = new RestTemplate();
    }

    public List<Station> getStations() {
        try {
            ResponseEntity<List<Station>> response = restTemplate.exchange(
                    BASE_URL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Station>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch stations", e);
        }
    }

    public Station getStation(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Station ID cannot be null or empty");
        }

        try {
            String url = BASE_URL + "/" + id;
            return restTemplate.getForObject(url, Station.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch station with ID: " + id, e);
        }
    }
}
