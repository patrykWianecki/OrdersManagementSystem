package com.app.service.name;

import com.app.dto.StockDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CustomerOrdersService {
    BigDecimal setOrderDiscount(String discount);

    LocalDate setOrderDate();

    Integer setOrderQuantity(String quantity, Long productId);
}
