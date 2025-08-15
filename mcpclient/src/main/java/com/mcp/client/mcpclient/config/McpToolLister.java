package com.mcp.client.mcpclient.config;

import com.mcp.client.mcpclient.data.Tool;
import io.modelcontextprotocol.client.McpSyncClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.definition.ToolDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class McpToolLister implements CommandLineRunner {

    private final List<McpSyncClient> mcpClients;

    @Autowired
    public McpToolLister(List<McpSyncClient> mcpClients) {
        this.mcpClients = mcpClients;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Looking for tools:");
        for (McpSyncClient client : mcpClients) {
            log.info("Connected to MCP Client: {}", client.getClientInfo().name());
            SyncMcpToolCallbackProvider provider = new SyncMcpToolCallbackProvider(List.of(client));
            List<ToolCallback> toolCallbacks = List.of(provider.getToolCallbacks());

            if (toolCallbacks.isEmpty()) {
                System.out.println("There are no tools listed. All queries will be routed to LLM by default.");
            } else {
                List<Tool> toolsAvailable = new ArrayList<>();
                for (ToolCallback toolCallback : toolCallbacks) {
                    ToolDefinition toolDefinition = toolCallback.getToolDefinition();

                    toolsAvailable.add(Tool.builder()
                                    .toolName(toolDefinition.name())
                                    .toolDescription(toolDefinition.description())
                                    .toolInputSchema(toolDefinition.inputSchema())
                            .build());
                }
                log.info("Tools Discovered and Ready to use :: ");
                toolsAvailable.forEach(tool -> {
                    log.info(tool.toString());
                });
            }
        }
    }
}