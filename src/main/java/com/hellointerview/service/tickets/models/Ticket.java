package com.hellointerview.service.tickets.models;

import java.time.Instant;

import com.hellointerview.models.Spot;

import lombok.Getter;
import lombok.Setter;

// @RequiredArgsConstructor
// @Getter 
// @Setter 
// @ToString 
public class Ticket {
    private final Integer id;
    private Instant startTime;
    private Instant endTime;
    private Spot spot;
    private Double fee;

    public Ticket(Integer id) {
        this.id = id;
    }

    public void open(Instant startTime, Spot spot)  {
         this.startTime =  Instant.now();
    }

    public void close(Instant end, Double fee) {
        this.endTime = Instant.now();
        this.fee = fee;
        this.spot = null;
    }

    public Integer getId() {
        return this.id;
    }

    public Instant getEndTime() {
        return this.endTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Spot getSpot() {
        return spot;
    }

    public Double getFee() {
        return fee;
    }

    @Override
    public String toString() {
        return "Ticket [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", spot=" + spot + ", fee="
                + fee + "]";
    }
}