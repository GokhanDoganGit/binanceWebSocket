package com.gokhan.binance_websocket_price_data.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gokhan.binance_websocket_price_data.exception.NotFoundException;
import com.gokhan.binance_websocket_price_data.handler.BinanceWebSocketHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BinanceController.class)
class BinanceControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BinanceWebSocketHandler handler;

  @Test
  void testRetrievePriceDataWithSuccess() throws Exception {
    // When
    String price = "60000.00";
    String symbol = "BTCUSDT";
    when(handler.getPrice(anyString())).thenReturn(price);

    // Then
    mockMvc.perform(get("/api/prices/{symbol}/", symbol)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(price));
  }

  @Test
  void testRetrievePriceDataWithSymbolNotFound() throws Exception {
    //When
    String symbol = "BTCUSDT";
    when(handler.getPrice(anyString()))
        .thenThrow(new NotFoundException("Symbol not found: BTCUSDT"));

    // Then
    mockMvc.perform(get("/api/prices/{symbol}", symbol)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

}