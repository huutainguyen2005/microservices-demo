package vn.edu.fpt.invoiceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.invoiceservice.entity.InvoiceLine;

public interface InvoiceLineRepository extends JpaRepository<InvoiceLine, Integer> {
}