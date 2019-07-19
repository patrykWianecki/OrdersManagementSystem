package com.app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.app.repository.trade.TradeRepository;

import static com.app.service.builders.MockDataForServiceTests.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeService tradeService;

    @Test
    void should_successfully_create_message_with_trades() {
        // given
        when(tradeRepository.findAll()).thenReturn(createTrades());

        // when
        String actualMessage =  tradeService.printAllTrades();

        // then
        assertTrue(actualMessage.contains("1. TRADE_NAME_1"));
        assertTrue(actualMessage.contains("2. TRADE_NAME_2"));
    }
}