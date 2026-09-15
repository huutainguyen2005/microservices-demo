package vn.edu.fpt.invoiceservice.service;

import java.util.List;

import vn.edu.fpt.invoiceservice.dto.request.InvoiceRequest;
import vn.edu.fpt.invoiceservice.dto.response.InvoiceResponse;

public interface IInvoiceService {

    List<InvoiceResponse> findAll();

    InvoiceResponse findById(Integer id);

    InvoiceResponse create(InvoiceRequest request);

    InvoiceResponse update(Integer id, InvoiceRequest request);

    void delete(Integer id);
}