package com.gokhan.binance_websocket_price_data.handler;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gokhan.binance_websocket_price_data.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

class BinanceWebSocketHandlerTest {

  private BinanceWebSocketHandler handler;
  private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() {
    handler = new BinanceWebSocketHandler();
    objectMapper = new ObjectMapper();
  }

  @Test
  void testHandleTextMessageWithSuccess() {
    WebSocketSession session = mock(WebSocketSession.class);
    String jsonMessage = "{\"s\":\"BTCUSDT\",\"p\":\"50000.00\"}";
    TextMessage message = new TextMessage(jsonMessage);

    handler.handleTextMessage(session, message);

    String price = handler.getPrice("BTCUSDT");
    assertEquals("50000.00", price);
  }

  @Test
  void testHandleTextMessageWithInvalidJson() {
    WebSocketSession session = mock(WebSocketSession.class);
    TextMessage message = new TextMessage("invalid json");

    assertDoesNotThrow(() -> handler.handleTextMessage(session, message));

    assertThrows(NotFoundException.class, () -> handler.getPrice("BTCUSDT"));
  }

  @Test
  void testGetPriceWithSymbolNotFound() {
    Exception exception = assertThrows(NotFoundException.class, () -> {
      handler.getPrice("ETXUSDT");
    });

    assertEquals("Symbol not found: ETXUSDT", exception.getMessage());
  }
}