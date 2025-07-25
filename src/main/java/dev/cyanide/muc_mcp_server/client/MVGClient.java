package dev.cyanide.muc_mcp_server.client;


import dev.cyanide.muc_mcp_server.types.Departure;
import dev.cyanide.muc_mcp_server.types.Location;
import dev.cyanide.muc_mcp_server.types.Station;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Component
public class MVGClient {

    public static final int LOCATION_LIMIT = 5;
    private final String API_BASE_URL = "https://www.mvg.de";

    private final RestTemplate restTemplate;

    public MVGClient() {
        this.restTemplate = new RestTemplate();
    }

    public List<Departure> getDepartures(String stationId, Integer limit, Integer offsetInMinutes) {
        var url = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .pathSegment("api", "bgw-pt", "v3", "departures")
                .queryParam("globalId", stationId)
                .queryParamIfPresent("limit", limit != null ? Optional.of(limit) : Optional.empty())
                .queryParamIfPresent("offsetInMinutes", offsetInMinutes != null
                        ? Optional.of(offsetInMinutes) : Optional.empty())
                .build()
                .toUriString();

        var response = restTemplate.getForEntity(url, Departure[].class);
        return List.of(response.getBody());
    }

    public List<Location> getLocations(String query) {
        var url = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .pathSegment("api", "bgw-pt", "v3", "locations")
                .queryParam("query", query)
                .queryParam("limit", LOCATION_LIMIT)
                .build()
                .toUriString();

        var response = restTemplate.getForEntity(url, Location[].class);
        return List.of(response.getBody());
    }

    public List<Station> getStations() {
        var url = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .pathSegment(".rest", "zdm", "stations")
                .build()
                .toUriString();

        var response = restTemplate.getForEntity(url, Station[].class);
        return List.of(response.getBody());
    }

    public Station getStationById(String stationId) {
        var url = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .pathSegment(".rest", "zdm", "stations", stationId)
                .build()
                .toUriString();

        var response = restTemplate.getForEntity(url, Station.class);
        return response.getBody();
    }

}
