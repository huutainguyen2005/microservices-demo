package vn.edu.fpt.customerservice.dto.response;

public record CustomerResponse(
        Integer customerId,
        String firstName,
        String lastName,
        String company,
        String address,
        String city,
        String state,
        String country,
        String postalCode,
        String phone,
        String fax,
        String email
) {
}