package vn.edu.fpt.invoiceservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI invoiceServiceOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Invoice Service API")
                .description("RESTful API for the Chinook invoice: invoices and invoice lines.")
                .version("v1")
                .contact(new Contact().name("FPT University"))
                .license(new License().name("MIT")));
    }
}