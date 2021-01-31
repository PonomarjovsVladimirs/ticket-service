package app.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigDecimalUtils {

    public static BigDecimal scaled(String val) {
        return new BigDecimal(val).setScale(2, RoundingMode.FLOOR);
    }

    public static BigDecimal multiplyRounded(BigDecimal multiplicand, BigDecimal multiplier) {
        return (scaled(multiplicand.toString())).multiply(scaled(multiplier.toString()), new MathContext(3, RoundingMode.FLOOR));
    }
}
