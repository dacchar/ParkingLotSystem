package com.hellointerview.service.tickets;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.hellointerview.models.Spot;
import com.hellointerview.service.tickets.models.Ticket;

public class TicketSystem {

    private List<Ticket> tickets = new ArrayList<>();

    public Ticket issueTicket(Instant instant, Spot spot) {
        Ticket ticket = new Ticket(tickets.size() + 1, instant, spot);
        tickets.add(ticket);

        return ticket;
    }

    public Ticket getOpenTicket(int ticketId) {
        Ticket ticket = tickets.stream()
            .filter(t -> ticketId == t.getId() && t.isOpen())
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Ticket not found: " + ticketId));
        
        validate(ticket);

        return ticket;
    }

    public Ticket getClosedTicket(int ticketId) {
        Ticket ticket = tickets.stream()
            .filter(t -> ticketId == t.getId() && t.isClosed())
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("The closed ticket not found: " + ticketId));

        return ticket;
    }

    private void validate(Ticket ticket) {
        checkIfIsUsed(ticket);
    }

    private void checkIfIsUsed(Ticket ticket) {
        if(ticket.getEndTime() != null) {
            throw new IllegalStateException("Ticket is invalid: " + ticket.getId());
        }
    }
}
