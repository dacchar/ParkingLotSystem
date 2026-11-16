package com.hellointerview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hellointerview.models.Spot;
import com.hellointerview.models.SpotType;
import com.hellointerview.models.Vehicle;
import com.hellointerview.service.billing.BillingSystem;
import com.hellointerview.service.billing.constants.Constants;
import com.hellointerview.service.spotmanagement.SpotManager;
import com.hellointerview.service.tickets.TicketSystem;

class ParkingLotSystemTest {

    private ParkingLotSystem parkingLotSystem;

    @BeforeEach
    void setUp() {
        this.parkingLotSystem = new ParkingLotSystem( new SpotManager(), new TicketSystem(), new BillingSystem());
    }

    @Test 
    void enterVehicle() {
        List<Spot> list = Arrays.asList(
            new Spot(1, SpotType.CAR, new Vehicle()),
            new Spot(2, SpotType.CAR),
            new Spot(3, SpotType.CAR, new Vehicle()),
            new Spot(4, SpotType.CAR)
        );
        parkingLotSystem.fillSpots(list);

        Vehicle vehicle = new Vehicle();
        Optional<Integer> ticketId = parkingLotSystem.enterVehicle(vehicle, Instant.now());
        assertEquals(1, ticketId.get());
        parkingLotSystem.leaveVehicle(ticketId.get());
    }

    @Test 
    void enterTwoVehicle() {
        List<Spot> list = Arrays.asList(
            new Spot(1, SpotType.CAR, new Vehicle()),
            new Spot(2, SpotType.CAR),
            new Spot(3, SpotType.CAR, new Vehicle()),
            new Spot(4, SpotType.CAR)
        );
        parkingLotSystem.fillSpots(list);

        Optional<Integer> ticketId1 = parkingLotSystem.enterVehicle(new Vehicle(), Instant.now());
        assertEquals(1, ticketId1.get());
        
        Optional<Integer> ticketId2 = parkingLotSystem.enterVehicle(new Vehicle(), Instant.now());
        assertEquals(2, ticketId2.get());

        parkingLotSystem.leaveVehicle(ticketId1.get());
    }

    @Test 
    void checkFee() {
        List<Spot> list = Arrays.asList(
            new Spot(1, SpotType.CAR),
            new Spot(2, SpotType.CAR),
            new Spot(3, SpotType.CAR, new Vehicle()),
            new Spot(4, SpotType.CAR)
        );
        parkingLotSystem.fillSpots(list);

        Vehicle vehicle = new Vehicle();
        Optional<Integer> ticketId1 = parkingLotSystem.enterVehicle(vehicle, Instant.parse("2026-11-12T10:00:00Z"));
        BigDecimal fee = parkingLotSystem.leaveVehicle(ticketId1.get(), Instant.parse("2026-11-12T10:10:00Z"));
        
        assertTrue(fee.compareTo(Constants.BILLING_RATE) == 0);
    }

    @Test 
    void checkLeavedFee() {
        List<Spot> list = Arrays.asList(
            new Spot(1, SpotType.CAR)
        );
        parkingLotSystem.fillSpots(list);

        Vehicle vehicle = new Vehicle();
        Optional<Integer> ticketId1 = parkingLotSystem.enterVehicle(vehicle, Instant.parse("2026-11-12T10:00:00Z"));
        BigDecimal fee = parkingLotSystem.leaveVehicle(ticketId1.get(), Instant.parse("2026-11-12T10:10:00Z"));
        BigDecimal fee2 = parkingLotSystem.getFee(ticketId1.get());
        assertTrue(fee.compareTo(fee2) == 0);
    }

}
