package com.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDto {

    private Long id;
    private Integer quantity;
    private ProductDto productDto;
    private ShopDto shopDto;
}
