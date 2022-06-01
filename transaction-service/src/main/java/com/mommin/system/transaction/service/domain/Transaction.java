package com.mommin.system.transaction.service.domain;

import com.mommin.system.common.domain.Amount;
import com.mommin.system.common.exceptions.ValidationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

import static com.mommin.system.common.utils.Guards.notNegative;
import static com.mommin.system.common.utils.Guards.notNull;
import static com.mommin.system.transaction.service.domain.Transaction.TransactionStatus.CREATED;
import static com.mommin.system.transaction.service.domain.Transaction.TransactionStatus.PROCESSED;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(of = "transactionId")
public final class Transaction {

    private final TransactionId transactionId;
    private final AccountId accountId;
    private final Amount amount;
    private final Instant time;
    private final TransactionStatus status;

    public static Transaction createTransaction(AccountId accountId, Amount amount) {
        notNull(accountId, "The accountId must not be null.");
        notNull(amount, "The amount must not be null.");

        return new Transaction(TransactionId.newIdentity(), accountId, amount, Instant.now(), CREATED);
    }

    public Transaction completeProcessing() {
        if (status == PROCESSED) {
            throw new ValidationException("The transaction " + transactionId + "has already PROCESSED status.");
        }
        return new Transaction(transactionId, accountId, amount, time, PROCESSED);
    }

    public enum TransactionStatus {
        CREATED, PROCESSED
    }
}
