package vn.edu.fpt.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.customerservice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}