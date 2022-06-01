package com.mommin.system.transaction.service.infrastracture.kafka.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mommin.system.common.domain.Amount;
import com.mommin.system.transaction.service.domain.AccountId;
import com.mommin.system.transaction.service.service.AccountWalletService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
final class KafkaAccountWalletInitializerListener {

    private final AccountWalletService accountWalletService;

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @KafkaListener(topics = "initialize_account_wallet", groupId = "transaction_initialize_account")
    public void listenTransactionInitializeAccountGroup(@Payload String message) {
        var event = objectMapper.readValue(message, KafkaInitializeAccountEvent.class);

        log.debug("The account {} started to be initialized.", event.getAccountId());

        accountWalletService.initialize(AccountId.valueOf(event.accountId), Amount.valueOf(event.amount));
    }

    @Getter
    @RequiredArgsConstructor
    private static final class KafkaInitializeAccountEvent {

        private final UUID accountId;
        private final BigDecimal amount;
    }
}
