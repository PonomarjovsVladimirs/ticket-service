package app.model;

import java.math.BigDecimal;

public class Ticket {
    private final String category;
    private final BigDecimal price;

    public Ticket(String category, BigDecimal price) {
        this.category = category;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
