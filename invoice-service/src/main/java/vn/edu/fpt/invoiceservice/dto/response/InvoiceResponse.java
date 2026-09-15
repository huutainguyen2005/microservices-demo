package vn.edu.fpt.invoiceservice.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record InvoiceResponse(
        Integer invoiceId,
        Integer customerId,
        String customerFirstName,
        String customerLastName,
        LocalDateTime invoiceDate,
        String billingAddress,
        String billingCity,
        String billingState,
        String billingCountry,
        String billingPostalCode,
        BigDecimal total,
        List<InvoiceLineResponse> lines
) {
}