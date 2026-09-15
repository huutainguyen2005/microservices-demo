package vn.edu.fpt.invoiceservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.invoiceservice.client.CustomerClient;
import vn.edu.fpt.invoiceservice.client.TrackClient;
import vn.edu.fpt.invoiceservice.client.dto.CustomerClientResponse;
import vn.edu.fpt.invoiceservice.client.dto.TrackClientResponse;
import vn.edu.fpt.invoiceservice.dto.request.InvoiceLineRequest;
import vn.edu.fpt.invoiceservice.dto.request.InvoiceRequest;
import vn.edu.fpt.invoiceservice.dto.response.InvoiceLineResponse;
import vn.edu.fpt.invoiceservice.dto.response.InvoiceResponse;
import vn.edu.fpt.invoiceservice.entity.Invoice;
import vn.edu.fpt.invoiceservice.entity.InvoiceLine;
import vn.edu.fpt.invoiceservice.exception.ResourceNotFoundException;
import vn.edu.fpt.invoiceservice.repository.InvoiceRepository;
import vn.edu.fpt.invoiceservice.service.IInvoiceService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements IInvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerClient customerClient;
    private final TrackClient trackClient;

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceResponse> findAll() {
        return invoiceRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public InvoiceResponse findById(Integer id) {
        return toResponse(findEntity(id));
    }

    @Override
    @Transactional
    public InvoiceResponse create(InvoiceRequest request) {

        CustomerClientResponse customer =
                customerClient.findById(request.customerId());

        Invoice invoice = new Invoice();

        setCustomerSnapshot(invoice, customer);

        invoice.setInvoiceDate(LocalDateTime.now());

        addInvoiceLines(invoice, request.lines());

        invoice.setTotal(calculateTotal(invoice));

        return toResponse(invoiceRepository.save(invoice));
    }

    @Override
    @Transactional
    public InvoiceResponse update(
            Integer id,
            InvoiceRequest request) {

        Invoice invoice = findEntity(id);

        CustomerClientResponse customer =
                customerClient.findById(
                        request.customerId()
                );

        setCustomerSnapshot(invoice, customer);

        invoice.getInvoiceLines().clear();

        addInvoiceLines(
                invoice,
                request.lines()
        );

        invoice.setTotal(calculateTotal(invoice));

        return toResponse(
                invoiceRepository.save(invoice)
        );
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        invoiceRepository.delete(findEntity(id));
    }

    private Invoice findEntity(Integer id) {

        return invoiceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Invoice",
                                id
                        )
                );
    }

    private void setCustomerSnapshot(
            Invoice invoice,
            CustomerClientResponse customer) {

        invoice.setCustomerId(customer.customerId());
        invoice.setCustomerFirstName(customer.firstName());
        invoice.setCustomerLastName(customer.lastName());

        invoice.setBillingAddress(customer.address());
        invoice.setBillingCity(customer.city());
        invoice.setBillingState(customer.state());
        invoice.setBillingCountry(customer.country());
        invoice.setBillingPostalCode(customer.postalCode());
    }

    private void setInvoiceFields(Invoice invoice) {
        invoice.setInvoiceDate(
                LocalDateTime.now()
        );
    }

    private void addInvoiceLines(
            Invoice invoice,
            List<InvoiceLineRequest> requests) {

        for (InvoiceLineRequest request : requests) {

            TrackClientResponse track =
                    trackClient.findById(
                            request.trackId()
                    );

            InvoiceLine line = new InvoiceLine();

            line.setInvoice(invoice);
            line.setTrackId(track.trackId());
            line.setTrackName(track.name());
            line.setUnitPrice(track.unitPrice());
            line.setQuantity(request.quantity());

            invoice.getInvoiceLines().add(line);
        }
    }

    private BigDecimal calculateLineTotal(
            InvoiceLine line) {

        return line.getUnitPrice()
                .multiply(
                        BigDecimal.valueOf(
                                line.getQuantity()
                        )
                );
    }

    private BigDecimal calculateTotal(
            Invoice invoice) {

        return invoice.getInvoiceLines()
                .stream()
                .map(this::calculateLineTotal)
                .reduce(
                        BigDecimal.ZERO,
                        BigDecimal::add
                );
    }

    private InvoiceResponse toResponse(
            Invoice invoice) {

        List<InvoiceLineResponse> lines =
                invoice.getInvoiceLines()
                        .stream()
                        .map(this::toLineResponse)
                        .toList();

        return new InvoiceResponse(
                invoice.getInvoiceId(),
                invoice.getCustomerId(),
                invoice.getCustomerFirstName(),
                invoice.getCustomerLastName(),
                invoice.getInvoiceDate(),
                invoice.getBillingAddress(),
                invoice.getBillingCity(),
                invoice.getBillingState(),
                invoice.getBillingCountry(),
                invoice.getBillingPostalCode(),
                invoice.getTotal(),
                lines
        );
    }

    private InvoiceLineResponse toLineResponse(
            InvoiceLine line) {

        return new InvoiceLineResponse(
                line.getInvoiceLineId(),
                line.getInvoice().getInvoiceId(),
                line.getTrackId(),
                line.getTrackName(),
                line.getUnitPrice(),
                line.getQuantity(),
                calculateLineTotal(line)
        );
    }
}