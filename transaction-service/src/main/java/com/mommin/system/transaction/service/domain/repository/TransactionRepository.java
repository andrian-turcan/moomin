package com.mommin.system.transaction.service.domain.repository;

import com.mommin.system.transaction.service.domain.AccountId;
import com.mommin.system.transaction.service.domain.Transaction;
import com.mommin.system.transaction.service.domain.TransactionId;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    void save(Transaction transaction);

    List<Transaction> findTransactions(AccountId accountId);

    Optional<Transaction> find(TransactionId transactionId);
}
