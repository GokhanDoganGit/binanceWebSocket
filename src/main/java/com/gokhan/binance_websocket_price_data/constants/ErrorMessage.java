package com.gokhan.binance_websocket_price_data.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessage {

  public static final String BAD_REQUEST = "Validation request problems.";
  public static final String INTERNAL_SERVER = "Internal server error occurred.";
  public static final String SERVICE_UNAVAILABLE = "Service is unavailable.";
  public static final String NOT_FOUND = "Symbol not found";
}
