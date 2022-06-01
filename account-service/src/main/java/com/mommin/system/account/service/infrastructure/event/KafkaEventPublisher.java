package com.mommin.system.account.service.infrastructure.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mommin.system.common.domain.Event;
import com.mommin.system.account.service.domain.DomainEventPublisher;
import com.mommin.system.account.service.domain.events.InitializeAccountWalletEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
final class KafkaEventPublisher implements DomainEventPublisher {

    private static final String INITIALIZE_ACCOUNT_WALLET_TOPIC = "initialize_account_wallet";

    private final KafkaTemplate<String, String> template;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void publish(Event event) {
        if (event instanceof InitializeAccountWalletEvent) {
            var initializeAccountEvent = ((InitializeAccountWalletEvent) event);
            var accountId = initializeAccountEvent.getAccountId().toUUID();

            template.send(
                    INITIALIZE_ACCOUNT_WALLET_TOPIC,
                    accountId.toString(),
                    objectMapper.writeValueAsString(KafkaInitializeAccountWalletEvent.valueOf(initializeAccountEvent)));

            log.debug("The initialize account wallet for {} was sent.", accountId);
        } else {
            throw new RuntimeException("Unknown event type for " + event.getClass().getName());
        }
    }

    @Getter
    @RequiredArgsConstructor
    private static final class KafkaInitializeAccountWalletEvent {

        private final UUID accountId;
        private final BigDecimal amount;

        static KafkaInitializeAccountWalletEvent valueOf(InitializeAccountWalletEvent event) {
            return new KafkaInitializeAccountWalletEvent(event.getAccountId().toUUID(), event.getAmount().value());
        }
    }
}
