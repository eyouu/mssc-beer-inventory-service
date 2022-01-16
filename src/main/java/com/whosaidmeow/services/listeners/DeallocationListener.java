package com.whosaidmeow.services.listeners;

import com.whosaidmeow.brewery.model.events.AllocateOrderRequestEvent;
import com.whosaidmeow.brewery.model.events.AllocateOrderResultEvent;
import com.whosaidmeow.services.AllocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import static com.whosaidmeow.config.JmsConfig.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeallocationListener {

    private final AllocationService allocationService;

    @JmsListener(destination = DEALLOCATE_ORDER_QUEUE)
    public void listen(AllocateOrderRequestEvent allocateOrderRequestEvent) {
        allocationService.deallocateOrder(allocateOrderRequestEvent.getBeerOrderDTO());
    }
}
