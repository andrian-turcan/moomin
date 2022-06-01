package com.mommin.system.common.domain;

import com.mommin.system.common.utils.Guards;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode
public class Amount {

    private final BigDecimal value;

    private Amount(BigDecimal value) {
        Guards.notNull(value, "The value must not be null.");
        Guards.notNegative(value, "The amount value must not be negative.");
        this.value = value;
    }

    public static Amount valueOf(BigDecimal value) {
        return new Amount(value);
    }

    public BigDecimal value() {
        return this.value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
