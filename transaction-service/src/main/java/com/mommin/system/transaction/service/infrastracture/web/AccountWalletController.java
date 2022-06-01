package com.mommin.system.transaction.service.infrastracture.web;

import com.mommin.system.transaction.service.service.AccountWalletService;
import com.mommin.system.transaction.service.service.dto.DepositDto;
import com.mommin.system.transaction.service.service.dto.WithdrawalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

//Todo think about changing /accountId in RequestParam or part of the body
@RestController
@RequestMapping("/v1/account-wallet")
@RequiredArgsConstructor
final class AccountWalletController {

    private final AccountWalletService service;

    @GetMapping(path = "/{accountId}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<?> getAccountWallet(@PathVariable("accountId") UUID accountId) {
        return ok().body(service.getAccountWallet(accountId));
    }

    @PostMapping(path = "/{accountId}/deposit", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<?> deposit(@PathVariable("accountId") UUID accountId, @Validated @RequestBody DepositDto depositDto) {
        service.deposit(accountId, depositDto);

        return ok().build();
    }

    @PostMapping(path = "/{accountId}/withdrawal", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<?> withdrawal(@PathVariable("accountId") UUID accountId, @Validated @RequestBody WithdrawalDto withdrawalDto) {
        service.withdraw(accountId, withdrawalDto);

        return ok().build();
    }

    @GetMapping(path = "/{accountId}/transactions", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<?> getAccountWalletTransactions(@PathVariable("accountId") UUID accountId) {
        return ok().body(service.getTransactions(accountId));
    }
}
