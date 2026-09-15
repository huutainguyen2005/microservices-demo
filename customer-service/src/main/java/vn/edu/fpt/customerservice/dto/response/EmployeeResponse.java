package vn.edu.fpt.customerservice.dto.response;

import java.time.LocalDateTime;

public record EmployeeResponse(
        Integer employeeId,
        String lastName,
        String firstName,
        String title,
        Integer reportsToId,
        String reportsToName,
        LocalDateTime birthDate,
        LocalDateTime hireDate,
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