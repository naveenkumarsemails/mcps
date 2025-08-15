package com.mcp.server.mcpserver.service.client.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Loan {

    @JsonProperty("id")
    Long id;

    @JsonProperty("loanBalance")
    private Double loanBalance;

    @JsonProperty("originationDate")
    private String originationDate;

    @JsonProperty("loanClientId")
    private Long loanClientId;

    @JsonProperty("propertyId")
    private Long propertyId;
}
