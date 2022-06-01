package com.mommin.system.transaction.service.infrastracture.persistence;

import com.mommin.system.transaction.service.domain.AccountId;
import com.mommin.system.transaction.service.domain.repository.AccountWalletRepository;
import com.mommin.system.transaction.service.domain.AccountWallet;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Component
final class AccountRepositoryCache implements AccountWalletRepository {

    private final Set<AccountWallet> accountWallets = new LinkedHashSet<>();

    @Override
    public Optional<AccountWallet> findAccountWallet(AccountId accountId) {
        return accountWallets.stream().filter(aw -> aw.getAccountId().equals(accountId)).findFirst();
    }

    @Override
    public void save(AccountWallet accountWallet) {
        accountWallets.remove(accountWallet);
        accountWallets.add(accountWallet);
    }
}
