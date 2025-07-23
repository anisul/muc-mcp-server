# Munich Transport MCP Server

A Model Context Protocol (MCP) server that provides real-time Munich public transport information through the MVG (Münchner Verkehrsgesellschaft) API. This server enables AI assistants to help users with public transport queries, departure times, and station information for Munich's public transportation network.

## Features

- **Real-time Departures**: Get live departure information from any Munich public transport location
- **Station Search**: Find stations by name or location query
- **Station Details**: Retrieve detailed information about specific stations
- **Complete Station List**: Access all available stations in the Munich transport network

## Available Tools

### `mvg_get_departures`
Get departure information from a location query.

**Parameters:**
- `query` (string): Location name or address to search for
- `limit` (integer): Maximum number of departures to return
- `offsetInMinutes` (integer): Time offset in minutes from now

**Returns:** List of departures from the nearest station to the queried location.

### `mvg_get_departures_from_station`
Get departure information from a specific station ID.

**Parameters:**
- `stationId` (string): The unique station identifier
- `limit` (integer): Maximum number of departures to return
- `offsetInMinutes` (integer): Time offset in minutes from now

**Returns:** List of departures from the specified station.

### `mvg_get_stations`
Retrieve all available stations in the Munich transport network.

**Parameters:** None

**Returns:** Complete list of all Munich public transport stations.

### `mvg_get_station`
Get detailed information about a specific station.

**Parameters:**
- `stationId` (string): The unique station identifier

**Returns:** Detailed station information including name, location, and available transport types.

## Installation

### Prerequisites
- Java 21 or higher
- Gradle build system

### Setup

1. Clone the repository:
```bash
git clone [your-repository-url]
cd [repository-name]
```

2. Build the project:
```bash
gradle build
```

3. Run the MCP server:
```bash
gradle run
```

## Configuration

### Claude Desktop Integration

Add the following configuration to your Claude Desktop `claude_desktop_config.json` file:

```json
{
  "mcpServers": {
    "muc-transport": {
      "command": "java",
      "args": [
        "-jar",
        "/path/to/your/muc-transport-mcp-server.jar"
      ],
      "env": {}
    }
  }
}
```

## Usage Examples

Once configured with Claude Desktop, you can ask questions like:

- "When is the next U-Bahn from Marienplatz?"
- "Show me departures from Hauptbahnhof in the next 30 minutes"
- "What stations are available in Munich?"
- "Get details for station with ID de:09162:6"

## API Dependencies

This server relies on the MVG (Münchner Verkehrsgesellschaft) public API to provide real-time transport information. The server includes an `MVGClient` that handles all communication with the MVG backend services.

## Data Models

The server works with the following main data types:

- **Departure**: Contains departure time, line information, destination, and delay data
- **Station**: Represents a transport station with ID, name, location, and available transport modes
- **Location**: General location data that can represent stations or other points of interest

## Important Disclaimer

**This server uses the MVG API which is not intended for commercial use.** The MVG API is provided for informational purposes and personal use only. Please refer to the [official MVG terms of service](https://www.mvg.de/impressum) for detailed usage guidelines and restrictions.

By using this MCP server, you acknowledge that:
- The MVG API is not meant for commercial applications
- Data accuracy and availability are subject to MVG's service terms
- You are responsible for complying with MVG's usage policies

## Support

For issues related to:
- **MCP Server**: Create an issue in this repository
- **MVG API**: Check the official MVG developer documentation
- **Claude Desktop Integration**: Refer to the Anthropic MCP documentation

## Acknowledgments

- MVG (Münchner Verkehrsgesellschaft) for providing the public transport API
- Anthropic for the Model Context Protocol specification