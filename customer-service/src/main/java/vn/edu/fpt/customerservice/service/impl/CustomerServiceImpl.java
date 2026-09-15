package vn.edu.fpt.customerservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.customerservice.dto.request.CustomerRequest;
import vn.edu.fpt.customerservice.dto.response.CustomerResponse;
import vn.edu.fpt.customerservice.entity.Customer;
import vn.edu.fpt.customerservice.entity.Employee;
import vn.edu.fpt.customerservice.exception.ResourceNotFoundException;
import vn.edu.fpt.customerservice.repository.CustomerRepository;
import vn.edu.fpt.customerservice.repository.EmployeeRepository;
import vn.edu.fpt.customerservice.service.ICustomerService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse findById(Integer id) {
        return toResponse(findEntity(id));
    }

    @Override
    @Transactional
    public CustomerResponse create(CustomerRequest request) {
        Customer customer = toEntity(request, new Customer());

        return toResponse(
                customerRepository.save(customer)
        );
    }

    @Override
    @Transactional
    public CustomerResponse update(
            Integer id,
            CustomerRequest request) {

        Customer customer = findEntity(id);

        return toResponse(
                customerRepository.save(
                        toEntity(request, customer)
                )
        );
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        customerRepository.delete(findEntity(id));
    }

    private Customer findEntity(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer",
                                id
                        )
                );
    }

    private Customer toEntity(
            CustomerRequest request,
            Customer customer) {

        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setCompany(request.company());
        customer.setAddress(request.address());
        customer.setCity(request.city());
        customer.setState(request.state());
        customer.setCountry(request.country());
        customer.setPostalCode(request.postalCode());
        customer.setPhone(request.phone());
        customer.setFax(request.fax());
        customer.setEmail(request.email());

        if (request.supportRepId() != null) {
            Employee supportRep =
                    employeeRepository.findById(
                            request.supportRepId()
                    ).orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Employee",
                                    request.supportRepId()
                            )
                    );

            customer.setSupportRep(supportRep);
        } else {
            customer.setSupportRep(null);
        }

        return customer;
    }

    private CustomerResponse toResponse(
            Customer customer) {

        return new CustomerResponse(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getCompany(),
                customer.getAddress(),
                customer.getCity(),
                customer.getState(),
                customer.getCountry(),
                customer.getPostalCode(),
                customer.getPhone(),
                customer.getFax(),
                customer.getEmail()
        );
    }
}