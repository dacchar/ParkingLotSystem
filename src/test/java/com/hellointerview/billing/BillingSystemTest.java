package com.hellointerview.billing;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hellointerview.service.billing.BillingSystem;

class BillingSystemTest {

    private BillingSystem billingSystem;

    @BeforeEach
    void setUp() {
        this.billingSystem = new BillingSystem();
    }

    @Test
    void durationTwoHours() {
        BigDecimal fee = billingSystem.calculateFee(
                Instant.parse("2026-11-12T10:00:00Z"),
                Instant.parse("2026-11-12T12:00:00Z")
            );

        assertTrue( fee.compareTo(new BigDecimal(6)) == 0 );
    }

    @Test
    void durationTenMinutes() {
        BigDecimal fee = billingSystem.calculateFee(
                Instant.parse("2026-11-12T10:00:00Z"),
                Instant.parse("2026-11-12T10:10:00Z")
            );

        assertTrue( fee.compareTo(new BigDecimal(3)) == 0);
    }

}
