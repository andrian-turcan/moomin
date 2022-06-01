package com.mommin.system.account.service.infrastructure.persistence;

import com.mommin.system.account.service.domain.Account;
import com.mommin.system.account.service.domain.CustomerId;
import com.mommin.system.account.service.domain.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Component
@Slf4j
final class AccountRepositoryCache implements AccountRepository {

    private final Set<AccountEntity> _tmpCache = new LinkedHashSet<>();

    @Override
    public void save(Account account) {
        _tmpCache.add(AccountEntity.valueOf(account));
        log.info("Account {} was modified.", account.getAccountId());
    }

    @Override
    public List<Account> findAccounts(CustomerId customerId) {
        return _tmpCache.stream()
                .map(AccountEntity::toAccount)
                .filter(account -> account.getCustomerId().equals(customerId))
                .collect(toList());
    }
}
