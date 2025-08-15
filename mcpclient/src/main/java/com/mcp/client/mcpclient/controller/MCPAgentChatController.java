package com.mcp.client.mcpclient.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mcp-chat-bot")
public class MCPAgentChatController {

    private final ChatClient chatClient;

    private final SyncMcpToolCallbackProvider syncMcpToolCallbackProvider;

    public MCPAgentChatController(ChatClient.Builder chatClientBuilder,
                                  @Qualifier("mcp-tool-list-provider") SyncMcpToolCallbackProvider syncMcpToolCallbackProvider) {
        this.chatClient = chatClientBuilder.build();
        this.syncMcpToolCallbackProvider = syncMcpToolCallbackProvider;
    }

    @PostMapping
    public String chat(@RequestBody String message) {
        return chatClient.prompt()
                .user(message)
                .system("You are a helpful assistant that provides concise and accurate answers. Use the tools available to answer this question. Only with results that you find from the user tools, use other information from internet or your knowledgebase to enrich the information that the tools return. Also avoid using IDs in response and instead use other information that might be easier for the users to understand from the results of the tools.")
                .toolCallbacks(syncMcpToolCallbackProvider)
                .call().content();
    }



}
