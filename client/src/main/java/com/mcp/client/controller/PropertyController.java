package com.mcp.client.controller;

import com.mcp.client.entity.Client;
import com.mcp.client.entity.Property;
import com.mcp.client.repository.ClientRepository;
import com.mcp.client.repository.PropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/property")
@Slf4j
public class PropertyController {

    private final PropertyRepository propertyRepository;;

    public PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @GetMapping("/all-properties")
    public List<Property> getAllProperties() {
        log.info("Getting all properties");
        return propertyRepository.findAll();
    }

    @GetMapping("/property-by-client-id")
    public List<Property> getAllPropertiesByClientId(@RequestParam Long clientId) {
        log.info("Getting all properties by Client ID :: {}", clientId);
        return propertyRepository.findByClientId(clientId);
    }
}
