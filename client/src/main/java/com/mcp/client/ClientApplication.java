package com.mcp.client;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

    @Bean
    public OpenAPI refrigeratorInventoryOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Refrigerator Inventory Management API")
                        .description("API for managing refrigerator inventory products")
                        .version("v0.0.1"));
    }


}
