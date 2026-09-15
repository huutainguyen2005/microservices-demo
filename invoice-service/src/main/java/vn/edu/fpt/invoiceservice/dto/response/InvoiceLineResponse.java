package vn.edu.fpt.invoiceservice.dto.response;

import java.math.BigDecimal;

public record InvoiceLineResponse(
        Integer invoiceLineId,
        Integer invoiceId,
        Integer trackId,
        String trackName,
        BigDecimal unitPrice,
        Integer quantity,
        BigDecimal lineTotal
) {
}