package com.mcp.client.mcpclient.data;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Tool {

    private String toolName;

    private String toolDescription;

    private String toolInputSchema;

}
