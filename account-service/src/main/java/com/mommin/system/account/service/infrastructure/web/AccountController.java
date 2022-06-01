package com.mommin.system.account.service.infrastructure.web;

import com.mommin.system.account.service.service.AccountService;
import com.mommin.system.account.service.service.dto.CreateAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
final class AccountController {

    private final AccountService accountService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    ResponseEntity<?> getAccounts(@RequestParam("customerId") UUID customerId) {
        return ok().body(accountService.getAccounts(customerId));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<?> createAccount(@RequestBody @Validated CreateAccountDto createAccountDto) {
        var accountId = accountService.createAccount(createAccountDto);

        return created(fromCurrentRequest().path("/{accountId}").build(accountId)).build();
    }
}
