package app.service;

import app.model.Passenger;
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
    public TicketResponse getTickets(List<Passenger> passengers, String destination) {
        return collectRates(destination)
                .thenApply(v -> priceService.getPricedTickets(passengers, dataCollector))
                .join();
    }

    CompletableFuture<Void> collectRates(String destination) {
        CompletableFuture<BigDecimal> adultBasePrice = basePriceRepository.getBasePrice(destination);
        CompletableFuture<List<BigDecimal>> taxRates = taxRepository.getTaxRates();
        adultBasePrice.whenComplete((result, ex) -> dataCollector.collectBasePrice(result));
        taxRates.whenComplete((result, ex) -> dataCollector.collectTaxRates(result));
        return CompletableFuture.allOf(adultBasePrice, taxRates);
    }
}