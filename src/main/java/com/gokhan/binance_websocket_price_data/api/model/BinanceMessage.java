package com.gokhan.binance_websocket_price_data.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BinanceMessage(
    String s,
    String p
) {
}
