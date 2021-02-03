package app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class TicketResponse implements Serializable {

    @JsonProperty("Ticket prices")
    private final List<Ticket> tickets;
    @JsonProperty("Total price")
    private final BigDecimal totalPrice;
    @JsonProperty("Currency")
    private final String currency;

    public TicketResponse(List<Ticket> tickets, BigDecimal totalPrice, String currency) {
        this.tickets = tickets;
        this.totalPrice = totalPrice;
        this.currency = currency;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getCurrency() {
        return currency;
    }
}
