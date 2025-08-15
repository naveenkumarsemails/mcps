package com.mcp.client.repository;

import com.mcp.client.entity.Client;
import com.mcp.client.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByClientId(Long clientId);
}
