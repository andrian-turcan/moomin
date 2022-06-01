package com.mommin.system.common.utils;

import com.mommin.system.common.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Guards {

    public static <T> T notNull(T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }

    public static BigDecimal notNegative(BigDecimal object, String message) {
        if (object.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException(message);
        }
        return object;
    }
}
