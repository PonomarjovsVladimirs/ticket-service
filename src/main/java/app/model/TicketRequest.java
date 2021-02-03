package app.model;

import java.io.Serializable;
import java.util.List;

public class TicketRequest implements Serializable {

    private final List<Passenger> passengers;
    private final String currency;
    private final String destination;

    public TicketRequest(List<Passenger> passengers, String destination, String currency){
        this.passengers = passengers;
        this.destination = destination;
        this.currency = currency;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDestination() {
        return destination;
    }

}
