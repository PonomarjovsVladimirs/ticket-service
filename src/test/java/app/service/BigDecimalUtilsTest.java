package app.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BigDecimalUtilsTest {

    @Test
    public void shouldReturnWithTwoDecimals() {
        assertEquals(new BigDecimal("10.00"), BigDecimalUtils.scaled("10.0000"), "should be 10.00");
    }

    @Test
    public void shouldMultipleAndReturnWithTwoDecimals() {
        assertEquals(new BigDecimal("8.00"),
                BigDecimalUtils.multiplyRounded(new BigDecimal("2.009"), new BigDecimal("4.0009")),
                "should be 8.00");
    }
}
