package app.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DataCollectorTest {

    @Autowired
    private DataCollector dataCollector;

    @Test
    public void shouldCollectTaxes() {
        dataCollector.collectTaxRates(asList(new BigDecimal("5.00")));
        assertEquals(asList(new BigDecimal("5.00")), dataCollector.getTaxRates());
    }

    @Test
    public void shouldBasePrice() {
        dataCollector.collectBasePrice(new BigDecimal("5.00"));
        assertEquals(new BigDecimal("5.00"), dataCollector.getBasePrice());
    }

    @Test
    public void shouldCollectTotalPrice() {
        dataCollector.collectTotalPrice(new BigDecimal("5.00"));
        assertEquals(new BigDecimal("5.00"), dataCollector.getTotalPrice());
    }
}
