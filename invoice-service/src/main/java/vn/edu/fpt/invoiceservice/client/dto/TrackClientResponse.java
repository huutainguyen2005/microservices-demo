package vn.edu.fpt.invoiceservice.client.dto;

import java.math.BigDecimal;

public record TrackClientResponse(
        Integer trackId,
        String name,
        BigDecimal unitPrice
) {
}