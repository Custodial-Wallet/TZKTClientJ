package com.bgnd.tzktjavaclient.model;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class Tx {

    // Constants

    private static final BigDecimal XTZ_COEFFICIENT = BigDecimal.valueOf(10).pow(6);

    // Variables

    private Address target;
    private Integer amount;

    // Public

    public BigDecimal getAmountAsXTZ() {
        final BigDecimal amount = BigDecimal.valueOf(this.amount);

        return amount.divide(XTZ_COEFFICIENT, 6, RoundingMode.HALF_UP);
    }

    // Address

    @Data
    public static class Address {
        private String address;
    }
}