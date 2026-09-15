package vn.edu.fpt.invoiceservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.edu.fpt.invoiceservice.client.dto.TrackClientResponse;

@FeignClient(
        name = "catalog-service",
        url = "${CATALOG_SERVICE_URL}"
)
public interface CatalogClient {

    @GetMapping("/api/v1/tracks/{id}")
    TrackClientResponse findById(
            @PathVariable Integer id
    );
}