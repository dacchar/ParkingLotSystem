package com.hellointerview.models;

import lombok.ToString;

@ToString 
public abstract class Vehicle {
    abstract SpotType getSpotType();
}
