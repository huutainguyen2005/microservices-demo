package vn.edu.fpt.invoiceservice.client.dto;

public record CustomerClientResponse(
        Integer customerId,
        String firstName,
        String lastName,
        String address,
        String city,
        String state,
        String country,
        String postalCode
) {
}