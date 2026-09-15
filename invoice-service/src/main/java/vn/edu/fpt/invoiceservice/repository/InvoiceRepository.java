package vn.edu.fpt.invoiceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.invoiceservice.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}