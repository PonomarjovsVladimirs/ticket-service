package app.repository;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class TaxRepository {

    public CompletableFuture<List<BigDecimal>> receiveTaxRates() {
        return CompletableFuture.completedFuture(Arrays.asList(new BigDecimal("0.21")));
    }
}
