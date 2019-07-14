package com.app.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.app.model.Producer;
import com.app.repository.producer.ProducerRepository;
import com.app.repository.stock.StockRepository;
import com.app.repository.trade.TradeRepository;

import static com.app.service.builders.MockDataForServiceTests.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MyServiceTest {

    @Mock
    private ProducerRepository producerRepository;
    @Mock
    private TradeRepository tradeRepository;
    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private MyService myService;

    @Test
    void should_successfully_find_producers_with_given_trade_name_and_who_produced_more_than_given() {
        // given
        when(tradeRepository.findAll()).thenReturn(createTrades());
        when(stockRepository.findAll()).thenReturn(createStocks());
        when(producerRepository.findAll()).thenReturn(createProducers());

        // when
        List<Producer> actualProducers = myService.producersWithGivenTradeNameWhichProducesMoreThanGivenNumber("TRADE_NAME_1", 10);

        // then
        assertNotNull(actualProducers);
        assertEquals(1, actualProducers.size());

        Producer actualProducer = actualProducers.get(0);
        assertEquals(1L, actualProducer.getId());
        assertEquals("PRODUCER_NAME_1", actualProducer.getName());
        assertEquals("TRADE_NAME_1", actualProducer.getTrade().getName());
    }
}