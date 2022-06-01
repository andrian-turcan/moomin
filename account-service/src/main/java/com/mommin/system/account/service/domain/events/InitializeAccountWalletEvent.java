package com.mommin.system.account.service.domain.events;

import com.mommin.system.common.domain.Event;
import com.mommin.system.account.service.domain.AccountId;
import com.mommin.system.common.domain.Amount;
import lombok.Getter;
import lombok.ToString;

import static com.mommin.system.common.utils.Guards.notNull;

@Getter
@ToString
public final class InitializeAccountWalletEvent implements Event {

    private final AccountId accountId;
    private final Amount amount;

    public InitializeAccountWalletEvent(AccountId accountId, Amount amount) {
        this.accountId = notNull(accountId, "The accountId must not be null.");
        this.amount = notNull(amount, "The amount must not be null.");
    }
}
