package app.service;

import app.model.Passenger;
import app.model.Ticket;
import app.model.TicketResponse;
import app.repository.DataCollector;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static app.service.BigDecimalUtils.multiplyRounded;

@Service
public class PriceService {

    private static final String ADULT = "Adult";
    private static final String EUR = " EUR";
    private static final String BAGS = " bag(s)";
    private static final BigDecimal INFANT_DISCOUNT = new BigDecimal("0.50");
    private static final BigDecimal BAG_DISCOUNT = new BigDecimal("0.30");


    public TicketResponse getPricedTickets(List<Passenger> passengers, DataCollector dataCollector) {
        return new TicketResponse(calculatePrices(passengers, dataCollector), dataCollector.getTotalPrice() + EUR);
    }

    LinkedList<Ticket> calculatePrices(List<Passenger> passengers, DataCollector dataCollector) {
        BigDecimal totalPrice = new BigDecimal("0.00");
        LinkedList<Ticket> tickets = new LinkedList<>();
        for (Passenger passenger : passengers) {
            BigDecimal basePriceTaxed = getTaxedPrice(getBasePassengerPrice(passenger, dataCollector.getBasePrice()), dataCollector.getTaxRates());
            tickets.add(new Ticket(passenger.getCategory(), basePriceTaxed + EUR));
            totalPrice = totalPrice.add(basePriceTaxed);
            if (!passenger.getBags().isEmpty()) {
                BigDecimal bagsPriceTaxed = getTaxedPrice(getBaseBagsPrice(passenger, dataCollector.getBasePrice()), dataCollector.getTaxRates());
                tickets.add(new Ticket(passenger.getBags().size() + BAGS, bagsPriceTaxed + EUR));
                totalPrice = totalPrice.add(bagsPriceTaxed);
            }
        }
        dataCollector.collectTotalPrice(totalPrice);
        return tickets;
    }

    BigDecimal getBasePassengerPrice(Passenger passenger, BigDecimal basePrice) {
        return passenger.getCategory().equalsIgnoreCase(ADULT) ?
                basePrice :
                multiplyRounded(basePrice, INFANT_DISCOUNT);
    }

    BigDecimal getBaseBagsPrice(Passenger passenger, BigDecimal basePrice) {
        return multiplyRounded(new BigDecimal(String.valueOf(passenger.getBags().size())), multiplyRounded(BAG_DISCOUNT, basePrice));
    }

    BigDecimal getTaxedPrice(BigDecimal price, List<BigDecimal> taxRates) {
        return taxRates.stream()
                .map(multiplicand -> multiplyRounded(multiplicand, price)
                        .setScale(2, RoundingMode.FLOOR))
                .reduce(price, BigDecimal::add);
    }
}
