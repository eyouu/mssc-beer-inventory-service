package com.whosaidmeow.services;

import com.whosaidmeow.brewery.model.events.NewInventoryEvent;
import com.whosaidmeow.domain.BeerInventory;
import com.whosaidmeow.repositories.BeerInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.whosaidmeow.config.JmsConfig.NEW_INVENTORY_QUEUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class NewInventoryListener {

    private final BeerInventoryRepository beerInventoryRepository;

    @Transactional
    @JmsListener(destination = NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent newInventoryEvent) {
        log.debug("Got inventory: {}", newInventoryEvent);

        beerInventoryRepository.save(BeerInventory.builder()
                .id(newInventoryEvent.getBeerDTO().getId())
                .upc(newInventoryEvent.getBeerDTO().getUpc())
                .quantityOnHand(newInventoryEvent.getBeerDTO().getQualityOnHand())
                .build());
    }
}
