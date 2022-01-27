package com.whosaidmeow.services.listeners;

import com.whosaidmeow.brewery.model.events.AllocateOrderRequestEvent;
import com.whosaidmeow.brewery.model.events.AllocateOrderResultEvent;
import com.whosaidmeow.config.JmsConfig;
import com.whosaidmeow.services.AllocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import static com.whosaidmeow.config.JmsConfig.ALLOCATE_ORDER_QUEUE;
import static com.whosaidmeow.config.JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class AllocationListener {

    private final JmsTemplate jmsTemplate;
    private final AllocationService allocationService;

    @JmsListener(destination = ALLOCATE_ORDER_QUEUE)
    public void listen(AllocateOrderRequestEvent allocateOrderRequestEvent) {
        AllocateOrderResultEvent.AllocateOrderResultEventBuilder builder = AllocateOrderResultEvent.builder();
        builder.beerOrderDTO(allocateOrderRequestEvent.getBeerOrderDTO());

        try {
            Boolean allocationResult = allocationService.allocateOrder(allocateOrderRequestEvent.getBeerOrderDTO());

            // if allocation result = false means we are not fully allocated and should be in pending
            if (allocationResult) {
                builder.pendingInventory(false);
            } else {
                builder.pendingInventory(true);
            }

            builder.allocationError(false);
        } catch (Exception e) {
            log.error("Allocation failed for oder with id: {}", allocateOrderRequestEvent.getBeerOrderDTO().getId());
            builder.allocationError(true);
        }

        jmsTemplate.convertAndSend(ALLOCATE_ORDER_RESPONSE_QUEUE, builder.build());
    }
}
