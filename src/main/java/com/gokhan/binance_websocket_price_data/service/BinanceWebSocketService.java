package com.gokhan.binance_websocket_price_data.service;

import com.gokhan.binance_websocket_price_data.handler.BinanceWebSocketHandler;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Service
@RequiredArgsConstructor
public class BinanceWebSocketService {

  private final BinanceWebSocketHandler handler;
  private final StandardWebSocketClient client;

  @Setter
  @Value("${symbols}")
  private List<String> symbols;

  @PostConstruct
  public void connect() {
    String joinedSymbols = symbols.stream()
        .map(symbol -> symbol + "@trade")
        .collect(Collectors.joining("/"));

    client.execute(handler, "wss://stream.binance.com:9443/ws/" + joinedSymbols);
  }

}
