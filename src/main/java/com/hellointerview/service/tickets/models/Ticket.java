package com.hellointerview.service.tickets.models;

import java.math.BigDecimal;
import java.time.Instant;

import com.hellointerview.models.Spot;

public class Ticket {
    private final int id;
    private final Instant startTime;
    private final Spot spot;
    private Instant endTime;
    private BigDecimal fee;

    public Ticket(int id, Instant startTime, Spot spot) {
        this.id = id;
        this.startTime = startTime;
        this.spot = spot;
    }

    public void close(Instant end, BigDecimal fee) {
        this.endTime = end;
        this.fee = fee;
    }

    public int getId() {
        return this.id;
    }

    public Instant getEndTime() {
        return this.endTime;
    }

        public Instant getStartTime() {
        return startTime;
    }

    public Spot getSpot() {
        return spot;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public boolean isOpen() {
        return this.endTime == null;
    }

    public boolean isClosed() {
        return !isOpen();
    }

    @Override
    public String toString() {
        return "Ticket [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", spot=" + spot + ", fee="
                + fee + "]";
    }
}