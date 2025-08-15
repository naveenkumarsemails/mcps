package com.mcp.client.controller;

import com.mcp.client.entity.Client;
import com.mcp.client.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client")
@Slf4j
public class ClientController {

    private final ClientRepository clientRepository;;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/all-clients")
    public List<Client> getAllClients() {
        log.info("Getting all clients");
        return clientRepository.findAll();
    }

    @GetMapping("/all-active-clients")
    public List<Client> getActiveClients() {
        log.info("Getting active clients");
        return clientRepository.findAllByStatus("Active");
    }
}
