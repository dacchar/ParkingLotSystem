package com.hellointerview.service.tickets;

import java.util.ArrayList;
import java.util.List;

import com.hellointerview.service.billing.BillingSystem;
import com.hellointerview.service.tickets.models.Ticket;

public class TicketSystem {

    private Integer currentTicketId = 0;
    private List<Ticket> tickets = new ArrayList<>();

    public Ticket issueNewTicket() {
        Integer nextTicketId = currentTicketId + 1;
        Ticket newTicket = new Ticket(nextTicketId);
        tickets.add(newTicket);
        currentTicketId = nextTicketId;

        return newTicket;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(issueNewTicket());
    }

    public Ticket getTicket(Integer ticketId) {
        Ticket ticket = tickets.stream()
            .filter(t -> ticketId.equals(t.getId()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Ticket not found: " + ticketId));
        
        validate(ticket);

        return ticket;
    }

    private void validate(Ticket ticket) {
        checkIfIsUsed(ticket);
    }

    private void checkIfIsUsed(Ticket ticket) {
        if(ticket.getEndTime() != null) {
            throw new IllegalArgumentException("Ticket is invalid: " + ticket.getId());
        }
    }
}
