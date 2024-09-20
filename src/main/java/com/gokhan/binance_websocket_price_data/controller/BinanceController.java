package com.gokhan.binance_websocket_price_data.controller;

import com.gokhan.binance_websocket_price_data.api.BinanceApi;
import com.gokhan.binance_websocket_price_data.handler.BinanceWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BinanceController implements BinanceApi {

  private final BinanceWebSocketHandler handler;

  @Override
  public ResponseEntity<String> retrievePriceData(String symbol) {
    return ResponseEntity.ok(handler.getPrice(symbol));
  }
}
