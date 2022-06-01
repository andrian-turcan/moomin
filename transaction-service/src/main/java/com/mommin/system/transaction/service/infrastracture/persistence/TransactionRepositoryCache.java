package com.mommin.system.transaction.service.infrastracture.persistence;

import com.mommin.system.transaction.service.domain.AccountId;
import com.mommin.system.transaction.service.domain.Transaction;
import com.mommin.system.transaction.service.domain.TransactionId;
import com.mommin.system.transaction.service.domain.repository.TransactionRepository;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Component
final class TransactionRepositoryCache implements TransactionRepository {

    private final Set<Transaction> transactions = new LinkedHashSet<>();

    @Override
    public void save(Transaction transaction) {
        transactions.remove(transaction);
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> findTransactions(AccountId accountId) {
        return transactions.stream().filter(tr -> tr.getAccountId().equals(accountId)).collect(toList());
    }

    @Override
    public Optional<Transaction> find(TransactionId transactionId) {
        return transactions.stream().filter(x -> x.getTransactionId().equals(transactionId)).findFirst();
    }
}
