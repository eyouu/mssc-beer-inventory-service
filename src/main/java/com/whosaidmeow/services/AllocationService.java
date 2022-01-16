package com.whosaidmeow.services;

import com.whosaidmeow.brewery.model.BeerOrderDTO;

public interface AllocationService {

    Boolean allocateOrder(BeerOrderDTO beerOrderDTO);

    void deallocateOrder(BeerOrderDTO beerOrderDTO);
}
