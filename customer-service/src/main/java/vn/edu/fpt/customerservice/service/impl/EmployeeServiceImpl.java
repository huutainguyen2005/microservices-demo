package vn.edu.fpt.customerservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.customerservice.dto.request.EmployeeRequest;
import vn.edu.fpt.customerservice.dto.response.EmployeeResponse;
import vn.edu.fpt.customerservice.entity.Employee;
import vn.edu.fpt.customerservice.exception.ResourceNotFoundException;
import vn.edu.fpt.customerservice.repository.EmployeeRepository;
import vn.edu.fpt.customerservice.service.IEmployeeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponse> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse findById(Integer id) {
        return toResponse(findEntity(id));
    }

    @Override
    @Transactional
    public EmployeeResponse create(EmployeeRequest request) {
        Employee employee = toEntity(request, new Employee());

        return toResponse(
                employeeRepository.save(employee)
        );
    }

    @Override
    @Transactional
    public EmployeeResponse update(
            Integer id,
            EmployeeRequest request) {

        Employee employee = findEntity(id);

        return toResponse(
                employeeRepository.save(
                        toEntity(request, employee)
                )
        );
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        employeeRepository.delete(findEntity(id));
    }

    private Employee findEntity(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee",
                                id
                        )
                );
    }

    private Employee toEntity(
            EmployeeRequest request,
            Employee employee) {

        employee.setLastName(request.lastName());
        employee.setFirstName(request.firstName());
        employee.setTitle(request.title());
        employee.setBirthDate(request.birthDate());
        employee.setHireDate(request.hireDate());
        employee.setAddress(request.address());
        employee.setCity(request.city());
        employee.setState(request.state());
        employee.setCountry(request.country());
        employee.setPostalCode(request.postalCode());
        employee.setPhone(request.phone());
        employee.setFax(request.fax());
        employee.setEmail(request.email());

        if (request.reportsToId() != null) {

            if (employee.getEmployeeId() != null
                    && employee.getEmployeeId()
                    .equals(request.reportsToId())) {

                throw new IllegalArgumentException(
                        "Employee cannot report to itself"
                );
            }

            Employee reportsTo =
                    employeeRepository.findById(
                            request.reportsToId()
                    ).orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Employee",
                                    request.reportsToId()
                            )
                    );

            employee.setReportsTo(reportsTo);

        } else {
            employee.setReportsTo(null);
        }

        return employee;
    }

    private EmployeeResponse toResponse(
            Employee employee) {

        Employee reportsTo = employee.getReportsTo();

        return new EmployeeResponse(
                employee.getEmployeeId(),
                employee.getLastName(),
                employee.getFirstName(),
                employee.getTitle(),
                reportsTo != null
                        ? reportsTo.getEmployeeId()
                        : null,
                reportsTo != null
                        ? reportsTo.getFirstName()
                        + " "
                        + reportsTo.getLastName()
                        : null,
                employee.getBirthDate(),
                employee.getHireDate(),
                employee.getAddress(),
                employee.getCity(),
                employee.getState(),
                employee.getCountry(),
                employee.getPostalCode(),
                employee.getPhone(),
                employee.getFax(),
                employee.getEmail()
        );
    }
}