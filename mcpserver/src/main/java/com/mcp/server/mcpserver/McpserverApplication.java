package com.mcp.server.mcpserver;

import com.mcp.server.mcpserver.service.client.ClientService;
import com.mcp.server.mcpserver.service.client.LoanService;
import com.mcp.server.mcpserver.service.client.PropertyService;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class McpserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(McpserverApplication.class, args);
	}


    @Bean
    public List<ToolCallback> tools(ClientService clientService, PropertyService propertyService, LoanService loanService) {
        return List.of(ToolCallbacks.from(clientService, propertyService, loanService));
    }
}
