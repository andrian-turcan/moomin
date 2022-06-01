package com.mommin.system.transaction.service.service.dto;

import com.mommin.system.transaction.service.domain.Transaction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@RequiredArgsConstructor
public final class TransactionDto {

    private final BigDecimal amount;
    private final Instant time;
    private final String status;

    public static TransactionDto valueOf(Transaction transaction) {
        return new TransactionDto(transaction.getAmount().value(), transaction.getTime(), transaction.getStatus().name());
    }
}
