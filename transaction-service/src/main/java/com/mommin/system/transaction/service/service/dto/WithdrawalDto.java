package com.mommin.system.transaction.service.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor //spring problem need to add custom convertor
public final class WithdrawalDto {

    @NotNull(message = "amount is required")
    @PositiveOrZero(message = "amount must be greater or equal with 0")
    private BigDecimal amount;

    public WithdrawalDto(BigDecimal amount) {
        this.amount = amount;
    }
}
