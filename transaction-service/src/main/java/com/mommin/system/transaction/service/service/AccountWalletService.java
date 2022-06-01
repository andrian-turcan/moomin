package com.mommin.system.transaction.service.service;

import com.mommin.system.common.domain.Amount;
import com.mommin.system.common.exceptions.NotFoundException;
import com.mommin.system.transaction.service.domain.*;
import com.mommin.system.transaction.service.domain.repository.AccountWalletRepository;
import com.mommin.system.transaction.service.domain.repository.TransactionRepository;
import com.mommin.system.transaction.service.service.dto.AccountWalletDetailDto;
import com.mommin.system.transaction.service.service.dto.DepositDto;
import com.mommin.system.transaction.service.service.dto.TransactionDto;
import com.mommin.system.transaction.service.service.dto.WithdrawalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public final class AccountWalletService {

    private final AccountWalletRepository accountRepository;
    private final TransactionRepository transactionRepository;

    private final DomainEventPublisher domainEventPublisher;

    public void initialize(AccountId accountId, Amount amount) {
        accountRepository.save(AccountWallet.initialize(accountId, amount, transactionRepository, domainEventPublisher));
    }

    public Optional<AccountWalletDetailDto> getAccountWallet(UUID accountId) {
        return accountRepository.findAccountWallet(AccountId.valueOf(accountId))
                .map(AccountWalletDetailDto::valueOf);
    }

    public void deposit(UUID accountId, DepositDto depositDto) {
        var accountWallet = findAccountWallet(AccountId.valueOf(accountId));

        accountWallet.deposit(Amount.valueOf(depositDto.getAmount()));
    }

    public void withdraw(UUID accountId, WithdrawalDto withdrawalDto) {
        var accountWallet = findAccountWallet(AccountId.valueOf(accountId));

        accountWallet.withdraw(Amount.valueOf(withdrawalDto.getAmount()));
    }

    public List<TransactionDto> getTransactions(UUID accountId) {
        return transactionRepository.findTransactions(AccountId.valueOf(accountId)).stream().map(TransactionDto::valueOf).collect(toList());
    }

    public void processTransaction(UUID accountId, UUID transactionId) {
        var accountWallet = findAccountWallet(AccountId.valueOf(accountId));
        var transaction = findTransaction(TransactionId.valueOf(transactionId));

        //Change recalculateBalance to return Either<Rejection, Account>
        /*
        * if(result.right()){
        * save result -> of recalculations and completeProcessing
        * } else {
        * mark transaction as fail
        * }
        */
        accountRepository.save(accountWallet.recalculateBalance(transaction.getAmount()));
        transactionRepository.save(transaction.completeProcessing());
    }

    private AccountWallet findAccountWallet(AccountId accountId) {
        var accountWallet = accountRepository.findAccountWallet(accountId);

        if (accountWallet.isPresent()) {
            return accountWallet.get();
        } else {
            throw new NotFoundException("Unknown account with id" + accountId);
        }
    }

    private Transaction findTransaction(TransactionId transactionId) {
        var transaction = transactionRepository.find(transactionId);

        if (transaction.isPresent()) {
            return transaction.get();
        } else {
            throw new NotFoundException("Unknown transaction with id" + transactionId);
        }
    }
}
