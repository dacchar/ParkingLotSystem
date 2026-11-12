package com.hellointerview.models;

public class Spot {
    final private Integer number;
    private SpotType spotType = SpotType.CAR;
    private Vehicle vehicle;

    public Spot(Integer number) {
        this.number = number;
    }

    public Spot(Integer number, Vehicle vehicle, SpotType spotType) {
        this.number = number;
        this.vehicle = vehicle;
        this.spotType = spotType;
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
