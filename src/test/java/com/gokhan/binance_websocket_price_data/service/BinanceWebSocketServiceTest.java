package com.gokhan.binance_websocket_price_data.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.gokhan.binance_websocket_price_data.handler.BinanceWebSocketHandler;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

class BinanceWebSocketServiceTest {

  @Mock
  private BinanceWebSocketHandler handler;

  @Mock
  private StandardWebSocketClient webSocketClient;

  @InjectMocks
  private BinanceWebSocketService service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    List<String> symbols = Arrays.asList("BTCUSDT", "ETHUSDT");

    service.setSymbols(symbols);
  }

  @Test
  void testConnect() {
    //When
    service.connect();
    String expectedUrl = "wss://stream.binance.com:9443/ws/BTCUSDT@trade/ETHUSDT@trade";

    //Then
    verify(webSocketClient, times(1)).execute(handler, expectedUrl);
  }

}