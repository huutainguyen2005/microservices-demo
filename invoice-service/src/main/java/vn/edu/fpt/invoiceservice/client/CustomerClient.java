package vn.edu.fpt.invoiceservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.edu.fpt.invoiceservice.client.dto.CustomerClientResponse;

@FeignClient(
        name = "customer-service",
        url = "${CUSTOMER_SERVICE_URL}"
)
public interface CustomerClient {

    @GetMapping("/api/v1/customers/{id}")
    CustomerClientResponse findById(
            @PathVariable Integer id
    );
}