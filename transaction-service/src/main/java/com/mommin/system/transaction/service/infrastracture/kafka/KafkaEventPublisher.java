package com.mommin.system.transaction.service.infrastracture.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mommin.system.common.domain.Event;
import com.mommin.system.transaction.service.domain.DomainEventPublisher;
import com.mommin.system.transaction.service.domain.events.ProcessTransactionEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
final class KafkaEventPublisher implements DomainEventPublisher {

    private static final String PROCESS_TRANSACTIONS = "process_transactions";

    private final KafkaTemplate<String, String> template;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void publish(Event event) {
        if (event instanceof ProcessTransactionEvent) {
            var processTransactionEvent = ((ProcessTransactionEvent) event);
            var accountId = processTransactionEvent.getAccountId().toUUID();

            template.send(
                    PROCESS_TRANSACTIONS,
                    accountId.toString(),
                    objectMapper.writeValueAsString(KafkaProcessTransactionEvent.valueOf(processTransactionEvent)));

            log.debug("The process transactions for {} was sent.", processTransactionEvent.getTransactionId());
        } else {
            throw new RuntimeException("Unknown event type for " + event.getClass().getName());
        }
    }

    @Getter
    @RequiredArgsConstructor
    private static final class KafkaProcessTransactionEvent {

        private final UUID accountId;
        private final UUID transactionId;

        static KafkaProcessTransactionEvent valueOf(ProcessTransactionEvent event) {
            return new KafkaProcessTransactionEvent(
                    event.getAccountId().toUUID(),
                    event.getTransactionId().toUUID());
        }
    }
}
