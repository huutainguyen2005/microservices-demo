package vn.edu.fpt.invoiceservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record InvoiceLineRequest(

        @NotNull(message = "Track id is required")
        Integer trackId,

        @NotNull(message = "Quantity is required")
        @Min(
                value = 1,
                message = "Quantity must be at least 1"
        )
        Integer quantity
) {
}