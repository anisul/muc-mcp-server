package dev.cyanide.muc_mcp_server.service;

import dev.cyanide.muc_mcp_server.client.MVGClient;
import dev.cyanide.muc_mcp_server.types.Station;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {

    private final MVGClient mvgClient;

    public StationService(MVGClient mvgClient) {
        this.mvgClient = mvgClient;
    }

    @Tool(name = "mvg_get_stations", description = "Get a list of all stations in Munich")
    public List<Station> getStations() {
        return mvgClient.getStations();
    }

    @Tool(name = "mvg_get_station", description = "Get a station in Munich by its ID")
    public Station getStation(String id) {
        return mvgClient.getStation(id);
    }
}
