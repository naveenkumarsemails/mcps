package com.mcp.client.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PROPERTY")
@Data
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CITY")
    private String city;

    @Column(name = "CLIENT_ID")
    private Long clientId;

}
