package com.mcp.client.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "LOAN")
@Data
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LOAN_BALANCE")
    private Double loanBalance;

    @Column(name = "ORIGINATION_DATE")
    private Date originationDate;

    @Column(name = "LOAN_CLIENT_ID")
    private Long loanClientId;

    @Column(name = "PROPERTY_ID")
    private Long propertyId;
}
