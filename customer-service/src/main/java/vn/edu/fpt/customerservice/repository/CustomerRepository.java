package vn.edu.fpt.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.customerservice.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}