package vn.edu.fpt.customerservice.service;

import vn.edu.fpt.customerservice.dto.request.CustomerRequest;
import vn.edu.fpt.customerservice.dto.response.CustomerResponse;

import java.util.List;

public interface ICustomerService {

    List<CustomerResponse> findAll();

    CustomerResponse findById(Integer id);

    CustomerResponse create(CustomerRequest request);

    CustomerResponse update(Integer id, CustomerRequest request);

    void delete(Integer id);
}