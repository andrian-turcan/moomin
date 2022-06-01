package com.mommin.system.common.domain;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

import static com.mommin.system.common.utils.Guards.notNull;

@EqualsAndHashCode
public class Amount {

    private final BigDecimal value;

    private Amount(BigDecimal value) {
        notNull(value, "The value must not be null.");
        this.value = value;
    }

    public static Amount valueOf(BigDecimal value) {
        return new Amount(value);
    }

    public BigDecimal value() {
        return this.value;
    }

    public Amount add(Amount amount) {
        notNull(amount, "The amount must not be null.");
        return new Amount(value.add(amount.value));
    }
    public Amount subtract(Amount amount) {
        notNull(amount, "The amount must not be null.");
        return new Amount(value.subtract(amount.value));
    }

    public Amount negate() {
        return new Amount(value.negate());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
