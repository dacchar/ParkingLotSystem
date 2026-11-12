package com.hellointerview.service.billing;

import java.time.Duration;

import com.hellointerview.constants.Constants;
import com.hellointerview.service.tickets.models.Ticket;

public class BillingSystem {

    public double calculateFee(Ticket ticket) {
        long minutes = Duration.between(ticket.getStartTime(), ticket.getEndTime()).toMinutes();
        long billedHours = (long) Math.ceil(minutes / 60.0);
        double fee = Constants.BILLING_RATE * billedHours;
        ticket.setFee(fee);

        return fee;
    }

}
