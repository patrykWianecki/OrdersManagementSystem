package com.app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.app.repository.shop.ShopRepository;

import static com.app.service.builders.MockDataForServiceTests.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ShopServiceTest {

    @Mock
    private ShopRepository shopRepository;

    @InjectMocks
    private ShopService shopService;

    @Test
    void should_successfully_create_message_with_trades() {
        // given
        when(shopRepository.findAll()).thenReturn(createShops());

        // when
        String actualMessage = shopService.printAllShops();

        // then
        assertTrue(actualMessage.contains("1. SHOP_NAME_1"));
        assertTrue(actualMessage.contains("2. SHOP_NAME_2"));
    }
}