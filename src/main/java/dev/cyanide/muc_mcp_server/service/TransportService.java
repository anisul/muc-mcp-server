package dev.cyanide.muc_mcp_server.service;


import dev.cyanide.muc_mcp_server.client.MVGClient;
import dev.cyanide.muc_mcp_server.types.Departure;
import dev.cyanide.muc_mcp_server.types.Location;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService {

    private final MVGClient mvgClient;

    public TransportService(MVGClient mvgClient) {
        this.mvgClient = mvgClient;
    }


    @Tool(name = "mvg_get_departures", description = "Get a list of departures from a given query for location")
    public List<Departure> getDepartures(String query, Integer limit, Integer offsetInMinutes) {
        var locations = mvgClient.getLocations(query);

        var nearestStationId = locations.stream()
                .filter(Location::isStation)
                .findFirst()
                .orElse(null);

        if (nearestStationId != null) {
            return getDeparturesFromStation(nearestStationId.getGlobalId(), limit, offsetInMinutes);
        }

        return List.of();
    }

    @Tool(name = "mvg_get_departures_from_station", description = "Get a list of departures from a given station ID")
    public List<Departure> getDeparturesFromStation(String stationId, Integer limit, Integer offsetInMinutes) {
        return mvgClient.getDepartures(stationId, limit, offsetInMinutes);
    }
}
