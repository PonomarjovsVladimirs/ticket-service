package app.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BasePriceRepositoryTest {

    @Autowired
    private BasePriceRepository basePriceRepository;

    @Test
    public void shouldReturnBasePrice() throws ExecutionException, InterruptedException {
        BigDecimal actualResult = basePriceRepository.getBasePrice("Lithuania").get();
        assertEquals(new BigDecimal("10.00"), actualResult, "should be 10.00");
    }
}
