package com.mommin.system.transaction.service.domain;

import com.mommin.system.common.domain.Event;

public interface DomainEventPublisher {

    void publish(Event event);
}
