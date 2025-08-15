package com.mcp.server.mcpserver.service.client;

import com.mcp.server.mcpserver.service.client.data.Loan;
import com.mcp.server.mcpserver.service.client.data.Property;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanService {

    private static final String BASE_URL = "http://localhost:9080";

    private final RestClient restClient;

    public LoanService() {

        this.restClient = RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader("Accept", "application/json")
                .build();
    }

    @Tool(name = "Get all Loans for Clients and Properties listed / available in the database", description = "Get all Loans listed in the database. Each of the records returned contains the client ID and the Property for which the Loan is availed. Use the clientId and propertyId in the loan information to associate a client or a property to the Loan.")
    public String listClients() {
        List<Loan> loans = restClient.get().uri("/loan/all-loans").retrieve().body(new ParameterizedTypeReference<List<Loan>>() {});
        assert loans != null;
        return loans
                .stream()
                .map(loan -> String.format("""
					Loan Id: %d,
					Loan Balance: %s
					Client ID: %s
					Property ID: %s
					Loan Origination Date: %s
					""", loan.getId(), loan.getLoanBalance(), loan.getLoanClientId(), loan.getPropertyId(), loan.getOriginationDate()))
                .collect(Collectors.joining("\n"));
    }

    @Tool(name = "Get all Loans for a given Client, if you know the Client Id", description = "Get all Loans listed in the database for a given Client ID. Use the Client ID to retrieve their loan details. Each of the records returned contains the client ID and the Property for which the Loan is availed.")
    public String loansByClientId(@ToolParam(description = "The Client ID for which Loan Information is to be retrieved") Long clientId) {
        List<Loan> loans = restClient.get().uri("/loan/loans-by-client-id?clientId=" + clientId).retrieve().body(new ParameterizedTypeReference<List<Loan>>() {});
        assert loans != null;
        return loans
                .stream()
                .map(loan -> String.format("""
					Loan Id: %d,
					Loan Balance: %s
					Client ID: %s
					Property ID: %s
					Loan Origination Date: %s
					""", loan.getId(), loan.getLoanBalance(), loan.getLoanClientId(), loan.getPropertyId(), loan.getOriginationDate()))
                .collect(Collectors.joining("\n"));
    }

    @Tool(name = "Get all Loans for a given Property, if you know the Property Id", description = "Get all Loans listed in the database for a given Property ID. Use the Property ID to retrieve the corresponding loan details. Each of the records returned contains the client ID and the Property for which the Loan is availed.")
    public String loansByPropertyId(@ToolParam(description = "The Property ID for which Loan Information is to be retrieved") Long propertyId) {
        List<Loan> loans = restClient.get().uri("/loan/loans-by-property-id?propertyId=" + propertyId).retrieve().body(new ParameterizedTypeReference<List<Loan>>() {});
        assert loans != null;
        return loans
                .stream()
                .map(loan -> String.format("""
					Loan Id: %d,
					Loan Balance: %s
					Client ID: %s
					Property ID: %s
					Loan Origination Date: %s
					""", loan.getId(), loan.getLoanBalance(), loan.getLoanClientId(), loan.getPropertyId(), loan.getOriginationDate()))
                .collect(Collectors.joining("\n"));
    }

    public static void main(String[] args) {
        LoanService client = new LoanService();
        System.out.println(client.loansByClientId(2L));
    }
}
