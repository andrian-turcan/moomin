package com.mommin.system.account.service.domain;

import com.mommin.system.common.domain.Event;

public interface DomainEventPublisher {
    void publish(Event event);
}
