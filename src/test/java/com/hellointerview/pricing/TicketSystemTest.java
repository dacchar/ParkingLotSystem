package com.hellointerview.pricing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hellointerview.ParkingLotSystem;
import com.hellointerview.models.Car;
import com.hellointerview.models.Spot;
import com.hellointerview.service.tickets.TicketSystem;
import com.hellointerview.service.tickets.models.Ticket;

class TicketSystemTest {

    private TicketSystem ticketSystem;
    private ParkingLotSystem parkingLotSystem;

    @BeforeEach
    void setUp() {
        ticketSystem = new TicketSystem();
    }

    @Test
    void firstTicketHasIdOne() {
        Ticket ticket = ticketSystem.issueNewTicket();

        assertEquals(1, ticket.getId());
        assertNotNull(ticket.getStartTime());
    }

    @Test
    void ticketIdsIncrement() {
        Ticket first = ticketSystem.issueNewTicket();
        Ticket second = ticketSystem.issueNewTicket();

        assertEquals(first.getId() + 1, second.getId());
    }

    @Test 
    void enterCar() {
        List<Spot> list = Arrays.asList(
            new Spot(1),
            new Spot(2),
            new Spot(4),
            new Spot(3, new Car())
        );

        parkingLotSystem.fillCarSpots(list);

        Car car = new Car();
        Optional<Integer> enterCar = parkingLotSystem.enterCar(car);
        // parkingLotSystem.leaveVehicle(car, enterCar.get());
    }
}
