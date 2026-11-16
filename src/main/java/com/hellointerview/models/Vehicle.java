package com.hellointerview.models;

public class Vehicle {
    private final VehicleType type;

    public Vehicle() {
        this(VehicleType.CAR);
    }

    public Vehicle(VehicleType type) {
        this.type = type;
    }

    public VehicleType getType() {
        return type;
    }

    public SpotType getRequiredSpotType() {
        return switch (type) {
            case CAR -> SpotType.CAR;
            case MOTORCYCLE -> SpotType.MOTORCYCLE;
            case LARGE_VEHICLE -> SpotType.LARGE_VEHICLE;
        };
    }
}
