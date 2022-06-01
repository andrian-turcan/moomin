package com.mommin.system.account.service.domain.repository;

import com.mommin.system.account.service.domain.Account;
import com.mommin.system.account.service.domain.CustomerId;

import java.util.List;

public interface AccountRepository {

    void save(Account account);

    List<Account> findAccounts(CustomerId customerId);
}
