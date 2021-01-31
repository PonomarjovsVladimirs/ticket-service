package app.service;

import app.model.Passenger;
import app.model.Ticket;
import app.model.TicketResponse;
import app.repository.DataCollector;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PriceServiceTest {

    @Autowired
    private PriceService priceService;
    @Spy
    private DataCollector dataCollector;

    @Test
    public void shouldReturnTaxedPrice() {
        BigDecimal actualResult = priceService.getTaxedPrice(new BigDecimal("10.00"), asList(new BigDecimal("0.20"), new BigDecimal("0.50")));
        assertEquals(new BigDecimal("17.00"), actualResult, "should be 17.00");
    }

    @Test
    public void shouldReturnBaseAdultPrice() {
        Passenger passenger = new Passenger("Adult", Arrays.asList("bag1", "bag2"));
        BigDecimal baseAdultPrice = priceService.getBasePassengerPrice(passenger, new BigDecimal("10.00"));
        assertEquals(new BigDecimal("10.00"), baseAdultPrice, "price should be 10.00");
    }

    @Test
    public void shouldReturnBaseInfantPrice() {
        Passenger passenger = new Passenger("Infant", Arrays.asList("bag1", "bag2"));
        BigDecimal baseAdultPrice = priceService.getBasePassengerPrice(passenger, new BigDecimal("10.00"));
        assertEquals(new BigDecimal("5.00"), baseAdultPrice, "price should be 5.00");
    }

    @Test
    public void shouldReturnBagsPrice() {
        Passenger passenger = new Passenger("Infant", Arrays.asList("bag1", "bag2"));
        BigDecimal bagsPrice = priceService.getBaseBagsPrice(passenger, new BigDecimal("10.00"));
        assertEquals(new BigDecimal("6.00"), bagsPrice, "price should be 6.00");
    }

    @Test
    public void shouldReturnTicketPricesAndCollectTotalPrice() {
        dataCollector.collectBasePrice(new BigDecimal("10.00"));
        dataCollector.collectTaxRates(Arrays.asList(new BigDecimal("0.20")));
        Passenger passenger = new Passenger("Adult", Arrays.asList("bag1", "bag2"));
        LinkedList<Ticket> actualResult = priceService.calculatePrices(Arrays.asList(passenger), dataCollector);
        assertEquals(dataCollector.getTotalPrice(), new BigDecimal("19.20"));
        assertEquals(2, actualResult.size(), "should be 2 tickets");
        assertEquals("Adult", actualResult.get(0).getCategory(), "category should be Adult");
        assertEquals("2 bag(s)", actualResult.get(1).getCategory(), "category should be 2 bag(s)");
        assertEquals(new BigDecimal("12.00") + " EUR", actualResult.get(0).getPrice(), "adult passenger ticket should be 12.00 EUR");
        assertEquals(new BigDecimal("7.20") + " EUR", actualResult.get(1).getPrice(), "bags ticket should be 7.20 EUR");
    }

    @Test
    public void shouldReturnPricedTickets() {
        PriceService spy = spy(priceService);
        LinkedList<Ticket> calculcatedPricesMock = new LinkedList<>();
        calculcatedPricesMock.add(new Ticket("adult", "10.00"));
        doReturn(new BigDecimal("15.00")).when(dataCollector).getTotalPrice();
        doReturn(calculcatedPricesMock).when(spy).calculatePrices(new ArrayList<>(), dataCollector);
        TicketResponse actualResult = spy.getPricedTickets(new ArrayList<>(), dataCollector);
        assertEquals(new BigDecimal("15.00") + " EUR", actualResult.getTotalPrice(), "total price should be 15.00 EUR");
        assertEquals("10.00", actualResult.getTickets().get(0).getPrice());
    }
}
