package com.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProducerDto {

    private Long id;
    private String name;
    private CountryDto countryDto;
    private TradeDto tradeDto;
}
