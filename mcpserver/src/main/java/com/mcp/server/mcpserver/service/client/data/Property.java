package com.mcp.server.mcpserver.service.client.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Property {

    @JsonProperty("id")
    Long id;

    @JsonProperty("address")
    String address;

    @JsonProperty("city")
    String city;

    @JsonProperty("clientId")
    Long clientId;

}
