package dev.cyanide.muc_mcp_server;

import dev.cyanide.muc_mcp_server.service.TransportService;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MucMcpServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MucMcpServerApplication.class, args);
	}

	@Bean
	public List<ToolCallback> getTools(TransportService transportService) {
		return List.of(ToolCallbacks.from(transportService));
	}
}
