package app.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaxRepositoryTest {

    @Autowired
    private TaxRepository taxRepository;

    @Test
    public void shouldReturnTaxes() throws ExecutionException, InterruptedException {
        List<BigDecimal> actualResult = taxRepository.receiveTaxRates().get();
        assertEquals(asList(new BigDecimal("0.21")), actualResult, "should be single tax 0.21");
    }
}
