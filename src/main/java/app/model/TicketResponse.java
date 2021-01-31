package app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.LinkedList;


public class TicketResponse implements Serializable {

    @JsonProperty("Ticket prices")
    private final LinkedList<Ticket> tickets;
    @JsonProperty("Total price")
    private final String totalPrice;

    public TicketResponse(LinkedList<Ticket> tickets, String totalPrice) {
        this.tickets = tickets;
        this.totalPrice = totalPrice;
    }

    public LinkedList<Ticket> getTickets() {
        return tickets;
    }

    public String getTotalPrice() {
        return totalPrice;
    }
}
