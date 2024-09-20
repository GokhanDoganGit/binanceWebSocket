package com.gokhan.binance_websocket_price_data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
public class WebSocketConfig {

  @Bean
  public StandardWebSocketClient standardWebSocketClient() {
    return new StandardWebSocketClient();
  }

}
