package vn.edu.fpt.customerservice.service;

import vn.edu.fpt.customerservice.dto.request.EmployeeRequest;
import vn.edu.fpt.customerservice.dto.response.EmployeeResponse;

import java.util.List;

public interface IEmployeeService {

    List<EmployeeResponse> findAll();

    EmployeeResponse findById(Integer id);

    EmployeeResponse create(EmployeeRequest request);

    EmployeeResponse update(Integer id, EmployeeRequest request);

    void delete(Integer id);
}