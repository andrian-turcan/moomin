package com.mommin.system.transaction.service.domain.events;

import com.mommin.system.common.domain.Event;
import com.mommin.system.transaction.service.domain.AccountId;
import com.mommin.system.transaction.service.domain.TransactionId;
import lombok.Getter;

import static com.mommin.system.common.utils.Guards.notNull;

@Getter
public final class ProcessTransactionEvent implements Event {

    private final AccountId accountId;
    private final TransactionId transactionId;

    public ProcessTransactionEvent(AccountId accountId, TransactionId transactionId) {
        this.accountId = notNull(accountId, "The accountId must not be null.");
        this.transactionId = notNull(transactionId, "The transactionId must not be null.");
    }
}
