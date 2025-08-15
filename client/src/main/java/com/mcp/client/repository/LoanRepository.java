package com.mcp.client.repository;

import com.mcp.client.entity.Loan;
import com.mcp.client.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByLoanClientId(Long clientId);

    List<Loan> findByPropertyId(Long propertyId);
}
