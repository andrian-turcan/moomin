package com.mommin.system.transaction.service.domain;

import com.mommin.system.common.domain.Amount;
import com.mommin.system.transaction.service.domain.events.ProcessTransactionEvent;
import com.mommin.system.transaction.service.domain.repository.TransactionRepository;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static com.mommin.system.common.utils.Guards.notNegative;
import static com.mommin.system.common.utils.Guards.notNull;
import static com.mommin.system.transaction.service.domain.Transaction.createTransaction;

@Getter
@EqualsAndHashCode(of = "accountWalletId")
public final class AccountWallet {

    private final AccountWalletId accountWalletId;
    private final AccountId accountId;
    private final Amount balance;

    private final TransactionRepository transactionRepository;
    private final DomainEventPublisher domainEventPublisher;

    public AccountWallet(AccountWalletId accountWalletId,
                         AccountId accountId,
                         Amount balance,
                         TransactionRepository transactionRepository,
                         DomainEventPublisher domainEventPublisher) {
        this.accountWalletId = notNull(accountWalletId, "The accountWalletId must not be null.");
        this.accountId = notNull(accountId, "The accountId must not be null.");
        notNull(balance, "The balance must not be null.");
        notNegative(balance.value(), "The balance must not be null.");
        this.balance = balance;
        this.transactionRepository = notNull(transactionRepository, "The transactionRepository must not be null.");
        this.domainEventPublisher = notNull(domainEventPublisher, "The domainEventPublisher must not be null.");
    }

    public static AccountWallet initialize(AccountId accountId,
                                           Amount amount,
                                           TransactionRepository transactionRepository,
                                           DomainEventPublisher domainEventPublisher) {

        return new AccountWallet(AccountWalletId.newIdentity(), accountId, amount, transactionRepository, domainEventPublisher);
    }

    public void withdraw(Amount amount) {
        notNull(amount, "The amount must not be null.");
        notNegative(amount.value(), "The amount must not be negative.");
        notNegative(balance.subtract(amount).value(), "Not enough money in the wallet.");

        var transaction = createTransaction(accountId, amount.negate());

        transactionRepository.save(transaction);

        domainEventPublisher.publish(new ProcessTransactionEvent(accountId, transaction.getTransactionId()));
    }

    public void deposit(Amount amount) {
        notNull(amount, "The amount must not be null.");
        notNegative(amount.value(), "The amount must not be negative.");

        var transaction = createTransaction(accountId, amount);

        transactionRepository.save(transaction);

        domainEventPublisher.publish(new ProcessTransactionEvent(accountId, transaction.getTransactionId()));
    }

    public AccountWallet recalculateBalance(Amount amount) {
        notNull(balance, "The balance must not be null.");
        notNegative(balance.value(), "The balance must not be negative.");

        var newBalance = balance.add(amount);
        return new AccountWallet(accountWalletId, accountId, newBalance, transactionRepository, domainEventPublisher);
    }
}
