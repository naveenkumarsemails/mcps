package com.mcp.server.mcpserver.service.client;

import com.mcp.server.mcpserver.service.client.data.Client;
import com.mcp.server.mcpserver.service.client.data.Property;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PropertyService {

    private static final String BASE_URL = "http://localhost:9080";

    private final RestClient restClient;

    public PropertyService() {

        this.restClient = RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader("Accept", "application/json")
                .build();
    }

   @Tool(name = "Get all properties listed / available in the database", description = "Gets all available properties in the database for all clients. Use this when you want to view all available properties, which has details such as address, city, and which client id the property belongs to.")
    public String listClients() {
        List<Property> properties = restClient.get().uri("/property/all-properties").retrieve().body(new ParameterizedTypeReference<List<Property>>() {});
        assert properties != null;
        return properties
                .stream()
                .map(property -> String.format("""
					Property Id: %d,
					Property Address: %s
					Property City: %s
					Property Owned by Client ID: %s
					""", property.getId(), property.getAddress(), property.getCity(), property.getClientId()))
                .collect(Collectors.joining("\n"));
    }

    @Tool(name = "Get all Properties by Client ID", description = "If you know the client id, then using this tool, you can get the list of properties that the client owns.")
    public String getPropertiesByClientID(@ToolParam(description = "The Client ID for which all Properties are to be retrieved.") Long clientId) {
        List<Property> properties = restClient.get().uri("/property/property-by-client-id?clientId=" + clientId).retrieve().body(new ParameterizedTypeReference<List<Property>>() {});
        assert properties != null;
        return properties
                .stream()
                .map(property -> String.format("""
					Property Id: %d,
					Property Address: %s
					Property City: %s
					Property Owned by Client ID: %s
					""", property.getId(), property.getAddress(), property.getCity(), property.getClientId()))
                .collect(Collectors.joining("\n"));
    }

    public static void main(String[] args) {
        PropertyService client = new PropertyService();
        System.out.println(client.getPropertiesByClientID(1L));
    }
}
