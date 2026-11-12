package com.hellointerview;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.hellointerview.models.Car;
import com.hellointerview.models.LargeVehicle;
import com.hellointerview.models.Motorcycle;
import com.hellointerview.models.Spot;
import com.hellointerview.models.Vehicle;
import com.hellointerview.service.billing.BillingSystem;
import com.hellointerview.service.tickets.TicketSystem;
import com.hellointerview.service.tickets.models.Ticket;

public class ParkingLotSystem {

    private List<Spot> carSpots = new ArrayList<>();
    private List<Spot> largeVehicleSpots = new ArrayList<>();
    private List<Spot> motorcycleSpots = new ArrayList<>();

    private TicketSystem ticketSystem;
    private BillingSystem billingSystem;

    public ParkingLotSystem(TicketSystem ticketSystem, BillingSystem billingSystem) {
        this.ticketSystem = ticketSystem;
        this.billingSystem = billingSystem;
    }

    public void fillCarSpots(List<Spot> spots) {
        fillSpots(spots, carSpots);
    }

    public void fillLargeVehicleSpots(List<Spot> spots) {
        fillSpots(spots, largeVehicleSpots);
    }

    public void fillMotorcycleSpots(List<Spot> spots) {
        fillSpots(spots, motorcycleSpots);
    }

    private void fillSpots(List<Spot> spots, List<Spot> target) {
        target.addAll(spots);
    }

    public Optional<Integer> enterCar(Car car) {
        return enterVehicle(car, carSpots);
    }

    public Optional<Integer> enterLargeVehicle(LargeVehicle largeVehicle) {
        return enterVehicle(largeVehicle, largeVehicleSpots);
    }

    public Optional<Integer> enterMotorcycle(Motorcycle motorcycle) {
        return enterVehicle(motorcycle, motorcycleSpots);
    }

    private Optional<Integer> enterVehicle(Vehicle vehicle, List<Spot> spots) {
        return spots.stream()
            .filter(s -> s.isFree())
            .findFirst()
            .map(spot -> {
                spot.park(vehicle);
                Ticket ticket = ticketSystem.issueNewTicket();
                ticket.open(Instant.now(), spot);

                return ticket.getId();
            });
    }

    public double leaveVehicle(Integer ticketId) {
        Ticket ticket = ticketSystem.getTicket(ticketId);
        double fee = billingSystem.calculateFee(ticket);
        ticket.close(Instant.now(), fee);

        return fee;
    }

    public Double getFee(Integer ticketId) {
        return ticketSystem.getTicket(ticketId).getFee();
    }

    public static void main(String[] args) {
        Integer foo = 77;
        System.out.println(foo);
        System.out.println("Hello, World!");
    }
}
