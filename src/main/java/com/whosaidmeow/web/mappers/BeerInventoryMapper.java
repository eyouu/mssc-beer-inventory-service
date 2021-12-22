package com.whosaidmeow.web.mappers;

import com.whosaidmeow.brewery.model.BeerInventoryDTO;
import com.whosaidmeow.domain.BeerInventory;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerInventoryMapper {

    BeerInventory beerInventoryDtoToBeerInventory(BeerInventoryDTO beerInventoryDTO);

    BeerInventoryDTO beerInventoryToBeerInventoryDto(BeerInventory beerInventory);
}
