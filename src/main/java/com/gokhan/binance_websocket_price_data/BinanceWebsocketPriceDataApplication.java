package com.gokhan.binance_websocket_price_data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BinanceWebsocketPriceDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(BinanceWebsocketPriceDataApplication.class, args);
	}

}
