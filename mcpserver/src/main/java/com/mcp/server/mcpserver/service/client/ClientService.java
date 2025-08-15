package com.mcp.server.mcpserver.service.client;

import com.mcp.server.mcpserver.service.client.data.Client;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientService {

    private static final String BASE_URL = "http://localhost:9080";

    private final RestClient restClient;

    public ClientService() {

        this.restClient = RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader("Accept", "application/json")
                .build();
    }

    @Tool(name = "Get all clients", description = "Gets all clients listed in the database, irrespective of their status")
    public String listClients() {
        List<Client> clients = restClient.get().uri("/client/all-clients").retrieve().body(new ParameterizedTypeReference<List<Client>>() {});
        assert clients != null;
        return clients
                .stream()
                .map(client -> String.format("""
					Client Id: %d,
					Client Name: %s
					Client Type: %s
					Client Status: %s
					""", client.getId(), client.getName(), client.getType(), client.getStatus()))
                .collect(Collectors.joining("\n"));
    }

    @Tool(name = "Get all Active Clients", description = "Gets all active clients listed in the database.")
    public String listActiveClients() {
        List<Client> clients = restClient.get().uri("/client/all-active-clients").retrieve().body(new ParameterizedTypeReference<List<Client>>() {});
        assert clients != null;
        return clients
                .stream()
                .map(client -> String.format("""
					Client Id: %d,
					Client Name: %s
					Client Type: %s
					Client Status: %s
					""", client.getId(), client.getName(), client.getType(), client.getStatus()))
                .collect(Collectors.joining("\n"));
    }

}
