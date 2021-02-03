package app.repository;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataCollector {

    private List<BigDecimal> taxRates;
    private BigDecimal basePrice;
    private BigDecimal totalPrice;

    public void collectBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public void collectTaxRates(List<BigDecimal> taxRates) {
        this.taxRates = taxRates;
    }

    public void collectTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<BigDecimal> getTaxRates() {
        return taxRates;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

}
