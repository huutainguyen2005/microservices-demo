package vn.edu.fpt.customerservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerRequest(

        @NotBlank(message = "First name is required")
        @Size(max = 40, message = "First name must be at most 40 characters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(max = 20, message = "Last name must be at most 20 characters")
        String lastName,

        @Size(max = 80, message = "Company must be at most 80 characters")
        String company,

        @Size(max = 70, message = "Address must be at most 70 characters")
        String address,

        @Size(max = 40, message = "City must be at most 40 characters")
        String city,

        @Size(max = 40, message = "State must be at most 40 characters")
        String state,

        @Size(max = 40, message = "Country must be at most 40 characters")
        String country,

        @Size(max = 10, message = "Postal code must be at most 10 characters")
        String postalCode,

        @Size(max = 24, message = "Phone must be at most 24 characters")
        String phone,

        @Size(max = 24, message = "Fax must be at most 24 characters")
        String fax,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        @Size(max = 60, message = "Email must be at most 60 characters")
        String email,

        Integer supportRepId
) {
}