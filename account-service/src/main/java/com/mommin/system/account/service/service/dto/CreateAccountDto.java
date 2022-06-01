package com.mommin.system.account.service.service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class CreateAccountDto {

    @NotNull(message = "customerId is required")
    private final UUID customerId;

    @NotNull(message = "amount is required")
    @PositiveOrZero(message = "amount must be greater or equal with 0")
    private final BigDecimal amount;
}
