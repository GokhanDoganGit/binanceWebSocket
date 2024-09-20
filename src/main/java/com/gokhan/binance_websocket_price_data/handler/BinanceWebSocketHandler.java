package com.gokhan.binance_websocket_price_data.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gokhan.binance_websocket_price_data.api.model.BinanceMessage;
import com.gokhan.binance_websocket_price_data.exception.JsonProcessingExceptionWrapper;
import com.gokhan.binance_websocket_price_data.exception.NotFoundException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
public class BinanceWebSocketHandler extends TextWebSocketHandler {

  private final ObjectMapper objectMapper = new ObjectMapper();

  private final ConcurrentHashMap<String, String> binanceData = new ConcurrentHashMap<>();

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) {
    try {
      BinanceMessage binanceMessage = objectMapper.readValue(message.getPayload(), BinanceMessage.class);
      binanceData.put(binanceMessage.s(), binanceMessage.p());
      log.info("For symbol {} price is: {}", binanceMessage.s(), binanceMessage.p());
    } catch (JsonProcessingException e) {
      log.error(e.getMessage());
      throw new JsonProcessingExceptionWrapper(e.getMessage(), e);
    }
  }

  public String getPrice(String symbol) {
    return Optional.ofNullable(binanceData.get(symbol))
        .orElseThrow(() -> new NotFoundException("Symbol not found: " + symbol));
  }
}
