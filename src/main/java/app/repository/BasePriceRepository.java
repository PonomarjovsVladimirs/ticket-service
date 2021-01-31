package app.repository;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@Component
public class BasePriceRepository {

    public CompletableFuture<BigDecimal> getBasePrice(String destination) {
        return CompletableFuture.completedFuture(new BigDecimal("10.00"));
    }
}
