package com.gokhan.binance_websocket_price_data.api;

import static com.gokhan.binance_websocket_price_data.constants.ApiConstraints.SYMBOL;
import static com.gokhan.binance_websocket_price_data.constants.ErrorMessage.INTERNAL_SERVER;
import static com.gokhan.binance_websocket_price_data.constants.ErrorMessage.NOT_FOUND;
import static com.gokhan.binance_websocket_price_data.constants.ErrorMessage.SERVICE_UNAVAILABLE;

import com.gokhan.binance_websocket_price_data.api.model.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface BinanceApi {

  @Operation(summary = "Retrieves price data from binance websocket by symbol.")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Price data retrieved.",
              content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = String.class))),
          @ApiResponse(responseCode = "404", description = "Symbol is not found.",
              content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class),
                  examples = {@ExampleObject(value = NOT_FOUND)})),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
              content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class),
                  examples = {@ExampleObject(value = INTERNAL_SERVER)})),
          @ApiResponse(responseCode = "503", description = "Service Unavailable",
              content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ErrorResponse.class),
                  examples = {@ExampleObject(value = SERVICE_UNAVAILABLE)}))
      })
  @Parameter(name = SYMBOL, description = "Binance Symbol", in = ParameterIn.PATH, example = "BTCUSDT")
  @GetMapping(value = "/api/prices/{symbol}/", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<String> retrievePriceData (
      final @PathVariable(value = SYMBOL) String symbol);
}
