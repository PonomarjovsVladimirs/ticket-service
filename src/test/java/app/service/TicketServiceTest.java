package app.service;

import app.model.TicketResponse;
import app.repository.BasePriceRepository;
import app.repository.DataCollector;
import app.repository.TaxRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;

import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TicketServiceTest {

    @Mock
    private BasePriceRepository basePriceRepository;
    @Mock
    private TaxRepository taxRepository;
    @Spy
    private DataCollector dataCollector;
    @Mock
    private PriceService priceService;
    @InjectMocks
    private TicketService ticketService;

    @Test
    public void shouldReturnTicketResponse() {
        TicketService spy = spy(ticketService);
        doReturn(CompletableFuture.allOf()).when(spy).collectRates("destination");
        doReturn(new TicketResponse(new LinkedList<>(), "20.00")).when(priceService).getPricedTickets(new ArrayList<>(), dataCollector);
        TicketResponse actualResult = spy.getTickets(new ArrayList<>(), "destination");
        assertEquals("20.00", actualResult.getTotalPrice(), "price should be 20.00");
    }

    @Test
    public void shouldCollectBasePriceAndTaxes() {
        doReturn(CompletableFuture.completedFuture(new BigDecimal("10.00"))).when(basePriceRepository).getBasePrice("destination");
        doReturn(CompletableFuture.completedFuture(asList(new BigDecimal("0.20")))).when(taxRepository).getTaxRates();
        ticketService.collectRates("destination");
        assertEquals(new BigDecimal("10.00"), dataCollector.getBasePrice(), "base price should be 10.00");
        assertEquals(asList(new BigDecimal("0.20")), dataCollector.getTaxRates(), "base price should be 10.00");
    }
}
