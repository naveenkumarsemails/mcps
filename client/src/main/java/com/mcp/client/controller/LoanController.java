package com.mcp.client.controller;

import com.mcp.client.entity.Loan;
import com.mcp.client.entity.Property;
import com.mcp.client.repository.LoanRepository;
import com.mcp.client.repository.PropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loan")
@Slf4j
public class LoanController {

    private final LoanRepository loanRepository;;

    public LoanController(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @GetMapping("/all-loans")
    public List<Loan> getAllLoans() {
        log.info("Getting all loans");
        return loanRepository.findAll();
    }

    @GetMapping("/loans-by-client-id")
    public List<Loan> getAllPropertiesByClientId(@RequestParam Long clientId) {
        log.info("Getting all loans by Client ID :: {}", clientId);
        return loanRepository.findByLoanClientId(clientId);
    }

    @GetMapping("/loans-by-property-id")
    public List<Loan> getAllLoansForProperty(@RequestParam Long propertyId) {
        log.info("Getting all loans for property ID :: {}", propertyId);
        return loanRepository.findByPropertyId(propertyId);
    }
}
