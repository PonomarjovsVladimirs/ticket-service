package app.service;

import app.model.TicketRequest;
import app.model.TicketResponse;
import app.repository.BasePriceRepository;
import app.repository.DataCollector;
import app.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TicketService {

    @Autowired
    private BasePriceRepository basePriceRepository;
    @Autowired
    private TaxRepository taxRepository;
    @Autowired
    private DataCollector dataCollector;
    @Autowired
    private PriceService priceService;

    /**
     * returns ticket prices for each individual item and total price. Taxes included
     */
    public TicketResponse processTicketRequest(TicketRequest request) {
        return collectRates(request.getDestination())
                .thenApply(v -> priceService.priceTickets(request.getPassengers(), dataCollector, request.getCurrency()))
                .join();
    }

    CompletableFuture<Void> collectRates(String destination) {
        CompletableFuture<BigDecimal> adultBasePrice = basePriceRepository.receiveBasePrice(destination);
        CompletableFuture<List<BigDecimal>> taxRates = taxRepository.receiveTaxRates();
        adultBasePrice.whenComplete((result, ex) -> dataCollector.collectBasePrice(result));
        taxRates.whenComplete((result, ex) -> dataCollector.collectTaxRates(result));
        return CompletableFuture.allOf(adultBasePrice, taxRates);
    }
}