package com.hellointerview.service.spotmanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hellointerview.models.Spot;
import com.hellointerview.models.SpotType;
import com.hellointerview.models.Vehicle;

public class SpotManager {

    private List<Spot> spotList = new ArrayList<>();

    public synchronized void fillSpots(List<Spot> spots) {
        spotList.clear();
        spotList.addAll(spots);
    }

    private synchronized Optional<Spot> findFirstOpenSpot(Vehicle vehicle) {
        SpotType spotType = vehicle.getRequiredSpotType();

        return spotList.stream()
            .filter(s -> s.getSpotType() == spotType && s.isFree())
            .findFirst();
    }

    public synchronized Optional<Spot> allocate(Vehicle vehicle) {
        return findFirstOpenSpot(vehicle)
                .map(spot -> {
                    spot.park(vehicle);
                    return spot;
                });
    }

    public synchronized void vacate(Spot spot) { 
        spot.vacate(); 
    }
}
