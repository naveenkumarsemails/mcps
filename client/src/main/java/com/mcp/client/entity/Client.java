package com.mcp.client.entity;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.Data;

@Entity
@Table(name = "CLIENT")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "STATUS")
    private String status;

}
