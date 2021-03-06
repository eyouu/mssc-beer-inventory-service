package com.whosaidmeow.brewery.model.events;

import com.whosaidmeow.brewery.model.BeerOrderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllocateOrderResultEvent {

    private BeerOrderDTO beerOrderDTO;
    private Boolean allocationError = false;
    private Boolean pendingInventory = false;
}
