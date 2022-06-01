package com.mommin.system.transaction.service.infrastracture.kafka.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mommin.system.transaction.service.service.AccountWalletService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
final class KafkaTransactionProcessorListener {

    private final AccountWalletService accountWalletService;

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @KafkaListener(topics = "process_transactions", groupId = "process_transaction")
    public void listenProcessTransactionGroup(@Payload String message) {
        var event = objectMapper.readValue(message, KafkaProcessTransactionEvent.class);

        log.debug("The process transactions for {} was sent.", event.getTransactionId());

        accountWalletService.processTransaction(event.accountId, event.transactionId);
    }

    @Getter
    @RequiredArgsConstructor
    private static final class KafkaProcessTransactionEvent {

        private final UUID accountId;
        private final UUID transactionId;
    }
}
