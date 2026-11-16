package com.hellointerview;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import com.hellointerview.models.Spot;
import com.hellointerview.models.Vehicle;
import com.hellointerview.service.billing.BillingSystem;
import com.hellointerview.service.spotmanagement.SpotManager;
import com.hellointerview.service.tickets.TicketSystem;
import com.hellointerview.service.tickets.models.Ticket;

public class ParkingLotSystem {

    private final SpotManager spotManager;
    private final TicketSystem ticketSystem;
    private final BillingSystem billingSystem;

    public ParkingLotSystem(SpotManager spotManager, TicketSystem ticketSystem, BillingSystem billingSystem) {
        this.spotManager = spotManager;
        this.ticketSystem = ticketSystem;
        this.billingSystem = billingSystem;
    }

    public synchronized void fillSpots(List<Spot> spots) {
        spotManager.fillSpots(spots);
    }

    public synchronized Optional<Integer> enterVehicle(Vehicle vehicle) {
        return enterVehicle(vehicle, Instant.now());
    }

    public synchronized Optional<Integer> enterVehicle(Vehicle vehicle, Instant instant) {

        return spotManager
                .allocate(vehicle)
                .map(spot -> {
                    Ticket ticket = ticketSystem.issueTicket(instant, spot);
                    return ticket.getId();
                });
    }

    public synchronized BigDecimal leaveVehicle(int ticketId) {
        return leaveVehicle(ticketId, Instant.now());
    }

    public synchronized BigDecimal leaveVehicle(int ticketId, Instant endTime) {
        Ticket ticket = ticketSystem.getOpenTicket(ticketId);
        ticket.close(
            endTime,
            billingSystem.calculateFee(ticket.getStartTime(), endTime)
        );
        spotManager.vacate(ticket.getSpot());
        
        return ticket.getFee();
    }

    public synchronized BigDecimal getFee(int ticketId) {
        return ticketSystem.getClosedTicket(ticketId).getFee();
    }

    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
