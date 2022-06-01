package com.mommin.system.transaction.service.domain.repository;

import com.mommin.system.transaction.service.domain.AccountId;
import com.mommin.system.transaction.service.domain.AccountWallet;

import java.util.Optional;

public interface AccountWalletRepository {

    Optional<AccountWallet> findAccountWallet(AccountId accountId);

    void save(AccountWallet accountWallet);
}
