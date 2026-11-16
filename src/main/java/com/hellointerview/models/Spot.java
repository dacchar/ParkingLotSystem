package com.hellointerview.models;

public class Spot {
    final private int number;
    final private SpotType spotType;
    private Vehicle vehicle;

    public Spot(int number, SpotType spotType, Vehicle vehicle) {
        this.number = number;
        this.vehicle = vehicle;
        this.spotType = spotType;
    }

    public Spot(int number, SpotType spotType) {
        this(number, spotType, null);
    }

    public Spot(int number) {
        this(number, SpotType.CAR, null);
    }

    public int getNumber() {
        return number;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }    


    public void park(Vehicle v) { 
        this.vehicle = v; 
    }

    public void vacate() { 
        this.vehicle = null; 
    }

    public boolean isFree() { 
        return this.vehicle == null; 
    }

    @Override
    public String toString() {
        return "Spot [number=" + number + ", vehicle=" + vehicle + "]";
    }

}
