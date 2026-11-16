package com.hellointerview.service.billing;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

import com.hellointerview.service.billing.constants.Constants;

public class BillingSystem {

    public BigDecimal calculateFee(Instant start, Instant end) {
        long minutes = Duration.between(start, end).toMinutes();
        long billedHours = (long) Math.ceil(minutes / 60.0);
        BigDecimal fee = Constants.BILLING_RATE.multiply( BigDecimal.valueOf(billedHours) );

        return fee;
    }
}
