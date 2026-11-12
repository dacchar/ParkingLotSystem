package com.hellointerview.pricing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hellointerview.constants.Constants;
import com.hellointerview.service.billing.BillingSystem;
import com.hellointerview.service.tickets.models.Ticket;

class BillingSystemTest {

    private BillingSystem billingSystem;

    @BeforeEach
    void setUp() {
        this.billingSystem = new BillingSystem();
    }

    @Test
    void durationTwoHours() {
        Ticket ticket = new Ticket(1);
        ticket.setStartTime(Instant.parse("2026-11-12T10:00:00Z"));
        ticket.setEndTime(Instant.parse("2026-11-12T12:00:00Z"));

        assertEquals(Constants.BILLING_RATE * 2, billingSystem.calculateFee(ticket));
    }

    @Test
    void durationTenMinutes() {
        Ticket ticket = new Ticket(1);
        ticket.setStartTime(Instant.parse("2026-11-12T10:00:00Z"));
        ticket.setEndTime(Instant.parse("2026-11-12T10:10:00Z"));

        assertEquals(Constants.BILLING_RATE * 1, billingSystem.calculateFee(ticket));
    }

}
