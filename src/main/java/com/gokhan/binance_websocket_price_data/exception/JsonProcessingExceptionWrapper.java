package com.gokhan.binance_websocket_price_data.exception;

public class JsonProcessingExceptionWrapper extends RuntimeException {

  public JsonProcessingExceptionWrapper(final String message, final Exception exception) {
    super(message, exception);
  }
}
