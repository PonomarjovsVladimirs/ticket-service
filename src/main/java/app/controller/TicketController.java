package app.controller;

import app.model.TicketRequest;
import app.model.TicketResponse;
import app.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping(value = "/tickets", consumes = "application/json", produces = "application/json")
    public TicketResponse gatherTickets(@RequestBody TicketRequest request) {
        return ticketService.processTicketRequest(request);
    }
}
