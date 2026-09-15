package vn.edu.fpt.invoiceservice.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record InvoiceRequest(

        @NotNull(message = "Customer id is required")
        Integer customerId,

        @NotEmpty(message = "Invoice lines are required")
        List<@Valid InvoiceLineRequest> lines
) {
}