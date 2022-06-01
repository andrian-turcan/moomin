package com.mommin.system.account.service.domain;

import com.mommin.system.account.service.domain.events.InitializeAccountWalletEvent;
import com.mommin.system.common.domain.Amount;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static com.mommin.system.common.utils.Guards.notNull;

@Getter
@EqualsAndHashCode(of = "accountId")
@ToString
public final class Account {

    private final AccountId accountId;
    private final CustomerId customerId;

    public Account(AccountId accountId, CustomerId customerId) {
        this.accountId = notNull(accountId, "The accountId must not be null.");
        this.customerId = notNull(customerId, "The customerId must not be null.");
    }

    public static Account initialize(AccountId accountId,
                                     CustomerId customerId,
                                     Amount amount,
                                     DomainEventPublisher domainEventPublisher) {
        notNull(amount, "The amount must not be null.");
        notNull(domainEventPublisher, "The domainEventPublisher must not be null.");

        var account = new Account(accountId, customerId);
        domainEventPublisher.publish(new InitializeAccountWalletEvent(accountId, amount));

        return account;
    }
}
