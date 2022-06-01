package com.mommin.system.account.service.service;

import com.mommin.system.account.service.domain.CustomerId;
import com.mommin.system.account.service.domain.DomainEventPublisher;
import com.mommin.system.account.service.domain.repository.AccountRepository;
import com.mommin.system.account.service.domain.repository.CustomerRepository;
import com.mommin.system.account.service.service.dto.AccountInfoDto;
import com.mommin.system.account.service.service.dto.CreateAccountDto;
import com.mommin.system.common.domain.Amount;
import com.mommin.system.common.exceptions.NotFoundException;
import com.mommin.system.common.exceptions.ValidationException;
import com.mommin.system.account.service.domain.Account;
import com.mommin.system.account.service.domain.AccountId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public final class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final DomainEventPublisher domainEventPublisher;

    public List<AccountInfoDto> getAccounts(UUID customerId) {
        return accountRepository.findAccounts(CustomerId.valueOf(customerId)).stream()
                .map(AccountInfoDto::valueOf)
                .collect(toList());
    }

    public UUID createAccount(CreateAccountDto createAccountDto) {
        validateAmount(createAccountDto.getAmount());
        validateCustomerExists(createAccountDto.getCustomerId());

        var account = Account.initialize(
                AccountId.newIdentity(),
                CustomerId.valueOf(createAccountDto.getCustomerId()),
                Amount.valueOf(createAccountDto.getAmount()),
                domainEventPublisher);

        accountRepository.save(account);

        log.info("The account was created. {}", account);

        return account.getAccountId().toUUID();
    }

    private void validateCustomerExists(UUID customerId) {
        if (customerRepository.find(CustomerId.valueOf(customerId)).isEmpty()) {
            throw new NotFoundException("Unknown customer with id " + customerId);
        }
    }

    private void validateAmount(BigDecimal value) {
        if (value.scale() > 3) {
            throw new ValidationException("Amount scale must be less than 4.");
        }
    }
}
