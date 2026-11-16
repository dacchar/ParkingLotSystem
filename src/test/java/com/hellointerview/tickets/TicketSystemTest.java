package com.hellointerview.tickets;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hellointerview.models.Spot;
import com.hellointerview.models.SpotType;
import com.hellointerview.service.tickets.TicketSystem;
import com.hellointerview.service.tickets.models.Ticket;

class TicketSystemTest {

    private TicketSystem ticketSystem;

    @BeforeEach
    void setUp() {
        this.ticketSystem = new  TicketSystem();
    }

    @Test
    void firstTicketHasIdOne() {
        Ticket ticket = ticketSystem.issueTicket(Instant.now(), new Spot(1, SpotType.CAR));
        assertEquals(1, ticket.getId());
    }

    @Test
    void secondTicketHasIdTwo() {
        Ticket ticket1 = ticketSystem.issueTicket(Instant.now(), new Spot(1, SpotType.CAR));
        assertEquals(1, ticket1.getId());

        Ticket ticket2 = ticketSystem.issueTicket(Instant.now(), new Spot(2, SpotType.CAR));
        assertEquals(2, ticket2.getId());
    }
}
