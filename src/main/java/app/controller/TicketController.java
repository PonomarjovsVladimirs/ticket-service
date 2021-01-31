package app.controller;

import app.model.Passenger;
import app.model.TicketResponse;
import app.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping(value = "/tickets/{destination}", consumes = "application/json", produces = "application/json")
    public TicketResponse getTickets(@RequestBody List<Passenger> passenger, @PathVariable("destination") String destination) {
        return ticketService.getTickets(passenger, destination);
    }
}
