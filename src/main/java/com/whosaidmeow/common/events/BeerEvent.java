package com.whosaidmeow.common.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class BeerEvent implements Serializable {

    static final long serialVersionUID = 3988545114367772700L;

    private BeerDTO beerDTO;
}

